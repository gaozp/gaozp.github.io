---
layout: post
title: 高通应用启动优化IO Prefetcher源码解析
---

### 前言：

I/O Prefetcher是高通本身提供的一套优化方案，可以用在Android手机App冷启动的时候。

#### 1.IO Prefetcher的初始化

首先模块的代码在 vendor/qcom/proprietary/android-perf/io-p/文件夹中，首先从入口main函数说起：io-p-d.c

```c
37int main(int argc, char *argv[])
38{
39    iop_server_init();
40
41    iop_server_exit();
42    return 0;
43}
```

在main函数中，打开了iopserver，但是为什么立刻就退出了呢？带着这个疑问，我们看一下iop_server_init的具体内容：io-p.c

```c
315int iop_server_init()
316{
//获取到当前的芯片id，只有如下的芯片才能打开iop服务，否则就直接退出了。
322    soc_id = get_soc_id();
323    switch(soc_id)
324    {
325         case SOCID_8994:
326         case SOCID_8092:
327         case SOCID_8992:
328         case SOCID_8996:
329         case SOCID_8996PRO:
330         case SOCID_8096PRO:
331         case SOCID_8096:
332         case SOCID_8998:
333         case SOCID_8939:
334             break;
335         default:
336             QLOGI("Fail to init IOP Server");
337             return 0;
338    }
//创建对应的数据库
340    create_database();
341//获取到对应的socket
342    comsoc = android_get_control_socket("iop");
343    if (comsoc < 0) {
344        stage = 1;
345        goto error;
346    }
347//listen对应的socket
348    rc = listen(comsoc, SOMAXCONN);
349    if (rc != 0) {
350        stage = 2;
351        goto error;
352    }
353//创建server线程
354    rc = pthread_create(&iop_server_thread, NULL, iop_server, NULL);
...
368}
```

这块其实也还是没有解决我们的为什么init了之后就直接exit的疑问呀，不要急，接着看一下iop_server

```c
173static void *iop_server(void *data)
174{
// 原来是在这个地方开启了一个无限循环，这样就可以一直监听而不用退出了
182    /* Main loop */
183    for (;;) {
184        len = sizeof(struct sockaddr_un);
185
186        clock_gettime(CLOCK_MONOTONIC, &m_accept);
187        time_delta = (BILLION * (m_accept.tv_sec - connected.tv_sec)) +
188                                (m_accept.tv_nsec - connected.tv_nsec);
189        QLOGI("time taken from connected to accept: %llu nanoseconds",
190                                (long long unsigned int)time_delta);
191
192        conn_socket = accept(comsoc, (struct sockaddr *) &addr, &len);
193        if (conn_socket == -1) {
194            QLOGE("PERFLOCK iop server %s: accept failed, errno=%d (%s)"
195                                    , __func__, errno, strerror(errno));
196            goto close_conn;
197        }
198
199        clock_gettime(CLOCK_MONOTONIC, &connected);
200
201        rc = recv(conn_socket, &msg, sizeof(iop_msg_t), 0);
202        if (rc < 0) {
203            QLOGE("PERFLOCK iop server: failed to receive message, errno=%d (%s)"
204                                    , errno, strerror(errno));
205            goto close_conn;
206        }
207
208        clock_gettime(CLOCK_MONOTONIC, &recvd);
209
210        /* Descramble client id */
211        msg.client_pid ^= msg.msg;
212        msg.client_tid ^= msg.msg;
213
214        QLOGV("Received len=%d, m=%u, v=%u, c=%u, s=%u, m=%u (0x%08x) d=%u",
215              rc, msg.magic, msg.version, msg.client_pid, msg.seq, msg.msg, msg.msg, msg.data);
216
217        if (rc != sizeof(iop_msg_t)) {
218            QLOGE("Bad size");
219        }
220        else if (msg.magic != IOP_MAGIC) {
221            QLOGE("Bad magic");
222            close(conn_socket);
223            continue;
224        }
225        else if (msg.version != IOP_VERSION) {
226            QLOGE("Version mismatch");
227            close(conn_socket);
228            continue;
229        }
230
231        //cmd = CMD_DECODE(msg);
232        cmd = msg.cmd;
233
234        switch (cmd) {
235            case IOP_CMD_PERFLOCK_IOPREFETCH_START:
236            {
237                static bool is_in_recent_list = false;
238                char property[PROPERTY_VALUE_MAX];
239                int enable_prefetcher = 0;
240
241                property_get("enable_prefetch", property, "1");
242                enable_prefetcher = atoi(property);
243
244                if(!enable_prefetcher)
245                {
246                    QLOGE("io prefetch is disabled");
247                    break;
248                }
249                // if PID < 0 consider it as playback operation
250                if(msg.pid < 0)
251                {
252                    int ittr = 0;
253                    is_in_recent_list = false;
254                    //Check app is in recent list
255                    for(ittr = 0; ittr < IOP_NO_RECENT_APP; ittr++)
256                    {
257                        if(0 == strcmp(msg.pkg_name,recent_list[ittr]))
258                        {
259                            is_in_recent_list = true;
260                            QLOGE("is_in_recent_list is TRUE");
261                            break;
262                        }
263                    }
264                    // IF Application is in recent list, return
265                    if(true == is_in_recent_list)
266                    {
267                        QLOGE("io prefetch is deactivate");
268                        break;
269                    }
270
271                    if(recent_list_cnt == IOP_NO_RECENT_APP)
272                        recent_list_cnt = 0;
273
274                    //Copy the package name to recent list
275                    strlcpy(recent_list[recent_list_cnt],msg.pkg_name,PKG_LEN);
276                    recent_list_cnt++;
277
278                    stop_capture();
279                    stop_playback();
280                    start_playback(msg.pkg_name);
281                }
282                // if PID > 0 then consider as capture operation
283                if(msg.pid > 0)
284                {
285                    if(true == is_in_recent_list)
286                    {
287                        QLOGE("io prefetch Capture is deactivated ");
288                        break;
289                    }
290                    stop_capture();
291                    start_capture(msg.pid,msg.pkg_name);
292                }
293
294                break;
295            }
296
297            case IOP_CMD_PERFLOCK_IOPREFETCH_STOP:
298            {
299                stop_capture();
300                break;
301            }
302
303            default:
304                QLOGE("Unknown command %d", cmd);
305        }
306close_conn:
307    close(conn_socket);
308    }
309
310    QLOGI("IOP server thread exit due to rc=%d", rc);
311    return NULL;
312}
```


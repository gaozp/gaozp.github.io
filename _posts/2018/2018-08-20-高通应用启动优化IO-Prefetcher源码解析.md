---
layout: post
title: 高通应用启动优化IO Prefetcher源码解析
---

### 前言：

I/O Prefetcher是高通本身提供的一套优化方案，可以用在Android手机App冷启动的时候。

但是因为是和soc相关的，所以android 原生代码中是没有办法查看到的，也就是andoridxref中是看不到的，只能通过高通的代码看。

同时我们也可以看到在n和o中的实现已经不同了，n中还是通过iop-p-d来启动的，但是o中已经是通过hidl来启动了。hidl其实就是project treble提出来的，具体的内容补充我们现在讨论的内容，所以也就不去细讲了。那现在就是基于o版本的源码。

#### 1.IO Prefetcher的初始化

首先模块的代码在 vendor/qcom/proprietary/android-perf/iop-hal/文件夹中，首先从入口main函数说起：io-p-d.c

```cpp
107IIop* HIDL_FETCH_IIop(const char* /* name */) {
108    ALOGE("IOP-HAL: inside HIDL_FETCH_IIop");
109    Iop *iopObj = new Iop();
110    ALOGE("IOP-HAL: boot Address of iop object");
111    if (iopObj) {
    //首先load对应的lib库，获取到对应的函数地址
112        iopObj->LoadIopLib();
113        ALOGE("IOP-HAL: loading library is done");
    //再进行init操作
114        if (iopObj->mHandle.iop_server_init != NULL ) {
115            (*(iopObj->mHandle.iop_server_init))();
116        }
117    }
118    return iopObj;
119}
```

获取函数地址的来看一下：

```cpp
62void Iop::LoadIopLib() {
...
75         *(void **) (&mHandle.iop_server_init) = dlsym(mHandle.dlhandle, "iop_server_init");
76         if ((rc = dlerror()) != NULL) {
77             ALOGE("IOP-HAL %s Failed to get iop_server_init\n", rc);
78             dlclose(mHandle.dlhandle);
79             mHandle.dlhandle = NULL;
80             return;
81         }
...
99         mHandle.is_opened = true;
100    }
102    return;
103}
```

所以可以看到这就到了iopinit服务了。我们看一下iop_server_init的具体内容：io-p.cpp

```cpp
247//interface implementation
248int iop_server_init() {
//得到当前soc的id只有几个芯片支持iop
254    soc_id = get_soc_id();
255    switch(soc_id)
256    {
257         case SOCID_8994:
258         case SOCID_8092:
259         case SOCID_8992:
260         case SOCID_8996:
261         case SOCID_8996PRO:
262         case SOCID_8096PRO:
263         case SOCID_8096:
264         case SOCID_8998:
265         case SOCID_8939:
266         case SOCID_SDM845:
267             break;
268         default:
269             QLOGI("Fail to init IOP Server");
270             return 0;
271    }
272
273    IOPevqueue.GetDataPool().SetCBs(Alloccb, Dealloccb);
    //进入无限循环来等待消息
274    rc = pthread_create(&iop_server_thread, NULL, iop_server, NULL);
275    if (rc != 0) {
276        stage = 3;
277        goto error;
278    }
279    return 1;
280
281error:
282    QLOGE("Unable to create control service (stage=%d, rc=%d)", stage, rc);
283    return 0;
284}
```

创建线程进入无限循环处理消息，除非产生了错误，才会退出这个服务。接着看一下iop_server

```c++
122static void *iop_server(void *data)
123{
129    /* Main loop */
130    for (;;) {
131       //wait for perflock commands
132        EventData *evData = IOPevqueue.Wait();
//获取到当前的数据库是否建立了，没有建立的话就先创建
142        if(is_db_init == false)
143        {
144            if(create_database() == 0)
145            {
146                //Success
147                is_db_init = true;
148            }
149        }
150
151        cmd = evData->mEvType;
152        msg = (iop_msg_t *)evData->mEvData;
//对产生的数据进行处理
154        switch (cmd) {
155            case IOP_CMD_PERFLOCK_IOPREFETCH_START:
156            {
157                static bool is_in_recent_list = false;
158                char property[PROPERTY_VALUE_MAX];
159                int enable_prefetcher = 0;
160
161                property_get("enable_prefetch", property, "1");
162                enable_prefetcher = atoi(property);
163
164                if(!enable_prefetcher)
165                {
166                    QLOGE("io prefetch is disabled");
167                    break;
168                }
169                // if PID < 0 consider it as playback operation
170                if(msg->pid < 0)
171                {
172                    int ittr = 0;
173                    is_in_recent_list = false;
174                    //Check app is in recent list
175                    for(ittr = 0; ittr < IOP_NO_RECENT_APP; ittr++)
176                    {
177                        if(0 == strcmp(msg->pkg_name,recent_list[ittr]))
178                        {
179                            is_in_recent_list = true;
180                            QLOGE("is_in_recent_list is TRUE");
181                            break;
182                        }
183                    }
184                    // IF Application is in recent list, return
185                    if(true == is_in_recent_list)
186                    {
187                        QLOGE("io prefetch is deactivate");
188                        break;
189                    }
190
191                    if(recent_list_cnt == IOP_NO_RECENT_APP)
192                        recent_list_cnt = 0;
193
194                    //Copy the package name to recent list
195                    strlcpy(recent_list[recent_list_cnt],msg->pkg_name,PKG_LEN);
196                    recent_list_cnt++;
197
198                    stop_capture();
199                    stop_playback();
200                    start_playback(msg->pkg_name);
201                }
202                // if PID > 0 then consider as capture operation
203                if(msg->pid > 0)
204                {
205                    if(true == is_in_recent_list)
206                    {
207                        QLOGE("io prefetch Capture is deactivated ");
208                        break;
209                    }
210                    stop_capture();
211                    start_capture(msg->pid,msg->pkg_name,msg->code_path);
212                }
213
214                break;
215            }
216
217            case IOP_CMD_PERFLOCK_IOPREFETCH_STOP:
218            {
219                stop_capture();
220                break;
221            }
222
223            default:
224                QLOGE("Unknown command %d", cmd);
225        }
226        IOPevqueue.GetDataPool().Return(evData);
227    }
228
229    QLOGI("IOP server thread exit due to rc=%d", rc);
230    return NULL;
231}
```




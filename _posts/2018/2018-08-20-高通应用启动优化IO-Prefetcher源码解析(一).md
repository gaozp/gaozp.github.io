---
layout: post
title: 高通应用启动优化IO Prefetcher源码解析
categories: [tech]
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
//处理的环节我们后面讨论
231}
```

所以可以看出先是创建了一个database，源码在dblayer.cpp

```cpp
15/******************************************************************************
16     pkg_file_tbl               pkg_tbl
17  |-----------------|      |-----------------|
18  |  pkg_name       |      |  pkg_name       |
19  |  file_name      |      |-----------------|
20  |-----------------|      | pkg_use_time    |
21  |                 |      | num_of_launches |
22  | file_use_ctr    |      |-----------------|
23  | file_time_stamp |
24  | file_size       |
25  | mark_for_delete |
26  |-----------------|
27******************************************************************************/
```

所以就直接看table的结构吧。看字段名就应该知道是什么意义了，现在来看具体是如何使用这个database的

```cpp
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
    //注意此处就是在playback
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
    //关键函数，开始进行capture抓取
211                    start_capture(msg->pid,msg->pkg_name,msg->code_path);
212                }
213
214                break;
215            }
216
217            case IOP_CMD_PERFLOCK_IOPREFETCH_STOP:
218            {
   //关闭函数，停止capture
219                stop_capture();
220                break;
221            }
222
223            default:
224                QLOGE("Unknown command %d", cmd);
225        }
```

所以我们一起来看一下start_capture函数：

```cpp
436int start_capture(pid_t pid, char *pkg_name, char *code_path) {
...
486    //TBD must remove comments
487    if(pthread_create(&capture_pthread_id, NULL, capture_thread, arg_bundle)) {
488        return -1;
489    }
490   QLOGI("start_capture EXIT");
491    return 0;
492}
```

恩，高通自己写了 to be done 一定要删除注释，然后还是留下了，哈哈！其实就是启动了一个capture_thread线程：

```cpp
375void * capture_thread(void * arg) {
    ...
390    //Copy the package to same string to remove ':'
391    pkg_len = strlen(arg_bundle->pkg_name);
392    i = 0;
393    while(i < pkg_len && arg_bundle->pkg_name[i] != ':')
394    {
395        i++;
396    }
397    arg_bundle->pkg_name[i] = '\0';
398    strlcpy(list_pkg_name,arg_bundle->pkg_name,PKG_NAME_LEN);
399
400    QLOGI("pkg_name  = %s",arg_bundle->pkg_name);
401
402    if(polling_enable)
403    {
404        while (duration_counter < halt_counter) {
405            if (halt_thread) goto cleanup;
406            QLOGI("Getting snapshot %d\n", arg_bundle->pid);
    //获取当前pid打开的文件描述符
407            get_snapshot(list_pkg_name,arg_bundle->pid);
408            duration_counter++;
409            usleep(read_fd_interval_ms * 1000);
410        }
411    }
    //获取到代码位置
412    get_priv_code_files(arg_bundle->pkg_name);
    //获取文件位置
413    get_priv_files(arg_bundle);
414    QLOGI("pkg_name = %s total_files = %d ",arg_bundle->pkg_name,total_files);
415
416    // Insert package into the table
417    strlcpy(pkg_info.pkg_name,arg_bundle->pkg_name,PKG_NAME_LEN);
418    time(&pkg_info.last_time_launched);
    //更新在database中
419    update_pkg_details(pkg_info);
420    update_file_details(arg_bundle->pkg_name, file_list, total_files);
421    delete_mark_files();
422cleanup:
423    for(i = 0; i < total_files; i++) {
424        QLOGE("%d. Final entry : %s %s %d %d\n",i,arg_bundle->pkg_name,file_list[i]->file_name
425                                , file_list[i]->file_time_stamp, file_list[i]->filesize);
426        free(file_list[i]);
427    }
428    ATRACE_END();
429
430    free(arg_bundle->pkg_name);
431    free(arg_bundle);
432    QLOGI("Exit capture_thread");
433    return NULL;
434}
```

首先来看get_snapshot函数，就是获取当前pid打开的所有文件描述符

```cpp
148int get_snapshot(char *pkg_name, pid_t pid)
149{

164
165    snprintf(dirname,PATH_MAX,"/proc/%d/fd", pid);
166    QLOGI("Dirname = %s MAX_PATH = %d\n", dirname, PATH_MAX);
167    dp = opendir(dirname);
168    QLOGI("get_snapshot Enter dp = %p",dp);
169    QLOGI("dp = %p",dp);
170    if (dp != NULL) {
171        int i = 0;
172        while ((ep = readdir(dp)))
173        {
...
207
208            if (S_ISREG(sb.st_mode)) {
    //将linke的文件存放到database中
209                find_insert_into_list(pkg_name, linkname, sb.st_size, file_capture_time);
210            }
211        }
212        QLOGI("get_snapshot Exit dp = %p",dp);
213        closedir(dp);
214    }
215    ATRACE_END();
216    return 0;
217}
```

这里可以看到

```sh
polaris:/ # ps -A | grep mobileq
u0_a153       9824   643 1821656 172712 SyS_epoll_wait      0 S com.tencent.mobileqqi
u0_a153       9848   643 1796008 123640 SyS_epoll_wait      0 S com.tencent.mobileqqi:MSF
polaris:/ # 
```

然后打开proc中的文件描述符

```sh
polaris:/proc/9824/fd # ls -al
total 0
dr-x------ 2 u0_a153 u0_a153  0 2018-08-21 14:24 .
dr-xr-xr-x 9 u0_a153 u0_a153  0 2018-08-21 14:24 ..
lrwx------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 0 -> /dev/null
lrwx------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 1 -> /dev/null
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 10 -> /system/framework/qtiNetworkLib.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 11 -> /system/framework/com.nxp.nfc.nq.jar
lrwx------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 12 -> anon_inode:[eventfd]
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 13 -> /system/framework/qcom.fmradio.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 14 -> /system/framework/com.qualcomm.qti.camera.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 15 -> /system/framework/QPerformance.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 16 -> /system/framework/UxPerformance.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 17 -> /system/framework/core-oj.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 18 -> /system/framework/core-libart.jar
lr-x------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 19 -> /system/framework/conscrypt.jar
lrwx------ 1 u0_a153 u0_a153 64 2018-08-21 14:25 2 -> /dev/null

```

然后就是插入到数据库中了：

```cpp
76static int find_insert_into_list(char *pkg_name,char *filename, long long size, long file_capture_time) {
...
99
100    if (index == total_files) {
101        file_list[index] = (file_details *) malloc (sizeof(file_details));
102        if(NULL == file_list[index])
103        {
104            QLOGE("Fail to allocate memory");
105            return -1;
106        }
107        strlcpy(file_list[index]->file_name, filename,FILE_NAME_LEN);
108        file_list[index]->file_time_stamp = file_capture_time;
109        file_list[index]->filesize = size;
110        total_files++;
111    }
112    return 0;
113}
```

先是存在了内存中了。在获取到snapshot后，就是get_priv_code_files和get_priv_files了

这两者都是打开不同的文件然后缓存起来

```cpp
310void get_priv_files(capture_thread_arg * arg_bundle)
311{
312    char f_name[FILE_NAME_LEN];
313    DIR *dp;
314    char trace_buf[1024];
315    snprintf(trace_buf, sizeof(trace_buf), "Get_prive_file");
316    ATRACE_BEGIN(trace_buf);
317
318    snprintf(f_name,FILE_NAME_LEN,"/data/user/0/%s",arg_bundle->pkg_name);
319    QLOGI("Dir = %s",f_name);
320    dp = opendir(f_name);
321    QLOGI("First dp = %p",dp);
322    if (dp != NULL) {
323        capture_pkg_file(arg_bundle->pkg_name, dp,f_name,2);
324        closedir(dp);
325    }
326    else
327    {
328        QLOGI("dp is NULL");
329    }
330
331
332    snprintf(f_name,FILE_NAME_LEN,"/data/data/%s",arg_bundle->pkg_name);
333    QLOGI("Dir = %s",f_name);
334    dp = opendir(f_name);
335    QLOGI("First dp = %p",dp);
336
337    if (dp != NULL) {
338        capture_pkg_file(arg_bundle->pkg_name, dp,f_name, 4);
339        closedir(dp);
340    }
341    else
342    {
343        QLOGI("dp is NULL");
344    }
345    ATRACE_END();
346}
347
348static void get_priv_code_files(char *pkg_name) {
349    char f_name[FILE_NAME_LEN];
350    DIR *dp;
351
352    //oat first
353    snprintf(f_name,FILE_NAME_LEN,"%s/oat",list_code_path);
354    QLOGI("Dir = %s",f_name);
355    dp = opendir(f_name);
356    QLOGI("First dp = %p",dp);
357    if (dp != NULL) {
358        capture_pkg_file(pkg_name, dp, f_name, -1);
359        closedir(dp);
360    } else {
361        QLOGI("dp is NULL");
362    }
363
364    //then scan apk file
365    dp = opendir(list_code_path);
366    QLOGI("First dp = %p",dp);
367    if (dp != NULL) {
368        capture_pkg_file(pkg_name, dp, list_code_path, 1);
369        closedir(dp);
370    } else {
371        QLOGI("dp is NULL");
372    }
373}
```

关键函数其实就是capture_pkg_file了,关键的方法也是find_insert_into_list方法，就是插入到内存中，这里就不赘述了。

后面就是update_pkg_detail和update_file_detail了，这些都是数据库操作

```cpp
522int update_pkg_details(pkg_details pkg_info)
523{
530    if(NULL == db_conn)
531        open_db();
532//可以看到，就是通过sqlite数据库进行更新的
533    if(is_file_present)
534    {
535        //Update
536        snprintf(query_str,sizeof(query_str),IO_PREFETCHER_QUERY_UPDATE_PKG_DETAILS
537                                            ,pkg_info.last_time_launched
538                                            ,pkg_info.pkg_name);
539        QLOGI("\nQuery = %s \n",query_str);
540        iop_query_exec(query_str);
541    }
542    else
543    {
544        //Insert
545        snprintf(query_str,sizeof(query_str),IO_PREFETCHER_QUERY_INSERT_PKG
546                                            ,pkg_info.pkg_name
547                                            ,pkg_info.last_time_launched
548                                            );
549
550        QLOGI("\nQuery = %s \n",query_str);
551        iop_query_exec(query_str);
552    }
553    return 0;
554}
```

至此，可以看到，在打开了iop之后，在打开应用的时候，会查看该pid打开的文件描述符列表，将一些需要打开的文件先缓存到数据库中。那么缓存到数据库中，该怎么使用呢。
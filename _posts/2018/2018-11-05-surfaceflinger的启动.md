surfaceflinger的启动：

在p之后的版本中，surfaceflinger的启动就已经不在init.rc中，而是在surfaceflinger.rc中的

```
service surfaceflinger /system/bin/surfaceflinger
    class core animation
    user system
    group graphics drmrpc readproc
    onrestart restart zygote
    writepid /dev/stune/foreground/tasks
    socket pdx/system/vr/display/client     stream 0666 system graphics u:object_r:pdx_display_client_endpoint_socket:s0
    socket pdx/system/vr/display/manager    stream 0666 system graphics u:object_r:pdx_display_manager_endpoint_socket:s0
    socket pdx/system/vr/display/vsync      stream 0666 system graphics u:object_r:pdx_display_vsync_endpoint_socket:s0
```

查看main_surfaceflinger.cpp可以看到

```cpp
72int main(int, char**) {
73    signal(SIGPIPE, SIG_IGN);
74
75    hardware::configureRpcThreadpool(1 /* maxThreads */,
76            false /* callerWillJoin */);
77    //启动了allocator服务-很重要的服务
78    startGraphicsAllocatorService();
79
80    // When SF is launched in its own process, limit the number of
81    // binder threads to 4.
82    ProcessState::self()->setThreadPoolMaxThreadCount(4);
83
84    // start the thread pool
85    sp<ProcessState> ps(ProcessState::self());
86    ps->startThreadPool();
87
88    // instantiate surfaceflinger 
      //这里和6.0的区别是这里解耦出了displayutil来整合，里面的代码其实也是new surfaceflinger()
89    sp<SurfaceFlinger> flinger = DisplayUtils::getInstance()->getSFInstance();
90
91    setpriority(PRIO_PROCESS, 0, PRIORITY_URGENT_DISPLAY);
92
93    set_sched_policy(0, SP_FOREGROUND);
94
95    // Put most SurfaceFlinger threads in the system-background cpuset
96    // Keeps us from unnecessarily using big cores
97    // Do this after the binder thread pool init
      //这里还讲surfaceflinger通过cpuset的方式绑在了小核上，这样就不会去竞争cpu资源了
98    if (cpusets_enabled()) set_cpuset_policy(0, SP_SYSTEM);
99
100    // initialize before clients can connect
101    flinger->init();
102
103    // publish surface flinger
104    sp<IServiceManager> sm(defaultServiceManager());
105    sm->addService(String16(SurfaceFlinger::getServiceName()), flinger, false,
106                   IServiceManager::DUMP_FLAG_PRIORITY_CRITICAL);
107
108    // publish GpuService
109    sp<GpuService> gpuservice = new GpuService();
110    sm->addService(String16(GpuService::SERVICE_NAME), gpuservice, false);
111
112    startDisplayService(); // dependency on SF getting registered above
113
114    struct sched_param param = {0};
115    param.sched_priority = 2;
116    if (sched_setscheduler(0, SCHED_FIFO, &param) != 0) {
117        ALOGE("Couldn't set SCHED_FIFO");
118    }
119
120    // run surface flinger in this thread
121    flinger->run();
122
123    return 0;
124}
125
```

创建了surfaceflinger，并且调用了init和run方法。

因为surfaceflinger继承了xxx，并且是强引用，所以先走了onfirstref

```

```

进行了messagequeue的初始化。创建了handler和looper

在run方法中，调用了messagequeue的waitmessage，当messagequeue获取到消息的时候，调用handler，其实也就是返回了surfaceflinger中的onmessagereceived

```

```

在onmessagereceived中

```

```







requestnextvsync 

surfaceflinger通过singnaltransaction和signallayerupdate会调用messagequeue的invalidate方法，会调用eventthread的requestnextvsync来和hw通信获取vsync信号。
---
layout: post
title: Android lowmemorykiller源码分析（一）
categories: [tech]
---
#### 前言
lowmemorykiller一直是kernel中的模块，但是在4.12中的时候，lowmemorykiller的驱动从内核中移除，取而代之的是用户控件的lmkd，那么我们就先来说一下内核空间的lmk。所以这篇文章就先从开始的lmkd开始说起。
#### lmkd的启动：
在lmkd.rc中可以看到，系统在启动后会启动lmkd的服务，同时是一个critical的，如果进程死亡，那么会引起手机重启来重启服务，同时会创建lmkd的seqpacket的socket监听。
```
//system/core/lmkd/lmkd.rc
service lmkd /system/bin/lmkd
    class core
    group root readproc
    critical
    socket lmkd seqpacket 0660 system system
    writepid /dev/cpuset/system-background/tasks
```
##### main方法
```c
//system/core/lmkd/lmkd.c
int main(int argc __unused, char **argv __unused) {
    struct sched_param param = {
            .sched_priority = 1,
    };

    ....// 一些默认的参数配置

    // MCL_ONFAULT pins pages as they fault instead of loading
    // everything immediately all at once. (Which would be bad,
    // because as of this writing, we have a lot of mapped pages we
    // never use.) Old kernels will see MCL_ONFAULT and fail with
    // EINVAL; we ignore this failure.
    //
    // N.B. read the man page for mlockall. MCL_CURRENT | MCL_ONFAULT
    // pins ⊆ MCL_CURRENT, converging to just MCL_CURRENT as we fault
    // in pages.
    // 将使用的内存都锁在物理内存上
    if (mlockall(MCL_CURRENT | MCL_FUTURE | MCL_ONFAULT) && errno != EINVAL)
        ALOGW("mlockall failed: errno=%d", errno);
    // 设置调度的优先级
    sched_setscheduler(0, SCHED_FIFO, &param);
    if (!init())// 进行初始化
        mainloop(); // 进入循环等待事件

#ifdef LMKD_LOG_STATS
    statslog_destroy(&log_ctx);
#endif

    ALOGI("exiting");
    return 0;
}
```
##### init方法：
init方法中，注册了lmkdsocket的监听，以及对应的handler来处理socket事件
```c
// system/core/lmkd/lmkd.c
static int init(void) {
    // 获取到lmkd的socket
    ctrl_sock.sock = android_get_control_socket("lmkd");
    if (ctrl_sock.sock < 0) {
        ALOGE("get lmkd control socket failed");
        return -1;
    }
    // 监听socket
    ret = listen(ctrl_sock.sock, MAX_DATA_CONN);
    if (ret < 0) {
        ALOGE("lmkd control socket listen failed (errno=%d)", errno);
        return -1;
    }
    // epoll的event事件
    epev.events = EPOLLIN;
    ctrl_sock.handler_info.handler = ctrl_connect_handler;
    // 注册epoll事件的监听
    epev.data.ptr = (void *)&(ctrl_sock.handler_info);
    if (epoll_ctl(epollfd, EPOLL_CTL_ADD, ctrl_sock.sock, &epev) == -1) {
        ALOGE("epoll_ctl for lmkd control socket failed (errno=%d)", errno);
        return -1;
    }
    maxevents++;

    has_inkernel_module = !access(INKERNEL_MINFREE_PATH, W_OK);
    use_inkernel_interface = has_inkernel_module;
    // 查看是否是采用kernel的 lmk
    if (use_inkernel_interface) {
        ALOGI("Using in-kernel low memory killer interface");
    } else {
        // 初始化用户空间的lmkd的相关参数
        if (!init_mp_common(VMPRESS_LEVEL_LOW) ||
            !init_mp_common(VMPRESS_LEVEL_MEDIUM) ||
            !init_mp_common(VMPRESS_LEVEL_CRITICAL)) {
            ALOGE("Kernel does not support memory pressure events or in-kernel low memory killer");
            return -1;
        }
    }

    for (i = 0; i <= ADJTOSLOT(OOM_SCORE_ADJ_MAX); i++) {
        procadjslot_list[i].next = &procadjslot_list[i];
        procadjslot_list[i].prev = &procadjslot_list[i];
    }

    return 0;
}
```
##### init_mp_common:
初始化memory pressure事件的监听
```c
static bool init_mp_common(enum vmpressure_level level) {

    mpfd = open(MEMCG_SYSFS_PATH "memory.pressure_level", O_RDONLY | O_CLOEXEC);
    if (mpfd < 0) {
        ALOGI("No kernel memory.pressure_level support (errno=%d)", errno);
        goto err_open_mpfd;
    }

    evctlfd = open(MEMCG_SYSFS_PATH "cgroup.event_control", O_WRONLY | O_CLOEXEC);
    if (evctlfd < 0) {
        ALOGI("No kernel memory cgroup event control (errno=%d)", errno);
        goto err_open_evctlfd;
    }

    evfd = eventfd(0, EFD_NONBLOCK | EFD_CLOEXEC);
    if (evfd < 0) {
        ALOGE("eventfd failed for level %s; errno=%d", levelstr, errno);
        goto err_eventfd;
    }

    ret = snprintf(buf, sizeof(buf), "%d %d %s", evfd, mpfd, levelstr);
    if (ret >= (ssize_t)sizeof(buf)) {
        ALOGE("cgroup.event_control line overflow for level %s", levelstr);
        goto err;
    }

    ret = TEMP_FAILURE_RETRY(write(evctlfd, buf, strlen(buf) + 1));
    if (ret == -1) {
        ALOGE("cgroup.event_control write failed for level %s; errno=%d",
              levelstr, errno);
        goto err;
    }

    epev.events = EPOLLIN;
    /* use data to store event level */
    vmpressure_hinfo[level_idx].data = level_idx;
    vmpressure_hinfo[level_idx].handler = mp_event_common;
    epev.data.ptr = (void *)&vmpressure_hinfo[level_idx];
    ret = epoll_ctl(epollfd, EPOLL_CTL_ADD, evfd, &epev);
    if (ret == -1) {
        ALOGE("epoll_ctl for level %s failed; errno=%d", levelstr, errno);
        goto err;
    }
    maxevents++;
    mpevfd[level] = evfd;
    close(evctlfd);
    return true;

err:
    close(evfd);
err_eventfd:
    close(evctlfd);
err_open_evctlfd:
    close(mpfd);
err_open_mpfd:
    return false;
}
```
##### mainloop:
初始化完成后，lmkd就会进入epoll wait来等待事件
```c
// system/core/lmkd/lmkd.c
static void mainloop(void) {
    struct event_handler_info* handler_info;
    struct epoll_event *evt;

    while (1) {
        struct epoll_event events[maxevents];
        int nevents;
        int i;
        // 进入wait来等待事件
        nevents = epoll_wait(epollfd, events, maxevents, -1);

        if (nevents == -1) {
            if (errno == EINTR)
                continue;
            ALOGE("epoll_wait failed (errno=%d)", errno);
            continue;
        }

        /*
         * First pass to see if any data socket connections were dropped.
         * Dropped connection should be handled before any other events
         * to deallocate data connection and correctly handle cases when
         * connection gets dropped and reestablished in the same epoll cycle.
         * In such cases it's essential to handle connection closures first.
         */
        for (i = 0, evt = &events[0]; i < nevents; ++i, evt++) {
            if ((evt->events & EPOLLHUP) && evt->data.ptr) {
                ALOGI("lmkd data connection dropped");
                handler_info = (struct event_handler_info*)evt->data.ptr;
                ctrl_data_close(handler_info->data);
            }
        }

        /* Second pass to handle all other events */
        for (i = 0, evt = &events[0]; i < nevents; ++i, evt++) {
            if (evt->events & EPOLLERR)
                ALOGD("EPOLLERR on event #%d", i);
            if (evt->events & EPOLLHUP) {
                /* This case was handled in the first pass */
                continue;
            }
            if (evt->data.ptr) {
                handler_info = (struct event_handler_info*)evt->data.ptr;
                handler_info->handler(handler_info->data, evt->events);
            }
        }
    }
}
```
#### 事件的处理：
在初始化的时候可以看到处理的handler是connecthandler，而对应的处理的handler是ctrl_data_handler,如果事件是epollin的话，就需要调用ctrl_command_handler来进行处理
```C
static void ctrl_connect_handler(int data __unused, uint32_t events __unused) {
    struct epoll_event epev;
    int free_dscock_idx = get_free_dsock();

    if (free_dscock_idx < 0) {
        /*
         * Number of data connections exceeded max supported. This should not
         * happen but if it does we drop all existing connections and accept
         * the new one. This prevents inactive connections from monopolizing
         * data socket and if we drop ActivityManager connection it will
         * immediately reconnect.
         */
        for (int i = 0; i < MAX_DATA_CONN; i++) {
            ctrl_data_close(i);
        }
        free_dscock_idx = 0;
    }

    data_sock[free_dscock_idx].sock = accept(ctrl_sock.sock, NULL, NULL);
    if (data_sock[free_dscock_idx].sock < 0) {
        ALOGE("lmkd control socket accept failed; errno=%d", errno);
        return;
    }

    ALOGI("lmkd data connection established");
    /* use data to store data connection idx */
    data_sock[free_dscock_idx].handler_info.data = free_dscock_idx;
    // 注意此处的handler
    data_sock[free_dscock_idx].handler_info.handler = ctrl_data_handler;
    epev.events = EPOLLIN;
    epev.data.ptr = (void *)&(data_sock[free_dscock_idx].handler_info);
    if (epoll_ctl(epollfd, EPOLL_CTL_ADD, data_sock[free_dscock_idx].sock, &epev) == -1) {
        ALOGE("epoll_ctl for data connection socket failed; errno=%d", errno);
        ctrl_data_close(free_dscock_idx);
        return;
    }
    maxevents++;
}
static void ctrl_data_handler(int data, uint32_t events) {
    if (events & EPOLLIN) {
        ctrl_command_handler(data);
    }
}
```
##### ctrl_data_handler
在客户端连接后，会有3种不同事件的处理方式，target，procprio和procremove对应的注释也就在后面了。
```c
/*
 * Supported LMKD commands
 */
enum lmk_cmd {
    LMK_TARGET = 0,  /* Associate minfree with oom_adj_score */
    LMK_PROCPRIO,    /* Register a process and set its oom_adj_score */
    LMK_PROCREMOVE,  /* Unregister a process */
};

static void ctrl_command_handler(int dsock_idx) {
    LMKD_CTRL_PACKET packet;
    int len;
    enum lmk_cmd cmd;
    int nargs;
    int targets;

    len = ctrl_data_read(dsock_idx, (char *)packet, CTRL_PACKET_MAX_SIZE);
    if (len <= 0)
        return;

    if (len < (int)sizeof(int)) {
        ALOGE("Wrong control socket read length len=%d", len);
        return;
    }

    cmd = lmkd_pack_get_cmd(packet);
    nargs = len / sizeof(int) - 1;
    if (nargs < 0)
        goto wronglen;

    switch(cmd) {
    case LMK_TARGET:
        targets = nargs / 2;
        if (nargs & 0x1 || targets > (int)ARRAY_SIZE(lowmem_adj))
            goto wronglen;
        cmd_target(targets, packet);
        break;
    case LMK_PROCPRIO:
        if (nargs != 3)
            goto wronglen;
        cmd_procprio(packet);
        break;
    case LMK_PROCREMOVE:
        if (nargs != 1)
            goto wronglen;
        cmd_procremove(packet);
        break;
    default:
        ALOGE("Received unknown command code %d", cmd);
        return;
    }

    return;
```
#### 总结：
系统在启动的时候会启动lmkd服务，同时会监听/dev/socked/lmkd的socket，进入无限循环状态，等待客户端给socket发送信息同时进行处理。

---
layout: post
title: servicemanager启动
---

servicemanager是由init启动的，看一下rc文件

#### 1.servicemanager.rc

```rc
1service servicemanager /system/bin/servicemanager
2    class core animation
3    user system
4    group system readproc
5    critical
6    onrestart restart healthd
7    onrestart restart zygote
8    onrestart restart audioserver
9    onrestart restart media
10    onrestart restart surfaceflinger
11    onrestart restart inputflinger
12    onrestart restart drm
13    onrestart restart cameraserver
14    onrestart restart keystore
15    onrestart restart gatekeeperd
16    writepid /dev/cpuset/system-background/tasks
17    shutdown critical
18
```

/system/bin/servicemanager文件是servicemanager.c编译出来的，看一下servicemanager.c文件

#### 2.service_manager.c

```c
374int main(int argc, char** argv)
375{
376    struct binder_state *bs;
377    union selinux_callback cb;
378    char *driver;
379
380    if (argc > 1) {
381        driver = argv[1];
382    } else {
383        driver = "/dev/binder"; //没有参数，在dev目录下的binder设备
384    }
385
386    bs = binder_open(driver, 128*1024);//调用binderopen打开一个128k的空间
387    if (!bs) {
388#ifdef VENDORSERVICEMANAGER
389        ALOGW("failed to open binder driver %s\n", driver);
390        while (true) {
391            sleep(UINT_MAX);
392        }
393#else
394        ALOGE("failed to open binder driver %s\n", driver);
395#endif
396        return -1;
397    }
398
399    if (binder_become_context_manager(bs)) { //调用函数，将自己设置为servicemanager
400        ALOGE("cannot become context manager (%s)\n", strerror(errno));
401        return -1;
402    }
403//设置selinux相关
404    cb.func_audit = audit_callback;
405    selinux_set_callback(SELINUX_CB_AUDIT, cb);
406    cb.func_log = selinux_log_callback;
407    selinux_set_callback(SELINUX_CB_LOG, cb);
408
409#ifdef VENDORSERVICEMANAGER
410    sehandle = selinux_android_vendor_service_context_handle();
411#else
412    sehandle = selinux_android_service_context_handle();
413#endif
414    selinux_status_open(true);
415
416    if (sehandle == NULL) {
417        ALOGE("SELinux: Failed to acquire sehandle. Aborting.\n");
418        abort();
419    }
420
421    if (getcon(&service_manager_context) != 0) {
422        ALOGE("SELinux: Failed to acquire service_manager context. Aborting.\n");
423        abort();
424    }
425
426//开启loop，等待事件
427    binder_loop(bs, svcmgr_handler);
428
429    return 0;
430}
```

servicemanager自己写了binder.c来和驱动进行交互，就来看一下binder_open操作

####3.binder.c -> binder_open

```c
97struct binder_state *binder_open(const char* driver, size_t mapsize)
98{
99    struct binder_state *bs;
100    struct binder_version vers;
101
102    bs = malloc(sizeof(*bs));//申请内存
103    if (!bs) {
104        errno = ENOMEM;
105        return NULL;
106    }
107
108    bs->fd = open(driver, O_RDWR | O_CLOEXEC);//打开文件，并且保存在bs->fd中
109    if (bs->fd < 0) {
110        fprintf(stderr,"binder: cannot open %s (%s)\n",
111                driver, strerror(errno));
112        goto fail_open;
113    }
114    //通过系统调用，获取到当前的版本信息
115    if ((ioctl(bs->fd, BINDER_VERSION, &vers) == -1) ||
116        (vers.protocol_version != BINDER_CURRENT_PROTOCOL_VERSION)) {
117        fprintf(stderr,
118                "binder: kernel driver version (%d) differs from user space version (%d)\n",
119                vers.protocol_version, BINDER_CURRENT_PROTOCOL_VERSION);
120        goto fail_open;
121    }
122//进行内存映射
123    bs->mapsize = mapsize;
124    bs->mapped = mmap(NULL, mapsize, PROT_READ, MAP_PRIVATE, bs->fd, 0);
125    if (bs->mapped == MAP_FAILED) {
126        fprintf(stderr,"binder: cannot map device (%s)\n",
127                strerror(errno));
128        goto fail_map;
129    }
130
131    return bs;
132
133fail_map:
134    close(bs->fd);
135fail_open:
136    free(bs);
137    return NULL;
138}
```

下面来看binder怎么成为servicemanager的

#### 4.binder.c -> binder_become_context_manager

```c
147int binder_become_context_manager(struct binder_state *bs)
148{//通过ioctl调用，传入文件描述符和操作指令BINDER_SET_CONTEXT_MGR 
149    return ioctl(bs->fd, BINDER_SET_CONTEXT_MGR, 0);
150}
```

调用到驱动的binder.c中

5.binder.c -> binder_ioctl

```c
4792static long binder_ioctl(struct file *filp, unsigned int cmd, unsigned long arg)
4793{
...
4848	case BINDER_SET_CONTEXT_MGR:
4849		ret = binder_ioctl_set_ctx_mgr(filp, NULL);
4850		if (ret)
4851			goto err;
4852		break;
...
4905}
```

```c
4720static int binder_ioctl_set_ctx_mgr(struct file *filp,
4721				    struct flat_binder_object *fbo)
4722{
4723	int ret = 0;
4724	struct binder_proc *proc = filp->private_data;
4725	struct binder_context *context = proc->context;
4726	struct binder_node *new_node;
4727	kuid_t curr_euid = current_euid();
4728
4729	mutex_lock(&context->context_mgr_node_lock);
4730	if (context->binder_context_mgr_node) {
4731		pr_err("BINDER_SET_CONTEXT_MGR already set\n");
4732		ret = -EBUSY;
4733		goto out;
4734	}
4735	ret = security_binder_set_context_mgr(proc->tsk);//进行selinux权限检查
4736	if (ret < 0)
4737		goto out;
4738	if (uid_valid(context->binder_context_mgr_uid)) {
4739		if (!uid_eq(context->binder_context_mgr_uid, curr_euid)) {
4740			pr_err("BINDER_SET_CONTEXT_MGR bad uid %d != %d\n",
4741			       from_kuid(&init_user_ns, curr_euid),
4742			       from_kuid(&init_user_ns,
4743					 context->binder_context_mgr_uid));
4744			ret = -EPERM;
4745			goto out;
4746		}
4747	} else {
4748		context->binder_context_mgr_uid = curr_euid;//设置为对应的uid
4749	}
4750	new_node = binder_new_node(proc, fbo);
4751	if (!new_node) {
4752		ret = -ENOMEM;
4753		goto out;
4754	}
4755	binder_node_lock(new_node);//创建实例
4756	new_node->local_weak_refs++;
4757	new_node->local_strong_refs++;
4758	new_node->has_strong_ref = 1;
4759	new_node->has_weak_ref = 1;
4760	context->binder_context_mgr_node = new_node;
4761	binder_node_unlock(new_node);
4762	binder_put_node(new_node);
4763out:
4764	mutex_unlock(&context->context_mgr_node_lock);
4765	return ret;
4766}
```

创建完成后就进入了binderloop

#### 6.binder.c -> binder_loop

```c
389void binder_loop(struct binder_state *bs, binder_handler func)
390{
391    int res;
392    struct binder_write_read bwr;
393    uint32_t readbuf[32];
394
395    bwr.write_size = 0;
396    bwr.write_consumed = 0;
397    bwr.write_buffer = 0;
398
399    readbuf[0] = BC_ENTER_LOOPER;
400    binder_write(bs, readbuf, sizeof(uint32_t));
401
402    for (;;) {//进入无限循环来获取消息
403        bwr.read_size = sizeof(readbuf);
404        bwr.read_consumed = 0;
405        bwr.read_buffer = (uintptr_t) readbuf;
406
407        res = ioctl(bs->fd, BINDER_WRITE_READ, &bwr);//等待消息
408
409        if (res < 0) {
410            ALOGE("binder_loop: ioctl failed (%s)\n", strerror(errno));
411            break;
412        }
413//解析消息，注意最后一个参数是一个函数
414        res = binder_parse(bs, 0, (uintptr_t) readbuf, bwr.read_consumed, func);
415        if (res == 0) {
416            ALOGE("binder_loop: unexpected reply?!\n");
417            break;
418        }
419        if (res < 0) {
420            ALOGE("binder_loop: io error %d %s\n", res, strerror(errno));
421            break;
422        }
423    }
424}
```





**ServiceManager启动流程：**

1. 打开binder驱动，并调用mmap()方法分配128k的内存映射空间：binder_open();
2. 通知binder驱动使其成为守护进程：binder_become_context_manager()；
3. 验证selinux权限，判断进程是否有权注册或查看指定服务；
4. 进入循环状态，等待Client端的请求：binder_loop()。
5. 注册服务的过程，根据服务名称，但同一个服务已注册，重新注册前会先移除之前的注册信息；
6. 死亡通知: 当binder所在进程死亡后,会调用binder_release方法,然后调用binder_node_release.这个过程便会发出死亡通知的回调.

ServiceManager最核心的两个功能为查询和注册服务：

- 注册服务：记录服务名和handle信息，保存到svclist列表；
- 查询服务：根据服务名查询相应的的handle信息。
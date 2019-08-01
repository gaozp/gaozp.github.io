---
layout: post
title: app compaction 源码分析（framework层）
---
### 前言：
app compaction是AndroidQ上的新功能，旨在将后台应用也就是adj非前台的，进行内存压缩，可以将1.8G内存的应用降到700兆，这明显的提高了内存的使用效率，但是就是不知道压缩内存和解压缩是否会造成效率的丢失。

看了下源码：ams中将oomadj的调整彻底的解耦出来，也就是之前的updateoomadj和computeoomadj还有applyadj都解耦到这个__OomAdjster.java__中了。

还有一个，其实Android原本的想法是尽可能多的缓存应用，这样才能效率更高，同时需要添加合适的内存回收机制，让内存的效率最大化，所以内存的优化才应该是我们努力的方向，而不是应该在app不用之后立刻就将内存释放，这样保持内存占用少的情况。

### 源码分析：
#### 1.ams启动：
在ams启动的时候就启动：
```JAVA
ams.java
// Note: This method is invoked on the main thread but may need to attach various
// handlers to other threads.  So take care to be explicit about the looper.
public ActivityManagerService(Context systemContext, ActivityTaskManagerService atm) {
    LockGuard.installLock(this, LockGuard.INDEX_ACTIVITY);
...
    mOomAdjuster = new OomAdjuster(this, mProcessList, activeUids);
...
}
```
#### 2.oomadjuster创建
在创建ams的时候进行了mOomAdjuster的创建，那我们看一下mOomAdjuster创建的时候做了哪一些工作：
```JAVA
    OomAdjuster(ActivityManagerService service, ProcessList processList, ActiveUids activeUids) {
        mService = service;
        mProcessList = processList;
        mActiveUids = activeUids;

        mLocalPowerManager = LocalServices.getService(PowerManagerInternal.class);
        mConstants = mService.mConstants;
        mAppCompact = new AppCompactor(mService);

        if(mPerf != null) {
            mMinBServiceAgingTime = Integer.valueOf(mPerf.perfGetProp("ro.vendor.qti.sys.fw.bservice_age", "5000"));
            mBServiceAppThreshold = Integer.valueOf(mPerf.perfGetProp("ro.vendor.qti.sys.fw.bservice_limit", "5"));
            mEnableBServicePropagation = Boolean.parseBoolean(mPerf.perfGetProp("ro.vendor.qti.sys.fw.bservice_enable", "false"));
        }

        // The process group is usually critical to the response time of foreground app, so the
        // setter should apply it as soon as possible.
        final ServiceThread adjusterThread = new ServiceThread(TAG, TOP_APP_PRIORITY_BOOST,
                false /* allowIo */);
        adjusterThread.start();
        Process.setThreadGroupAndCpuset(adjusterThread.getThreadId(), THREAD_GROUP_TOP_APP);
        mProcessGroupHandler = new Handler(adjusterThread.getLooper(), msg -> {
            Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "setProcessGroup");
            final int pid = msg.arg1;
            final int group = msg.arg2;
            try {
                setProcessGroup(pid, group);
            } catch (Exception e) {
                if (DEBUG_ALL) {
                    Slog.w(TAG, "Failed setting process group of " + pid + " to " + group, e);
                }
            } finally {
                Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
            }
            return true;
        });
    }
```
可以看到在创建的时候，同时创建了一个mAppCompact，这个就是我们需要的压缩的功能啦。还有就是创建了一个调整process的group和cpuset的线程，是放在了TOP_APP的线程中。
#### 3.appcompactor的创建
```JAVA
    public AppCompactor(ActivityManagerService am) {
        mAm = am;
        mCompactionThread = new ServiceThread("CompactionThread",
                THREAD_PRIORITY_FOREGROUND, true);
        mProcStateThrottle = new HashSet<>();
    }
```
拿住了ams的引用，同时创建一个线程mcompactionthread,和一个hashset mprocstatethrottle.
现在让我们回到1的ams中：
#### 4.ams.installsystemproviders
```java
    public final void installSystemProviders() {
        ...
        mOomAdjuster.initSettings();
        ...
    }
```
看到，代码中调用了oomadjuster.initsettings
#### 5.oomadjuster中调用appcompact方法
因为oomadjuster已经完全解耦出来了，所以就不赘述ams中的adj的计算和过程了，可以看到，在applyoomadjlocked方法中，会调用到appcompact进行压缩。
```JAVA
        // don't compact during bootup
        if (mAppCompact.useCompaction() && mService.mBooted) {
            // Cached and prev/home compaction
            if (app.curAdj != app.setAdj) {
                // Perform a minor compaction when a perceptible app becomes the prev/home app
                // Perform a major compaction when any app enters cached
                // reminder: here, setAdj is previous state, curAdj is upcoming state
                if (app.setAdj <= ProcessList.PERCEPTIBLE_APP_ADJ &&
                        (app.curAdj == ProcessList.PREVIOUS_APP_ADJ ||
                                app.curAdj == ProcessList.HOME_APP_ADJ)) {
                    mAppCompact.compactAppSome(app);
                } else if ((app.setAdj < ProcessList.CACHED_APP_MIN_ADJ
                                || app.setAdj > ProcessList.CACHED_APP_MAX_ADJ)
                        && app.curAdj >= ProcessList.CACHED_APP_MIN_ADJ
                        && app.curAdj <= ProcessList.CACHED_APP_MAX_ADJ) {
                    mAppCompact.compactAppFull(app);
                }
            } else if (mService.mWakefulness != PowerManagerInternal.WAKEFULNESS_AWAKE
                    && app.setAdj < ProcessList.FOREGROUND_APP_ADJ
                    // Because these can fire independent of oom_adj/procstate changes, we need
                    // to throttle the actual dispatch of these requests in addition to the
                    // processing of the requests. As a result, there is throttling both here
                    // and in AppCompactor.
                    && mAppCompact.shouldCompactPersistent(app, now)) {
                mAppCompact.compactAppPersistent(app);
            } else if (mService.mWakefulness != PowerManagerInternal.WAKEFULNESS_AWAKE
                    && app.getCurProcState()
                        == ActivityManager.PROCESS_STATE_BOUND_FOREGROUND_SERVICE
                    && mAppCompact.shouldCompactBFGS(app, now)) {
                mAppCompact.compactAppBfgs(app);
            }
        }
```
__逻辑可以看到
1.当一个可感知的应用进入到prev或者home的时候进行一次minor的compact
2.当一个app进入到cache中的时候进行一个major的compact
3.当前不在awake，并且进程不是前台app的时候，并且不就前没进行过压缩
4.前台服务在机器非唤醒的状态下，也会进行compact__
调用了以上的方法后，就会进入到appcompactor中了
#### 6.appcompactor
```JAVA
    @GuardedBy("mAm")
    void compactAppSome(ProcessRecord app) {
        app.reqCompactAction = COMPACT_PROCESS_SOME;
        mPendingCompactionProcesses.add(app);
        mCompactionHandler.sendMessage(
            mCompactionHandler.obtainMessage(
                COMPACT_PROCESS_MSG, app.setAdj, app.setProcState));
    }

    @GuardedBy("mAm")
    void compactAppFull(ProcessRecord app) {
        app.reqCompactAction = COMPACT_PROCESS_FULL;
        mPendingCompactionProcesses.add(app);
        mCompactionHandler.sendMessage(
            mCompactionHandler.obtainMessage(
                COMPACT_PROCESS_MSG, app.setAdj, app.setProcState));

    }

    @GuardedBy("mAm")
    void compactAppPersistent(ProcessRecord app) {
        app.reqCompactAction = COMPACT_PROCESS_PERSISTENT;
        mPendingCompactionProcesses.add(app);
        mCompactionHandler.sendMessage(
                mCompactionHandler.obtainMessage(
                    COMPACT_PROCESS_MSG, app.curAdj, app.setProcState));
    }

    @GuardedBy("mAm")
    boolean shouldCompactPersistent(ProcessRecord app, long now) {
        return (app.lastCompactTime == 0
                || (now - app.lastCompactTime) > mCompactThrottlePersistent);
    }

    @GuardedBy("mAm")
    void compactAppBfgs(ProcessRecord app) {
        app.reqCompactAction = COMPACT_PROCESS_BFGS;
        mPendingCompactionProcesses.add(app);
        mCompactionHandler.sendMessage(
                mCompactionHandler.obtainMessage(
                    COMPACT_PROCESS_MSG, app.curAdj, app.setProcState));
    }
```
通过以上的几个方法都可以看到，是在一个arraylist中添加了这个需要进行修改的processrecord，然后发送消息给handler，用app.reqcompactaction来标记需要的操作。
```JAVA
                    switch (pendingAction) {
                        case COMPACT_PROCESS_SOME:
                            action = mCompactActionSome;
                            break;
                        // For the time being, treat these as equivalent.
                        case COMPACT_PROCESS_FULL:
                        case COMPACT_PROCESS_PERSISTENT:
                        case COMPACT_PROCESS_BFGS:
                            action = mCompactActionFull;
                            break;
                        default:
                            action = COMPACT_ACTION_NONE;
                            break;
                    }

```
可以看到在消息处理的时候，其实full，persistent和bfgs都是调用的full，还有一个注释：for the time being, treat these as equivalent
哈哈，原来是刚开始就不会做太大的细分。
```JAVA
                try {
                        Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "Compact "
                                + ((pendingAction == COMPACT_PROCESS_SOME) ? "some" : "full")
                                + ": " + name);
                        long zramFreeKbBefore = Debug.getZramFreeKb();
                        FileOutputStream fos = new FileOutputStream("/proc/" + pid + "/reclaim");
                        fos.write(action.getBytes());
                        fos.close();
                        long[] rssAfter = Process.getRss(pid);
                        long end = SystemClock.uptimeMillis();
                        long time = end - start;
                        long zramFreeKbAfter = Debug.getZramFreeKb();
                        EventLog.writeEvent(EventLogTags.AM_COMPACT, pid, name, action,
                                rssBefore[0], rssBefore[1], rssBefore[2], rssBefore[3],
                                rssAfter[0] - rssBefore[0], rssAfter[1] - rssBefore[1],
                                rssAfter[2] - rssBefore[2], rssAfter[3] - rssBefore[3], time,
                                lastCompactAction, lastCompactTime, lastOomAdj, procState,
                                zramFreeKbBefore, zramFreeKbAfter - zramFreeKbBefore);

                        // Note that as above not taking mPhenoTypeFlagLock here to avoid locking
                        // on every single compaction for a flag that will seldom change and the
                        // impact of reading the wrong value here is low.
                        if (mRandom.nextFloat() < mStatsdSampleRate) {
                            StatsLog.write(StatsLog.APP_COMPACTED, pid, name, pendingAction,
                                    rssBefore[0], rssBefore[1], rssBefore[2], rssBefore[3],
                                    rssAfter[0], rssAfter[1], rssAfter[2], rssAfter[3], time,
                                    lastCompactAction, lastCompactTime, lastOomAdj,
                                    ActivityManager.processStateAmToProto(procState),
                                    zramFreeKbBefore, zramFreeKbAfter);
                        }

                        synchronized (mAm) {
                            proc.lastCompactTime = end;
                            proc.lastCompactAction = pendingAction;
                        }

                        if (action.equals(COMPACT_ACTION_FULL)
                                || action.equals(COMPACT_ACTION_ANON)) {
                            mLastCompactionStats.put(pid, new LastCompactionStats(rssAfter));
                        }
                    } catch (Exception e) {
                        // nothing to do, presumably the process died
                    } finally {
                        Trace.traceE
```
在进行了一系列的判断后，最后调用方法其实也还是在/proc/pid/reclaim文件中写入了full，或者是some。

### 结语
那么framework层的就解析到这里了，framework层比较简单，简单来说就是在进程状态进行改变的时候，比如应用进入前后台，这些，kernel是没有办法感知的，需要framework去暴露给kernel，kernel会根据这些记录在/proc/pid/下面的信息来统一进行处理。




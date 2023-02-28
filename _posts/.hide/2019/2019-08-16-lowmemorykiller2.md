---
layout: post
title: Android lowmemorykiller源码分析（二）
categories: [tech]
---
#### 前言：
上次说到了lmkd的启动，那么这次就说一下，是哪些客户端会给lmkd发送一些消息。
#### 消息的类别：
```C
/*
 * Supported LMKD commands
 */
enum lmk_cmd {
    LMK_TARGET = 0,  /* Associate minfree with oom_adj_score */
    LMK_PROCPRIO,    /* Register a process and set its oom_adj_score */
    LMK_PROCREMOVE,  /* Unregister a process */
};
```
那么这三类消息是如何发到lmkd层的呢？
#### 消息的发送：
```java
//LMK_TARGET: ProcessList.java
    private void updateOomLevels(int displayWidth, int displayHeight, boolean write) {
        ...
        if (write) {
            ByteBuffer buf = ByteBuffer.allocate(4 * (2*mOomAdj.length + 1));
            buf.putInt(LMK_TARGET);
            for (int i=0; i<mOomAdj.length; i++) {
                buf.putInt((mOomMinFree[i]*1024)/PAGE_SIZE);
                buf.putInt(mOomAdj[i]);
            }

            writeLmkd(buf);
            SystemProperties.set("sys.sysctl.extra_free_kbytes", Integer.toString(reserve));
        }
```
```JAVA
// LMK_PROCPRIO : ProcessList.java
    public static final void setOomAdj(int pid, int uid, int amt) {
        // This indicates that the process is not started yet and so no need to proceed further.
        if (pid <= 0) {
            return;
        }
        if (amt == UNKNOWN_ADJ)
            return;

        long start = SystemClock.elapsedRealtime();
        ByteBuffer buf = ByteBuffer.allocate(4 * 4);
        buf.putInt(LMK_PROCPRIO);
        buf.putInt(pid);
        buf.putInt(uid);
        buf.putInt(amt);
        writeLmkd(buf);
        long now = SystemClock.elapsedRealtime();
        if ((now-start) > 250) {
            Slog.w("ActivityManager", "SLOW OOM ADJ: " + (now-start) + "ms for pid " + pid
                    + " = " + amt);
        }
    }
```
```java
// LMK_PROCREMOVE : ProcessList.java
    public static final void remove(int pid) {
        // This indicates that the process is not started yet and so no need to proceed further.
        if (pid <= 0) {
            return;
        }
        ByteBuffer buf = ByteBuffer.allocate(4 * 2);
        buf.putInt(LMK_PROCREMOVE);
        buf.putInt(pid);
        writeLmkd(buf);
    }
```
其实总结下来，这三个操作都是在processlist中操作的，而这些操作又是被ams进行调用的。
|ams|processlist|lmkd.c|desc|
|-|-|-|-|
|applyOomAdjLocked|setOomAdj|LMK_PROCPRIO|设置进程对应的优先级|
|cleanUpApplicationRecordLocked/handleAppDiedLocked|remove|LMK_PROCREMOVE|移除进程|
|updateConfiguration|updateOomLevels|LMK_TARGET|更新minfree和adj|

#### 总结
ams根据当前android的进程状态，将进程信息写到lmkd的socket中，lmkd收到消息后，根据对应的消息进行对应的处理，比如写proc/pid/oom_score_adj或者minfree其他操作，进行记录。


---
layout: post
title: 有序广播和无序广播anr问题
---
#### 背景描述
昨天在看到同事弄的笔试题的时候，发现一个很有意思的题目：无序广播一定没有anr吗？
哈哈这个题目真的是,陷阱还很多。
简单来说这个题目的答案是：有序广播会产生anr，但是无序在发送端系统不会anr，但是如果在onreseive中耗时操作，那么还是会产生anr，只不过这个anr是activity的anr了。

#### 解析
##### 1.有序广播无序广播发送时的anr
此处我们只讨论广播的anr，至于其他的anr下次再说。
首先需要理解下广播发送的流程：  
长话短说，广播发送是调用了：
```JAVA 
contextimpl.java
//无序广播
1013    @Override
1014    public void sendBroadcast(Intent intent) {
1015        warnIfCallingFromSystemProcess();
1016        String resolvedType = intent.resolveTypeIfNeeded(getContentResolver());
1017        try {
1018            intent.prepareToLeaveProcess(this);
1019            ActivityManager.getService().broadcastIntent(
1020                    mMainThread.getApplicationThread(), intent, resolvedType, null,
1021                    Activity.RESULT_OK, null, null, null, AppOpsManager.OP_NONE, null, false, false,
1022                    getUserId());
1023        } catch (RemoteException e) {
1024            throw e.rethrowFromSystemServer();
1025        }
1026    }
//有序广播
1110    @Override
1111    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
1112        warnIfCallingFromSystemProcess();
1113        String resolvedType = intent.resolveTypeIfNeeded(getContentResolver());
1114        String[] receiverPermissions = receiverPermission == null ? null
1115                : new String[] {receiverPermission};
1116        try {
1117            intent.prepareToLeaveProcess(this);
1118            ActivityManager.getService().broadcastIntent(
1119                    mMainThread.getApplicationThread(), intent, resolvedType, null,
1120                    Activity.RESULT_OK, null, null, receiverPermissions, AppOpsManager.OP_NONE,
1121                    null, true, false, getUserId());
1122        } catch (RemoteException e) {
1123            throw e.rethrowFromSystemServer();
1124        }
1125    }

```
注意到倒数第三个参数没有，无序是false，而有序是true，而在ams中接收到的结果便是ordered,
```java
ams.java
22605    public final int broadcastIntent(IApplicationThread caller,
22606            Intent intent, String resolvedType, IIntentReceiver resultTo,
22607            int resultCode, String resultData, Bundle resultExtras,
22608            String[] requiredPermissions, int appOp, Bundle bOptions,
22609            boolean serialized, boolean sticky, int userId) {
22610        enforceNotIsolatedCaller("broadcastIntent");
22611        synchronized(this) {
22612            intent = verifyBroadcastLocked(intent);
22613
22614            final ProcessRecord callerApp = getRecordForAppLocked(caller);
22615            final int callingPid = Binder.getCallingPid();
22616            final int callingUid = Binder.getCallingUid();
22617            final long origId = Binder.clearCallingIdentity();
22618            int res = broadcastIntentLocked(callerApp,
22619                    callerApp != null ? callerApp.info.packageName : null,
22620                    intent, resolvedType, resultTo, resultCode, resultData, resultExtras,
22621                    requiredPermissions, appOp, bOptions, serialized, sticky,
22622                    callingPid, callingUid, userId);
22623            Binder.restoreCallingIdentity(origId);
22624            return res;
22625        }
22626    }
```
那么这个ordered是什么地方用到的呢
```JAVA
AMS.JAVA
21557    @GuardedBy("this")
21558    final int broadcastIntentLocked(ProcessRecord callerApp,
21559            String callerPackage, Intent intent, String resolvedType,
21560            IIntentReceiver resultTo, int resultCode, String resultData,
21561            Bundle resultExtras, String[] requiredPermissions, int appOp, Bundle bOptions,
21562            boolean ordered, boolean sticky, int callingPid, int callingUid, int userId) {
    ...
    //无序广播的逻辑
22052        int NR = registeredReceivers != null ? registeredReceivers.size() : 0;
22053        if (!ordered && NR > 0) {
22054            // If we are not serializing this broadcast, then send the
22055            // registered receivers separately so they don't wait for the
22056            // components to be launched.
22057            if (isCallerSystem) {
22058                checkBroadcastFromSystem(intent, callerApp, callerPackage, callingUid,
22059                        isProtectedBroadcast, registeredReceivers);
22060            }
22061            final BroadcastQueue queue = broadcastQueueForIntent(intent);
22062            BroadcastRecord r = new BroadcastRecord(queue, intent, callerApp,
22063                    callerPackage, callingPid, callingUid, callerInstantApp, resolvedType,
22064                    requiredPermissions, appOp, brOptions, registeredReceivers, resultTo,
22065                    resultCode, resultData, resultExtras, ordered, sticky, false, userId);
22066            if (DEBUG_BROADCAST) Slog.v(TAG_BROADCAST, "Enqueueing parallel broadcast " + r);
22067            final boolean replaced = replacePending
22068                    && (queue.replaceParallelBroadcastLocked(r) != null);
22069            // Note: We assume resultTo is null for non-ordered broadcasts.
22070            if (!replaced) {
22071                queue.enqueueParallelBroadcastLocked(r);
22072                queue.scheduleBroadcastsLocked();
22073            }
22074            registeredReceivers = null;
22075            NR = 0;
22076        }
//有序广播的逻辑
22154
22155        if ((receivers != null && receivers.size() > 0)
22156                || resultTo != null) {
22157            BroadcastQueue queue = broadcastQueueForIntent(intent);
22158            BroadcastRecord r = new BroadcastRecord(queue, intent, callerApp,
22159                    callerPackage, callingPid, callingUid, callerInstantApp, resolvedType,
22160                    requiredPermissions, appOp, brOptions, receivers, resultTo, resultCode,
22161                    resultData, resultExtras, ordered, sticky, false, userId);
22162
22163            if (DEBUG_BROADCAST) Slog.v(TAG_BROADCAST, "Enqueueing ordered broadcast " + r
22164                    + ": prev had " + queue.mOrderedBroadcasts.size());
22165            if (DEBUG_BROADCAST) Slog.i(TAG_BROADCAST,
22166                    "Enqueueing broadcast " + r.intent.getAction());
22167
22168            final BroadcastRecord oldRecord =
22169                    replacePending ? queue.replaceOrderedBroadcastLocked(r) : null;
22170            if (oldRecord != null) {
22171                // Replaced, fire the result-to receiver.
22172                if (oldRecord.resultTo != null) {
22173                    final BroadcastQueue oldQueue = broadcastQueueForIntent(oldRecord.intent);
22174                    try {
22175                        oldQueue.performReceiveLocked(oldRecord.callerApp, oldRecord.resultTo,
22176                                oldRecord.intent,
22177                                Activity.RESULT_CANCELED, null, null,
22178                                false, false, oldRecord.userId);
22179                    } catch (RemoteException e) {
22180                        Slog.w(TAG, "Failure ["
22181                                + queue.mQueueName + "] sending broadcast result of "
22182                                + intent, e);
22183
22184                    }
22185                }
22186            } else {
22187                queue.enqueueOrderedBroadcastLocked(r);
22188                queue.scheduleBroadcastsLocked();
22189            }
22190        } else {
22191            // There was nobody interested in the broadcast, but we still want to record
22192            // that it happened.
22193            if (intent.getComponent() == null && intent.getPackage() == null
22194                    && (intent.getFlags()&Intent.FLAG_RECEIVER_REGISTERED_ONLY) == 0) {
22195                // This was an implicit broadcast... let's record it for posterity.
22196                addBroadcastStatLocked(intent.getAction(), callerPackage, 0, 0, 0);
22197            }
22198        }
22201    }
```
上面的代码可以看到，如果是有序广播则会添加到orderbroadcast中，无序的就会添加到ParallelBroadcast中。
```JAVA
BROADCASTQUEUE.JAVA
216    public void enqueueParallelBroadcastLocked(BroadcastRecord r) {
217        mParallelBroadcasts.add(r);
218        enqueueBroadcastHelper(r);
219    }
220
221    public void enqueueOrderedBroadcastLocked(BroadcastRecord r) {
222        mOrderedBroadcasts.add(r);
223        enqueueBroadcastHelper(r);
224    }
```
就是添加在了不同的arraylist中，那么这个list在什么时候用的呢？是在进行广播发送的时候需要一一取出来。
```java
broadcastqueue.java
829    final void processNextBroadcastLocked(boolean fromMsg, boolean skipOomAdj) {
843        // First, deliver any non-serialized broadcasts right away.
844        while (mParallelBroadcasts.size() > 0) {
845            r = mParallelBroadcasts.remove(0);
846            r.dispatchTime = SystemClock.uptimeMillis();
847            r.dispatchClockTime = System.currentTimeMillis();
848
849            if (Trace.isTagEnabled(Trace.TRACE_TAG_ACTIVITY_MANAGER)) {
850                Trace.asyncTraceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER,
851                    createBroadcastTraceTitle(r, BroadcastRecord.DELIVERY_PENDING),
852                    System.identityHashCode(r));
853                Trace.asyncTraceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER,
854                    createBroadcastTraceTitle(r, BroadcastRecord.DELIVERY_DELIVERED),
855                    System.identityHashCode(r));
856            }
857
858            final int N = r.receivers.size();
859            if (DEBUG_BROADCAST_LIGHT) Slog.v(TAG_BROADCAST, "Processing parallel broadcast ["
860                    + mQueueName + "] " + r);
861            for (int i=0; i<N; i++) {
862                Object target = r.receivers.get(i);
863                if (DEBUG_BROADCAST)  Slog.v(TAG_BROADCAST,
864                        "Delivering non-ordered on [" + mQueueName + "] to registered "
865                        + target + ": " + r);
866                deliverToRegisteredReceiverLocked(r, (BroadcastFilter)target, false, i);
867            }
868            addBroadcastToHistoryLocked(r);
869            if (DEBUG_BROADCAST_LIGHT) Slog.v(TAG_BROADCAST, "Done with parallel broadcast ["
870                    + mQueueName + "] " + r);
871        }
```
备注很明显，首先直接发送无序广播，delivertoregisteredreceiverlocked就是发送广播的方法，暂时先记下来。这里就可以看到，broadcastqueue只是将无序广播发送出去而已，并没有做任何其他操作。
```java
910        do {
911            if (mOrderedBroadcasts.size() == 0) {
912                // No more broadcasts pending, so all done!
913                mService.scheduleAppGcsLocked();
914                if (looped) {
915                    // If we had finished the last ordered broadcast, then
916                    // make sure all processes have correct oom and sched
917                    // adjustments.
918                    mService.updateOomAdjLocked();
919                }
920                return;
921            }
922            r = mOrderedBroadcasts.get(0);
923            boolean forceReceive = false;
924
925            // Ensure that even if something goes awry with the timeout
926            // detection, we catch "hung" broadcasts here, discard them,
927            // and continue to make progress.
928            //
929            // This is only done if the system is ready so that PRE_BOOT_COMPLETED
930            // receivers don't get executed with timeouts. They're intended for
931            // one time heavy lifting after system upgrades and can take
932            // significant amounts of time.
933            int numReceivers = (r.receivers != null) ? r.receivers.size() : 0;
934            if (mService.mProcessesReady && r.dispatchTime > 0) {
935                long now = SystemClock.uptimeMillis();
936                if ((numReceivers > 0) &&
937                        (now > r.dispatchTime + (2*mTimeoutPeriod*numReceivers))) {
938                    Slog.w(TAG, "Hung broadcast ["
939                            + mQueueName + "] discarded after timeout failure:"
940                            + " now=" + now
941                            + " dispatchTime=" + r.dispatchTime
942                            + " startTime=" + r.receiverTime
943                            + " intent=" + r.intent
944                            + " numReceivers=" + numReceivers
945                            + " nextReceiver=" + r.nextReceiver
946                            + " state=" + r.state);
947                    broadcastTimeoutLocked(false); // forcibly finish this broadcast
948                    forceReceive = true;
949                    r.state = BroadcastRecord.IDLE;
950                }
951            }
952
953            if (r.state != BroadcastRecord.IDLE) {
954                if (DEBUG_BROADCAST) Slog.d(TAG_BROADCAST,
955                        "processNextBroadcast("
956                        + mQueueName + ") called when not idle (state="
957                        + r.state + ")");
958                return;
959            }
960
961            if (r.receivers == null || r.nextReceiver >= numReceivers
962                    || r.resultAbort || forceReceive) {
963                // No more receivers for this broadcast!  Send the final
964                // result if requested...
965                if (r.resultTo != null) {
966                    try {
967                        if (DEBUG_BROADCAST) Slog.i(TAG_BROADCAST,
968                                "Finishing broadcast [" + mQueueName + "] "
969                                + r.intent.getAction() + " app=" + r.callerApp);
970                        performReceiveLocked(r.callerApp, r.resultTo,
971                            new Intent(r.intent), r.resultCode,
972                            r.resultData, r.resultExtras, false, false, r.userId);
973                        // Set this to null so that the reference
974                        // (local and remote) isn't kept in the mBroadcastHistory.
975                        r.resultTo = null;
976                    } catch (RemoteException e) {
977                        r.resultTo = null;
978                        Slog.w(TAG, "Failure ["
979                                + mQueueName + "] sending broadcast result of "
980                                + r.intent, e);
981
982                    }
983                }
984
985                if (DEBUG_BROADCAST) Slog.v(TAG_BROADCAST, "Cancelling BROADCAST_TIMEOUT_MSG");
986                cancelBroadcastTimeoutLocked();
987
988                if (DEBUG_BROADCAST_LIGHT) Slog.v(TAG_BROADCAST,
989                        "Finished with ordered broadcast " + r);
990
991                // ... and on to the next...
992                addBroadcastToHistoryLocked(r);
993                if (r.intent.getComponent() == null && r.intent.getPackage() == null
994                        && (r.intent.getFlags()&Intent.FLAG_RECEIVER_REGISTERED_ONLY) == 0) {
995                    // This was an implicit broadcast... let's record it for posterity.
996                    mService.addBroadcastStatLocked(r.intent.getAction(), r.callerPackage,
997                            r.manifestCount, r.manifestSkipCount, r.finishTime-r.dispatchTime);
998                }
999                mOrderedBroadcasts.remove(0);
1000                r = null;
1001                looped = true;
1002                continue;
1003            }
1004        } while (r == null);
1005
1006        // Get the next receiver...
1007        int recIdx = r.nextReceiver++;
1008
1009        // Keep track of when this receiver started, and make sure there
1010        // is a timeout message pending to kill it if need be.
1011        r.receiverTime = SystemClock.uptimeMillis();
1012        if (recIdx == 0) {
1013            r.dispatchTime = r.receiverTime;
1014            r.dispatchClockTime = System.currentTimeMillis();
1015            if (Trace.isTagEnabled(Trace.TRACE_TAG_ACTIVITY_MANAGER)) {
1016                Trace.asyncTraceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER,
1017                    createBroadcastTraceTitle(r, BroadcastRecord.DELIVERY_PENDING),
1018                    System.identityHashCode(r));
1019                Trace.asyncTraceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER,
1020                    createBroadcastTraceTitle(r, BroadcastRecord.DELIVERY_DELIVERED),
1021                    System.identityHashCode(r));
1022            }
1023            if (DEBUG_BROADCAST_LIGHT) Slog.v(TAG_BROADCAST, "Processing ordered broadcast ["
1024                    + mQueueName + "] " + r);
1025        }
1026        if (! mPendingBroadcastTimeoutMessage) {
1027            long timeoutTime = r.receiverTime + mTimeoutPeriod;
1028            if (DEBUG_BROADCAST) Slog.v(TAG_BROADCAST,
1029                    "Submitting BROADCAST_TIMEOUT_MSG ["
1030                    + mQueueName + "] for " + r + " at " + timeoutTime);
1031            setBroadcastTimeoutLocked(timeoutTime);
1032        }
1033
1034        final BroadcastOptions brOptions = r.options;
1035        final Object nextReceiver = r.receivers.get(recIdx);
1036
```
紧接着往下看就可以看到，有序广播发送给下一个的时候，首先会判断，上一个是否已经超时，也就是anr，如果超时，就会发送anr既broadcasttimoutlocked，否则继续往下，就会取消之前的timeout（cancelbroadcasttimeoutlocked），在发送给下一个之前，首先发一个超时的消息（setbroadcasttimeoutlocked），这样就连环起来了。  
发送每个广播之前都需要先发送一个消息给handler。
##### 2.消息处理时候的anr
这个就比较简单了，之前我们说到delivertoregisteredreceiverlocked是真的让广播处理的地方
```JAVA
520    private void deliverToRegisteredReceiverLocked(BroadcastRecord r,
521            BroadcastFilter filter, boolean ordered, int index) {
    ...
706                performReceiveLocked(filter.receiverList.app, filter.receiverList.receiver,
707                        new Intent(r.intent), r.resultCode, r.resultData,
708                        r.resultExtras, r.ordered, r.initialSticky, r.userId);
...
494                    app.thread.scheduleRegisteredReceiver(receiver, intent, resultCode,
495                            data, extras, ordered, sticky, sendingUser, app.repProcState);
```
那么在loadedapk中就可以找到
```JAVA
1293            public void performReceive(Intent intent, int resultCode, String data,
1294                    Bundle extras, boolean ordered, boolean sticky, int sendingUser) {
1295                final LoadedApk.ReceiverDispatcher rd;
1296                if (intent == null) {
1297                    Log.wtf(TAG, "Null intent received");
1298                    rd = null;
1299                } else {
1300                    rd = mDispatcher.get();
1301                }
1302                if (ActivityThread.DEBUG_BROADCAST) {
1303                    int seq = intent.getIntExtra("seq", -1);
1304                    Slog.i(ActivityThread.TAG, "Receiving broadcast " + intent.getAction()
1305                            + " seq=" + seq + " to " + (rd != null ? rd.mReceiver : null));
1306                }
1307                if (rd != null) {
1308                    rd.performReceive(intent, resultCode, data, extras,
1309                            ordered, sticky, sendingUser);
```
在dispatcher中的performreceive的地方
```JAVA
1474
1475        public void performReceive(Intent intent, int resultCode, String data,
1476                Bundle extras, boolean ordered, boolean sticky, int sendingUser) {
1477            final Args args = new Args(intent, resultCode, data, extras, ordered,
1478                    sticky, sendingUser);
1479            if (intent == null) {
1480                Log.wtf(TAG, "Null intent received");
1481            } else {
1482                if (ActivityThread.DEBUG_BROADCAST) {
1483                    int seq = intent.getIntExtra("seq", -1);
1484                    Slog.i(ActivityThread.TAG, "Enqueueing broadcast " + intent.getAction()
1485                            + " seq=" + seq + " to " + mReceiver);
1486                }
1487            }
1488            if (intent == null || !mActivityThread.post(args.getRunnable())) {
1489                if (mRegistered && ordered) {
1490                    IActivityManager mgr = ActivityManager.getService();
1491                    if (ActivityThread.DEBUG_BROADCAST) Slog.i(ActivityThread.TAG,
1492                            "Finishing sync broadcast to " + mReceiver);
1493                    args.sendFinished(mgr);
1494                }
1495            }
1496        }
```
```JAVA
1383
1384                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "broadcastReceiveReg");
1385                    try {
1386                        ClassLoader cl = mReceiver.getClass().getClassLoader();
1387                        intent.setExtrasClassLoader(cl);
1388                        intent.prepareToEnterProcess();
1389                        setExtrasClassLoader(cl);
1390                        receiver.setPendingResult(this);
1391                        receiver.onReceive(mContext, intent);
```
到了这里就可以看到，就是在主线程的handler中使用的runnable，那么就是跑在主线程中的。
那么如果在主线程中去做耗时操作，那么必然会影响input事件的消费，而导致anr。
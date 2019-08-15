---
layout: post
title: Android P ClientTransaction
categories: [tech]
---

在Android P中，应用的生命周期和AMS的联动有了改变。看一下其中的改动吧。

首先ActivityThread继承了ClientTransactionHandler,而在O中是没有的。

```java
public final class ActivityThread extends ClientTransactionHandler {
```

ClientTransactionHandler

```java
38public abstract class ClientTransactionHandler {
43    void scheduleTransaction(ClientTransaction transaction) {
44        transaction.preExecute(this);
45        sendMessage(ActivityThread.H.EXECUTE_TRANSACTION, transaction);
46    }
53    public void executeTransaction(ClientTransaction transaction) {
54        transaction.preExecute(this);
55        getTransactionExecutor().execute(transaction);
56        transaction.recycle();
57    }
...一系列生命周期函数
82    public abstract void handleDestroyActivity(IBinder token, boolean finishing, int configChanges,
83            boolean getNonConfigInstance, String reason);
84
85    /** Pause the activity. */
86    public abstract void handlePauseActivity(IBinder token, boolean finished, boolean userLeaving,
87            int configChanges, PendingTransactionActions pendingActions, String reason);
88
89    /**
90     * Resume the activity.
91     * @param token Target activity token.
92     * @param finalStateRequest Flag indicating if this call is handling final lifecycle state
93     *                          request for a transaction.
94     * @param isForward Flag indicating if next transition is forward.
95     * @param reason Reason for performing this operation.
96     */
97    public abstract void handleResumeActivity(IBinder token, boolean finalStateRequest,
98            boolean isForward, String reason);
99
100    /**
101     * Stop the activity.
102     * @param token Target activity token.
103     * @param show Flag indicating whether activity is still shown.
104     * @param configChanges Activity configuration changes.
105     * @param pendingActions Pending actions to be used on this or later stages of activity
106     *                       transaction.
107     * @param finalStateRequest Flag indicating if this call is handling final lifecycle state
108     *                          request for a transaction.
109     * @param reason Reason for performing this operation.
110     */
111    public abstract void handleStopActivity(IBinder token, boolean show, int configChanges,
112            PendingTransactionActions pendingActions, boolean finalStateRequest, String reason);
113
114    /** Report that activity was stopped to server. */
115    public abstract void reportStop(PendingTransactionActions pendingActions);
116
117    /** Restart the activity after it was stopped. */
118    public abstract void performRestartActivity(IBinder token, boolean start);
119
120    /** Deliver activity (override) configuration change. */
121    public abstract void handleActivityConfigurationChanged(IBinder activityToken,
...
195}
```

注意其中的一系列生命周期的操作

那么就从一个场景来查看之前的startactvity

```java
1528                final ClientTransaction clientTransaction = ClientTransaction.obtain(app.thread,// ①
1529                        r.appToken);
1530                clientTransaction.addCallback(LaunchActivityItem.obtain(new Intent(r.intent),// ②
1531                        System.identityHashCode(r), r.info,
1532                        // TODO: Have this take the merged configuration instead of separate global
1533                        // and override configs.
1534                        mergedConfiguration.getGlobalConfiguration(),
1535                        mergedConfiguration.getOverrideConfiguration(), r.compat,
1536                        r.launchedFromPackage, task.voiceInteractor, app.repProcState, r.icicle,
1537                        r.persistentState, results, newIntents, mService.isNextTransitionForward(),
1538                        profilerInfo));
1539
1540                // Set desired final state.
1541                final ActivityLifecycleItem lifecycleItem;
1542                if (andResume) {
1543                    lifecycleItem = ResumeActivityItem.obtain(mService.isNextTransitionForward());// ③
1544                } else {
1545                    lifecycleItem = PauseActivityItem.obtain();
1546                }
1547                clientTransaction.setLifecycleStateRequest(lifecycleItem); // ④
1548
1549                // Schedule transaction.
1550                mService.getLifecycleManager().scheduleTransaction(clientTransaction); // ⑤

```

先看第一个： clienttransaction -> obtain

```java
137    /** Obtain an instance initialized with provided params. */
138    public static ClientTransaction obtain(IApplicationThread client, IBinder activityToken) {
139        ClientTransaction instance = ObjectPool.obtain(ClientTransaction.class);
140        if (instance == null) {
141            instance = new ClientTransaction();
142        }
143        instance.mClient = client;// 将activitythread保存
144        instance.mActivityToken = activityToken;
145
146        return instance;
147    }
1
```

再看步骤二 clienttransaction -> addCallback

```java
68    public void addCallback(ClientTransactionItem activityCallback) {
69        if (mActivityCallbacks == null) {
70            mActivityCallbacks = new ArrayList<>();
71        }
72        mActivityCallbacks.add(activityCallback);//保存在arraylist中
73    }
```

再看步骤三 

步骤三种的resumeitem还有launchitem都是继承自 activitylifecycleitem，这个我们之后再说

先看步骤四: clienttransaction -> setLifecycleStateRequest

```java
97    public void setLifecycleStateRequest(ActivityLifecycleItem stateRequest) {
98        mLifecycleStateRequest = stateRequest;
99    }
```

将request保存在变量中。

再看步骤五：lifecyclemanager  -> scheduleTransaction

```java
45    void scheduleTransaction(ClientTransaction transaction) throws RemoteException {
46        final IApplicationThread client = transaction.getClient();
47        transaction.schedule();
48        if (!(client instanceof Binder)) {
49            // If client is not an instance of Binder - it's a remote call and at this point it is
50            // safe to recycle the object. All objects used for local calls will be recycled after
51            // the transaction is executed on client in ActivityThread.
52            transaction.recycle();
53        }
54    }
```

getclient获取到的就是保存到的appthread。调用transaction.schedule

clienttransaction -> schedule

```java
128    public void schedule() throws RemoteException {
129        mClient.scheduleTransaction(this);
130    }
```

调用的就是appthread的scheduletransaction方法

因为Android P 的activitythread继承了clienttransactionhandler -> scheduleTransaction

```java
43    void scheduleTransaction(ClientTransaction transaction) {
44        transaction.preExecute(this);
45        sendMessage(ActivityThread.H.EXECUTE_TRANSACTION, transaction);
46    }
```

首先是preexecute

```java
106    public void preExecute(android.app.ClientTransactionHandler clientTransactionHandler) {
107        if (mActivityCallbacks != null) {
108            final int size = mActivityCallbacks.size();
109            for (int i = 0; i < size; ++i) {
110                mActivityCallbacks.get(i).preExecute(clientTransactionHandler, mActivityToken);//获取到callback，就是我们之前存进去的
111            }
112        }
113        if (mLifecycleStateRequest != null) {
114            mLifecycleStateRequest.preExecute(clientTransactionHandler, mActivityToken);
115        }
116    }
```

我们可以看到之前保存的launchactivityitem

```java
65    public void preExecute(ClientTransactionHandler client, IBinder token) {
66        client.updateProcessState(mProcState, false);
67        client.updatePendingConfiguration(mCurConfig);
68    }
```

就是调用了activitythread的updateProcessState 和 updatePendingConfiguration

后面就是activitythread和applicationthread的相关方法

回到pre的地方，查看scheduleTransaction方法中的sendMessage(ActivityThread.H.EXECUTE_TRANSACTION, transaction);

sendmessage就在调用了mH的handler

在O的版本中就是依靠Activitythread中的H handler进行通信的。现在也是到了这里。

```java
public void handleMessage(Message msg) {
1646            if (DEBUG_MESSAGES) Slog.v(TAG, ">>> handling: " + codeToString(msg.what));
1647            switch (msg.what) {
。。。
1807                case EXECUTE_TRANSACTION:
1808                    final ClientTransaction transaction = (ClientTransaction) msg.obj;
1809                    mTransactionExecutor.execute(transaction);
1810                    if (isSystem()) {
1811                        // Client transactions inside system process are recycled on the client side
1812                        // instead of ClientLifecycleManager to avoid being cleared before this
1813                        // message is handled.
1814                        transaction.recycle();
1815                    }
1816                    // TODO(lifecycler): Recycle locally scheduled transactions.
1817                    break;
。。。
1821            }
1822            Object obj = msg.obj;
1823            if (obj instanceof SomeArgs) {
1824                ((SomeArgs) obj).recycle();
1825            }
1826            if (DEBUG_MESSAGES) Slog.v(TAG, "<<< done: " + codeToString(msg.what));
1827        }
1828    }
```

查看 transactionexecutor -> execute

```java
64    public void execute(ClientTransaction transaction) {
65        final IBinder token = transaction.getActivityToken();
66        log("Start resolving transaction for client: " + mTransactionHandler + ", token: " + token);
67
68        executeCallbacks(transaction);
69
70        executeLifecycleState(transaction);
71        mPendingActions.clear();
72        log("End resolving transaction");
73    }
```

```java
77    public void executeCallbacks(ClientTransaction transaction) {
78        final List<ClientTransactionItem> callbacks = transaction.getCallbacks();
79        if (callbacks == null) {
80            // No callbacks to execute, return early.
81            return;
82        }
83        log("Resolving callbacks");
84
85        final IBinder token = transaction.getActivityToken();
86        ActivityClientRecord r = mTransactionHandler.getActivityClient(token);
87
88        // In case when post-execution state of the last callback matches the final state requested
89        // for the activity in this transaction, we won't do the last transition here and do it when
90        // moving to final state instead (because it may contain additional parameters from server).
91        final ActivityLifecycleItem finalStateRequest = transaction.getLifecycleStateRequest();
92        final int finalState = finalStateRequest != null ? finalStateRequest.getTargetState()
93                : UNDEFINED;
94        // Index of the last callback that requests some post-execution state.
95        final int lastCallbackRequestingState = lastCallbackRequestingState(transaction);
96
97        final int size = callbacks.size();
98        for (int i = 0; i < size; ++i) {
99            final ClientTransactionItem item = callbacks.get(i);
100            log("Resolving callback: " + item);
101            final int postExecutionState = item.getPostExecutionState();
102            final int closestPreExecutionState = mHelper.getClosestPreExecutionState(r,
103                    item.getPostExecutionState());
104            if (closestPreExecutionState != UNDEFINED) {
105                cycleToPath(r, closestPreExecutionState);
106            }
107
108            item.execute(mTransactionHandler, token, mPendingActions);
109            item.postExecute(mTransactionHandler, token, mPendingActions);
110            if (r == null) {
111                // Launch activity request will create an activity record.
112                r = mTransactionHandler.getActivityClient(token);
113            }
114
115            if (postExecutionState != UNDEFINED && r != null) {
116                // Skip the very last transition and perform it by explicit state request instead.
117                final boolean shouldExcludeLastTransition =
118                        i == lastCallbackRequestingState && finalState == postExecutionState;
119                cycleToPath(r, postExecutionState, shouldExcludeLastTransition);
120            }
121        }
122    }
```

此时就拿出了第一个launchitem的callback

launchactivityitem-> execute

```java
71    public void execute(ClientTransactionHandler client, IBinder token,
72            PendingTransactionActions pendingActions) {
73        Trace.traceBegin(TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
74        ActivityClientRecord r = new ActivityClientRecord(token, mIntent, mIdent, mInfo,
75                mOverrideConfig, mCompatInfo, mReferrer, mVoiceInteractor, mState, mPersistentState,
76                mPendingResults, mPendingNewIntents, mIsForward,
77                mProfilerInfo, client);
78        client.handleLaunchActivity(r, pendingActions, null /* customIntent */);
79        Trace.traceEnd(TRACE_TAG_ACTIVITY_MANAGER);
80    }
```

activitythread -> handlelaunchactivity

```java
3025    public Activity handleLaunchActivity(ActivityClientRecord r,
3026            PendingTransactionActions pendingActions, Intent customIntent) {
3027        // If we are getting ready to gc after going to the background, well
3028        // we are back active so skip it.
3029        unscheduleGcIdler();
3030        mSomeActivitiesChanged = true;
3031
3032        if (r.profilerInfo != null) {
3033            mProfiler.setProfiler(r.profilerInfo);
3034            mProfiler.startProfiling();
3035        }
3036
3037        // Make sure we are running with the most recent config.
3038        handleConfigurationChanged(null, null);
3039
3040        if (localLOGV) Slog.v(
3041            TAG, "Handling launch of " + r);
3042
3043        // Initialize before creating the activity
3044        if (!ThreadedRenderer.sRendererDisabled) {
3045            GraphicsEnvironment.earlyInitEGL();
3046        }
3047        WindowManagerGlobal.initialize();
3048
3049        final Activity a = performLaunchActivity(r, customIntent);//关键步骤
3050
3051        if (a != null) {
3052            r.createdConfig = new Configuration(mConfiguration);
3053            reportSizeConfigurations(r);
3054            if (!r.activity.mFinished && pendingActions != null) {
3055                pendingActions.setOldState(r.state);
3056                pendingActions.setRestoreInstanceState(true);
3057                pendingActions.setCallOnPostCreate(true);
3058            }
3059        } else {
3060            // If there was an error, for any reason, tell the activity manager to stop us.
3061            try {
3062                ActivityManager.getService()
3063                        .finishActivity(r.token, Activity.RESULT_CANCELED, null,
3064                                Activity.DONT_FINISH_TASK_WITH_ACTIVITY);
3065            } catch (RemoteException ex) {
3066                throw ex.rethrowFromSystemServer();
3067            }
3068        }
3069
3070        return a;
3071    }
```

至于performlaunchactivity就不说了.

但是别忘记了，我们在添加了launch之后，还添加一个resume作为lifecycle的状态

transactionexecutor -> executeLifecycleState

```java
125    private void executeLifecycleState(ClientTransaction transaction) {
126        final ActivityLifecycleItem lifecycleItem = transaction.getLifecycleStateRequest();
127        if (lifecycleItem == null) {
128            // No lifecycle request, return early.
129            return;
130        }
131        log("Resolving lifecycle state: " + lifecycleItem);
132
133        final IBinder token = transaction.getActivityToken();
134        final ActivityClientRecord r = mTransactionHandler.getActivityClient(token);
135
136        if (r == null) {
137            // Ignore requests for non-existent client records for now.
138            return;
139        }
140
141        // Cycle to the state right before the final requested state.
142        cycleToPath(r, lifecycleItem.getTargetState(), true /* excludeLastState */);
143
144        // Execute the final transition with proper parameters.
145        lifecycleItem.execute(mTransactionHandler, token, mPendingActions);
146        lifecycleItem.postExecute(mTransactionHandler, token, mPendingActions);
147    }
```

这里就是相当于拿出了当时的resumeitem进行execute。






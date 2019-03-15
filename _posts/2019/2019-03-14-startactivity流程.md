---
layout: post
title: startactivity流程
---

#### 1.activity.startactivity

```java
4872    public void startActivity(Intent intent) {
4873        this.startActivity(intent, null);
4874    }
4899    public void startActivity(Intent intent, @Nullable Bundle options) {
4900        if (options != null) {
4901            startActivityForResult(intent, -1, options);
4902        } else {
4903            // Note we want to go through this call for compatibility with
4904            // applications that may have overridden the method.
4905            startActivityForResult(intent, -1);
4906        }
4907    }
4543    public void startActivityForResult(@RequiresPermission Intent intent, int requestCode) {
4544        startActivityForResult(intent, requestCode, null);
4545    }
4581    public void startActivityForResult(@RequiresPermission Intent intent, int requestCode,
4582            @Nullable Bundle options) {
4583        if (mParent == null) {
4584            options = transferSpringboardActivityOptions(options);
4585            Instrumentation.ActivityResult ar =
4586                mInstrumentation.execStartActivity(
4587                    this, mMainThread.getApplicationThread(), mToken, this,
4588                    intent, requestCode, options);
4589            if (ar != null) {
4590                mMainThread.sendActivityResult(
4591                    mToken, mEmbeddedID, requestCode, ar.getResultCode(),
4592                    ar.getResultData());
4593            }
4594            if (requestCode >= 0) {
4595                // If this start is requesting a result, we can avoid making
4596                // the activity visible until the result is received.  Setting
4597                // this code during onCreate(Bundle savedInstanceState) or onResume() will keep the
4598                // activity hidden during this time, to avoid flickering.
4599                // This can only be done when a result is requested because
4600                // that guarantees we will get information back when the
4601                // activity is finished, no matter what happens to it.
4602                mStartedActivity = true;
4603            }
4604
4605            cancelInputsAndStartExitTransition(options);
4606            // TODO Consider clearing/flushing other event sources and events for child windows.
4607        } else {
4608            if (options != null) {
4609                mParent.startActivityFromChild(this, intent, requestCode, options);
4610            } else {
4611                // Note we want to go through this method for compatibility with
4612                // existing applications that may have overridden it.
4613                mParent.startActivityFromChild(this, intent, requestCode);
4614            }
4615        }
4616    }
```

####2.instrumentation.java -> execstartactivity

```java
1636    public ActivityResult execStartActivity(
1637            Context who, IBinder contextThread, IBinder token, Activity target,
1638            Intent intent, int requestCode, Bundle options) {
1639        android.util.SeempLog.record_str(377, intent.toString());
1640        IApplicationThread whoThread = (IApplicationThread) contextThread;
1641        Uri referrer = target != null ? target.onProvideReferrer() : null;
1642        if (referrer != null) {
1643            intent.putExtra(Intent.EXTRA_REFERRER, referrer);
1644        }
1645        if (mActivityMonitors != null) {
1646            synchronized (mSync) {
1647                final int N = mActivityMonitors.size();
1648                for (int i=0; i<N; i++) {
1649                    final ActivityMonitor am = mActivityMonitors.get(i);
1650                    ActivityResult result = null;
1651                    if (am.ignoreMatchingSpecificIntents()) {
1652                        result = am.onStartActivity(intent);
1653                    }
1654                    if (result != null) {
1655                        am.mHits++;
1656                        return result;
1657                    } else if (am.match(who, null, intent)) {
1658                        am.mHits++;
1659                        if (am.isBlocking()) {
1660                            return requestCode >= 0 ? am.getResult() : null;
1661                        }
1662                        break;
1663                    }
1664                }
1665            }
1666        }
1667        try {
1668            intent.migrateExtraStreamToClipData();
1669            intent.prepareToLeaveProcess(who);
1670            int result = ActivityManager.getService()
1671                .startActivity(whoThread, who.getBasePackageName(), intent,
1672                        intent.resolveTypeIfNeeded(who.getContentResolver()),
1673                        token, target != null ? target.mEmbeddedID : null,
1674                        requestCode, 0, null, options);
1675            checkStartActivityResult(result, intent);
1676        } catch (RemoteException e) {
1677            throw new RuntimeException("Failure from system", e);
1678        }
1679        return null;
1680    }
```

然后这里就和startservice相同，采用了aidl的方式来binder调用到systemserver端

#### 3.ams.startactivity-> am.startactivityasuser->am.startactivityasuser

```java
5194    public final int startActivityAsUser(IApplicationThread caller, String callingPackage,
5195            Intent intent, String resolvedType, IBinder resultTo, String resultWho, int requestCode,
5196            int startFlags, ProfilerInfo profilerInfo, Bundle bOptions, int userId,
5197            boolean validateIncomingUser) {
5198        enforceNotIsolatedCaller("startActivity");
5199
5200        userId = mActivityStartController.checkTargetUser(userId, validateIncomingUser,
5201                Binder.getCallingPid(), Binder.getCallingUid(), "startActivityAsUser");
5202
5203        // TODO: Switch to user app stacks here.
5204        return mActivityStartController.obtainStarter(intent, "startActivityAsUser")
5205                .setCaller(caller)
5206                .setCallingPackage(callingPackage)
5207                .setResolvedType(resolvedType)
5208                .setResultTo(resultTo)
5209                .setResultWho(resultWho)
5210                .setRequestCode(requestCode)
5211                .setStartFlags(startFlags)
5212                .setProfilerInfo(profilerInfo)
5213                .setActivityOptions(bOptions)
5214                .setMayWait(userId)
5215                .execute();
5216
5217    }
```

p之后采用了工厂模式，首先取出一个starter，然后将配置starter的相关属性，然后再进行start

#### 4.activitystarter -> execute

```java
485    int execute() {
486        try {
487            // TODO(b/64750076): Look into passing request directly to these methods to allow
488            // for transactional diffs and preprocessing.
489            if (mRequest.mayWait) {
490                return startActivityMayWait(mRequest.caller, mRequest.callingUid,
491                        mRequest.callingPackage, mRequest.intent, mRequest.resolvedType,
492                        mRequest.voiceSession, mRequest.voiceInteractor, mRequest.resultTo,
493                        mRequest.resultWho, mRequest.requestCode, mRequest.startFlags,
494                        mRequest.profilerInfo, mRequest.waitResult, mRequest.globalConfig,
495                        mRequest.activityOptions, mRequest.ignoreTargetSecurity, mRequest.userId,
496                        mRequest.inTask, mRequest.reason,
497                        mRequest.allowPendingRemoteAnimationRegistryLookup);
498            } else {
499                return startActivity(mRequest.caller, mRequest.intent, mRequest.ephemeralIntent,
500                        mRequest.resolvedType, mRequest.activityInfo, mRequest.resolveInfo,
501                        mRequest.voiceSession, mRequest.voiceInteractor, mRequest.resultTo,
502                        mRequest.resultWho, mRequest.requestCode, mRequest.callingPid,
503                        mRequest.callingUid, mRequest.callingPackage, mRequest.realCallingPid,
504                        mRequest.realCallingUid, mRequest.startFlags, mRequest.activityOptions,
505                        mRequest.ignoreTargetSecurity, mRequest.componentSpecified,
506                        mRequest.outActivity, mRequest.inTask, mRequest.reason,
507                        mRequest.allowPendingRemoteAnimationRegistryLookup);
508            }
509        } finally {
510            onExecutionComplete();
511        }
512    }
```

#### ####5.activitystarter.startactivitymaywait

```java
949    private int startActivityMayWait(IApplicationThread caller, int callingUid,
950            String callingPackage, Intent intent, String resolvedType,
951            IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor,
952            IBinder resultTo, String resultWho, int requestCode, int startFlags,
953            ProfilerInfo profilerInfo, WaitResult outResult,
954            Configuration globalConfig, SafeActivityOptions options, boolean ignoreTargetSecurity,
955            int userId, TaskRecord inTask, String reason,
956            boolean allowPendingRemoteAnimationRegistryLookup) {
    ...
994        ResolveInfo rInfo = mSupervisor.resolveIntent(intent, resolvedType, userId,
995                0 /* matchFlags */,
996                        computeResolveFilterUid(
997                                callingUid, realCallingUid, mRequest.filterCallingUid));
    ...
1101
1102            final ActivityRecord[] outRecord = new ActivityRecord[1];
1103            int res = startActivity(caller, intent, ephemeralIntent, resolvedType, aInfo, rInfo,
1104                    voiceSession, voiceInteractor, resultTo, resultWho, requestCode, callingPid,
1105                    callingUid, callingPackage, realCallingPid, realCallingUid, startFlags, options,
1106                    ignoreTargetSecurity, componentSpecified, outRecord, inTask, reason,
1107                    allowPendingRemoteAnimationRegistryLookup);
    ...
1178    }
```

####6.activitystart.startactivity

```java
532    private int startActivity(IApplicationThread caller, Intent intent, Intent ephemeralIntent,
533            String resolvedType, ActivityInfo aInfo, ResolveInfo rInfo,
534            IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor,
535            IBinder resultTo, String resultWho, int requestCode, int callingPid, int callingUid,
536            String callingPackage, int realCallingPid, int realCallingUid, int startFlags,
537            SafeActivityOptions options, boolean ignoreTargetSecurity, boolean componentSpecified,
538            ActivityRecord[] outActivity, TaskRecord inTask, String reason,
539            boolean allowPendingRemoteAnimationRegistryLookup) {
540
541        if (TextUtils.isEmpty(reason)) {
542            throw new IllegalArgumentException("Need to specify a reason.");
543        }
544        mLastStartReason = reason;
545        mLastStartActivityTimeMs = System.currentTimeMillis();
546        mLastStartActivityRecord[0] = null;
547	//继续调用内部的startactivity方法
548        mLastStartActivityResult = startActivity(caller, intent, ephemeralIntent, resolvedType,
549                aInfo, rInfo, voiceSession, voiceInteractor, resultTo, resultWho, requestCode,
550                callingPid, callingUid, callingPackage, realCallingPid, realCallingUid, startFlags,
551                options, ignoreTargetSecurity, componentSpecified, mLastStartActivityRecord,
552                inTask, allowPendingRemoteAnimationRegistryLookup);
553
554        if (outActivity != null) {
555            // mLastStartActivityRecord[0] is set in the call to startActivity above.
556            outActivity[0] = mLastStartActivityRecord[0];
557        }
558
559        return getExternalResult(mLastStartActivityResult);
560    }
```

acitvitystart.startactivity

```java
575    private int startActivity(IApplicationThread caller, Intent intent, Intent ephemeralIntent,
576            String resolvedType, ActivityInfo aInfo, ResolveInfo rInfo,
577            IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor,
578            IBinder resultTo, String resultWho, int requestCode, int callingPid, int callingUid,
579            String callingPackage, int realCallingPid, int realCallingUid, int startFlags,
580            SafeActivityOptions options,
581            boolean ignoreTargetSecurity, boolean componentSpecified, ActivityRecord[] outActivity,
582            TaskRecord inTask, boolean allowPendingRemoteAnimationRegistryLookup) {
872        return startActivity(r, sourceRecord, voiceSession, voiceInteractor, startFlags,
873                true /* doResume */, checkedOptions, inTask, outActivity);
874    }
```

####activitystarter.startactivityunchecked

需要注意的是，在这个地方会进行launchmode的判断，

```java
1224    private int startActivityUnchecked(final ActivityRecord r, ActivityRecord sourceRecord,
1225            IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor,
1226            int startFlags, boolean doResume, ActivityOptions options, TaskRecord inTask,
1227            ActivityRecord[] outActivity) {
...//如果有可用的stack，那么就将那个stack放置到前台。
1343            if (reusedActivity != null) {
1344                setTaskFromIntentActivity(reusedActivity);
1345
1346                if (!mAddingToTask && mReuseTask == null) {
1347                    // We didn't do anything...  but it was needed (a.k.a., client don't use that
1348                    // intent!)  And for paranoia, make sure we have correctly resumed the top activity.
1349
1350                    resumeTargetStackIfNeeded();
1351                    if (outActivity != null && outActivity.length > 0) {
1352                        outActivity[0] = reusedActivity;
1353                    }
1354
1355                    return mMovedToFront ? START_TASK_TO_FRONT : START_DELIVERED_TO_TOP;
1356                }
1357            }
1358        }
...
1447//在stack中启动应用
1448        mTargetStack.startActivityLocked(mStartActivity, topFocused, newTask, mKeepCurTransition,
1449                mOptions);
...
1450        if (mDoResume) {
1451            final ActivityRecord topTaskActivity =
1452                    mStartActivity.getTask().topRunningActivityLocked();
1453            if (!mTargetStack.isFocusable()
1454                    || (topTaskActivity != null && topTaskActivity.mTaskOverlay
1455                    && mStartActivity != topTaskActivity)) {
1456                // If the activity is not focusable, we can't resume it, but still would like to
1457                // make sure it becomes visible as it starts (this will also trigger entry
1458                // animation). An example of this are PIP activities.
1459                // Also, we don't want to resume activities in a task that currently has an overlay
1460                // as the starting activity just needs to be in the visible paused state until the
1461                // over is removed.
1462                mTargetStack.ensureActivitiesVisibleLocked(null, 0, !PRESERVE_WINDOWS);
1463                // Go ahead and tell window manager to execute app transition for this activity
1464                // since the app transition will not be triggered through the resume channel.
1465                mService.mWindowManager.executeAppTransition();
1466            } else {
1467                // If the target stack was not previously focusable (previous top running activity
1468                // on that stack was not visible) then any prior calls to move the stack to the
1469                // will not update the focused stack.  If starting the new activity now allows the
1470                // task stack to be focusable, then ensure that we now update the focused stack
1471                // accordingly.
1472                if (mTargetStack.isFocusable() && !mSupervisor.isFocusedStack(mTargetStack)) {
1473                    mTargetStack.moveToFront("startActivityUnchecked");
1474                }//此处需要resumeactivity
1475                mSupervisor.resumeFocusedStackTopActivityLocked(mTargetStack, mStartActivity,
1476                        mOptions);
1477            }
1478        } else if (mStartActivity != null) {
1479            mSupervisor.mRecentTasks.add(mStartActivity.getTask());
1480        }
1481        mSupervisor.updateUserStackLocked(mStartActivity.userId, mTargetStack);
1482
1483        mSupervisor.handleNonResizableTaskIfNeeded(mStartActivity.getTask(), preferredWindowingMode,
1484                preferredLaunchDisplayId, mTargetStack);
1485
1486        return START_SUCCESS;
1487    }
}
```

#### activitystack.startactivitylocked

```java
2915    void startActivityLocked(ActivityRecord r, ActivityRecord focusedTopActivity,
2916            boolean newTask, boolean keepCurTransition, ActivityOptions options) {
2917        TaskRecord rTask = r.getTask();
2918        final int taskId = rTask.taskId;
2919        // mLaunchTaskBehind tasks get placed at the back of the task stack.
2920        if (!r.mLaunchTaskBehind && (taskForIdLocked(taskId) == null || newTask)) {
2921            // Last activity in task had been removed or ActivityManagerService is reusing task.
2922            // Insert or replace.
2923            // Might not even be in.
2924            insertTaskAtTop(rTask, r);
2925        }
2926        TaskRecord task = null;
2927        if (!newTask) {
2928            // If starting in an existing task, find where that is...
2929            boolean startIt = true;
2930            for (int taskNdx = mTaskHistory.size() - 1; taskNdx >= 0; --taskNdx) {
2931                task = mTaskHistory.get(taskNdx);
2932                if (task.getTopActivity() == null) {
2933                    // All activities in task are finishing.
2934                    continue;
2935                }
2936                if (task == rTask) {
2937                    // Here it is!  Now, if this is not yet visible to the
2938                    // user, then just add it without starting; it will
2939                    // get started when the user navigates back to it.
2940                    if (!startIt) {
2941                        if (DEBUG_ADD_REMOVE) Slog.i(TAG, "Adding activity " + r + " to task "
2942                                + task, new RuntimeException("here").fillInStackTrace());
2943                        r.createWindowContainer();
2944                        ActivityOptions.abort(options);
2945                        return;
2946                    }
2947                    break;
2948                } else if (task.numFullscreen > 0) {
2949                    startIt = false;
2950                }
2951            }
2952        }
2953
2954        // Place a new activity at top of stack, so it is next to interact with the user.
2955
2956        // If we are not placing the new activity frontmost, we do not want to deliver the
2957        // onUserLeaving callback to the actual frontmost activity
2958        final TaskRecord activityTask = r.getTask();
2959        if (task == activityTask && mTaskHistory.indexOf(task) != (mTaskHistory.size() - 1)) {
2960            mStackSupervisor.mUserLeaving = false;
2961            if (DEBUG_USER_LEAVING) Slog.v(TAG_USER_LEAVING,
2962                    "startActivity() behind front, mUserLeaving=false");
2963        }
2964
2965        task = activityTask;
2966
2967        // Slot the activity into the history stack and proceed
2968        if (DEBUG_ADD_REMOVE) Slog.i(TAG, "Adding activity " + r + " to stack to task " + task,
2969                new RuntimeException("here").fillInStackTrace());
2970        // TODO: Need to investigate if it is okay for the controller to already be created by the
2971        // time we get to this point. I think it is, but need to double check.
2972        // Use test in b/34179495 to trace the call path.
2973        if (r.getWindowContainerController() == null) {
2974            r.createWindowContainer();
2975        }
2976        task.setFrontOfTask();
2977
2978        if (mActivityTrigger != null) {
2979            mActivityTrigger.activityStartTrigger(r.intent, r.info, r.appInfo, r.fullscreen);
2980        }
2981        if (mActivityPluginDelegate != null) {
2982            mActivityPluginDelegate.activityInvokeNotification
2983                (r.appInfo.packageName, r.fullscreen);
2984        }
2985        if (!isActivityTypeHome() || numActivities() > 0) {
2986            // We want to show the starting preview window if we are
2987            // switching to a new task, or the next activity's process is
2988            // not currently running.
2989            if (DEBUG_TRANSITION) Slog.v(TAG_TRANSITION,
2990                    "Prepare open transition: starting " + r);
2991            if ((r.intent.getFlags() & Intent.FLAG_ACTIVITY_NO_ANIMATION) != 0) {
2992                mWindowManager.prepareAppTransition(TRANSIT_NONE, keepCurTransition);
2993                mStackSupervisor.mNoAnimActivities.add(r);
2994            } else {
2995                int transit = TRANSIT_ACTIVITY_OPEN;
2996                if (newTask) {
2997                    if (r.mLaunchTaskBehind) {
2998                        transit = TRANSIT_TASK_OPEN_BEHIND;
2999                    } else {
3000                        // If a new task is being launched, then mark the existing top activity as
3001                        // supporting picture-in-picture while pausing only if the starting activity
3002                        // would not be considered an overlay on top of the current activity
3003                        // (eg. not fullscreen, or the assistant)
3004                        if (canEnterPipOnTaskSwitch(focusedTopActivity,
3005                                null /* toFrontTask */, r, options)) {
3006                            focusedTopActivity.supportsEnterPipOnTaskSwitch = true;
3007                        }
3008                        transit = TRANSIT_TASK_OPEN;
3009                    }
3010                }
3011                mWindowManager.prepareAppTransition(transit, keepCurTransition);
3012                mStackSupervisor.mNoAnimActivities.remove(r);
3013            }
3014            boolean doShow = true;
3015            if (newTask) {
3016                // Even though this activity is starting fresh, we still need
3017                // to reset it to make sure we apply affinities to move any
3018                // existing activities from other tasks in to it.
3019                // If the caller has requested that the target task be
3020                // reset, then do so.
3021                if ((r.intent.getFlags() & Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED) != 0) {
3022                    resetTaskIfNeededLocked(r, r);
3023                    doShow = topRunningNonDelayedActivityLocked(null) == r;
3024                }
3025            } else if (options != null && options.getAnimationType()
3026                    == ActivityOptions.ANIM_SCENE_TRANSITION) {
3027                doShow = false;
3028            }
3029            if (r.mLaunchTaskBehind) {
3030                // Don't do a starting window for mLaunchTaskBehind. More importantly make sure we
3031                // tell WindowManager that r is visible even though it is at the back of the stack.
3032                r.setVisibility(true);
3033                ensureActivitiesVisibleLocked(null, 0, !PRESERVE_WINDOWS);
3034            } else if (SHOW_APP_STARTING_PREVIEW && doShow) {
3035                // Figure out if we are transitioning from another activity that is
3036                // "has the same starting icon" as the next one.  This allows the
3037                // window manager to keep the previous window it had previously
3038                // created, if it still had one.
3039                TaskRecord prevTask = r.getTask();
3040                ActivityRecord prev = prevTask.topRunningActivityWithStartingWindowLocked();
3041                if (prev != null) {
3042                    // We don't want to reuse the previous starting preview if:
3043                    // (1) The current activity is in a different task.
3044                    if (prev.getTask() != prevTask) {
3045                        prev = null;
3046                    }
3047                    // (2) The current activity is already displayed.
3048                    else if (prev.nowVisible) {
3049                        prev = null;
3050                    }
3051                }
    //注意此处的showstartingwindow，这个地方是我们经常用来性能提升的地方
3052                r.showStartingWindow(prev, newTask, isTaskSwitch(r, focusedTopActivity));
3053            }
3054        } else {
3055            // If this is the first activity, don't do any fancy animations,
3056            // because there is nothing for it to animate on top of.
3057            ActivityOptions.abort(options);
3058        }
3059    }
```

接着我们可以看resumeactivity

#### activitystacksupervisor -> resumefocusedstacktopactivitylocked

```java
2226    boolean resumeFocusedStackTopActivityLocked(
2227            ActivityStack targetStack, ActivityRecord target, ActivityOptions targetOptions) {
2228
2229        if (!readyToResume()) {
2230            return false;
2231        }
2232//将目标stack的栈顶activity进行resume
2233        if (targetStack != null && isFocusedStack(targetStack)) {
2234            return targetStack.resumeTopActivityUncheckedLocked(target, targetOptions);
2235        }
2236
2237        final ActivityRecord r = mFocusedStack.topRunningActivityLocked();
2238        if (r == null || !r.isState(RESUMED)) {
2239            mFocusedStack.resumeTopActivityUncheckedLocked(null, null);
2240        } else if (r.isState(RESUMED)) {
2241            // Kick off any lingering app transitions form the MoveTaskToFront operation.
2242            mFocusedStack.executeAppTransition(targetOptions);
2243        }
2244
2245        return false;
2246    }
```

#### activitystack.resumetopactivityuncheckedlocked

```java
2302    boolean resumeTopActivityUncheckedLocked(ActivityRecord prev, ActivityOptions options) {
2303        if (mStackSupervisor.inResumeTopActivity) {
2304            // Don't even start recursing.
2305            return false;
2306        }
2307
2308        boolean result = false;
2309        try {
2310            // Protect against recursion.
2311            mStackSupervisor.inResumeTopActivity = true;
2312            result = resumeTopActivityInnerLocked(prev, options);//真正resume的地方
2313

2321            final ActivityRecord next = topRunningActivityLocked(true /* focusableOnly */);
2322            if (next == null || !next.canTurnScreenOn()) {
2323                checkReadyForSleep();
2324            }
2325        } finally {
2326            mStackSupervisor.inResumeTopActivity = false;
2327        }
2328
2329        return result;
2330    }
```

activitystack -> resumetopactivityinnerlocked

```java
2350    private boolean resumeTopActivityInnerLocked(ActivityRecord prev, ActivityOptions options) {
2351        if (!mService.mBooting && !mService.mBooted) {
2352            // Not ready yet!
2353            return false;
2354        }
2355
2356        // Find the next top-most activity to resume in this stack that is not finishing and is
2357        // focusable. If it is not focusable, we will fall into the case below to resume the
2358        // top activity in the next focusable task.
2359        final ActivityRecord next = topRunningActivityLocked(true /* focusableOnly */);
2360
2361        final boolean hasRunningActivity = next != null;
2362
2363        // TODO: Maybe this entire condition can get removed?
2364        if (hasRunningActivity && !isAttached()) {
2365            return false;
2366        }
2367
2368        mStackSupervisor.cancelInitializingActivities();
2369
2370        // Remember how we'll process this pause/resume situation, and ensure
2371        // that the state is reset however we wind up proceeding.
2372        boolean userLeaving = mStackSupervisor.mUserLeaving;
2373        mStackSupervisor.mUserLeaving = false;
2374
2375        if (!hasRunningActivity) {
2376            // There are no activities left in the stack, let's look somewhere else.
2377            return resumeTopActivityInNextFocusableStack(prev, options, "noMoreActivities");
2378        }
2379
2380        next.delayedResume = false;
2381
2382        // If the top activity is the resumed one, nothing to do.
2383        if (mResumedActivity == next && next.isState(RESUMED)
2384                && mStackSupervisor.allResumedActivitiesComplete()) {
2385            // Make sure we have executed any pending transitions, since there
2386            // should be nothing left to do at this point.
2387            executeAppTransition(options);
2388            if (DEBUG_STATES) Slog.d(TAG_STATES,
2389                    "resumeTopActivityLocked: Top activity resumed " + next);
2390            if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2391            return false;
2392        }
2393
2394        // If we are sleeping, and there is no resumed activity, and the top
2395        // activity is paused, well that is the state we want.
2396        if (shouldSleepOrShutDownActivities()
2397                && mLastPausedActivity == next
2398                && mStackSupervisor.allPausedActivitiesComplete()) {
2399            // Make sure we have executed any pending transitions, since there
2400            // should be nothing left to do at this point.
2401            executeAppTransition(options);
2402            if (DEBUG_STATES) Slog.d(TAG_STATES,
2403                    "resumeTopActivityLocked: Going to sleep and all paused");
2404            if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2405            return false;
2406        }
2407
2408        // Make sure that the user who owns this activity is started.  If not,
2409        // we will just leave it as is because someone should be bringing
2410        // another user's activities to the top of the stack.
2411        if (!mService.mUserController.hasStartedUserState(next.userId)) {
2412            Slog.w(TAG, "Skipping resume of top activity " + next
2413                    + ": user " + next.userId + " is stopped");
2414            if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2415            return false;
2416        }
2417
2418        // The activity may be waiting for stop, but that is no longer
2419        // appropriate for it.
2420        mStackSupervisor.mStoppingActivities.remove(next);
2421        mStackSupervisor.mGoingToSleepActivities.remove(next);
2422        next.sleeping = false;
2423        mStackSupervisor.mActivitiesWaitingForVisibleActivity.remove(next);
2424        next.launching = true;
2425
2426        if (DEBUG_SWITCH) Slog.v(TAG_SWITCH, "Resuming " + next);
2427
2428        if (mActivityTrigger != null) {
2429            mActivityTrigger.activityResumeTrigger(next.intent, next.info, next.appInfo,
2430                    next.fullscreen);
2431        }
2432
2433        if (mActivityPluginDelegate != null) {
2434            mActivityPluginDelegate.activityInvokeNotification
2435                (next.appInfo.packageName, next.fullscreen);
2436        }
2437        // If we are currently pausing an activity, then don't do anything
2438        // until that is done.
2439        if (!mStackSupervisor.allPausedActivitiesComplete()) {
2440            if (DEBUG_SWITCH || DEBUG_PAUSE || DEBUG_STATES) Slog.v(TAG_PAUSE,
2441                    "resumeTopActivityLocked: Skip resume: some activity pausing.");
2442            if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2443            return false;
2444        }
2445
2446        mStackSupervisor.setLaunchSource(next.info.applicationInfo.uid);
2447
2448        // MIUI ADD: START
2449        // Resume boost.
2450        if (mPerf == null) {
2451            mPerf = new BoostFramework();
2452        } else {
2453            ProcessRecord app = mService.getProcessRecordLocked(next.processName,
2454                next.info.applicationInfo.uid, true);
2455            if (app != null && app.thread != null) {
2456                mPerf.perfHint(BoostFramework.VENDOR_HINT_SUBSEQ_LAUNCH_BOOST, next.packageName,
2457                   -1, BoostFramework.Launch.BOOST_V1);
2458            }
2459        }
2460        // END
2461
2462        boolean lastResumedCanPip = false;
2463        ActivityRecord lastResumed = null;
2464        final ActivityStack lastFocusedStack = mStackSupervisor.getLastStack();
2465        if (lastFocusedStack != null && lastFocusedStack != this) {
2466            // So, why aren't we using prev here??? See the param comment on the method. prev doesn't
2467            // represent the last resumed activity. However, the last focus stack does if it isn't null.
2468            lastResumed = lastFocusedStack.mResumedActivity;
2469            if (userLeaving && inMultiWindowMode() && lastFocusedStack.shouldBeVisible(next)) {
2470                // The user isn't leaving if this stack is the multi-window mode and the last
2471                // focused stack should still be visible.
2472                if(DEBUG_USER_LEAVING) Slog.i(TAG_USER_LEAVING, "Overriding userLeaving to false"
2473                        + " next=" + next + " lastResumed=" + lastResumed);
2474                userLeaving = false;
2475            }
2476            lastResumedCanPip = lastResumed != null && lastResumed.checkEnterPictureInPictureState(
2477                    "resumeTopActivity", userLeaving /* beforeStopping */);
2478        }
2479        // If the flag RESUME_WHILE_PAUSING is set, then continue to schedule the previous activity
2480        // to be paused, while at the same time resuming the new resume activity only if the
2481        // previous activity can't go into Pip since we want to give Pip activities a chance to
2482        // enter Pip before resuming the next activity.
2483        final boolean resumeWhilePausing = (next.info.flags & FLAG_RESUME_WHILE_PAUSING) != 0
2484                && !lastResumedCanPip;
2485
2486        boolean pausing = mStackSupervisor.pauseBackStacks(userLeaving, next, false);
2487        if (mResumedActivity != null) {
2488            if (DEBUG_STATES) Slog.d(TAG_STATES,
2489                    "resumeTopActivityLocked: Pausing " + mResumedActivity);
    //注意此处是pause前一个activity
2490            pausing |= startPausingLocked(userLeaving, false, next, false);
2491        }
2492        if (pausing && !resumeWhilePausing) {
2493            if (DEBUG_SWITCH || DEBUG_STATES) Slog.v(TAG_STATES,
2494                    "resumeTopActivityLocked: Skip resume: need to start pausing");
2495            // At this point we want to put the upcoming activity's process
2496            // at the top of the LRU list, since we know we will be needing it
2497            // very soon and it would be a waste to let it get killed if it
2498            // happens to be sitting towards the end.
2499            if (next.app != null && next.app.thread != null) {
2500                mService.updateLruProcessLocked(next.app, true, null);
2501            }
2502            if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2503            if (lastResumed != null) {
2504                lastResumed.setWillCloseOrEnterPip(true);
2505            }
2506            return true;
2507        } else if (mResumedActivity == next && next.isState(RESUMED)
2508                && mStackSupervisor.allResumedActivitiesComplete()) {
2509            // It is possible for the activity to be resumed when we paused back stacks above if the
2510            // next activity doesn't have to wait for pause to complete.
2511            // So, nothing else to-do except:
2512            // Make sure we have executed any pending transitions, since there
2513            // should be nothing left to do at this point.
2514            executeAppTransition(options);
2515            if (DEBUG_STATES) Slog.d(TAG_STATES,
2516                    "resumeTopActivityLocked: Top activity resumed (dontWaitForPause) " + next);
2517            if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2518            return true;
2519        }
2520
2521        // If the most recent activity was noHistory but was only stopped rather
2522        // than stopped+finished because the device went to sleep, we need to make
2523        // sure to finish it as we're making a new activity topmost.
2524        if (shouldSleepActivities() && mLastNoHistoryActivity != null &&
2525                !mLastNoHistoryActivity.finishing) {
2526            if (DEBUG_STATES) Slog.d(TAG_STATES,
2527                    "no-history finish of " + mLastNoHistoryActivity + " on new resume");
2528            requestFinishActivityLocked(mLastNoHistoryActivity.appToken, Activity.RESULT_CANCELED,
2529                    null, "resume-no-history", false);
2530            mLastNoHistoryActivity = null;
2531        }
2532
2533        if (prev != null && prev != next) {
2534            if (!mStackSupervisor.mActivitiesWaitingForVisibleActivity.contains(prev)
2535                    && next != null && !next.nowVisible) {
2536                mStackSupervisor.mActivitiesWaitingForVisibleActivity.add(prev);
2537                if (DEBUG_SWITCH) Slog.v(TAG_SWITCH,
2538                        "Resuming top, waiting visible to hide: " + prev);
2539            } else {
2540                // The next activity is already visible, so hide the previous
2541                // activity's windows right now so we can show the new one ASAP.
2542                // We only do this if the previous is finishing, which should mean
2543                // it is on top of the one being resumed so hiding it quickly
2544                // is good.  Otherwise, we want to do the normal route of allowing
2545                // the resumed activity to be shown so we can decide if the
2546                // previous should actually be hidden depending on whether the
2547                // new one is found to be full-screen or not.
2548                if (prev.finishing) {
2549                    prev.setVisibility(false);
2550                    if (DEBUG_SWITCH) Slog.v(TAG_SWITCH,
2551                            "Not waiting for visible to hide: " + prev + ", waitingVisible="
2552                            + mStackSupervisor.mActivitiesWaitingForVisibleActivity.contains(prev)
2553                            + ", nowVisible=" + next.nowVisible);
2554                } else {
2555                    if (DEBUG_SWITCH) Slog.v(TAG_SWITCH,
2556                            "Previous already visible but still waiting to hide: " + prev
2557                            + ", waitingVisible="
2558                            + mStackSupervisor.mActivitiesWaitingForVisibleActivity.contains(prev)
2559                            + ", nowVisible=" + next.nowVisible);
2560                }
2561            }
2562        }
2563
2564        // Launching this app's activity, make sure the app is no longer
2565        // considered stopped.
2566        try {
2567            AppGlobals.getPackageManager().setPackageStoppedState(
2568                    next.packageName, false, next.userId); /* TODO: Verify if correct userid */
2569        } catch (RemoteException e1) {
2570        } catch (IllegalArgumentException e) {
2571            Slog.w(TAG, "Failed trying to unstop package "
2572                    + next.packageName + ": " + e);
2573        }
2574
2575        // We are starting up the next activity, so tell the window manager
2576        // that the previous one will be hidden soon.  This way it can know
2577        // to ignore it when computing the desired screen orientation.
2578        boolean anim = true;
2579        if (mPerf == null) {
2580            mPerf = new BoostFramework();
2581        }
2582        if (prev != null) {
2583            if (prev.finishing) {
2584                if (DEBUG_TRANSITION) Slog.v(TAG_TRANSITION,
2585                        "Prepare close transition: prev=" + prev);
2586                if (mStackSupervisor.mNoAnimActivities.contains(prev)) {
2587                    anim = false;
2588                    mWindowManager.prepareAppTransition(TRANSIT_NONE, false);
2589                } else {
2590                    mWindowManager.prepareAppTransition(prev.getTask() == next.getTask()
2591                            ? TRANSIT_ACTIVITY_CLOSE
2592                            : TRANSIT_TASK_CLOSE, false);
2593                    if(prev.getTask() != next.getTask() && mPerf != null) {
2594                       mPerf.perfHint(BoostFramework.VENDOR_HINT_ANIM_BOOST,
2595                               next.packageName, -1, 1);
2596                    }
2597                }
2598                prev.setVisibility(false);
2599            } else {
2600                if (DEBUG_TRANSITION) Slog.v(TAG_TRANSITION,
2601                        "Prepare open transition: prev=" + prev);
2602                if (mStackSupervisor.mNoAnimActivities.contains(next)) {
2603                    anim = false;
2604                    mWindowManager.prepareAppTransition(TRANSIT_NONE, false);
2605                } else {
2606                    mWindowManager.prepareAppTransition(prev.getTask() == next.getTask()
2607                            ? TRANSIT_ACTIVITY_OPEN
2608                            : next.mLaunchTaskBehind
2609                                    ? TRANSIT_TASK_OPEN_BEHIND
2610                                    : TRANSIT_TASK_OPEN, false);
2611                    if(prev.getTask() != next.getTask() && mPerf != null) {
2612                       mPerf.perfHint(BoostFramework.VENDOR_HINT_ANIM_BOOST,
2613                               next.packageName, -1, 1);
2614                    }
2615                }
2616            }
2617        } else {
2618            if (DEBUG_TRANSITION) Slog.v(TAG_TRANSITION, "Prepare open transition: no previous");
2619            if (mStackSupervisor.mNoAnimActivities.contains(next)) {
2620                anim = false;
2621                mWindowManager.prepareAppTransition(TRANSIT_NONE, false);
2622            } else {
2623                mWindowManager.prepareAppTransition(TRANSIT_ACTIVITY_OPEN, false);
2624            }
2625        }
2626
2627        if (anim) {
2628            next.applyOptionsLocked();
2629        } else {
2630            next.clearOptionsLocked();
2631        }
2632
2633        mStackSupervisor.mNoAnimActivities.clear();
2634
2635        ActivityStack lastStack = mStackSupervisor.getLastStack();
2636        if (next.app != null && next.app.thread != null) {
2637            if (DEBUG_SWITCH) Slog.v(TAG_SWITCH, "Resume running: " + next
2638                    + " stopped=" + next.stopped + " visible=" + next.visible);
2639
2640            // If the previous activity is translucent, force a visibility update of
2641            // the next activity, so that it's added to WM's opening app list, and
2642            // transition animation can be set up properly.
2643            // For example, pressing Home button with a translucent activity in focus.
2644            // Launcher is already visible in this case. If we don't add it to opening
2645            // apps, maybeUpdateTransitToWallpaper() will fail to identify this as a
2646            // TRANSIT_WALLPAPER_OPEN animation, and run some funny animation.
2647            final boolean lastActivityTranslucent = lastStack != null
2648                    && (lastStack.inMultiWindowMode()
2649                    || (lastStack.mLastPausedActivity != null
2650                    && !lastStack.mLastPausedActivity.fullscreen));
2651
2652            // The contained logic must be synchronized, since we are both changing the visibility
2653            // and updating the {@link Configuration}. {@link ActivityRecord#setVisibility} will
2654            // ultimately cause the client code to schedule a layout. Since layouts retrieve the
2655            // current {@link Configuration}, we must ensure that the below code updates it before
2656            // the layout can occur.
2657            synchronized(mWindowManager.getWindowManagerLock()) {
2658                // This activity is now becoming visible.
2659                if (!next.visible || next.stopped || lastActivityTranslucent) {
2660                    next.setVisibility(true);
2661                }
2662
2663                // schedule launch ticks to collect information about slow apps.
2664                next.startLaunchTickingLocked();
2665
2666                ActivityRecord lastResumedActivity =
2667                        lastStack == null ? null :lastStack.mResumedActivity;
2668                final ActivityState lastState = next.getState();
2669
2670                mService.updateCpuStats();
2671
2672                if (DEBUG_STATES) Slog.v(TAG_STATES, "Moving to RESUMED: " + next
2673                        + " (in existing)");
2674
2675                next.setState(RESUMED, "resumeTopActivityInnerLocked");
2676
2677                mService.updateLruProcessLocked(next.app, true, null);
2678                updateLRUListLocked(next);
2679                mService.updateOomAdjLocked();
2680
2681                // Have the window manager re-evaluate the orientation of
2682                // the screen based on the new activity order.
2683                boolean notUpdated = true;
2684
2685                if (mStackSupervisor.isFocusedStack(this)) {
2686                    // We have special rotation behavior when here is some active activity that
2687                    // requests specific orientation or Keyguard is locked. Make sure all activity
2688                    // visibilities are set correctly as well as the transition is updated if needed
2689                    // to get the correct rotation behavior. Otherwise the following call to update
2690                    // the orientation may cause incorrect configurations delivered to client as a
2691                    // result of invisible window resize.
2692                    // TODO: Remove this once visibilities are set correctly immediately when
2693                    // starting an activity.
2694                    notUpdated = !mStackSupervisor.ensureVisibilityAndConfig(next, mDisplayId,
2695                            true /* markFrozenIfConfigChanged */, false /* deferResume */);
2696                }
2697
2698                if (notUpdated) {
2699                    // The configuration update wasn't able to keep the existing
2700                    // instance of the activity, and instead started a new one.
2701                    // We should be all done, but let's just make sure our activity
2702                    // is still at the top and schedule another run if something
2703                    // weird happened.
2704                    ActivityRecord nextNext = topRunningActivityLocked();
2705                    if (DEBUG_SWITCH || DEBUG_STATES) Slog.i(TAG_STATES,
2706                            "Activity config changed during resume: " + next
2707                                    + ", new next: " + nextNext);
2708                    if (nextNext != next) {
2709                        // Do over!
2710                        mStackSupervisor.scheduleResumeTopActivities();
2711                    }
2712                    if (!next.visible || next.stopped) {
2713                        next.setVisibility(true);
2714                    }
2715                    next.completeResumeLocked();
2716                    if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2717                    return true;
2718                }
2719
2720                try {
2721                    final ClientTransaction transaction = ClientTransaction.obtain(next.app.thread,
2722                            next.appToken);
2723                    // Deliver all pending results.
2724                    ArrayList<ResultInfo> a = next.results;
2725                    if (a != null) {
2726                        final int N = a.size();
2727                        if (!next.finishing && N > 0) {
2728                            if (DEBUG_RESULTS) Slog.v(TAG_RESULTS,
2729                                    "Delivering results to " + next + ": " + a);
2730                            transaction.addCallback(ActivityResultItem.obtain(a));
2731                        }
2732                    }
2733
2734                    if (next.newIntents != null) {
2735                        transaction.addCallback(NewIntentItem.obtain(next.newIntents,
2736                                false /* andPause */));
2737                    }
2738
2739                    // Well the app will no longer be stopped.
2740                    // Clear app token stopped state in window manager if needed.
2741                    next.notifyAppResumed(next.stopped);
2742
2743                    EventLog.writeEvent(EventLogTags.AM_RESUME_ACTIVITY, next.userId,
2744                            System.identityHashCode(next), next.getTask().taskId,
2745                            next.shortComponentName);
2746
2747                    next.sleeping = false;
2748                    mService.getAppWarningsLocked().onResumeActivity(next);
2749                    mService.showAskCompatModeDialogLocked(next);
2750                    next.app.pendingUiClean = true;
2751                    next.app.forceProcessStateUpTo(mService.mTopProcessState);
2752                    next.clearOptionsLocked();
2753                    transaction.setLifecycleStateRequest(
2754                            ResumeActivityItem.obtain(next.app.repProcState,
2755                                    mService.isNextTransitionForward()));
2756                    mService.getLifecycleManager().scheduleTransaction(transaction);
2757
2758                    if (DEBUG_STATES) Slog.d(TAG_STATES, "resumeTopActivityLocked: Resumed "
2759                            + next);
2760                } catch (Exception e) {
2761                    // Whoops, need to restart this activity!
2762                    if (DEBUG_STATES) Slog.v(TAG_STATES, "Resume failed; resetting state to "
2763                            + lastState + ": " + next);
2764                    next.setState(lastState, "resumeTopActivityInnerLocked");
2765
2766                    // lastResumedActivity being non-null implies there is a lastStack present.
2767                    if (lastResumedActivity != null) {
2768                        lastResumedActivity.setState(RESUMED, "resumeTopActivityInnerLocked");
2769                    }
2770
2771                    Slog.i(TAG, "Restarting because process died: " + next);
2772                    if (!next.hasBeenLaunched) {
2773                        next.hasBeenLaunched = true;
2774                    } else  if (SHOW_APP_STARTING_PREVIEW && lastStack != null
2775                            && getDisplay() != null && lastStack.isTopStackOnDisplay()) {
2776                        next.showStartingWindow(null /* prev */, false /* newTask */,
2777                                false /* taskSwitch */);
2778                    }
2779                    mStackSupervisor.startSpecificActivityLocked(next, true, false);
2780                    if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2781                    return true;
2782                }
2783            }
2784
2785            // From this point on, if something goes wrong there is no way
2786            // to recover the activity.
2787            try {
2788                next.completeResumeLocked();
2789            } catch (Exception e) {
2790                // If any exception gets thrown, toss away this
2791                // activity and try the next one.
2792                Slog.w(TAG, "Exception thrown during resume of " + next, e);
2793                requestFinishActivityLocked(next.appToken, Activity.RESULT_CANCELED, null,
2794                        "resume-exception", true);
2795                if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2796                return true;
2797            }
2798        } else {
2799            // Whoops, need to restart this activity!
2800            if (!next.hasBeenLaunched) {
2801                next.hasBeenLaunched = true;
2802            } else {
2803                if (SHOW_APP_STARTING_PREVIEW) {
2804                    next.showStartingWindow(null /* prev */, false /* newTask */,
2805                            false /* taskSwich */);
2806                }
2807                if (DEBUG_SWITCH) Slog.v(TAG_SWITCH, "Restarting: " + next);
2808            }
2809            if (DEBUG_STATES) Slog.d(TAG_STATES, "resumeTopActivityLocked: Restarting " + next);
    //此处是resume对应的activity
2810            mStackSupervisor.startSpecificActivityLocked(next, true, true);
2811        }
2812
2813        if (DEBUG_STACK) mStackSupervisor.validateTopActivitiesLocked();
2814        return true;
2815    }
```

#### activitystacksupervisor -> startspecificactivitylocked

```java
1690    void startSpecificActivityLocked(ActivityRecord r,
1691            boolean andResume, boolean checkConfig) {
1692        // Is this activity's application already running?
1693        ProcessRecord app = mService.getProcessRecordLocked(r.processName,
1694                r.info.applicationInfo.uid, true);
1695
1696        getLaunchTimeTracker().setLaunchTime(r);
1697
1698        if (app != null && app.thread != null) {
1699            try {
1700                if ((r.info.flags&ActivityInfo.FLAG_MULTIPROCESS) == 0
1701                        || !"android".equals(r.info.packageName)) {
1702                    // Don't add this if it is a platform component that is marked
1703                    // to run in multiple processes, because this is actually
1704                    // part of the framework so doesn't make sense to track as a
1705                    // separate apk in the process.
1706                    app.addPackage(r.info.packageName, r.info.applicationInfo.longVersionCode,
1707                            mService.mProcessStats);
1708                }
1709                realStartActivityLocked(r, app, andResume, checkConfig);
1710                return;
1711            } catch (RemoteException e) {
1712                Slog.w(TAG, "Exception when starting activity "
1713                        + r.intent.getComponent().flattenToShortString(), e);
1714            }
1715
1716            // If a dead object exception was thrown -- fall through to
1717            // restart the application.
1718        }
1719
1720        mService.startProcessLocked(r.processName, r.info.applicationInfo, true, 0,
1721                "activity", r.intent.getComponent(), false, false, true);
1722    }
```

#### activitystacksupervisor -> realstartactivitylocked

```java
1381
1382    final boolean realStartActivityLocked(ActivityRecord r, ProcessRecord app,
1383            boolean andResume, boolean checkConfig) throws RemoteException {
1384
    ...
1527                // Create activity launch transaction.
1528                final ClientTransaction clientTransaction = ClientTransaction.obtain(app.thread,
1529                        r.appToken);
1530                clientTransaction.addCallback(LaunchActivityItem.obtain(new Intent(r.intent),
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
1543                    lifecycleItem = ResumeActivityItem.obtain(mService.isNextTransitionForward());
1544                } else {
1545                    lifecycleItem = PauseActivityItem.obtain();
1546                }
1547                clientTransaction.setLifecycleStateRequest(lifecycleItem);
1548
1549                // Schedule transaction.
1550                mService.getLifecycleManager().scheduleTransaction(clientTransaction);
1551
1552
    ...
1632    }
```

之后就会回调到activitythread中的handlelaunchactivity中了

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
3049        final Activity a = performLaunchActivity(r, customIntent);
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

启动流程：

1. 点击桌面App图标，Launcher进程采用Binder IPC向system_server进程发起startActivity请求；
2. system_server进程接收到请求后，向zygote进程发送创建进程的请求；
3. Zygote进程fork出新的子进程，即App进程；
4. App进程，通过Binder IPC向sytem_server进程发起attachApplication请求；
5. system_server进程在收到请求后，进行一系列准备工作后，再通过binder IPC向App进程发送scheduleLaunchActivity请求；
6. App进程的binder线程（ApplicationThread）在收到请求后，通过handler向主线程发送LAUNCH_ACTIVITY消息；
7. 主线程在收到Message后，通过发射机制创建目标Activity，并回调Activity.onCreate()等方法。
---
layout: post
title: wms开坑(基于AndroidP)
---

首先从wms启动的地方说起:

SystemServer.java

```java
private void startOtherServices() {
875            traceBeginAndSlog("StartWindowManagerService");
876            // WMS needs sensor service ready
877            ConcurrentUtils.waitForFutureNoInterrupt(mSensorServiceStart, START_SENSOR_SERVICE);
878            mSensorServiceStart = null;
879            wm = WindowManagerService.main(context, inputManager,
880                    mFactoryTestMode != FactoryTest.FACTORY_TEST_LOW_LEVEL,
881                    !mFirstBoot, mOnlyCore, new PhoneWindowManager());
882            ServiceManager.addService(Context.WINDOW_SERVICE, wm, /* allowIsolated= */ false,
883                    DUMP_FLAG_PRIORITY_CRITICAL | DUMP_FLAG_PROTO);
884            ServiceManager.addService(Context.INPUT_SERVICE, inputManager,
885                    /* allowIsolated= */ false, DUMP_FLAG_PRIORITY_CRITICAL);
886            traceEnd();
887
888            traceBeginAndSlog("SetWindowManagerService");
889            mActivityManagerService.setWindowManager(wm);
890            traceEnd();
891
892            traceBeginAndSlog("WindowManagerServiceOnInitReady");
893            wm.onInitReady();
894            traceEnd();
    ...
978        traceBeginAndSlog("MakeDisplayReady");
979        try {
980            wm.displayReady();
981        } catch (Throwable e) {
982            reportWtf("making display ready", e);
983        }
984        traceEnd();
    ...
1721        traceBeginAndSlog("MakeWindowManagerServiceReady");
1722
1723        try {
1724            wm.systemReady();
1725        } catch (Throwable e) {
1726            reportWtf("making Window Manager Service ready", e);
1727        }
1728        traceEnd();
}
```

从这段我们可以看到

1.wms需要等待sensor起来才行

2.调用main函数来初始化

3.需要将wms设置给ams

4.调用initready来进行初始化

WindowManagerService.java

```java
910    public static WindowManagerService main(final Context context, final InputManagerService im,
911            final boolean haveInputMethods, final boolean showBootMsgs, final boolean onlyCore,
912            WindowManagerPolicy policy) {
913        DisplayThread.getHandler().runWithScissors(() ->
914                sInstance = new WindowManagerService(context, im, haveInputMethods, showBootMsgs,
915                        onlyCore, policy), 0);
916        return sInstance;
917    }
```

可以看到是在调用new方法创建一个wms实例，然后放在DisplayThread的进行执行，而wms本身的handler没有参数，说明就是采用的"android.display"线程，wms的handler就是运行在此线程中。

接着看new过程中

```java

```

紧接着在oninitready方法

```java
1090    public void onInitReady() {
1091        initPolicy();//初始化phonewindowmanager策略
1092
1093        // Add ourself to the Watchdog monitors.
1094        Watchdog.getInstance().addMonitor(this);
1095
1096        openSurfaceTransaction();
1097        try {
1098            createWatermarkInTransaction();
1099        } finally {
1100            closeSurfaceTransaction("createWatermarkInTransaction");
1101        }
1102
1103        showEmulatorDisplayOverlayIfNeeded();
1104    }
```

而在initpolicy方法中

```java
919    private void initPolicy() {
920        UiThread.getHandler().runWithScissors(new Runnable() {
921            @Override
922            public void run() {
923                WindowManagerPolicyThread.set(Thread.currentThread(), Looper.myLooper());
924                mPolicy.init(mContext, WindowManagerService.this, WindowManagerService.this);
925            }
926        }, 0);
927    }
```

所以这段代码是运行在”android.ui“线程中，那么我们看phonewindowmanager的init操作

```java

```

这样就phonewindowmanager的init

紧接着就是wms的displayReady

```java
4611    private void displayReady(int displayId) {
4612        synchronized(mWindowMap) {
4613            final DisplayContent displayContent = mRoot.getDisplayContent(displayId);
4614            if (displayContent != null) {
4615                mAnimator.addDisplayLocked(displayId);
4616                displayContent.initializeDisplayBaseInfo();
4617                reconfigureDisplayLocked(displayContent);
4618            }
4619        }
4620    }
```

然后是wms的systemReady

```java
4622    public void systemReady() {
4623        mPolicy.systemReady();
4624        mTaskSnapshotController.systemReady();
4625        mHasWideColorGamutSupport = queryWideColorGamutSupport();
4626    }
```

于是就是到了phonewindowmanager的systemready

```

```


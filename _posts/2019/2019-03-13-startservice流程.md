---
layout: post
title: startservice流程
---

activity.startservice其实是继承的contextwrapper的startservice

####1.contextwrapper.startservice

```java
662    @Override
663    public ComponentName startService(Intent service) {
664        return mBase.startService(service);
665    }
```

mbase是contextimpl

#### 2.context.impl->startservice

```java
1530    public ComponentName startService(Intent service) {
1531        warnIfCallingFromSystemProcess();
1532        return startServiceCommon(service, false, mUser);
1533    }

1557    private ComponentName startServiceCommon(Intent service, boolean requireForeground,
1558            UserHandle user) {
1559        try {
1560            validateServiceIntent(service);
1561            service.prepareToLeaveProcess(this);
1562            ComponentName cn = ActivityManager.getService().startService(
1563                mMainThread.getApplicationThread(), service, service.resolveTypeIfNeeded(
1564                            getContentResolver()), requireForeground,
1565                            getOpPackageName(), user.getIdentifier());
1566            if (cn != null) {
1567                if (cn.getPackageName().equals("!")) {
1568                    throw new SecurityException(
1569                            "Not allowed to start service " + service
1570                            + " without permission " + cn.getClassName());
1571                } else if (cn.getPackageName().equals("!!")) {
1572                    throw new SecurityException(
1573                            "Unable to start service " + service
1574                            + ": " + cn.getClassName());
1575                } else if (cn.getPackageName().equals("?")) {
1576                    throw new IllegalStateException(
1577                            "Not allowed to start service " + service + ": " + cn.getClassName());
1578                }
1579            }
1580            return cn;
1581        } catch (RemoteException e) {
1582            throw e.rethrowFromSystemServer();
1583        }
1584    }
```

activitymanager.getservice

```java
4125    public static IActivityManager getService() {
4126        return IActivityManagerSingleton.get();
4127    }
```

activitymanager

```java
4129    private static final Singleton<IActivityManager> IActivityManagerSingleton =
4130            new Singleton<IActivityManager>() {
4131                @Override
4132                protected IActivityManager create() {
4133                    final IBinder b = ServiceManager.getService(Context.ACTIVITY_SERVICE);
4134                    final IActivityManager am = IActivityManager.Stub.asInterface(b);
4135                    return am;
4136                }
4137            };
```

通过binder调用，在本地拿到了activitymanagerservice的binder对端，这样再调用startservice，其实调用的就是activitymanagerservice的startservice方法。

####3.AMS.startservice

```java
20661    public ComponentName startService(IApplicationThread caller, Intent service,
20662            String resolvedType, boolean requireForeground, String callingPackage, int userId)
20663            throws TransactionTooLargeException {
20664        enforceNotIsolatedCaller("startService");
20665        // Refuse possible leaked file descriptors
20666        if (service != null && service.hasFileDescriptors() == true) {
20667            throw new IllegalArgumentException("File descriptors passed in Intent");
20668        }
20669
20670        if (callingPackage == null) {
20671            throw new IllegalArgumentException("callingPackage cannot be null");
20672        }
20673
20674        if (DEBUG_SERVICE) Slog.v(TAG_SERVICE,
20675                "*** startService: " + service + " type=" + resolvedType + " fg=" + requireForeground);
20676        synchronized(this) {
20677            final int callingPid = Binder.getCallingPid();
20678            final int callingUid = Binder.getCallingUid();
20679            final long origId = Binder.clearCallingIdentity();//注意此处将调用进程的特城去除，现在就相当于在ams自身调用
20680            ComponentName res;
20681            try {
20682                res = mServices.startServiceLocked(caller, service,
20683                        resolvedType, callingPid, callingUid,
20684                        requireForeground, callingPackage, userId);
20685            } finally {
20686                Binder.restoreCallingIdentity(origId);//最后再进行restore
20687            }
20688            return res;
20689        }
20690    }
```

此处的mservices是activeservices

```java

```




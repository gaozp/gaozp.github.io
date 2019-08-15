---
layout: post
title: Android流量统计源码分析
categories: [tech]
---

Android是基于linux的，所以Android本身并没有去做流量统计的工作，只是将linux暴露出来的流量统计结果保存在了本地而已。简而言之的话就是：__linux在数据包经过iptables的时候，将这些流量包的统计结果都暴露在了虚拟设备/proc下面，Android则在特定的时间进行统计，也就是将/proc下的统计结果保存到/data/system/下，原因比较简单，因为每次开关机，虚拟的文件系统/proc就会进行重新挂载，这个时候之前的所有数据就都已经没有了。__  

####开始统计  
主要涉及的代码在NetworkStatsService中，首先，这个系统服务刚刚起来的时候  

    mBaseDir = new File(systemDir, "netstats");
    mBaseDir.mkdirs();

先创建了保存流量统计结果的目录，就是在/data/system/netstats/目录下，如果有真机的话，可以看到这个目录下会有uid,dev,xt,uid_tag等文件。  
接着往下看，走到了systemready的时候，

    public void systemReady() {
        mSystemReady = true;

        if (!isBandwidthControlEnabled()) {
            Slog.w(TAG, "bandwidth controls disabled, unable to track stats");
            return;
        }

        // create data recorders along with historical rotators
        mDevRecorder = buildRecorder(PREFIX_DEV, mSettings.getDevConfig(), false);
        mXtRecorder = buildRecorder(PREFIX_XT, mSettings.getXtConfig(), false);
        mUidRecorder = buildRecorder(PREFIX_UID, mSettings.getUidConfig(), false);
        mUidTagRecorder = buildRecorder(PREFIX_UID_TAG, mSettings.getUidTagConfig(), true);

        updatePersistThresholds();

        synchronized (mStatsLock) {
            // upgrade any legacy stats, migrating them to rotated files
            maybeUpgradeLegacyStatsLocked();

            // read historical network stats from disk, since policy service
            // might need them right away.
            mXtStatsCached = mXtRecorder.getOrLoadCompleteLocked();

            // bootstrap initial stats to prevent double-counting later
            bootstrapStatsLocked();
        }

    ....
        try {
            mNetworkManager.registerObserver(mAlertObserver);
        } catch (RemoteException e) {
            // ignored; service lives in system_server
        }

        registerPollAlarmLocked();
        registerGlobalAlert(); 
    }

首先看到会创建文件的 




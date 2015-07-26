android系统中数据流量统计源码分析

Android系统的/proc目录是一个虚拟的文件系统，里面的文件不是真实存在的，是linux内核映射出来给用户态的应用读取内核信息的接口。  
Android系统添加了数据流量统计的功能，但是这些数据都是从proc中读取出来的，proc目录是每次开机的时候映射出来的，所以不会持续记录，只会记录从开机之后的流量统计信息记录在/proc/net/下，还有一些其他的信息，那么，设置是如何获取到统计数据，并且可以根据日期去显示不同应用使用的流量。  
首先看到设置中流量统计页面：DataUsageSummary.java,在onresume方法中进行的数据的加载：
	
	new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // wait a few seconds before kicking off
                    Thread.sleep(2 * DateUtils.SECOND_IN_MILLIS);
                    mStatsService.forceUpdate();
                } catch (InterruptedException e) {
                } catch (RemoteException e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (isAdded()) {
                    updateBody();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

在dobackground方法中，有一个mstatsservice.forceupdate方法。该方法是在每次进入流量显示页面的时候，就将最新的流量统计信息更新在界面上。先看mstatsservice的初始化吧。  
	
	public NetworkStatsService(Context context, INetworkManagementService networkManager,
            IAlarmManager alarmManager, TrustedTime time, File systemDir,
            NetworkStatsSettings settings) {
        //初始化检查
        mContext = checkNotNull(context, "missing Context");
        mNetworkManager = checkNotNull(networkManager, "missing INetworkManagementService");
        mTime = checkNotNull(time, "missing TrustedTime");
        mTeleManager = checkNotNull(TelephonyManager.getDefault(), "missing TelephonyManager");
        mSettings = checkNotNull(settings, "missing NetworkStatsSettings");
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        final PowerManager powerManager = (PowerManager) context.getSystemService(
                Context.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);

        HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        //callback中处理流量更新，interface更新等
        mHandler = new Handler(thread.getLooper(), mHandlerCallback);
		//创建文件保存目录
        mSystemDir = checkNotNull(systemDir);
        mBaseDir = new File(systemDir, "netstats");
        mBaseDir.mkdirs();
    }
oncreate也就是初始化了一个handler和创建了流量保存目录/data/system/netstats/目录。  
在systemready()方法中
	
	
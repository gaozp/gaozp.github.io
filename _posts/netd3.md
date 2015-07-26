netd是连接framework以及kernel的中间桥梁，那么就看看首先是如何进行连接的吧。  
两种方式：  
1.kernel底层状态向framework层上报，framework层接受状态反馈，做出对应处理。  
2.framework层向kernel发送命令，并且将处理结果返回给framework。  
  
先看第一种方式，第一种方式主要是接收kernel主动上报的状态变化：比如600的InterfaceChange,601的BandwidthControl,以及一些其他需要主动上报的，这些code对应的状态值都在server/Responsecode.h中，同时，在NetworkMangementService.java的NetdCallbackReceiver的onreceive方法中也对对应上报消息做了对应处理。那么就来看看这一过程吧。  
  
	
	if (!(nm = NetlinkManager::Instance())) {
        ALOGE("Unable to create NetlinkManager");
        exit(1);
    };

    cl = new CommandListener();
    nm->setBroadcaster((SocketListener *) cl);

    if (nm->start()) {
        ALOGE("Unable to start NetlinkManager (%s)", strerror(errno));
        exit(1);
    }  
  

  
按照main函数的顺序，首先实例化了NetlinkManager:  
	
	NetlinkManager *NetlinkManager::Instance() {
    	if (!sInstance)
        	sInstance = new NetlinkManager();
    	return sInstance;
	}
	
	NetlinkManager::NetlinkManager() {
    	mBroadcaster = NULL;
	}

就是一个简单的单例设计模式，然后在新建实例的时候将mBroadcaster初始化为null。 
后面就是创建CommandListener，调用nm的setBroadcaster为新创建的cl。这样，nm中就拥有了cl的实例，就可以通过cl来向framework层传递信息。  
  
随后调用nm的start方法  
	
	int NetlinkManager::start() {
    if ((mUeventHandler = setupSocket(&mUeventSock, NETLINK_KOBJECT_UEVENT,
         0xffffffff, NetlinkListener::NETLINK_FORMAT_ASCII)) == NULL) {
        return -1;
    }

    if ((mRouteHandler = setupSocket(&mRouteSock, NETLINK_ROUTE,
                                     RTMGRP_LINK |
                                     RTMGRP_IPV4_IFADDR |
                                     RTMGRP_IPV6_IFADDR |
                                     RTMGRP_IPV6_ROUTE |
                                     (1 << (RTNLGRP_ND_USEROPT - 1)),
         NetlinkListener::NETLINK_FORMAT_BINARY)) == NULL) {
        return -1;
    }

    if ((mQuotaHandler = setupSocket(&mQuotaSock, NETLINK_NFLOG,
        NFLOG_QUOTA_GROUP, NetlinkListener::NETLINK_FORMAT_BINARY)) == NULL) {
        ALOGE("Unable to open quota2 logging socket");
        // TODO: return -1 once the emulator gets a new kernel.
    }

    return 0;
	}

创建了3个监听线程，根据setupSocket的参数不同可以限定监听socket的监听内核模块时间，分别start，并且返回了handler实例。  

	
	NetlinkHandler *NetlinkManager::setupSocket(int *sock, int netlinkFamily,
    int groups, int format) {

    struct sockaddr_nl nladdr;
    int sz = 64 * 1024;
    int on = 1;

    memset(&nladdr, 0, sizeof(nladdr));
    nladdr.nl_family = AF_NETLINK;
    nladdr.nl_pid = getpid();
    nladdr.nl_groups = groups;
	//创建socket
    if ((*sock = socket(PF_NETLINK, SOCK_DGRAM, netlinkFamily)) < 0) {
        ALOGE("Unable to create netlink socket: %s", strerror(errno));
        return NULL;
    }
	//设置socket属性
    if (setsockopt(*sock, SOL_SOCKET, SO_RCVBUFFORCE, &sz, sizeof(sz)) < 0) {
        ALOGE("Unable to set uevent socket SO_RCVBUFFORCE option: %s", strerror(errno));
        close(*sock);
        return NULL;
    }

    if (setsockopt(*sock, SOL_SOCKET, SO_PASSCRED, &on, sizeof(on)) < 0) {
        SLOGE("Unable to set uevent socket SO_PASSCRED option: %s", strerror(errno));
        close(*sock);
        return NULL;
    }

    if (bind(*sock, (struct sockaddr *) &nladdr, sizeof(nladdr)) < 0) {
        ALOGE("Unable to bind netlink socket: %s", strerror(errno));
        close(*sock);
        return NULL;
    }
	//NetlinkHandler的start方法
	//NetlinkHandler继承NetlinkListener,继承SocketListener
    NetlinkHandler *handler = new NetlinkHandler(this, *sock, format);
    if (handler->start()) {
        ALOGE("Unable to start NetlinkHandler: %s", strerror(errno));
        close(*sock);
        return NULL;
    }

    return handler;
	}
创建监听线程大概如此，首先创建socket，设置相关属性，最后调用了NetlinkHandler的start方法，该方法在socketlistener中，  
	
	int SocketListener::startListener() {
    return startListener(4);
	}

	int SocketListener::startListener(int backlog) {

    if (!mSocketName && mSock == -1) {
        SLOGE("Failed to start unbound listener");
        errno = EINVAL;
        return -1;
    } else if (mSocketName) {
        if ((mSock = android_get_control_socket(mSocketName)) < 0) {
            SLOGE("Obtaining file descriptor socket '%s' failed: %s",
                 mSocketName, strerror(errno));
            return -1;
        }
        SLOGV("got mSock = %d for %s", mSock, mSocketName);
    }

    if (mListen && listen(mSock, backlog) < 0) {
        SLOGE("Unable to listen on socket (%s)", strerror(errno));
        return -1;
    } else if (!mListen)
        mClients->push_back(new SocketClient(mSock, false, mUseCmdNum));

    if (pipe(mCtrlPipe)) {
        SLOGE("pipe failed (%s)", strerror(errno));
        return -1;
    }

    if (pthread_create(&mThread, NULL, SocketListener::threadStart, this)) {
        SLOGE("pthread_create (%s)", strerror(errno));
        return -1;
    }

    return 0;
	}

	
首先进行了一些初始化的判断操作，不赘述，然后判断是tcp还是udp，因为此处是监听kernel上报信息，所以是udp，随后进行了pthread_create操作，同时也调用了SocketListener的threadStart方法  
	
	void *SocketListener::threadStart(void *obj) {
    SocketListener *me = reinterpret_cast<SocketListener *>(obj);

    me->runListener();
    pthread_exit(NULL);
    return NULL;
	}
	
	




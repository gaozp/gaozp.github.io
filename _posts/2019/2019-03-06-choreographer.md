---
layout: post
title: choreographer(一)
categories: [tech]
---

在viewrootimpl中进行的初始化。

初始化的时候进行了framehandler，framedisplayeventreceiver的初始化。

framedisplayeventreceiver实现了DisplayEventReceiver,初始化的时候进行了nativeinit，进入到jni层。

在DisplayEventReceiver进行nativeinit的时候，需要创建nativeDisplayeventreceiver,并且进行initialize。

nativeDisplayeventreceiver继承自Displayeventdispatcher，然后调用initialize。

在Displayeventdispatcher的initialize中，DisplayEventReceiver调用initcheck

```
49status_t DisplayEventReceiver::initCheck() const {
50    if (mDataChannel != NULL)
51        return NO_ERROR;
52    return NO_INIT;
53}
```

监听获取到的receiver文件句柄。

于是在收到底层的vsync信号的时候，就会回调Displayeventdispatcher的handleevent函数

```
89int DisplayEventDispatcher::handleEvent(int, int events, void*) {
...
102    // Drain all pending events, keep the last vsync.
103    nsecs_t vsyncTimestamp;
104    int32_t vsyncDisplayId;
105    uint32_t vsyncCount;
106    if (processPendingEvents(&vsyncTimestamp, &vsyncDisplayId, &vsyncCount)) {
107        ALOGV("dispatcher %p ~ Vsync pulse: timestamp=%" PRId64 ", id=%d, count=%d",
108                this, ns2ms(vsyncTimestamp), vsyncDisplayId, vsyncCount);
109        mWaitingForVsync = false;
110        dispatchVsync(vsyncTimestamp, vsyncDisplayId, vsyncCount);
111    }
112
113    return 1; // keep the callback
114}
```

调用了自身的processpendingevent，又分成热插拔和vsync

```
116bool DisplayEventDispatcher::processPendingEvents(
117        nsecs_t* outTimestamp, int32_t* outId, uint32_t* outCount) {
118    bool gotVsync = false;
...
126            case DisplayEventReceiver::DISPLAY_EVENT_VSYNC:
127                // Later vsync events will just overwrite the info from earlier
128                // ones. That's fine, we only care about the most recent.
129                gotVsync = true;//进入vsync
130                *outTimestamp = ev.header.timestamp;
131                *outId = ev.header.id;
132                *outCount = ev.vsync.count;
133                break;
134            case DisplayEventReceiver::DISPLAY_EVENT_HOTPLUG:
135                dispatchHotplug(ev.header.timestamp, ev.header.id, ev.hotplug.connected);
136                break;
...
146    return gotVsync;
147}
```

然后就回到了android_view_displayeventreceiver.cpp中，进行vsync的dispatch

```
87void NativeDisplayEventReceiver::dispatchVsync(nsecs_t timestamp, int32_t id, uint32_t count) {
90    ScopedLocalRef<jobject> receiverObj(env, jniGetReferent(env, mReceiverWeakGlobal));
91    if (receiverObj.get()) {
92        ALOGV("receiver %p ~ Invoking vsync handler.", this);
93        env->CallVoidMethod(receiverObj.get(),
94                gDisplayEventReceiverClassInfo.dispatchVsync, timestamp, id, count);
95        ALOGV("receiver %p ~ Returned from vsync handler.", this);
96    }
97
98    mMessageQueue->raiseAndClearException(env, "dispatchVsync");
99}
```

获取到当时传下来的FrameDisplayEventReceiver，回调他的父类的dispatchvsync，也就是他自己的onvsync

```
942        public void onVsync(long timestampNanos, int builtInDisplayId, int frame) {
982            Message msg = Message.obtain(mHandler, this);
983            msg.setAsynchronous(true);
984            mHandler.sendMessageAtTime(msg, timestampNanos / TimeUtils.NANOS_PER_MS);
985        }
```

传入的是自己runnable，所以调用了自己的run方法，于是调用了doframe

```
685    void doFrame(long frameTimeNanos, int frame) {

744            mFrameInfo.markInputHandlingStart();
745            doCallbacks(Choreographer.CALLBACK_INPUT, frameTimeNanos);
746
747            mFrameInfo.markAnimationsStart();
748            doCallbacks(Choreographer.CALLBACK_ANIMATION, frameTimeNanos);
749
750            mFrameInfo.markPerformTraversalsStart();
751            doCallbacks(Choreographer.CALLBACK_TRAVERSAL, frameTimeNanos);
752
753            doCallbacks(Choreographer.CALLBACK_COMMIT, frameTimeNanos);

765    }
```

调用了docallback

```
767    void doCallbacks(int callbackType, long frameTimeNanos) {

810            for (CallbackRecord c = callbacks; c != null; c = c.next) {
811                if (DEBUG_FRAMES) {
812                    Log.d(TAG, "RunCallback: type=" + callbackType
813                            + ", action=" + c.action + ", token=" + c.token
814                            + ", latencyMillis=" + (SystemClock.uptimeMillis() - c.dueTime));
815                }
816                c.run(frameTimeNanos);
817            }

829    }
```

取出对应的回调，调用其中的方法。




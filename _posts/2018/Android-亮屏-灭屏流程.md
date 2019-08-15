---
layout: post
title: Android 亮屏 灭屏流程
---

#

先说明亮屏的流程。

首先从硬件的点击事件如何到framework就不说了，因为之前的点击事件的传递已经讲解了，通过inputreader获取。

在interceptkeybeforequeue中，判断是keycode_power，然后先会处理down事件

```

```

```

```

在down事件中，就会处理wakeupfrompowerkey事件来点亮屏幕，而抬起的事件只是会做简单的记录。

那么就看wakeup事件：

```

```

通过调用powermanager的wakeup事件来进行唤醒。再通过wakeupinternal来实行。

```

```

那里面的wakeupnoupdatelocked来通知ams，wms来进行发广播等等处理。

在updatepowerstatelocked方法中：

```

```

在跳出循环后，进入到了pdatedisplaypowerstatelocked中，

将数值封装在request中后，通过displaymanagerservice的requestpowerstate来传递

```

```

displaymanagerservice通过displaypowercontroller来进行处理

```

```

在sendupdatepowerstatelocked方法中

```

```

在updatepowerstate中，主要做了两件事：

1.调用animateScreenStateChange函数处理屏幕状态变化
2.调用animateScreenBrightness函数设置屏幕亮度

```

```

在animatescreenstatechange中最主要的就是设置setscreenstate

```

```

注意在setscreenstate中会blockscreenon和unblock此处就是打印我们平时系统中的：

"Blocking screen on unitl initial contents have been drawn"的地方

在最后的时候调用phonewindowmanager的screenturningon来进行keyguard和home的绘制。

```

```

在绘制完成后，调用phonewindowmanager的callback的run方法，发送了对应的消息，调用finishwindowdrawn方法，回调到displaypowercontroller中的onscreenon方法：

```

```

发送screenon_unblocked的广播：

```

```

然后就会走入unblocked的方法中，也就是打印一下，整个keyguard和home绘制使用了多久

```

```

跳过了animatescreenstatechange后，继续往下走，

就走到了animatescreenbrightness中

```

```

在animatescreenbrightness中，通过动画来进行改变。

在动画的回调中，调用了mproperty的setvalue，这个property是在创建的时候传入的，是displaypowerstate

，看看对应的setvalue方法

```

```

调用了setScreenBrightness 函数，然后继续调用了scheduleScreenUpdate

向handler中post了一个runnable：mScreenUpdateRunnable

```

```

然后就调用到了displaymanagerservice中的displayblanker的requestdisplaystate函数了

```

```

走到了requestGlobalDisplayStateInternal中

```

```

在函数中的applyGlobalDisplayStateLocked

```

```

updateDisplayStateLocked方法：

```

```

那这其中的device自然就是localdisplaydevice了

```

```

在localdisplaydevice中的requestDisplayStateLocked

```

```

就能看到最后的setbrightness了，这就是最终设置亮度的地方了

```

```





在动画结束后的回调后，调用callback，也就是displaypowercontroller的onanimationend，调用了sendupdatepowersate函数

```

```

又重新走到了updatepowerstate函数中。



```

```


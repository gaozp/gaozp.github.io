---
layout: post
title: Direct Boot
categories: [tech]
---
#### 前言：
在android 7.0之后，系统为了安全和磁盘加密的考虑，添加了directboot模式，也就是在用户未解锁手机的情况下，凭据加密存储位置是加密的，而设备加密存储位置是可以使用的，这样在手机丢失或者失窃，捡到手机的人在没有密码的情况下，无法获取到手机中的信息，从而提高了手机信息的安全性。  
#### 官方文档：
再多的博客，也都没有[官方文档](https://developer.android.com/training/articles/direct-boot)来的实在。同时也提供了[demo](https://github.com/googlearchive/android-DirectBoot)实例。  
文档中介绍了应用如果有需要的话，应该如何去适配direct boot。  
#### 对direct boot的理解：
1. 为什么要这么做：  
在之前版本的全盘加密也就是FDE上，用户访问任何数据都是需要提供凭据，导致手机只能执行最基本的操作，甚至手机都无法接听电话，只能紧急拨号，闹钟也无法运行。在引入文件及加密也就是FBE后，可以将应用设置在受限的环境中运行，这意味着用户可以在提供凭据之前使用基本功能，同时系统还能够保护用户隐私。  
2. 如何实现：  
应用层manifest中添加：
```XML
<application
    android:directBootAware="true">
```
M原生权限sample解读  
  
M的权限做了很大的改动基本上是已经既成事实的事情了，同时从4.4就开始使用的appops虽然也有所内容的扩充，但是还是隐藏在设置的最深处，目前可以看到使用了appops的功能也就只有短信中设置默认短信应用了（设置默认短信应用的时候将默认短信应用的WRITE_SMS权限设置为允许同时将其他应用权限全部设置为拒绝），所以最可能的事情就是appops可能会永远雪藏在aosp的代码深处了，而manifest中的权限控制反而出现在了设置中。所以在可以预见的几个版本，可能manifest的权限管理才是aosp的重点发展方向呢。  

google也给出了sample的地址，其实也有很多文章也说了m的权限改变，那我就从代码角度解读一下。  
源码下载地址  

那么就来看看这个例子吧：  


界面上两个按钮，一个是使用相机权限，一个是使用联系人权限。发现的区别就是在xml中定义权限的方式不同：
	
	<uses-permission android:name="android.permission.CAMERA"/>
	<uses-permission-sdk-m android:name="android.permission.READ_CONTACTS" />

一个新的标签，目前倒是没有看出来具体有什么区别，这一块是packagemanagerservice中新添加的，还没有开始看呢，目前看到的区别就是在dumpsys的时候，两个权限所在的组是不一样的，camera是在installpermission的组中，而contact是在requestpermission组中的。但是从demo中倒是没有发现具体区别。  

界面上两个按钮对应的方法  
	
	116     public void showCamera(View view) {     
	117         Log.i(TAG, "Show camera button pressed. Checking permission.");
	118         // BEGIN_INCLUDE(camera_permission) 
	119         // Check if the Camera permission is already available.
	120         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
	121                 != PackageManager.PERMISSION_GRANTED) {
	122             // Camera permission has not been granted.                                                                                                                                                  
	123 
	124             requestCameraPermission();                                                                                                                                                                  
	125   
	126         } else {               
	127       
	128             // Camera permissions is already available, show the camera preview.                                                                                                                        
	129             Log.i(TAG,         
	130                     "CAMERA permission has already been granted. Displaying camera preview.");                                                                                                          
	131             showCameraPreview();
	132         }     
	133         // END_INCLUDE(camera_permission)                                                                                                                                                               
	134 
	135     }

联系人也和这个一样。所以只分析这么一个吧。  

首先需要使用checkselfpermission这个方法来获取到当前权限的
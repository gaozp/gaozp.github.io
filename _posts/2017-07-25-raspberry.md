去年年底买的树莓派，装起来之后就一直没有动过，因为当时没有屏幕所以也就没有多折腾，这次搬家之后，有了时间和空间，终于可以折腾起来啦。  
首先是树莓派的安装，其实也没有什么可以安装的，就是我当时买的是有一个亚克力的板子，可以包裹住树莓派，以免被磕磕碰碰到，然后还有一些其他的东西：无线网卡，读卡器等等。  
首先还是说树莓派的系统安装吧（其实我还是没有屏幕），首先你要有一个读卡器，一张sd卡，我用的是16g的，因为我看官网的树莓派系统已经3g多了，网上说的2g以上估计现在是不行了吧。  
#####装系统  
1.去官网上下载[树莓派镜像](https://www.raspberrypi.org/downloads/)。  
2.我们需要将下载好的系统镜像烧到你的sd卡上，简单说一下mac使用的命令吧：  
a.首先必须保证你的sd卡的格式是FAT32的，如果不是的话可以格式化成FAT32  
b.然后点击左上角苹果图标，选择aboutmymac，在弹出的对话框中选择system report。然后在usb栏中找到自己sdcard的disk#，记得记住这个后面的这个数字。  
c.unmount这个sdcard，否则在写入的时候会提示你设备正忙，mac系统是有自带diskutils命令行工具，可以使用这个来unmount你的sdcard。  
d.最后就是将镜像烧到sd卡上了，使用的是dd命令。这个时候注意了，因为整个镜像有3g，所以需要一点时间，这个时候，你可以使用ctrl+t来查看当前写入的进度。

	diskutil unmountDisk /dev/disk<disk# from diskutil>  
	sudo dd bs=1m if=image.img of=/dev/rdisk<disk# from diskutil>

当写入完成后，就可以将sd卡插入到你的树莓派上了。通上电源，你的树莓派就可以启动起来啦。  
######连接你的树莓派  
当你树莓派风扇呼呼的转起来的时候（话说风扇声音是真大），这个时候就可以连接到你的树莓派了。如果你是有屏幕的话，那肯定很简单了，直接使用hdmi的线连接到你的屏幕上，___千万记住，不要使用没有电源供应的hdmi转vga的线，因为这会烧掉pi上的一个电容，尽量使用hdmi转hdmi的线吧。___  
那我们现在是没有屏幕，那我们如何才能连接到树莓派上呢。大概思路就是：树莓派系统默认是开启了ssh连接的，默认的账户是pi，密码是raspberry。所以我们只要知道了pi的ip地址，那么我们就可以通过ssh的方式连接上pi了。  
需要的工具：一台电脑，一个pi，一根网线。  
1.将树莓派连接上路由器，然后通过路由器的192.168.1.1的dhcp选项获取到raspberry的ip地址。  
对的，就是这么简单，于是你就得到了树莓派的ip地址了。那么直接在你的电脑上打上
	
	ssh pi@xxx.xxx.xxx.xxx
然后输入树莓派的密码raspberry，就可以通过ssh登录上去了。  
#####设置无线连接以及静态ip  
因为买的时候一起买了wifi无线网卡，于是也一起折腾了吧，不然也不能一直使用网线连接不是。  
首先确保自己已经连上了pi，然后将无线网卡插在usb，通过lsusb命令可以查看无线网卡是否已经生效。  
然后需要查看自己网络的网络掩码以及广播和gw：
通过ifconfig命令可以看到bcast以及mask等信息。
然后需要修改无线网卡的设置：
	
	sudo vim /etc/networks/interfaces 
这个文件
	
	auto wlan0 
	allow-hotplug wlan0  #支持热插拔
	iface wlan0 inet static #这个地方的dhcp是指自动获取ip，改成static就是静态ip了
	wpa-ssid 201 #需要连接的无线的ssid
	wpa-psk 4001001111 #无线密码
	address 192.168.1.222 #静态ip设置的地址
	netmask 255.255.255.0 #子网掩码
	broadcast 192.168.1.255 #广播
	gateway 192.168.1.1 #
	#wpa-conf /etc/wpa_supplicant/wpa_supplicant.conf #这句话需要注释 

在修改完成这个之后，你需要重新启动你的网络服务：
	
	/etc/init.d/networking restart
在重新启动之后，你可以在有线的ssh连接到pi的情况下，使用ifconfig来看到wlan的网络情况，如果没有意外的情况下，基本是可以连接上去了。  
___那么问题来了，静态ip设置好了，但是不能每次每次都打pi@xxx.xxx.xxx.xxx吧___  
其实也挺简单的，在电脑上设置host：
	
	vim /etc/hosts
添加文件内容如下：
	
	192.168.1.222 pi #前面ip是你刚刚设置的静态ip
这个时候你还是没有办法直接通过___ssh pi___来直接连接的，因为你需要设置连接的默认账号：  
	
	vim ~/.ssh/config
在该文件中添加如下：
	
	Host pi #hosts中填的后面的名字
  	User pi #树莓派中的用户
  	Port 22 #ssh端口默认是22，除非你自己修改了，否则不需要
  	Hostname pi #
  	IdentityFile ~/.ssh/id_rsa #这个ssh的证书了
  	
目前我使用的还是密码登陆，就没有使用ssh的证书，不用输密码了。  


好了，到这里，基本都能连接上自己的树莓派了，后面比如说要装什么豆瓣fm，装什么shadowsocks做翻墙路由器什么的都是水到渠成啦。
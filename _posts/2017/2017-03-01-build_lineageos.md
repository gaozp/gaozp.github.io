---
layout: post
title: LineageOS本地nexus 5编译烧机过程
---

自从cm转行做其他东西之后，从此开源界又少了一个大头，但是后来LineageOS系统继承了cm的遗志，由原班人马倾情打造的系统也就出来了。还是看看吧。

#### 下载编译前的准备：

1.ubuntu16.04的系统

2.可以翻墙的网络

#### 下载相关依赖：

下载编译环境以及java等等：

```shell
sudo apt-get install bison build-essential curl flex git gnupg libesd0-dev liblz4-tool libncurses5-dev libsdl1.2-dev libxml2 libxml2-utils lzop maven pngcrush schedtool squashfs-tools xsltproc zip zlib1g-dev bc ccache automake lzop gperf zlib1g-dev g++-multilib  gcc-multilib lib32ncurses5-dev lib32readline6-dev lib32z1-dev python-networkx libxml2-utils bzip2 libbz2-dev libbz2-1.0 libghc-bzlib-dev squashfs-tools schedtool dpkg-dev liblz4-tool make optipng libwxgtk3.0-dev openjdk-8-jdk
```

下载repo等工具

```shell
mkdir -p ~/bin
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
```

#### 下载代码：

创建目录，init代码库,__这个地方需要翻墙__.

```
cd ~/sourcecode/
repo init -u https://github.com/LineageOS/android.git -b cm-14.1
```

```shell
repo sync -j8 --no-tags 
```

#### 编译代码：

之后就可以编译代码

```shell
source build/envsetup.sh
breakfast hammerhead
brunch hammerhead
```

在breakfast hammerhead的时候会去github中查询对应的kernel和device相关信息。然后下载对应的库，驱动部分。

brunch就相当于我们平时使用的make命令

#### 烧机：

烧机就比较简单了，编译完成后的东西都在out目录。有一个lineage-14.1-unofficial.zip

直接将recovery.img刷入到手机中：

```shell
fastboot flash recovery recovery.img
```

重启至recovery，然后使用sideload将zip刷入：

```shell
adb sideload Lineage*.zip
```



#### 遇到的问题：

1.刷机后无法开机问题：

我不知道是库的依赖有问题还是就是少添加了库，在烧完机后，kernel无法启动，都无法启动到android部分。判断是驱动出了问题，于是在添加了以下代码在

```xml
.repo/local_manifest/roomservice.xml

  <project name="LineageOS/android_packages_resources_devicesettings" path="packages/resources/devicesettings" remote="github" />
  <project name="TheMuppets/proprietary_vendor_lge" path="vendor/lge" remote="github" />
```

重新sync代码，更新了以上的两个驱动库之后，编译完成可以启动。


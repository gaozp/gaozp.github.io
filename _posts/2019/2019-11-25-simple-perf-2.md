---
layout: post
title: simple-perf工具（二）
categories: [tech]
---
#### Android application profiling 安卓应用性能剖析
正常使用simple-perf需要经过3个步骤：
1. 准备一个安卓应用
2. 记录剖析数据
3. 报告剖析数据
#### 1.准备一个安卓应用
1. 如果你想剖析一个debug版本的应用
只要manifest中设置了 android::debuggable="true"，那么就可以，不需要其他的操作。
2. 如果你想剖析一个发行版本的应用
因为发行版本的debuggable已经被设置成了false在Androidmanifest中，同时jnicheck也已经被关闭了，c/c++代码优化也已经打开。所以simpleperf只能在以下的情况下剖析发行版本的应用：
a.如果你手机是root，那么你可以剖析任何应用
b.如果你在大于O的版本上，可以使用wrap.sh来进行
```
step1:将debuggable=true设置在AndroidManifes.xml
中
<manifest ...>
    <application android::debuggable="true" ...>

step2:将wrap.sh 放在lib/arch下
android {
    buildTypes {
        release {
            sourceSets {
                release {
                    resources {
                        srcDir {
                            "wrap_sh_lib_dir"
                        }
                    }
                }
            }
        }
    }
}

task createWrapShLibDir
    for (String abi : ["armeabi", "armeabi-v7a", "arm64-v8a", "x86", "x86_64"]) {
        def dir = new File("app/wrap_sh_lib_dir/lib/" + abi)
        dir.mkdirs()
        def wrapFile = new File(dir, "wrap.sh")
        wrapFile.withWriter { writer ->
            writer.write('#!/system/bin/sh\n\$@\n')
        }
    }
}
```
关于[wrap.sh](https://developer.android.com/ndk/guides/wrap-script.html),链接有更多的原理解释。
3. 如果你想剖析一个c/c++的代码
4. 如果你想剖析一个java代码
#### 记录和剖析数据
我们可以使用app-profiler.py来进行数据的手机
```
# Cd to the directory of simpleperf scripts. Record perf.data.
# -p option selects the profiled app using its package name.
# --compile_java_code option compiles Java code into native instructions, which isn't needed on
# Android >= P.
# -a option selects the Activity to profile.
# -lib option gives the directory to find debug native libraries.
$ python app_profiler.py -p com.packagename --compile_java_code
```
这个时候产生的数据是perf.data或者我们也可以重定向到另外的名字中：
```
python app_profiler.py -p com.baidu.BaiduMap  > info.data
# 改变记录的时长
python app_profiler.py -p com.baidu.BaiduMap  -r "--duration 20"
# 记录函数的调用图
# Record dwarf based call graphs: add "-g" in the -r option.
$ python app_profiler.py -p com.baidu.BaiduMap  -r "-e task-clock:u -f 1000 --duration 10 -g" 
```
具体的参数可以查看script的说明文档  
当我们拿到数据后，我们就可以对数据进行分析了，可以采用终端记录的方式，也可以采用html的形式：
```
# Report perf.data in stdio interface.
$ python report.py
Cmdline: /data/data/com.example.simpleperf.simpleperfexamplewithnative/simpleperf record ...
Arch: arm64
Event: task-clock:u (type 1, config 1)
Samples: 10023
Event count: 10023000000

Overhead  Command     Pid   Tid   Shared Object              Symbol
27.04%    BusyThread  5703  5729  /system/lib64/libart.so    art::JniMethodStart(art::Thread*)
25.87%    BusyThread  5703  5729  /system/lib64/libc.so      long StrToI<long, ...
...


#采用html的形式：
# Report perf.data in html interface.
$ python report_html.py

# Add source code and disassembly. Change the path of source_dirs if it not correct.
$ python report_html.py --add_source_code --source_dirs path_of_SimpleperfExampleWithNative \
      --add_disassembly
```
#### 记录和剖析函数的调用关系
```
# Record dwarf based call graphs: add "-g" in the -r option.
$ python app_profiler.py -p com.example.simpleperf.simpleperfexamplewithnative \
        -r "-e task-clock:u -f 1000 --duration 10 -g" -lib path_of_SimpleperfExampleWithNative

# Record stack frame based call graphs: add "--call-graph fp" in the -r option.
$ python app_profiler.py -p com.example.simpleperf.simpleperfexamplewithnative \
        -r "-e task-clock:u -f 1000 --duration 10 --call-graph fp" \
        -lib path_of_SimpleperfExampleWithNative

# Report call graphs in stdio interface.
$ python report.py -g

# Report call graphs in python Tk interface.
$ python report.py -g --gui

# Report call graphs in html interface.
$ python report_html.py

# Report call graphs in flamegraphs.
# On Windows, use inferno.bat instead of ./inferno.sh.
$ ./inferno.sh -sc
```
#### 显示火焰图
```
# On Windows, use inferno.bat instead of ./inferno.sh.
$ ./inferno.sh -sc
```
#### 记录cpu和gpu时间
1.检查机器是否支持
```
$ python run_simpleperf_on_device.py list --show-features
dwarf-based-call-graph
trace-offcpu
```
2.如果机器支持的话
```
$ python app_profiler.py -p com.example.simpleperf.simpleperfexamplewithnative -a .SleepActivity \
    -r "-g -e task-clock:u -f 1000 --duration 10 --trace-offcpu" \
    -lib path_of_SimpleperfExampleWithNative
$ python report_html.py --add_disassembly --add_source_code \
    --source_dirs path_of_SimpleperfExampleWithNative
```
#### 从启动开始数据记录
```
# Start simpleperf recording, then start the Activity to profile.
$ python app_profiler.py -p com.example.simpleperf.simpleperfexamplewithnative -a .MainActivity

# We can also start the Activity on the device manually.
# 1. Make sure the application isn't running or one of the recent apps.
# 2. Start simpleperf recording.
$ python app_profiler.py -p com.example.simpleperf.simpleperfexamplewithnative
# 3. Start the app manually on the device.
```
#### 手动去解析数据
我们也可以自己去写一些python脚本去解析收集到的数据。可以使用 simpleperf_report_lib.py。report_sample.py和report_html.py都是基于这个python脚本去做的。

---
layout: post
title: simple-perf工具（四）
categories: [tech]
---
#### 概述
其实前面已经说明了simpleperf具体是如何使用的，直接使用脚本的话会方便很多，但是我们也需要知道对应的二进制执行文件是如何进行工作的，需要做到知其然知其所以然。
#### simpleperf如何工作
现代cpu有一个硬件叫做performance monitoring unit(pmu),PMU 有硬件计数器，用来计算如多少个cpu周期，多少个指令已经执行了，还有多少的cache misses出现了。  
linux内核将这些硬件事件包装成性能事件，同时，linux内核还提供了独立于硬件的软件时间和跟踪点时间，linux内核通过perf_event_open系统调用将这些都暴露给了用户控件，这正是simpleperf所使用的机制。  
simpleperf主要使用了3个命令：stat，record和report：
##### stat工作原理
stat命令给了一个在特定时间段的特定进程发生了多少次性能事件：  
1.通过用户提供的参数，simpleperf通过系统调用到kernel   
2.kernel启用pmu的计数器来收集对应进程的数据  
3.收集结束后，simpleperf获取到内核提供的数据，展示给用户  
##### record工作原理
1.通过用户提供的参数，simpleperf系统调用到kernel  
2.simpleperf创建一个buffer用来打通simpleperf和kernel  
3.kernel启动pmu的计数器来对收集对应进程的数据  
4.每当预设的事件发生的时候，kernel会创建一个快照到buffer中  
5.simpleperf从buffer中读取对应的快照放在perf.data中  

#### 命令：
simpleperf支持的命令包括了：
```
The debug-unwind command: debug/test dwarf based offline unwinding, used for debugging simpleperf.
The dump command: dumps content in perf.data, used for debugging simpleperf.
The help command: prints help information for other commands.
The kmem command: collects kernel memory allocation information (will be replaced by Python scripts).
The list command: lists all event types supported on the Android device.
The record command: profiles processes and stores profiling data in perf.data.
The report command: reports profiling data in perf.data.
The report-sample command: reports each sample in perf.data, used for supporting integration of
                           simpleperf in Android Studio.
The stat command: profiles processes and prints counter summary.
```
以上的命令的支持--help参数
```
# List all commands.
$ simpleperf --help

# Print help message for record command.
$ simpleperf record --help
```
下面是一些常用的命令，list,stat,record和report
##### list
```
$ simpleperf list
List of hw-cache events:
  branch-loads
  ...
List of hardware events:
  cpu-cycles
  instructions
  ...
List of software events:
  cpu-clock
  task-clock
  ...
```
在arm的平台上，list命令还会额外的显示一些事件，这些时间也都是被arm PMU所支持的。
##### stat
stat命令是用来获取到对应的事件数量，通过输入不同的参数，我们可以获取到不同的数据
```
# Stat using default events (cpu-cycles,instructions,...), and monitor process 7394 for 10 seconds.
$ simpleperf stat -p 7394 --duration 10
Performance counter statistics:

 1,320,496,145  cpu-cycles         # 0.131736 GHz                     (100%)
   510,426,028  instructions       # 2.587047 cycles per instruction  (100%)
     4,692,338  branch-misses      # 468.118 K/sec                    (100%)
886.008130(ms)  task-clock         # 0.088390 cpus used               (100%)
           753  context-switches   # 75.121 /sec                      (100%)
           870  page-faults        # 86.793 /sec                      (100%)

Total test time: 10.023829 seconds.
```
选择不同的时间来进行展示
```
# Stat event cpu-cycles.
$ simpleperf stat -e cpu-cycles -p 11904 --duration 10

# Stat event cache-references and cache-misses.
$ simpleperf stat -e cache-references,cache-misses -p 11904 --duration 10
```
选择不同的进程来进行分析
```
# Stat process 11904 and 11905.
$ simpleperf stat -p 11904,11905 --duration 10

# Stat thread 11904 and 11905.
$ simpleperf stat -t 11904,11905 --duration 10

# Start a child process running `ls`, and stat it.
$ simpleperf stat ls

# Stat the process of an Android application. This only works for debuggable apps on non-rooted
# devices.
$ simpleperf stat --app com.example.simpleperf.simpleperfexamplewithnative

# Stat system wide using -a.
$ simpleperf stat -a --duration 10
```
选择不同的收集时长
```
# Stat process 11904 for 10 seconds.
$ simpleperf stat -p 11904 --duration 10

# Stat until the child process running `ls` finishes.
$ simpleperf stat ls

# Stop monitoring using Ctrl-C.
$ simpleperf stat -p 11904 --duration 10
^C
```
周期的进行打印
```
# Print stat for process 11904 every 300ms.
$ simpleperf stat -p 11904 --duration 10 --interval 300

# Print system wide stat at interval of 300ms for 10 seconds. Note that system wide profiling needs
# root privilege.
$ su 0 simpleperf stat -a --duration 10 --interval 300
```
**在systrace中显示**
```
# Capture instructions (kernel only) and cache misses with interval of 300 milliseconds for 15
# seconds.
$ su 0 simpleperf stat -e instructions:k,cache-misses -a --interval 300 --duration 15
# On host launch systrace to collect trace for 10 seconds.
(HOST)$ external/chromium-trace/systrace.py --time=10 -o new.html sched gfx view
# Open the collected new.html in browser and perf counters will be shown up.
```
##### record命令
record命令就是用来dump快照的，快照包含许多的信息如快照的创建时间点妈蛋，从上个快照到这个快照的所有perf事件。  
```
# Record on process 7394 for 10 seconds, using default event (cpu-cycles), using default sample
# frequency (4000 samples per second), writing records to perf.data.
$ simpleperf record -p 7394 --duration 10
simpleperf I cmd_record.cpp:316] Samples recorded: 21430. Samples lost: 0.
```
选择事件来进行record  
默认 cpu-cycles是默认的事件，我们可以选择其他事件。
```
# Record using event instructions.
$ simpleperf record -e instructions -p 11904 --duration 10

# Record using task-clock, which shows the passed CPU time in nanoseconds.
$ simpleperf record -e task-clock -p 11904 --duration 10
```
选择不同的进程来进行record
```
# Record process 11904 and 11905.
$ simpleperf record -p 11904,11905 --duration 10

# Record thread 11904 and 11905.
$ simpleperf record -t 11904,11905 --duration 10

# Record a child process running `ls`.
$ simpleperf record ls

# Record the process of an Android application. This only works for debuggable apps on non-rooted
# devices.
$ simpleperf record --app com.example.simpleperf.simpleperfexamplewithnative

# Record system wide.
$ simpleperf record -a --duration 10
```
选择记录的频率
```
# Record with sample frequency 1000: sample 1000 times every second running.
$ simpleperf record -f 1000 -p 11904,11905 --duration 10

# Record with sample period 100000: sample 1 time every 100000 events.
$ simpleperf record -c 100000 -t 11904,11905 --duration 10
```
选择记录的时长
```
# Record process 11904 for 10 seconds.
$ simpleperf record -p 11904 --duration 10

# Record until the child process running `ls` finishes.
$ simpleperf record ls

# Stop monitoring using Ctrl-C.
$ simpleperf record -p 11904 --duration 10
^C
```
选择文件存放路径
```
# Write records to data/perf2.data.
$ simpleperf record -p 11904 -o data/perf2.data --duration 10
```
##### 记录调用栈
一个调用栈就是如图：
```
main() {
    FunctionOne();
    FunctionTwo();
}
FunctionOne() {
    FunctionTwo();
    FunctionThree();
}
a call graph:
    main-> FunctionOne
       |    |
       |    |-> FunctionTwo
       |    |-> FunctionThree
       |
       |-> FunctionTwo
```
两种方式可以获取到调用栈:一种是记录一个短小的调用图，还有一种是基于调用帧的调用图。
```
# Record a dwarf based call graph
$ simpleperf record -p 11904 -g --duration 10

# Record a stack frame based call graph
$ simpleperf record -p 11904 --call-graph fp --duration 10
```
##### 记录cpu时间
simpleperf是一个记录cpu时间的，但是它只能记录当前进程在cpu上跑的时候的快照。有时候我们也需要知道当前进程还有哪些地方也花了时间，不管他是不是在cpu上跑，或者只是进入了等待队列。或者在等待io事件。  
为了支持这种情况，使用 --trace-offcpu 可以同时记录cpu时间和非cpu时间。  
trace-offcpu 是使用 sched:sched_switch的trace记录点，这可能在旧版本的kernel上并不适用。但是可以确保在android8.1后的版本上，可以使用下面的命令来查看是否有这个feature。
```
$ simpleperf list --show-features
dwarf-based-call-graph
trace-offcpu
```
如果支持这个feature，那么就可以尝试着使用它
```
# Record with --trace-offcpu.
$ simpleperf record -g -p 11904 --duration 10 --trace-offcpu

# Record with --trace-offcpu using app_profiler.py.
$ python app_profiler.py -p com.example.simpleperf.simpleperfexamplewithnative -a .SleepActivity \
    -r "-g -e task-clock:u -f 1000 --duration 10 --trace-offcpu"
```
##### report命令
report命令是用来解析和输出用record命令收集的数据。例子：
```
# Reports perf.data, using only records sampled in libsudo-game-jni.so, grouping records using
# thread name(comm), process id(pid), thread id(tid), function name(symbol), and showing sample
# count for each row.
$ simpleperf report --dsos /data/app/com.example.sudogame-2/lib/arm64/libsudo-game-jni.so \
      --sort comm,pid,tid,symbol -n
Cmdline: /data/data/com.example.sudogame/simpleperf record -p 7394 --duration 10
Arch: arm64
Event: cpu-cycles (type 0, config 0)
Samples: 28235
Event count: 546356211

Overhead  Sample  Command    Pid   Tid   Symbol
59.25%    16680   sudogame  7394  7394  checkValid(Board const&, int, int)
20.42%    5620    sudogame  7394  7394  canFindSolution_r(Board&, int, int)
13.82%    4088    sudogame  7394  7394  randomBlock_r(Board&, int, int, int, int, int)
6.24%     1756    sudogame  7394  7394  @plt
```
设置使用不同的文件
```
$ simpleperf report -i data/perf2.data
```
设置symbols路径
```
# In this case, when simpleperf wants to read executable binary /A/b, it reads file in /A/b.
$ simpleperf report

# In this case, when simpleperf wants to read executable binary /A/b, it prefers file in
# /debug_dir/A/b to file in /A/b.
$ simpleperf report --symfs /debug_dir

# Read symbols for system libraries built locally. Note that this is not needed since Android O,
# which ships symbols for system libraries on device.
$ simpleperf report --symfs $ANDROID_PRODUCT_OUT/symbols
```
过滤信息
```
# Report records in threads having name sudogame.
$ simpleperf report --comms sudogame

# Report records in process 7394 or 7395
$ simpleperf report --pids 7394,7395

# Report records in thread 7394 or 7395.
$ simpleperf report --tids 7394,7395

# Report records in libsudo-game-jni.so.
$ simpleperf report --dsos /data/app/com.example.sudogame-2/lib/arm64/libsudo-game-jni.so
```
排序信息
```
# Group records based on their process id: records having the same process id are in the same
# sample entry.
$ simpleperf report --sort pid

# Group records based on their thread id and thread comm: records having the same thread id and
# thread name are in the same sample entry.
$ simpleperf report --sort tid,comm

# Group records based on their binary and function: records in the same binary and function are in
# the same sample entry.
$ simpleperf report --sort dso,symbol

# Default option: --sort comm,pid,tid,dso,symbol. Group records in the same thread, and belong to
# the same function in the same binary.
$ simpleperf report
```
显示调用栈
```
$ simpleperf report -g
```
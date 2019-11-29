---
layout: post
title: simple-perf工具（三）
categories: [tech]
---
#### android平台进行数据剖析
给Android系统开发的几点建议：  
1.在adb root之后，simpleperf可以用来收集任何进程的信息。  
2.建议采用最新的simpleperf版本，最新的脚本在system/extras/simpleperf/scripts，而最新的二进制可执行程序在：system/extras/simpleperf/scripts/bin/android。  
3.建议采用app_profiler.py和report_html.py来提高使用简便程度。  
```
# Record surfaceflinger process for 10 seconds with dwarf based call graph. More examples are in
# scripts reference in the doc.
$ python app_profiler.py -np surfaceflinger -r "-g --duration 10"

# Generate html report.
$ python report_html.py
```
4.因为android o之后的系统都有symbols在收集中，所以我们不需要再使用$ANDROID_PRODUCT_OUT/symbols来进行调用栈的手机，但是这些还是需要来添加一些信息如line number在报告中的。
```
# Doing recording with app_profiler.py or simpleperf on device, and generates perf.data on host.
$ python app_profiler.py -np surfaceflinger -r "--call-graph fp --duration 10"

# Collect unstripped binaries from $ANDROID_PRODUCT_OUT/symbols to binary_cache/.
$ python binary_cache_builder.py -lib $ANDROID_PRODUCT_OUT/symbols

# Report source code and disassembly. Disassembling all binaries is slow, so it's better to add
# --binary_filter option to only disassemble selected binaries.
$ python report_html.py --add_source_code --source_dirs $ANDROID_BUILD_TOP --add_disassembly \
  --binary_filter surfaceflinger.so
```---
layout: post
title: simple-perf工具（四）
categories: [tech]
---
#### QUESTION:

#### EXPLANATION:

#### SOLUTION:
```java
```

---
layout: post
title: Android lowmemorykiller源码分析（四）
categories: [tech]
---
#### 前言
上次说到了内核空间的lmk是如何进行查杀的，简单来说就是依据/proc/pid/oom_score_adj来进行查杀adj最大的并且占用内存最多的。那么这次来看看n之后采用的用户控件的lmkd的查杀方式。
#### 遗留的两个问题：
##### LMK_TARGET的处理：
将对应的值写到minfree和adj中。如果采用的用户控件的lmkd，那么就保存在lowmem_minfree和lowmem_adj两个数组中
```C
static void cmd_target(int ntargets, LMKD_CTRL_PACKET packet) {
    int i;
    struct lmk_target target;

    if (ntargets > (int)ARRAY_SIZE(lowmem_adj))
        return;

    for (i = 0; i < ntargets; i++) {
        lmkd_pack_get_target(packet, i, &target);
        lowmem_minfree[i] = target.minfree;
        lowmem_adj[i] = target.oom_adj_score;
    }

    lowmem_targets_size = ntargets;

    if (has_inkernel_module) {
        char minfreestr[128];
        char killpriostr[128];

        minfreestr[0] = '\0';
        killpriostr[0] = '\0';

        for (i = 0; i < lowmem_targets_size; i++) {
            char val[40];

            if (i) {
                strlcat(minfreestr, ",", sizeof(minfreestr));
                strlcat(killpriostr, ",", sizeof(killpriostr));
            }

            snprintf(val, sizeof(val), "%d", use_inkernel_interface ? lowmem_minfree[i] : 0);
            strlcat(minfreestr, val, sizeof(minfreestr));
            snprintf(val, sizeof(val), "%d", use_inkernel_interface ? lowmem_adj[i] : 0);
            strlcat(killpriostr, val, sizeof(killpriostr));
        }

        writefilestring(INKERNEL_MINFREE_PATH, minfreestr);
        writefilestring(INKERNEL_ADJ_PATH, killpriostr);
    }
}
```
##### LMK_PROCREMOVE的处理：
如果采用的是kernel的，那么进程的杀死就proc/pid/的移除就交给内核去完成，如果采用的是用户控件的lmkd，那么就在对应的hash表中去除该进程。
```c
static void cmd_procremove(LMKD_CTRL_PACKET packet) {
    struct lmk_procremove params;

    if (use_inkernel_interface)
        return;

    lmkd_pack_get_procremove(packet, &params);
    pid_remove(params.pid);
}
```
#### LMKD中的hash表
在cmd_procprio的设置中，我们可以看到，如果采用内核就在proc/pid/oom_score_adj中写入即可，否则需要修改对应的hash表：
```c
// 在hash表中进行寻找
    procp = pid_lookup(params.pid);
    if (!procp) {
            procp = malloc(sizeof(struct proc));
            if (!procp) {
                // Oh, the irony.  May need to rebuild our state.
                return;
            }
            // 没找到说明是新创建的进程，需要添加到hash表中
            procp->pid = params.pid;
            procp->uid = params.uid;
            procp->oomadj = params.oomadj;
            proc_insert(procp);
    } else { // 找到了就直接更新
        proc_unslot(procp);
        procp->oomadj = params.oomadj;
        proc_slot(procp);
    }
```
那么就查看pid_lookup函数：
```c
static struct proc *pid_lookup(int pid) {
    struct proc *procp;

    for (procp = pidhash[pid_hashfn(pid)]; procp && procp->pid != pid;
         procp = procp->pidhash_next)
            ;

    return procp;
}
```
对应的结构体：需要注意的是有两个hash表来记录进程的信息。  
可以看到pidhash是一个长度为1024的以pid经过hash转换后为index的数组。  
procadjslot_list而是一个以adj为index的数组，内部存储的。  
```c
#define PIDHASH_SZ 1024
static struct proc *pidhash[PIDHASH_SZ];
#define pid_hashfn(x) ((((x) >> 8) ^ (x)) & (PIDHASH_SZ - 1)) //通过pid的值经过hash转换来获取到对应的index

// 对应的proc的结构体
struct proc {
    struct adjslot_list asl;
    int pid;
    uid_t uid;
    int oomadj;
    struct proc *pidhash_next;
};

static void proc_insert(struct proc *procp) {
    int hval = pid_hashfn(procp->pid); // 通过hash转换获取到对应的index

    procp->pidhash_next = pidhash[hval]; // 将原本位置上的值往后挪一个
    pidhash[hval] = procp; // 将这次的值放在第一个
    proc_slot(procp);
}

static void proc_slot(struct proc *procp) {
    int adjslot = ADJTOSLOT(procp->oomadj);

    adjslot_insert(&procadjslot_list[adjslot], &procp->asl); // 往下看
}

#define ADJTOSLOT(adj) ((adj) + -OOM_SCORE_ADJ_MIN) // 直接将adj的值+1000，这样所有的adj都在0-2000内
#define ADJTOSLOT_COUNT (ADJTOSLOT(OOM_SCORE_ADJ_MAX) + 1) 
static struct adjslot_list procadjslot_list[ADJTOSLOT_COUNT];

// 一个双向链表
struct adjslot_list {
    struct adjslot_list *next;
    struct adjslot_list *prev;
};
// 将新的插入到链表的头部
static void adjslot_insert(struct adjslot_list *head, struct adjslot_list *new)
{
    struct adjslot_list *next = head->next;
    new->prev = head;
    new->next = next;
    next->prev = new;
    head->next = new;
}
这个地方有两个点是比较难以理解的：  
1. pidhash是以pid的hash值为key，所以有可能两个不同的pid，会有相同的hash值，所以才会有链表。  
2. procadjslot_list的双向链表，存储的是adjslot_list，正好是struct proc的第一个指针，根据c的指针强转，可以被强转成proc的数据结构。换句话说，也就是procadjslot_list存储的就是以oomadj为index，lru的proc双向链表。  
```
#### 触发lowmemory事件：
在第一篇的时候已经提到了，在init的时候，会init_mp_common进行内存监听的初始化，而对应的handler就是mp_event_common
```C
static void mp_event_common(int data, uint32_t events __unused) {

    ...

    if (use_minfree_levels) {
        int i;

        other_free = mi.field.nr_free_pages - zi.field.totalreserve_pages;
        if (mi.field.nr_file_pages > (mi.field.shmem + mi.field.unevictable + mi.field.swap_cached)) {
            other_file = (mi.field.nr_file_pages - mi.field.shmem -
                          mi.field.unevictable - mi.field.swap_cached);
        } else {
            other_file = 0;
        }

        min_score_adj = OOM_SCORE_ADJ_MAX + 1;
        for (i = 0; i < lowmem_targets_size; i++) {
            minfree = lowmem_minfree[i];
            if (other_free < minfree && other_file < minfree) {
                min_score_adj = lowmem_adj[i];
                break;
            }
        }

        if (min_score_adj == OOM_SCORE_ADJ_MAX + 1) {
            if (debug_process_killing) {
                ALOGI("Ignore %s memory pressure event "
                      "(free memory=%ldkB, cache=%ldkB, limit=%ldkB)",
                      level_name[level], other_free * page_k, other_file * page_k,
                      (long)lowmem_minfree[lowmem_targets_size - 1] * page_k);
            }
            return;
        }

        /* Free up enough pages to push over the highest minfree level */
        pages_to_free = lowmem_minfree[lowmem_targets_size - 1] -
            ((other_free < other_file) ? other_free : other_file);
        goto do_kill;
    }
}
```
这里的use_minfree_levels是从系统属性中读取到的，意义为党我们准备杀掉进程的时候，使用系统剩余的内存和文件缓存阈值作为判断条件。
```C
    if (level == VMPRESS_LEVEL_LOW) {
        record_low_pressure_levels(&mi);
    }

    if (level_oomadj[level] > OOM_SCORE_ADJ_MAX) {
        /* Do not monitor this pressure level */
        return;
    }

    if ((mem_usage = get_memory_usage(&mem_usage_file_data)) < 0) {
        goto do_kill;
    }
    if ((memsw_usage = get_memory_usage(&memsw_usage_file_data)) < 0) {
        goto do_kill;
    }
```
而get_memory_usage的实现就比较简单，就是读取对应的文件。  
终于来到了最重要的do_kill
#### do_kill
```c
do_kill:
...
        int pages_freed;

        if (!use_minfree_levels) {
            /* If pressure level is less than critical and enough free swap then ignore */
            if (level < VMPRESS_LEVEL_CRITICAL &&
                mi.field.free_swap > low_pressure_mem.max_nr_free_pages) {
                if (debug_process_killing) {
                    ALOGI("Ignoring pressure since %" PRId64
                          " swap pages are available ",
                          mi.field.free_swap);
                }
                return;
            }
            /* Free up enough memory to downgrate the memory pressure to low level */
            if (mi.field.nr_free_pages < low_pressure_mem.max_nr_free_pages) {
                pages_to_free = low_pressure_mem.max_nr_free_pages -
                    mi.field.nr_free_pages;
            } else {
                if (debug_process_killing) {
                    ALOGI("Ignoring pressure since more memory is "
                        "available (%" PRId64 ") than watermark (%" PRId64 ")",
                        mi.field.nr_free_pages, low_pressure_mem.max_nr_free_pages);
                }
                return;
            }
            min_score_adj = level_oomadj[level];
        }

        pages_freed = find_and_kill_processes(level, min_score_adj, pages_to_free);

        if (use_minfree_levels) {
            ALOGI("Killing because cache %ldkB is below "
                  "limit %ldkB for oom_adj %d\n"
                  "   Free memory is %ldkB %s reserved",
                  other_file * page_k, minfree * page_k, min_score_adj,
                  other_free * page_k, other_free >= 0 ? "above" : "below");
        }

        if (pages_freed < pages_to_free) {
            ALOGI("Unable to free enough memory (pages to free=%d, pages freed=%d)",
                  pages_to_free, pages_freed);
        } else {
            ALOGI("Reclaimed enough memory (pages to free=%d, pages freed=%d)",
                  pages_to_free, pages_freed);
            gettimeofday(&last_report_tm, NULL);
        }
    }
```
这里可以看到，最重要的方法便是find_and_kill_processes，但是也不可以忽视一开始的变量，一开始就已经计算出了需要回收的页数，那么lmkd的逻辑就是一次性直接回收到需要的内存数。  
#### find_and_kill_processes
而查找对应的进程用到了kill_heaviest_task属性，这个属性也是从系统属性中去获取到的。
```c
static int find_and_kill_processes(enum vmpressure_level level,
                                   int min_score_adj, int pages_to_free) {
    int i;
    int killed_size;
    int pages_freed = 0;

#ifdef LMKD_LOG_STATS
    bool lmk_state_change_start = false;
#endif

    for (i = OOM_SCORE_ADJ_MAX; i >= min_score_adj; i--) {
        struct proc *procp;

        while (true) {
            procp = kill_heaviest_task ? //这里的参数
                proc_get_heaviest(i) : proc_adj_lru(i);

            if (!procp)
                break;

            killed_size = kill_one_process(procp, min_score_adj, level);
            if (killed_size >= 0) {
#ifdef LMKD_LOG_STATS
                if (enable_stats_log && !lmk_state_change_start) {
                    lmk_state_change_start = true;
                    stats_write_lmk_state_changed(log_ctx, LMK_STATE_CHANGED,
                                                  LMK_STATE_CHANGE_START);
                }
#endif

                pages_freed += killed_size;
                if (pages_freed >= pages_to_free) { // 如果释放的内存已经达到了阈值，那么就直接返回

#ifdef LMKD_LOG_STATS
                    if (enable_stats_log && lmk_state_change_start) {
                        stats_write_lmk_state_changed(log_ctx, LMK_STATE_CHANGED,
                                LMK_STATE_CHANGE_STOP);
                    }
#endif
                    return pages_freed;
                }
            }
        }
    }

#ifdef LMKD_LOG_STATS
    if (enable_stats_log && lmk_state_change_start) {
        stats_write_lmk_state_changed(log_ctx, LMK_STATE_CHANGED, LMK_STATE_CHANGE_STOP);
    }
#endif

    return pages_freed;
}
```
这里比较关键的就是两个查找proc的方法了。一个是lru算法的，另外一个则是找到内存占用最大的proc。
##### 查找内存lru算法
算法很简单，因为每次链表的插入都放在head后面，那么head前面一个就是最后使用的，因为这个链表是一个头尾相连的环形链表。所以head->prev就是最后使用的.这里还有一个就是asl为什么可以强转成proc的问题，因为asl是proc的第一个参数，asl的指针也可以直接视为proc的指针，只不过内存占用长度不同。
```C
static struct proc *proc_adj_lru(int oomadj) {
    return (struct proc *)adjslot_tail(&procadjslot_list[ADJTOSLOT(oomadj)]);
}
static struct adjslot_list *adjslot_tail(struct adjslot_list *head) {
    struct adjslot_list *asl = head->prev;

    return asl == head ? NULL : asl;
}
//环形链表
static int init(void) {
    for (i = 0; i <= ADJTOSLOT(OOM_SCORE_ADJ_MAX); i++) {
        procadjslot_list[i].next = &procadjslot_list[i];
        procadjslot_list[i].prev = &procadjslot_list[i];
    }
}
```
##### 查找内存占用最大的
这个就比较简单了，就是拿到oomadj对应的链表，进行查找，因为是头尾循环，那么判断条件就是curr!=head，就可以找到内存占用最大的proc的。
```C
static struct proc *proc_get_heaviest(int oomadj) {
    struct adjslot_list *head = &procadjslot_list[ADJTOSLOT(oomadj)];
    struct adjslot_list *curr = head->next;
    struct proc *maxprocp = NULL;
    int maxsize = 0;
    while (curr != head) {
        int pid = ((struct proc *)curr)->pid;
        int tasksize = proc_get_size(pid);
        if (tasksize <= 0) {
            struct adjslot_list *next = curr->next;
            pid_remove(pid);
            curr = next;
        } else {
            if (tasksize > maxsize) {
                maxsize = tasksize;
                maxprocp = (struct proc *)curr;
            }
            curr = curr->next;
        }
    }
    return maxprocp;
}
```
#### kill_one_process
```c
/* Kill one process specified by procp.  Returns the size of the process killed */
static int kill_one_process(struct proc* procp, int min_score_adj,
                            enum vmpressure_level level) {
    int pid = procp->pid;
    uid_t uid = procp->uid;
    char *taskname;
    int tasksize;
    int r;

#ifdef LMKD_LOG_STATS
    struct memory_stat mem_st = {};
    int memory_stat_parse_result = -1;
#endif

    taskname = proc_get_name(pid);
    if (!taskname) {
        pid_remove(pid);
        return -1;
    }

    tasksize = proc_get_size(pid);
    if (tasksize <= 0) {
        pid_remove(pid);
        return -1;
    }

#ifdef LMKD_LOG_STATS
    if (enable_stats_log) {
        memory_stat_parse_result = memory_stat_parse(&mem_st, pid, uid);
    }
#endif

    TRACE_KILL_START(pid);

    r = kill(pid, SIGKILL); //直接发signal_kill给进程
    ALOGI(
        "Killing '%s' (%d), uid %d, adj %d\n"
        "   to free %ldkB because system is under %s memory pressure oom_adj %d\n",
        taskname, pid, uid, procp->oomadj, tasksize * page_k,
        level_name[level], min_score_adj);
    pid_remove(pid);

    TRACE_KILL_END();

    if (r) {
        ALOGE("kill(%d): errno=%d", pid, errno);
        return -1;
    } else {
#ifdef LMKD_LOG_STATS
        if (memory_stat_parse_result == 0) {
            stats_write_lmk_kill_occurred(log_ctx, LMK_KILL_OCCURRED, uid, taskname,
                    procp->oomadj, mem_st.pgfault, mem_st.pgmajfault, mem_st.rss_in_bytes,
                    mem_st.cache_in_bytes, mem_st.swap_in_bytes);
        }
#endif
        return tasksize;
    }

    return tasksize;
}
```
这个一目了然，直接发送signal_kill给进程来进行查杀。
#### 总结
lmkd用户空间的杀进程也说完了。其中比较关键的点就是两个数据结构，一个是pid的hash值为key的pid链表，虽然并没有写到，但是在lmkd中还是用到挺多的。另外一个就是以oomadj为index的双向循环链表，这个结构体因为包含了很多信息：  
1.按照oomadj进行了排序  
2.按照lru算法进行了排序  
所以这个数据结构可以做很多的事情，而lmkd的杀进程就很大的程度上依赖了这个数据结构。 
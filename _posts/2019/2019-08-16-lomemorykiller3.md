---
layout: post
title: Android lowmemorykiller源码分析（三）
categories: [tech]
---
#### 前言
上篇说到frameowrk层是如何将消息传到lmkd的，那么这篇就看一下lmkd收到消息后，使用的是kernel中的lmk进行杀进程是怎么样的一个过程。
#### lmkd中的消息处理：
由上篇文章可以知道，在process.setoomadj的时候就会发送消息给lmkd，那么lmkd处理的是cmd_procprio方法
```C
    switch(cmd) {
    case LMK_TARGET:
        targets = nargs / 2;
        if (nargs & 0x1 || targets > (int)ARRAY_SIZE(lowmem_adj))
            goto wronglen;
        cmd_target(targets, packet);
        break;
    case LMK_PROCPRIO:
        if (nargs != 3)
            goto wronglen;
        cmd_procprio(packet);
        break;
    case LMK_PROCREMOVE:
        if (nargs != 1)
            goto wronglen;
        cmd_procremove(packet);
        break;
    default:
        ALOGE("Received unknown command code %d", cmd);
        return;
    }
```
##### cmd_procprio方法：
如果使用的kernel的lmk，那么只需要在/proc/pid/oom_score_adj中写下对应的adj就可以
```c
static void cmd_procprio(LMKD_CTRL_PACKET packet) {
    struct proc *procp;
    char path[80];
    char val[20];
    int soft_limit_mult;
    struct lmk_procprio params;

    lmkd_pack_get_procprio(packet, &params);

    if (params.oomadj < OOM_SCORE_ADJ_MIN ||
        params.oomadj > OOM_SCORE_ADJ_MAX) {
        ALOGE("Invalid PROCPRIO oomadj argument %d", params.oomadj);
        return;
    }
// 在/proc/pid/oom_score_adj中写下对应的adj
    snprintf(path, sizeof(path), "/proc/%d/oom_score_adj", params.pid);
    snprintf(val, sizeof(val), "%d", params.oomadj);
    writefilestring(path, val);

    if (use_inkernel_interface)
        return;
}
```
#### 内核中的lowmemorykiller
在内核lowmemorykiller启动的时候，注册shrinker的监听
```c
static int __init lowmem_init(void)
{
	register_shrinker(&lowmem_shrinker);
	return 0;
}

static struct shrinker lowmem_shrinker = {
	.scan_objects = lowmem_scan,
	.count_objects = lowmem_count,
	.seeks = DEFAULT_SEEKS * 16
};
```
##### lowmem_scan
当接收到shrinker事件的时候，回调 lowmem_scan事件,最为关键的lowmem_scan方法，写上了对应的注释。  
/sys/module/lowmemorykiller/parameters/adj  
/sys/module/lowmemorykiller/parameters/minfree  
两者对应了剩余内存需要回收的节点
```C
static unsigned long lowmem_scan(struct shrinker *s, struct shrink_control *sc)
{

	other_free = global_zone_page_state(NR_FREE_PAGES) - totalreserve_pages;

	if (global_node_page_state(NR_SHMEM) + total_swapcache_pages() +
			global_node_page_state(NR_UNEVICTABLE) <
			global_node_page_state(NR_FILE_PAGES))
		other_file = global_node_page_state(NR_FILE_PAGES) -
					global_node_page_state(NR_SHMEM) -
					global_node_page_state(NR_UNEVICTABLE) -
					total_swapcache_pages();
	else
		other_file = 0;

	if (!get_nr_swap_pages() && (other_free <= lowmem_minfree[0] >> 1) &&
	    (other_file <= lowmem_minfree[0] >> 1))
		lock_required = false;

	if (likely(lock_required) && !mutex_trylock(&scan_mutex))
		return 0;

	tune_lmk_param(&other_free, &other_file, sc);

	scale_percent = get_minfree_scalefactor(sc->gfp_mask);
	if (lowmem_adj_size < array_size)
		array_size = lowmem_adj_size;
	if (lowmem_minfree_size < array_size)
		array_size = lowmem_minfree_size;
	for (i = 0; i < array_size; i++) {
		minfree = mult_frac(lowmem_minfree[i], scale_percent, 100);
		if (other_free < minfree && other_file < minfree) {
			min_score_adj = lowmem_adj[i];
			break;
		}
	}

	ret = adjust_minadj(&min_score_adj);

	lowmem_print(3, "%s %lu, %x, ofree %d %d, ma %hd\n",
		     __func__, sc->nr_to_scan, sc->gfp_mask, other_free,
		     other_file, min_score_adj);
    // 如果当前获取到的minscoreadj并不在我们需要关注的中，就直接stop
	if (min_score_adj == OOM_SCORE_ADJ_MAX + 1) {
		trace_almk_shrink(0, ret, other_free, other_file, 0);
		lowmem_print(5, "%s %lu, %x, return 0\n",
			     __func__, sc->nr_to_scan, sc->gfp_mask);
		if (lock_required)
			mutex_unlock(&scan_mutex);
		return SHRINK_STOP;
	}

	selected_oom_score_adj = min_score_adj;

	rcu_read_lock();
	for_each_process(tsk) { //对当前的所有进程进行循环遍历
		struct task_struct *p;
		short oom_score_adj;

		if (tsk->flags & PF_KTHREAD)
			continue;

		/* if task no longer has any memory ignore it */
		if (test_task_flag(tsk, TIF_MM_RELEASED))
			continue;

		if (oom_reaper) {
			p = find_lock_task_mm(tsk);
			if (!p)
				continue;

			if (test_bit(MMF_OOM_VICTIM, &p->mm->flags)) {
				if (test_bit(MMF_OOM_SKIP, &p->mm->flags)) {
					task_unlock(p);
					continue;
				} else if (time_before_eq(jiffies,
						lowmem_deathpending_timeout)) {
					task_unlock(p);
					rcu_read_unlock();
					if (lock_required)
						mutex_unlock(&scan_mutex);
					return 0;
				}
			}
		} else {
			if (time_before_eq(jiffies,
					   lowmem_deathpending_timeout))
				if (test_task_lmk_waiting(tsk)) {
					rcu_read_unlock();
					if (lock_required)
						mutex_unlock(&scan_mutex);
					return 0;
				}

			p = find_lock_task_mm(tsk);
			if (!p)
				continue;
		}

		oom_score_adj = p->signal->oom_score_adj;
		if (oom_score_adj < min_score_adj) {//如果当前adj小于需要回收的adj,那么就直接跳过
			task_unlock(p);
			continue;
		}
		tasksize = get_mm_rss(p->mm);
		task_unlock(p);
		if (tasksize <= 0) //如果当前内存占用《=0则直接跳过
			continue;
		if (selected) { //第一次循环并不会经过，第二次循环会
			if (oom_score_adj < selected_oom_score_adj) //小于当前选择的adj，直接跳过。目的是为了选出当前系统中最大的adj
				continue;
			if (oom_score_adj == selected_oom_score_adj &&
			    tasksize <= selected_tasksize)// adj相同，小于当前size就直接跳过。目的是为了在相同adj的情况下杀掉内存占用最大的进程
				continue;
		}
		selected = p;
		selected_tasksize = tasksize;
		selected_oom_score_adj = oom_score_adj;
		lowmem_print(3, "select '%s' (%d), adj %hd, size %d, to kill\n",
			     p->comm, p->pid, oom_score_adj, tasksize);
	}
	if (selected) {
		long cache_size = other_file * (long)(PAGE_SIZE / 1024);
		long cache_limit = minfree * (long)(PAGE_SIZE / 1024);
		long free = other_free * (long)(PAGE_SIZE / 1024);

		atomic64_set(&lmk_feed, 0);
		if (test_task_lmk_waiting(selected) &&
		    (test_task_state(selected, TASK_UNINTERRUPTIBLE))) {
			lowmem_print(2, "'%s' (%d) is already killed\n",
				     selected->comm,
				     selected->pid);
			rcu_read_unlock();
			if (lock_required)
				mutex_unlock(&scan_mutex);
			return 0;
		}

		task_lock(selected);
		send_sig(SIGKILL, selected, 0);// 发送singkill，杀死进程
		if (selected->mm) {
			task_set_lmk_waiting(selected);
			if (!test_bit(MMF_OOM_SKIP, &selected->mm->flags) &&
			    oom_reaper) {
				mark_lmk_victim(selected);
				wake_oom_reaper(selected);
			}
		}
		task_unlock(selected);
		trace_lowmemory_kill(selected, cache_size, cache_limit, free);
		lowmem_print(1, "Killing '%s' (%d) (tgid %d), adj %hd,\n"
			"to free %ldkB on behalf of '%s' (%d) because\n"
			"cache %ldkB is below limit %ldkB for oom score %hd\n"
			"Free memory is %ldkB above reserved.\n"
			"Free CMA is %ldkB\n"
			"Total reserve is %ldkB\n"
			"Total free pages is %ldkB\n"
			"Total file cache is %ldkB\n"
			"GFP mask is 0x%x\n",
			selected->comm, selected->pid, selected->tgid,
			selected_oom_score_adj,
			selected_tasksize * (long)(PAGE_SIZE / 1024),
			current->comm, current->pid,
			cache_size, cache_limit,
			min_score_adj,
			free,
			global_zone_page_state(NR_FREE_CMA_PAGES) *
			(long)(PAGE_SIZE / 1024),
			totalreserve_pages * (long)(PAGE_SIZE / 1024),
			global_zone_page_state(NR_FREE_PAGES) *
			(long)(PAGE_SIZE / 1024),
			global_node_page_state(NR_FILE_PAGES) *
			(long)(PAGE_SIZE / 1024),
			sc->gfp_mask);

		if (lowmem_debug_level >= 2 && selected_oom_score_adj == 0) {
			show_mem(SHOW_MEM_FILTER_NODES, NULL);
			show_mem_call_notifiers();
			dump_tasks(NULL, NULL);
		}

		lowmem_deathpending_timeout = jiffies + HZ;
		rem += selected_tasksize;
		rcu_read_unlock();
		/* give the system time to free up the memory */
		msleep_interruptible(20);
		trace_almk_shrink(selected_tasksize, ret,
				  other_free, other_file,
				  selected_oom_score_adj);
	} else {
		trace_almk_shrink(1, ret, other_free, other_file, 0);
		rcu_read_unlock();
		if (other_free < lowmem_minfree[0] &&
		    other_file < lowmem_minfree[0])
			atomic64_set(&lmk_feed, jiffies + HZ);
		else
			atomic64_set(&lmk_feed, 0);

	}

	lowmem_print(4, "%s %lu, %x, return %lu\n",
		     __func__, sc->nr_to_scan, sc->gfp_mask, rem);
	if (lock_required)
		mutex_unlock(&scan_mutex);
	if (rem == 0)
		return SHRINK_STOP;
	else
		return rem;
}
```
需要注意的是，__shrinker事件是频繁进行上报的__，如果内存低于minfree中的档位，则会进入到杀进程的逻辑中，会在当前的进程中选择adj最大，并且占用内存最大的进行杀掉。**每次事件只会杀死一个选择的进程**。其实这样也会有问题，就是那些刚刚杀死的，立刻就重启了，其实会导致不停的进行查杀。  

#### 总结：
内核空间的流程就是：  
lowmemorykiller注册shrinker的监听，在收到事件后，进行查杀。而查杀的逻辑是：每次shrinker事件只会选择一个进程进行杀掉。而选择的逻辑就是  
1.选择adj最大的  
2.选择同样adj中内存占用最大的

   
---
layout: post
title: APP COMPACTION 源码分析(kernel层)
categories: [tech]
---
### 前言：
上篇说到了framework层其实是通过在/proc/pid/reclaim下面写入full，或者some来进行reclaim，但是，这只是frameworks层的，那么kernel层的是什么样子的呢，我们书接上回。

### 读取：
既然我们知道了我们是在向proc中去写的，那么就可以去proc文件系统中可以找到：
```cpp
#ifdef CONFIG_PROCESS_RECLAIM
    REG("reclaim",0200,proc_reclaim_operations),
#endif
```
看到注册了reclaim节点的操作：proc_reclaim_operations,zl task_mmu.c中可以看到该操作的具体：
```cpp
const struct file_operations proc_reclaim_operations = {
    .write = reclaim_write,
    .llseek = noop_llseek,
}
```
可以看到在节点write事件后，会触发reclaim_write方法：
### reclaim_write方法
这个方法很是关键，因为具体的操作逻辑都是在这个地方的。
```CPP
static ssize_t reclaim_write(struct file *file, const char __user *buf,
				size_t count, loff_t *ppos)
{
	struct task_struct *task;
	char buffer[200];
	struct mm_struct *mm;
	struct vm_area_struct *vma;
	enum reclaim_type type;
	char *type_buf;
	struct mm_walk reclaim_walk = {};
	unsigned long start = 0;
	unsigned long end = 0;
	struct reclaim_param rp;
	int ret;

	memset(buffer, 0, sizeof(buffer));
	if (count > sizeof(buffer) - 1)
		count = sizeof(buffer) - 1;

	if (copy_from_user(buffer, buf, count))
		return -EFAULT;

	type_buf = strstrip(buffer);
	if (!strcmp(type_buf, "file"))
		type = RECLAIM_FILE;
	else if (!strcmp(type_buf, "anon"))
		type = RECLAIM_ANON;
	else if (!strcmp(type_buf, "all"))
		type = RECLAIM_ALL;
	else if (isdigit(*type_buf))
		type = RECLAIM_RANGE;
	else
		goto out_err;

	if (type == RECLAIM_RANGE) {
		char *token;
		unsigned long long len, len_in, tmp;

		token = strsep(&type_buf, " ");
		if (!token)
			goto out_err;
		tmp = memparse(token, &token);
		if (tmp & ~PAGE_MASK || tmp > ULONG_MAX)
			goto out_err;
		start = tmp;

		token = strsep(&type_buf, " ");
		if (!token)
			goto out_err;
		len_in = memparse(token, &token);
		len = (len_in + ~PAGE_MASK) & PAGE_MASK;
		if (len > ULONG_MAX)
			goto out_err;
		/*
		 * Check to see whether len was rounded up from small -ve
		 * to zero.
		 */
		if (len_in && !len)
			goto out_err;

		end = start + len;
		if (end < start)
			goto out_err;
	}

	task = get_proc_task(file->f_path.dentry->d_inode);
	if (!task)
		return -ESRCH;

	mm = get_task_mm(task);
	if (!mm)
		goto out;

	reclaim_walk.mm = mm;
	reclaim_walk.pmd_entry = reclaim_pte_range;

	rp.nr_to_reclaim = INT_MAX;
	rp.nr_reclaimed = 0;
	reclaim_walk.private = &rp;

	down_read(&mm->mmap_sem);
	if (type == RECLAIM_RANGE) {
		vma = find_vma(mm, start);
		while (vma) {
			if (vma->vm_start > end)
				break;
			if (is_vm_hugetlb_page(vma))
				continue;

			rp.vma = vma;
			ret = walk_page_range(max(vma->vm_start, start),
					min(vma->vm_end, end),
					&reclaim_walk);
			if (ret)
				break;
			vma = vma->vm_next;
		}
	} else {
		for (vma = mm->mmap; vma; vma = vma->vm_next) {
			if (is_vm_hugetlb_page(vma))
				continue;

			if (type == RECLAIM_ANON && vma->vm_file)
				continue;

			if (type == RECLAIM_FILE && !vma->vm_file)
				continue;

			rp.vma = vma;
			ret = walk_page_range(vma->vm_start, vma->vm_end,
				&reclaim_walk);
			if (ret)
				break;
		}
	}

	flush_tlb_mm(mm);
	up_read(&mm->mmap_sem);
	mmput(mm);
out:
	put_task_struct(task);
	return count;

out_err:
	return -EINVAL;
}

```

可以看到其实内部就是有关与文件页和匿名页的回收，这块的我页不是特别了解逻辑，还需要继续学习。  
同时在网上找到了相关的patch，了解到原来这个kim的process reclaim其实在很早的时候就已经进入linux主线了。而之所以没有用到android上，应该还是因为kernel无法去制定策略去给android使用，但是现在framework可以提供前后台信息和需要给定的策略信息，就可以来使用这个模块了。  

参考链接：  
[kim的process reclaim](https://lore.kernel.org/patchwork/patch/688100/)

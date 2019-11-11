---
layout: post
title: permissive domain not allowed in user builds
categories: [tech]
---
#### 起因：
错误log：
```
ERROR: permissive domains not allowed in user builds" 1>&2;
```
**起因：**在进一笔sepolicy代码的时候，报出的build break,从log也能很明显的看出，有一个domain，因为添加了permissive关键字而在user版本上导致的编译错误。  
**思索：**为什么要在user版本上禁止使用permissive标签，在翻了资料后发现为了开发和debug的需要，加上permissive关键字后，可以将该domain的一些denied log打印出来，这样就方便了一次将所有的权限添加。这样做的方式了避免了那些开机启动的应用程序没有权限，有时候log太快被冲掉的问题。而且这个好处也避免了让整台机器都处于permissive的非安全状态。
**使用：**  
1. 在te文件中添加
```
permissive myapp_t;
```
2. 动态添加
```
// 添加
semanager permissive -a myapp_t
// 删除
semanager permissive -d myapp_t
```
#### 错误跟踪链：
**总结：** 在编译bootimage的时候，sepolicy编译出来后，会进行一些条件的check，而permissivedomain的check也就在其中。
##### log出处：
```makefile
// system/sepolicy/Android.mk
$(built_sepolicy_neverallows)
	@mkdir -p $(dir $@)
	$(hide) $< -m -M true -G -c $(POLICYVERS) $(PRIVATE_NEVERALLOW_ARG) $(PRIVATE_CIL_FILES) -o $@.tmp -f /dev/null  
	$(hide) $(HOST_OUT_EXECUTABLES)/sepolicy-analyze $@.tmp permissive > $@.permissivedomains // 1
	$(hide) if [ "$(TARGET_BUILD_VARIANT)" = "user" -a -s $@.permissivedomains ]; then \ //2
		echo "==========" 1>&2; \
		echo "ERROR: permissive domains not allowed in user builds" 1>&2; \
		echo "List of invalid domains:" 1>&2; \
		cat $@.permissivedomains 1>&2; \
		exit 1; \
		fi
	$(hide) mv $@.tmp $@
```
首先看到1处，调用sepolicy-analyze来对之前创建出的tmp文件进行分析，将结果保存在permissivedomain中。   
紧接着2处进行判断，如果当前的目标版本是user，并且有permissivedomain这个文件，那么就说明已经违反了规定，将cat的结果打印到终端，同时exit，将整个打包进行失败。
#### sepolicy-analyze内容
上面说到在makefile中的进行分析，如果有分析结果就说明是错误了，那么分析的内容是什么呢？
```C
// system/sepolicy/tools/sepolicy-analyze/sepolicy-analyze.c
int main(int argc, char **argv)
{
    char *policy;
    struct policy_file pf;
    policydb_t policydb;
    int rc;
    int i;

    if (argc < 3)
        usage(argv[0]);
    policy = argv[1];
    if(load_policy(policy, &policydb, &pf))
        exit(1);
    for(i = 0; i < NUM_COMPONENTS; i++) { 
        if (!strcmp(analyze_components[i].key, argv[2])) { // 1
            rc = analyze_components[i].func(argc - 2, argv + 2, &policydb);//2
            if (rc && USAGE_ERROR) {
                usage(argv[0]); }
            policydb_destroy(&policydb);
            return rc;
        }
    }
    usage(argv[0]);
    exit(0);
}
```
从上面的分析，我们可以知道，第一个参数是文件，而第二个参数是permissive。1处的意思在这里一个for循环来找到对应的策略，也是就permissive的策略：
```C
#define COMP(x) { #x, sizeof(#x) - 1, x ##_usage, x ##_func }
static struct {
    const char *key;
    size_t keylen;
    void (*usage) (void);
    int (*func) (int argc, char **argv, policydb_t *policydb);
} analyze_components[] = {
    COMP(dups),
    COMP(neverallow),
    COMP(permissive),
    COMP(typecmp),
    COMP(booleans),
    COMP(attribute)
};
```
数组中的permissive简化下来：
```c
static struct {
    const char *key = "permissive";
    size_t keylen = sizeof("permissive");
    void permissive_usage ();
    void permissive_func (int argc , char ** argv, policydb_t *policydb);
}
```
所以2处调用的就是permissive_func
```C
// system/sepolicy/tools/sepolicy-analyze/perm.c
static int list_permissive(policydb_t * policydb)
{
    struct ebitmap_node *n;
    unsigned int bit;

    /*
     * iterate over all domains and check if domain is in permissive
     */
    ebitmap_for_each_bit(&policydb->permissive_map, n, bit)
    {
        if (ebitmap_node_get_bit(n, bit)) {
            printf("%s\n", policydb->p_type_val_to_name[bit -1]);
        }
    }
    return 0;
}

int permissive_func (int argc, __attribute__ ((unused)) char **argv, policydb_t *policydb) {
    if (argc != 1) {
        USAGE_ERROR = true;
        return -1;
    }
    return list_permissive(policydb);
}
```
到这里也就很清晰，通过for循环来寻找文件找到permissive的domain。因为将print的结果都重定向到文件中，所以$@.permissivedomain中就有了对应的permissive的domain。  

#### 总结：
permissive domain是用来帮助开发者进行开发以及debug的工具，用来一次性查看需要用到的权限，并且可以通过工具生成对应的规则，而permissive domain在设计中更多的被看做是一个临时的状态，而不是一个最终的状态。所以在user版本也就是发型版本中是不能够出现permissive的。而我们应该遵循这样的设计逻辑，不要在user版本中permissivedomain，只在开发和debug阶段添加对应逻辑方便调试。

**参考资料**
1. [红帽doc](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/6/html/security-enhanced_linux/sect-security-enhanced_linux-fixing_problems-permissive_domains)
2. [selinux官方文档](https://selinuxproject.org/page/PermissiveDomainRecipe)
3. [Android SEAndroid](https://source.android.com/security/selinux/device-policy#run_in_permissive_mode)
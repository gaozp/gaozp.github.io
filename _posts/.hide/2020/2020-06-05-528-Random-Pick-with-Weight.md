---
layout: post
title: 528. Random Pick with Weight
categories: [leetcode]
---
#### QUESTION:
Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

**Note:**
```
1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
```
**Example 1:**
```
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
```
Example 2:
```

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
```
**Explanation of Input Syntax:**

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
#### EXPLANATION:
这道题目其实还是挺容易的，就是题意比较难以理解。先说一下题意吧：给定一个数组，每个数字表示权重，调用pick的时候，产出的数字，符合权重。比如第二个例子，1+3 = 4，意思是pick输出index 0的概率是0.25，而输出1的概率是0.75.这样就很好理解了。  
思路肯定就是：先算出对应的和sum，然后再通过随机函数来获取到随机数，查看这个随机数的对应的index。一开始的思路就是：直接创建一个数组sum，sum[index]就是对应的值，如第二个例子就是： 【0，1，1，1】这样，比如我随机1，2，3的时候，就是权重3的索引，但是这个一提交，就提示我内存溢出，哈哈，看来只能想其他办法了。现在问题就处在查找到对应的索引，其实没有必要将每个索引都给一个位置，这样虽然可以拿到线性的时间复杂度，但是空间复杂度太大了。可以只保存每个权重开头的数字，这样在pick的时候，就可以判断开头的位置来获取到索引了。  
思路：
1. 堆w进行求和，同时标记索引位置
2. 随机产生数
3. 将产生的数在索引中找位置（优化点，采用二分查找法
4. 第一个小于这个的，就是对应的位置

#### SOLUTION:
```java
class Solution {
        Random r;
        int[] ints;
        int sum;
    public Solution(int[] w) {
            ints = new int[w.length];
            for(int i = 0;i<w.length;i++){
                sum+=w[i];
                ints[i] = sum;
            }
            r = new Random(sum);
    }
    
    public int pickIndex() {
            int random = r.nextInt(sum);
            for(int i = 0;i<ints.length;i++){
                if(random<ints[i]) return i;
            }
            return 0;
    }
}
```

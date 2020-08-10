---
layout: post
title: 获得最多的奖金
categories: [leetcode]
---
#### QUESTION:
**题目描述**
小明在越南旅游，参加了当地的娱乐活动。小明运气很好，拿到了大奖， 到了最后的拿奖金环节。小明发现桌子上放着一列红包，每个红包上写着奖金数额。
现在主持人给要求小明在这一列红包之间“切”2刀，将这一列红包“切”成3组，并且第一组的奖金之和等于最后一组奖金和（允许任意一组的红包集合是空）。最终第一组红包的奖金之和就是小明能拿到的总奖金。小明想知道最多能拿到的奖金是多少，你能帮他算算吗。

举例解释：桌子上放了红包  1, 2, 3, 4, 7, 10。小明在“4,7”之间、“7,10” 之间各切一刀，将红包分成3组 [1, 2, 3, 4]   [7]   [10]，其中第一组奖金之和=第三组奖金之和=10，所以小明可以拿到10越南盾。
**输入描述:**
```
第一行包含一个正整数n，(1<=n<= 200 000)，表示有多少个红包。

第二行包含n个正整数d[i]，表示每个红包包含的奖金数额。其中1<= d[i] <= 1000 000 000
```
**输出描述:**

小明可以拿到的总奖金
示例1
```
输入
5
1 3 1 1 4
输出
5
说明
[1,3,1]  [ ]   [1,4] ，其中第一组奖金和是5，等于第三组奖金和。所以小明可以拿到5越南盾
```
```
示例2
输入
5
1 3 2 1 4
输出
4
说明
[1,3]   [2,1]  [4]，小明可以拿到4越南盾
```
```
示例3
输入
3
4 1 2
输出
0
说明
[ ]  [4, 1, 2] [ ] ，小明没办法，为了保证第一组第三组相等，只能都分成空的。所以小明只能拿到0越南盾。
```
#### EXPLANATION:
看到题目的第一想法就是使用双指针，从头和末尾开始往中间靠，哪边小就移动哪边的指针。直到两者都到了中间为止。那么思路有了，就可以直接按照思路来进行代码的编辑。
1. 定义start，end两个指针，同时定义sumstart和sumend两个来表示前后的和
2. 定义循环，判断条件是start和end的指针位置
3. 进行判断， 如果sumstart和sumend相等，说明我们遇到了相等的情况，可以进行一次结果的判断
4. 否则就将小的那位进行增加操作

#### SOLUTION:
```java
    public static int yuenandun(int n , int[] moneys){
        if(n<3) return 0;
        int start = 0;
        int end = moneys.length-1;
        int sumStart = 0;
        int sumEnd = 0;
        int result = 0;
        while (start-1<=end+1){
            if(sumStart == sumEnd) {
                result = Math.max(sumStart,result);
                sumStart+=moneys[start];
                sumEnd+=moneys[end];
                start++;end--;
            }else if(sumStart > sumEnd) {
                sumEnd+=moneys[end];
                end--;
            }else if(sumStart < sumEnd) {
                sumStart+=moneys[start];
                start++;
            }
        }
        return result;
    }
```

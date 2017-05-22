---
layout: post
title: 88 Merge Sorted Array
---

####QUESTION:  
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

####ANALYSE  
其实刚开始看到这个题目的时候的想法是：  
新建一个数组是a数组和b数组的总量，然后从第一个对比，谁小就放在新建数组的位置，然后依序下去就可以了。

后来写完了发现还提供了两个参数m,n.这个时候才发现note上给了界定，就是知道nums1的数组有足够的长度来容纳nums2的数据和本身初始化的数据。所以问题就简单了，因为知道了数据的长度，那么nums1的内容就应该是m个数据再加上n个空数据，那么只要把nums2的数据填充就可以了。于是考虑到nums1和nums2都是已经排过序的，那么就可以从最后一位开始填充，谁最大就填充在最后，直到一个填充完了，那么就将另外一个没填充完的直接补上去就可以。  

其实这里有个问题，排序是确定了是指从大到小排好了，还是从小到大排的。于是默认从小到大排，写完之后提交也通过了。


####SOLUTION


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m+n-1;
        int aindex = m-1;
        int bindex = n-1;
        while(aindex>=0&&bindex>=0){
            int x = nums1[aindex];
            int y = nums2[bindex];
            if(x>y){
                nums1[index] = x;
                index--;
                aindex--;
            }else{
                nums1[index] = y;
                index--;
                bindex--;
            }
        }

        while (aindex>=0){
            nums1[index] = nums1[aindex];
            index--;
            aindex--;
        }

        while (bindex>=0){
            nums1[index] = nums2[bindex];
            index--;
            bindex--;
        }
    }
    



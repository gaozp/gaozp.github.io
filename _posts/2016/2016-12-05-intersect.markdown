---
layout: post
title: 350. Intersection of Two Arrays II
categories: [leetcode]
---

####QUESTION:
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?


####EXPLANATION:

其实也没有什么可以解释的，因为1是不需要计算重复的，所以只要判断有这个数字就可以，但是2需要计算重复的，所以最好就是计算一下两个的数组差，大于等于0的就说明是有重复的元素的，话说对比了下python和c++还有c的代码，发现java的代码真的很冗余啊。

####SOLUTION:

    
    public int[] intersect(int[] nums1, int[] nums2) {
        Hashtable<Integer,Integer> table = new Hashtable<>();
        ArrayList<Integer> result = new ArrayList<>();
        for(int i= 0;i<nums1.length;i++){
            int value = table.getOrDefault(nums1[i],0);
            table.put(nums1[i],++value);
        }
        for(int i = 0;i<nums2.length;i++){
            int value = table.getOrDefault(nums2[i],0);
            if(value-1>=0){
                result.add(nums2[i]);
            }
            table.put(nums2[i],--value);
        }
        int[] newResule =new int[result.size()];
        for(int i = 0;i<result.size();i++){
            newResule[i] = result.get(i);
        }
        return newResule;
    }


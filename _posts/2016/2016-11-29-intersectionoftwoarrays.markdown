---
layout: post
title: 349. Intersection of Two Arrays
categories: [leetcode]
---

####QUESTION:
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

####EXPLANATION:

使用set，因为set是没有重复的。  

####SOLUTION:

    
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] result;
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    set.add(nums1[i]);
                }
            }
        }
        Object[] objects = set.toArray();
        result = new int[objects.length];
        for(int i=0;i<objects.length;i++){
            result[i] = (int)objects[i];
        }
        return  result;
    }
    
    



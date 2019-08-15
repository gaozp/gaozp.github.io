---
layout: post
title: 219. Contains Duplicate II
categories: [leetcode]
---

#### QUESTION:

Given an array of integers and an integer *k*, find out whether there are two distinct indices *i* and *j* in the array such that **nums[i] = nums[j]**and the **absolute** difference between *i* and *j* is at most *k*.

#### EXPLANATION:

其实就是用hashtable来进行重复的判断，如果有重复的，就判断下两者的index是否小于k，否则就直接将最新的index放到table中。

#### SOLUTION:

```java
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Hashtable<Integer,Integer> table = new Hashtable<>();
        for(int i = 0;i<nums.length;i++){
            int key = nums[i];
            Object value = table.get(key);
            if(value==null){
                table.put(key,i);
                continue;
            }
            int index = (int)value;
            if(i-index<=k)
                return true;
            table.put(key,i);
        }
        return false;
    }
}
```


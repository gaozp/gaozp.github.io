---
layout: post
title: 26. Remove Duplicates from Sorted Array
categories: [leetcode]
---

#### QUESTION:

Given a sorted array, remove the duplicates in place such that each element appear only *once* and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array *nums* = `[1,1,2]`,

Your function should return length = `2`, with the first two elements of *nums* being `1` and `2` respectively. It doesn't matter what you leave beyond the new length.

#### EXPLANATION:

1.首先循环这个数组

2.如果循环到的数和count的数不一样，那么就把它放在count++的位置上

3.循环到结束，把count+1（因为count是下标的标识）返回

#### SOLUTION:

```java
public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums==null && nums.length==0)return 0;
        int count = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[count]!=nums[i]){
                count ++;
                nums[count]=nums[i];
            }
        }
        return count+1;
    }
}
```


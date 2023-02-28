---
layou: post
title: 35. Search Insert Position
categories: [leetcode]
---

#### QUESTION:

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.

[1,3,5,6], 5 → 2

[1,3,5,6], 2 → 1

[1,3,5,6], 7 → 4

[1,3,5,6], 0 → 0

#### EXPLANATION:

其实就是二分法的查找，但是要注意插入的顺序，看了discuss中其他的回答，代码也许会简洁点，但是只使用了一次二分法，会导致效率并不会很高。

#### SOLUTION:

```java
public int searchInsert(int[] nums, int target) {
        if(nums==null||nums.length==0) return 0;
        return searchInsertHelper(nums,target,0,nums.length-1);
    }
    public int searchInsertHelper(int[] nums,int target,int low,int high){
        int middle = (low+high)/2;
        if(nums[middle]==target) return middle;
        if(middle==low || middle==high){
            if(target<=nums[low])
                return low-1>0?low-1:0;
            if(target>nums[high])
                return high+1;
            if(target==nums[high])
                return high;
            if(target>nums[low]&&target<nums[high])
                return low+1;
        }
        if(nums[middle]>target)
            high = middle;
        else
            low = middle;
        return searchInsertHelper(nums,target,low,high);

    }
```


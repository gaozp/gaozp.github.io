---
layout: post
title: 540. Single Element in a Sorted Array
---

#### QUESTION:

Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

**Example 1:**

```
Input: [1,1,2,3,3,4,4,8,8]
Output: 2

```

**Example 2:**

```
Input: [3,3,7,7,10,11,11]
Output: 10

```

**Note:** Your solution should run in O(log n) time and O(1) space.

#### EXPLANATION:

其实说到O(logn)的时候就差不多知道应该是二分法了。二分法就没有好说的了，但是其实我一开始并没有注意到logn的情况，写了一个O(n)的情况。

这道题很容易，直接看代码逻辑就行。

#### SOLUTION:

```JAVA
public class Solution {
    public int singleNonDuplicate(int[] nums) {
        int count = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] - nums[i-1] != 0 && count ==1)
                return nums[i-1];
            if(nums[i] - nums[i-1] != 0 && count ==2)
                count=1;
            if(nums[i]-nums[i-1]==0)
                count++;
        }
        return nums[nums.length-1];
    }
}

    public static int singleNonDuplicate(int[] nums) {
//        if(nums.length==1) return nums[0];
        int start = 0, end=nums.length-1;
        while(start<=end){
            if(start==end)
                return nums[start];
            int mid = (start+end)/2;
            if((mid+1<=end && nums[mid]!=nums[mid+1]) && (mid-1>=start && nums[mid]!=nums[mid-1]) ){
                return nums[mid];
            }
            if(mid-1>=start && (mid+1-start)%2==0 && nums[mid]==nums[mid-1]){
                start = mid+1;
            }else if(mid-1>=start && (mid+1-start)%2!=0 && nums[mid]==nums[mid-1]){
                end=mid-2;
            }else if(mid+1<=end && nums[mid]==nums[mid+1] && (mid+1-start)%2==0){
                end = mid-1;
            }else{
                start = mid+2;
            }

        }
        return 0;
    }
```


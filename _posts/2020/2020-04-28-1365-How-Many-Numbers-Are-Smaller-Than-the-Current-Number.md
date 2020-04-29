---
layout: post
title: 1365. How Many Numbers Are Smaller Than the Current Number
categories: [tech]
---
#### QUESTION:
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

 

Example 1:
```
Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation: 
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1). 
For nums[3]=2 there exist one smaller number than it (1). 
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
```
Example 2:
```
Input: nums = [6,5,4,8]
Output: [2,1,0,3]
Example 3:

Input: nums = [7,7,7,7]
Output: [0,0,0,0]
```

Constraints:
```
2 <= nums.length <= 500
0 <= nums[i] <= 100
```
#### EXPLANATION:
如果从逻辑来考虑的话，肯定是找到一个，然后再遍历每个数来获取到小的数，那么这样时间复杂度就是O(n^2),同时我们可以看到，数字其实是在0-100之间的，既然是有限的，那么我们就可以采用数组占位的方式来。具体逻辑  
1. 遍历数组，将个数字的次数记录下来
2. 再次遍历数组，记录比自己小的数，也就是用一个count来记录目前已经统计的数
3. 遍历数组，通过index就可以直接获取到小于当前的数

这样的逻辑，只需要O(n)的时间复杂度。

#### SOLUTION:
```java
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = new int[101];
        for(int n:nums) arr[n]++;
        int count=0;
        for(int i=0;i<arr.length;i++){
            int cur = arr[i];
            arr[i]=count;
            count+=cur;
        }
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i] = arr[nums[i]];
        }
        return res;
    }
```

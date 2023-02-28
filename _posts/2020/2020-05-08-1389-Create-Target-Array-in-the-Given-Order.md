---
layout: post
title: 1389. Create Target Array in the Given Order
categories: [leetcode]
---
#### QUESTION:
Given two arrays of integers nums and index. Your task is to create target array under the following rules:

Initially target array is empty.
From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
Repeat the previous step until there are no elements to read in nums and index.
Return the target array.

It is guaranteed that the insertion operations will be valid.

 

Example 1:
```
Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
Output: [0,4,1,3,2]
Explanation:
nums       index     target
0            0        [0]
1            1        [0,1]
2            2        [0,1,2]
3            2        [0,1,3,2]
4            1        [0,4,1,3,2]
```
Example 2:
```
Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
Output: [0,1,2,3,4]
Explanation:
nums       index     target
1            0        [1]
2            1        [1,2]
3            2        [1,2,3]
4            3        [1,2,3,4]
0            0        [0,1,2,3,4]
```
Example 3:
```
Input: nums = [1], index = [0]
Output: [1]
```

Constraints:
```
1 <= nums.length, index.length <= 100
nums.length == index.length
0 <= nums[i] <= 100
0 <= index[i] <= i
```
#### EXPLANATION:
题意就是在一个空数组中的index[i]的位置插入num[i]的值，但是其中有两个需要关注的点。一是其中会有重复的index，那么就需要将后来的插入到前面。二是可以确保每次插入都是有效的，不会出现现在数组长度才是3，我就插入到了7位。这样的话，很容易就可以想到，先用list进行插入操作，因为list有通过index进行插入的功能，最后再进行数组的填充。
#### SOLUTION:
```java
class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int i = 0;i<index.length;i++)
                tmp.add(index[i],nums[i]);
            
            int[] result = new int[nums.length];
            for(int i = 0;i<nums.length;i++)
                result[i] = tmp.get(i);
            
            return result;
    }
}
```


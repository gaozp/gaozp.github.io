---
layout: post
title: 697. Degree of an Array
categories: [leetcode]
---

#### QUESTION:

Given a non-empty array of non-negative integers `nums`, the **degree** of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of `nums`, that has the same degree as `nums`.

**Example 1:**

```
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

```

**Example 2:**

```
Input: [1,2,2,3,1,4,2]
Output: 6

```

**Note:**

`nums.length` will be between 1 and 50,000.

`nums[i]` will be an integer between 0 and 49,999.

#### EXPLANATION:

逻辑比较简单：

1.遍历数组

2.找到相同的数字的个数以及最大的距离。

3.遍历结果，找到最大个数和最小距离。



#### SOLUTION:

```JAVA
class Solution {
    class ShortestSubArray{
        int firstIndex = -1;
        int count = 1;
        int length = 1;
    }
    public int findShortestSubArray(int[] nums) {
       HashMap<Integer,ShortestSubArray> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            ShortestSubArray tmp = map.get(nums[i]);
            if(tmp ==null){
                tmp = new ShortestSubArray();
                tmp.firstIndex = i;
            }else{
                tmp.length = i-tmp.firstIndex+1;
                tmp.count++;
            }
            map.put(nums[i],tmp);
        }
        int result = Integer.MAX_VALUE;
        int count = 0;
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            ShortestSubArray shortestSubArray = map.get(next);
            if(shortestSubArray.count>count){
                count = shortestSubArray.count;
                result = shortestSubArray.length;
            }else if(shortestSubArray.count==count&&shortestSubArray.length<result){
                count = shortestSubArray.count;
                result = shortestSubArray.length;
            }
        }
        return result; 
    }
}
```


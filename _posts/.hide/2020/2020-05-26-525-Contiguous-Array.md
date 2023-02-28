---
layout: post
title: 525. Contiguous Array
categories: [leetcode]
---
#### QUESTION:
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

**Example 1:**
```
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
```
**Example 2:**
```
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
```
**Note:** The length of the given binary array will not exceed 50,000.

#### EXPLANATION:
看到题目，第一个想到的肯定是穷举法，但是很明显，穷举法肯定会导致TLE，所以立马放弃考虑，接着我们可以想到stack，如果用stack的话，1就往stack里面添加1，而0就从里面进行减去。那么如果我们就可以根据stack的size来进行查看。相当于水杯那种，1就是加水，而0就是喝水。那么如果他们不同时间段的size相同，则说明是有了相同的0和1的数量。  
那我们反过来一想，其实stack的size也就是数组对应的和，而我们需要记录的是第一次出现这个水线的位置，这样就可以得到对应的长度了，再与当前长度进行对比，就能获取到最终的结果。  
思路： 
1. 定义一个hashmap来记录水线出现的位置
2. 定义一个sum来记录当前的水线
3. for循环来进行水线的累加
4. 如果当前水线出现过，则获取到i-水线第一次出现的位置值，并和最终的result进行比较
5. 如果当前水线没出现过，则将该水线记录在案

#### SOLUTION:
```java
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer,Integer> map= new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            sum+= nums[i]==0?-1:1;
            if(map.containsKey(sum))
                result = Math.max(result,i-map.get(sum));
            else map.put(sum,i);
        }
        return result;
    }
}
```

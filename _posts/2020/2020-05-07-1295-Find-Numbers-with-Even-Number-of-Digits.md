---
layout: post
title: 1295. Find Numbers with Even Number of Digits
categories: [leetcode]
---
#### QUESTION:
Given an array nums of integers, return how many of them contain an even number of digits.
 

Example 1:
```
Input: nums = [12,345,2,6,7896]
Output: 2
Explanation: 
12 contains 2 digits (even number of digits). 
345 contains 3 digits (odd number of digits). 
2 contains 1 digit (odd number of digits). 
6 contains 1 digit (odd number of digits). 
7896 contains 4 digits (even number of digits). 
Therefore only 12 and 7896 contain an even number of digits.
```
Example 2:
```
Input: nums = [555,901,482,1771]
Output: 1 
Explanation: 
Only 1771 contains an even number of digits.
```

Constraints:
```
1 <= nums.length <= 500
1 <= nums[i] <= 10^5
```
#### EXPLANATION:
题意为：输出数字长度为偶数的个数。  
思路：  
1.将数字利用tostring方法转化成string  
2.判断string的length，是否为偶数  
3.循环到数组结尾  
#### SOLUTION:
```java
class Solution {
    public int findNumbers(int[] nums) {
        int result = 0;
        for(int num: nums){
            String num_str = Integer.toString(num);
            if(num_str.length()%2==0) result++;
        }
        return result;
    }
}
```

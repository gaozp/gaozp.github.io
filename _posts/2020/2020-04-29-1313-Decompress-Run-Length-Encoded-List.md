---
layout: post
title: 1313. Decompress Run-Length Encoded List
categories: [leetcode]
---
#### QUESTION:
We are given a list nums of integers representing a list compressed with run-length encoding.

Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are freq elements with value val concatenated in a sublist. Concatenate all the sublists from left to right to generate the decompressed list.

Return the decompressed list.

 

Example 1:
```
Input: nums = [1,2,3,4]
Output: [2,4,4,4]
Explanation: The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
At the end the concatenation [2] + [4,4,4] is [2,4,4,4].
```
Example 2:
```
Input: nums = [1,1,2,3]
Output: [1,3,3]
```

Constraints:
```
2 <= nums.length <= 100
nums.length % 2 == 0
1 <= nums[i] <= 100
```
#### EXPLANATION:
题意就是将pair的数组展开。首先看到返回值是一个数组，那么就有两个思路，一个是先确定长度，再进行填充，第二个思路是先进行填充list，再转化成数组，因为转化成list需要额外的空间，并且还有装箱拆箱的操作，得不偿失，所以采用第一种方式。  
思路：  
1. 遍历0，2，4...偶数下标，获取到新数组长度
2. 遍历数组，获取到0，1下标的freq和val，进行填充，同时定义index用来表示在result中的位置，index每次增加为num[freq]的结果
3. 重复第二步，直到填充完成
#### SOLUTION:
```java
class Solution {
    public int[] decompressRLElist(int[] nums) {
        int length = 0;
        for(int i = 0;i<nums.length;i+=2) length+= nums[i];
        int[] result = new int[length];
        int index = 0;
        int anchor = 0;
        while (index<length){
            int freq = nums[anchor];
            int val = nums[anchor+1];
            int tmp = 0;
            while(tmp<freq){
                result[index] = val;
                index++;
                tmp++;
            }
            anchor+=2;
        }
        return result;
    }
}
```

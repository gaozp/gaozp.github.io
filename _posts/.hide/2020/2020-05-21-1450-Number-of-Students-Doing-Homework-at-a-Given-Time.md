---
layout: post
title: 1450. Number of Students Doing Homework at a Given Time
categories: [leetcode]
---
#### QUESTION:
Given two integer arrays startTime and endTime and given an integer queryTime.

The ith student started doing their homework at the time startTime[i] and finished it at time endTime[i].

Return the number of students doing their homework at time queryTime. More formally, return the number of students where queryTime lays in the interval [startTime[i], endTime[i]] inclusive.

**Example 1:**
```
Input: startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
Output: 1
Explanation: We have 3 students where:
The first student started doing homework at time 1 and finished at time 3 and wasn't doing anything at time 4.
The second student started doing homework at time 2 and finished at time 2 and also wasn't doing anything at time 4.
The third student started doing homework at time 3 and finished at time 7 and was the only student doing homework at time 4.
```
**Example 2:**
```
Input: startTime = [4], endTime = [4], queryTime = 4
Output: 1
Explanation: The only student was doing their homework at the queryTime.
```
**Example 3:**
```
Input: startTime = [4], endTime = [4], queryTime = 5
Output: 0
```
**Example 4:**
```
Input: startTime = [1,1,1,1], endTime = [1,3,2,4], queryTime = 7
Output: 0
```
**Example 5:**
```
Input: startTime = [9,8,7,6,5,4,3,2,1], endTime = [10,10,10,10,10,10,10,10,10], queryTime = 5
Output: 5
 ```

**Constraints:**
```
startTime.length == endTime.length
1 <= startTime.length <= 100
1 <= startTime[i] <= endTime[i] <= 1000
1 <= queryTime <= 1000
```
#### EXPLANATION:
到了3点，忽然发现今天的explore竟然是已经做过的题目了，想着也不能断了，没了感觉就不好了，所以就做了一个简单的题目，这个题目比较容易。思路就是判断querytime是否同时在startTime[i]和endTime[i]中。  
思路： 
1. 定义一个数组，用来表示时间点上有多少同学在学习
2. 遍历数组，如果start和end相等的话，只需要将time[start]++就可以
3. 如果不相等的话，则需要将time[start]->times[end]都进行++，来表示有同学在学习
4. 直接获取到time[queryTime]进行返回

#### SOLUTION:
```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int[] times = new int[1001];
        for(int i = 0;i<startTime.length;i++){
            int start = startTime[i];
            int end = endTime[i];
            if(start==end) times[start]++;
            else {
                for (int j = start;j<=end;j++) times[j]++;
            }
        }
        return times[queryTime];
    }
}
```

---
layout: post
title: 1491. Average Salary Excluding the Minimum and Maximum Salary
categories: [leetcode]
---
#### QUESTION:
You are given an array of unique integers salary where salary[i] is the salary of the ith employee.

Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.

 

__Example 1:__
```
Input: salary = [4000,3000,1000,2000]
Output: 2500.00000
Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
```
__Example 2:__
```
Input: salary = [1000,2000,3000]
Output: 2000.00000
Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
```
 

__Constraints:__
```
3 <= salary.length <= 100
1000 <= salary[i] <= 106
All the integers of salary are unique.
```
#### EXPLANATION:

这道题目就比较简单了, 直接用流操作即可.一行代码就可以搞定.

#### SOLUTION:
```kotlin
class Solution {
    fun average(salary: IntArray): Double {
        return salary.filter { it -> it != salary.min() && it != salary.max() }.average()
    }
}
```

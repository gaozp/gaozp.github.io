---
layout: post
title: 1672. Richest Customer Wealth
categories: [leetcode]
---
#### QUESTION:
You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the i​​​​​​​​​​​th​​​​ customer has in the j​​​​​​​​​​​th​​​​ bank. Return the wealth that the richest customer has.

A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.

 

Example 1:
```
Input: accounts = [[1,2,3],[3,2,1]]
Output: 6
Explanation:
1st customer has wealth = 1 + 2 + 3 = 6
2nd customer has wealth = 3 + 2 + 1 = 6
Both customers are considered the richest with a wealth of 6 each, so return 6.
```
Example 2:
```
Input: accounts = [[1,5],[7,3],[3,5]]
Output: 10
Explanation: 
1st customer has wealth = 6
2nd customer has wealth = 10 
3rd customer has wealth = 8
The 2nd customer is the richest with a wealth of 10.
```
Example 3:
```
Input: accounts = [[2,8,7],[7,1,3],[1,9,5]]
Output: 17
 ```

Constraints:
```
m == accounts.length
n == accounts[i].length
1 <= m, n <= 50
1 <= accounts[i][j] <= 100
```
#### EXPLANATION:
这道题目其实很简单,就是需要计算二维数组的每一个单独的数组的和. 不过既然用了kotlin,那必然要学习使用kotlin的链式调用了. lambda表达式在之前的java其实已经说过了. 

#### SOLUTION:
```kotlin
class Solution {
    fun maximumWealth(accounts: Array<IntArray>): Int {
        var result = -1;
        for (i in accounts) {
            var sum = 0;
            for (j in i) {
                sum+=j
            }
            result = Math.max(result,sum)
        }
        return result
    }
    // 既然用到了kotlin,必然要用lambda表达式
    fun maximumWealth(accounts: Array<IntArray>): Int {
        var result = -1;
        accounts.forEach { banks ->
            var sum = 0;
            banks.forEach { money -> sum+=money }
            if (sum > result) result = sum
        }
        return result
    }
}

```

---
layout: post
title: 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
categories: [leetcode]
---
#### QUESTION:
A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros. For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.

Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.

 

__Example 1:__
```
Input: n = "32"
Output: 3
Explanation: 10 + 11 + 11 = 32
```
__Example 2:__
```
Input: n = "82734"
Output: 8
```
__Example 3:__
```
Input: n = "27346209830709182346"
Output: 9
``` 

__Constraints:__
```
1 <= n.length <= 105
n consists of only digits.
n does not contain any leading zeros and represents a positive integer.
```
#### EXPLANATION:
这道题目也比较简单, 只要找到了规律就很简单了. 规律就是,因为所有的只可能是0或者1, 那么数字只能由1组成, 那么字符串中最大的数字,就是需要的个数. 就像杯子接水一样.  
那其实就很简单了,但是代码还是有区别的, 上面是我写的用的lambda表达式的方式来计算最大值,但是完全可以直接用本身的api来获取.  
1. 首先创建流式操作,获取到max的值
2. 将max的值转换成int值,也就是对应字符的ascii值
3. 将这个值减去'0'的值,就可以获取到对应的值了.

#### SOLUTION:
```java
class Solution {
    fun minPartitions(n: String): Int {
        var result = 0
        n.toCharArray().forEach { c ->
            result = Math.max(result,Integer.parseInt(c.toString()))
        }
        return result
    }
}
// 一行代码
fun minPartitions(n: String): Int {
    return n.chars().max().asInt - 48
}
```

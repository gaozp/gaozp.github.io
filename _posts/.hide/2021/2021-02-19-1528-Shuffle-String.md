---
layout: post
title: 1528. Shuffle String
categories: [leetcode]
---
#### QUESTION:
Given a string s and an integer array indices of the same length.

The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.

Return the shuffled string.

 

__Example 1:__
![img](https://assets.leetcode.com/uploads/2020/07/09/q1.jpg)
```
Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
```
__Example 2:__
```
Input: s = "abc", indices = [0,1,2]
Output: "abc"
Explanation: After shuffling, each character remains in its position.
```
__Example 3:__
```
Input: s = "aiohn", indices = [3,1,4,2,0]
Output: "nihao"
```
__Example 4:__
```
Input: s = "aaiougrt", indices = [4,0,2,6,7,3,1,5]
Output: "arigatou"
```
__Example 5:__
```
Input: s = "art", indices = [1,0,2]
Output: "rat"
 ```

__Constraints:__
```
s.length == indices.length == n
1 <= n <= 100
s contains only lower-case English letters.
0 <= indices[i] < n
All values of indices are unique (i.e. indices is a permutation of the integers from 0 to n - 1).
```
#### EXPLANATION:
这道题目就比较简单了. 其实就是两个数组的一个拼凑成一个数组的问题. 一个数组的值是位置, 一个数组的值是对应的值. 所以将这两个配合起来,就是对应的结果了. 最后再将结果转化成string,就是我们需要的结果了.
#### SOLUTION:
```java
class Solution {
    fun restoreString(s: String, indices: IntArray): String {
        var resultArray:CharArray  = CharArray(s.length)
        val charArray = s.toCharArray()
        for (i in charArray.indices) 
            resultArray[indices[i]] = charArray[i]
        return String(resultArray)
    }
}
```

---
layout: post
title: 1662. Check If Two String Arrays are Equivalent
categories: [leetcode]
---
#### QUESTION:
Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

 

__Example 1:__
```
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.
```
__Example 2:__
```
Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false
```
__Example 3:__
```
Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true
 ```

__Constraints:__
```
1 <= word1.length, word2.length <= 103
1 <= word1[i].length, word2[i].length <= 103
1 <= sum(word1[i].length), sum(word2[i].length) <= 103
word1[i] and word2[i] consist of lowercase letters.
```
#### EXPLANATION:
这道题目比较简单,其实就是将两个字符数组进行组合,最后再判断两者是否equals. 那么问题来了. 是不是可以直接采用 result = "ab" + "c"的方式呢? 在java中,我们肯定不建议这样做, 因为这样会在string常量池中创建多个string常量, 造成内存的扩大, 如果正好遇到瓶颈, 就会造成GC. 那在kotlin中呢, 如果你知道了kotlin其实也是运行在java虚拟机中, 那么久基本可以判定, 底层的内存结构也是相同的. 所以在kotlin中去用字符串加法的方式也是不推荐的, 还是采用stringbuilder或者stringbuffer去进行append为好.
#### SOLUTION:
```java
class Solution {
    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        var sb1: StringBuilder = StringBuilder()
        word1.forEach { s -> sb1.append(s) }
        var sb2: StringBuilder = StringBuilder()
        word2.forEach { s -> sb2.append(s) }
        return sb1.toString().equals(sb2.toString())
    }
}
```

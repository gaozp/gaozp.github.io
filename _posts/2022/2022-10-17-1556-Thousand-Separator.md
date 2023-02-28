---
layout: post
title: 1556. Thousand Separator
categories: [leetcode]
---
#### QUESTION:
Given an integer n, add a dot (".") as the thousands separator and return it in string format.

 

__Example 1:__
```
Input: n = 987
Output: "987"
```
__Example 2:__
```
Input: n = 1234
Output: "1.234"
```
 

__Constraints:__
```
0 <= n <= 231 - 1
```
#### EXPLANATION:

easy的题目， 直接首先反向， 然后for循环进行添加， 最后再进行反向输出即可。

#### SOLUTION:
```kotlin
class Solution {
    fun thousandSeparator(n: Int): String {
        var result: StringBuilder = StringBuilder()
        var nString = n.toString().reversed()
        for (index in nString.indices) {
            if (index != 0 && index % 3 == 0) {
                result.append(".")
            }
            result.append(nString[index])
        }
        return result.reversed().toString()
    }
}
```

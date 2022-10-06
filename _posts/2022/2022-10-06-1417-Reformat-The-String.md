---
layout: post
title: 1417. Reformat The String
categories: [leetcode]
---
#### QUESTION:
You are given an alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).

You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.

Return the reformatted string or return an empty string if it is impossible to reformat the string.

 

__Example 1:__
```
Input: s = "a0b1c2"
Output: "0a1b2c"
Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
```
__Example 2:__
```
Input: s = "leetcode"
Output: ""
Explanation: "leetcode" has only characters so we cannot separate them by digits.
```
__Example 3:__
```
Input: s = "1229857369"
Output: ""
Explanation: "1229857369" has only digits so we cannot separate them by characters.
```
 

__Constraints:__
```
1 <= s.length <= 500
s consists of only lowercase English letters and/or digits.
```
#### EXPLANATION:

拆分成两个queue, 然后通过数量进行单个的添加既可.

#### SOLUTION:
```kotlin
import kotlin.math.min
import kotlin.math.abs
class Solution {
    fun reformat(s: String): String {
        var digitsQueue:ArrayDeque<Char> = ArrayDeque()
        var lettersQueue:ArrayDeque<Char> = ArrayDeque()
        s.forEach { c ->
            if (c in 'a'..'z') {
                lettersQueue.add(c)
            } else if (c in '0'..'9') {
                digitsQueue.add(c)
            }
        }
        if (abs(digitsQueue.size - lettersQueue.size) > 1) {
            return ""
        }
        var result:StringBuilder = StringBuilder()
        var size:Int = min(lettersQueue.size, digitsQueue.size)
        var flag:Boolean = lettersQueue.size >= digitsQueue.size
        for (i in 1..size) {
            if (flag) {
                result.append(lettersQueue.removeFirst())
                result.append(digitsQueue.removeFirst())
            } else {
                result.append(digitsQueue.removeFirst())
                result.append(lettersQueue.removeFirst())
            }
        }
        if (lettersQueue.size != 0) {
            result.append(lettersQueue.removeFirst())
        }
        if (digitsQueue.size != 0) {
            result.append(digitsQueue.removeFirst())
        }
        return result.toString()
    }
}
```

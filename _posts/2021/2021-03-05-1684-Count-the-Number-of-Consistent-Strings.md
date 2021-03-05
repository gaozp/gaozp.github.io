---
layout: post
title: 1684. Count the Number of Consistent Strings
categories: [leetcode]
---
#### QUESTION:
You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.

 

__Example 1:__
```
Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
```
__Example 2:__
```
Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
```
__Example 3:__
```
Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 ```

__Constraints:__
```
1 <= words.length <= 104
1 <= allowed.length <= 26
1 <= words[i].length <= 10
The characters in allowed are distinct.
words[i] and allowed contain only lowercase English letters.
```

#### EXPLANATION:
就是看word中有没有不被允许出现的字符. 那么我们只要将允许出现的放在一个表里,然后用word去匹配就可以. 但是这个表的设计也是有讲究的, 你可以用hashmap去存放,这样匹配时就需要每次使用contains, 但是如果你用array去存放,虽然可能会占用26个位置,有些位置确实可能用不到, 但是是在用空间换时间. 并且也是可以接受的. 

#### SOLUTION:
```java
class Solution {
    fun countConsistentStrings(allowed: String, words: Array<String>): Int {
        var array: IntArray = IntArray(26)
        var result: Int = 0
        allowed.toCharArray().forEach { c -> array[c - 'a']++ }
        words.forEach { word ->
            var unfound: Boolean = false
            word.toCharArray().forEach { c ->
                if (array[c - 'a'] == 0) unfound = true
            }
            if (!unfound) result++
        }
        return result
    }
}
```

---
layout: post
title: 2315. Count Asterisks
categories: [leetcode]
---
#### QUESTION:
You are given a string s, where every two consecutive vertical bars '|' are grouped into a pair. In other words, the 1st and 2nd '|' make a pair, the 3rd and 4th '|' make a pair, and so forth.

Return the number of '*' in s, excluding the '*' between each pair of '|'.

Note that each '|' will belong to exactly one pair.

 

__Example 1:__
```
Input: s = "l|*e*et|c**o|*de|"
Output: 2
Explanation: The considered characters are underlined: "l|*e*et|c**o|*de|".
The characters between the first and second '|' are excluded from the answer.
Also, the characters between the third and fourth '|' are excluded from the answer.
There are 2 asterisks considered. Therefore, we return 2.
```
__Example 2:__
```
Input: s = "iamprogrammer"
Output: 0
Explanation: In this example, there are no asterisks in s. Therefore, we return 0.
```
__Example 3:__
```
Input: s = "yo|uar|e**|b|e***au|tifu|l"
Output: 5
Explanation: The considered characters are underlined: "yo|uar|e**|b|e***au|tifu|l". There are 5 asterisks considered. Therefore, we return 5.
 ```

__Constraints:__
```
1 <= s.length <= 1000
s consists of lowercase English letters, vertical bars '|', and asterisks '*'.
s contains an even number of vertical bars '|'.
```
#### EXPLANATION:

一个for循环,加上一个flag作为前后的标记即可. 

#### SOLUTION:
```swift
class Solution {
    func countAsterisks(_ s: String) -> Int {
        var result:Int = 0
        var flag:Bool = true
        for ch in s {
            if flag {
                if ch == "*" {
                    result += 1
                }
            }
            if ch == "|" {
                flag = !flag
            }
        }
        return result
    }
}
```

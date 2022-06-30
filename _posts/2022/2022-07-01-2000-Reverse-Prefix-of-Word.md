---
layout: post
title: 2000. Reverse Prefix of Word
categories: [leetcode]
---
#### QUESTION:
Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.

For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
Return the resulting string.

 

__Example 1:__
```
Input: word = "abcdefd", ch = "d"
Output: "dcbaefd"
Explanation: The first occurrence of "d" is at index 3. 
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
```
__Example 2:__
```
Input: word = "xyxzxe", ch = "z"
Output: "zxyxxe"
Explanation: The first and only occurrence of "z" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
```
__Example 3:__
```
Input: word = "abcd", ch = "z"
Output: "abcd"
Explanation: "z" does not exist in word.
You should not do any reverse operation, the resulting string is "abcd".
 ```

__Constraints:__
```
1 <= word.length <= 250
word consists of lowercase English letters.
ch is a lowercase English letter.
```
#### EXPLANATION:

easy的题目  一个for循环, 在ch之前的就在前面添加, ch之后的就加载后面即可. 不要忘记刚开始判断是否包含.

#### SOLUTION:
```swift
class Solution {
    func reversePrefix(_ word: String, _ ch: Character) -> String {
        if !word.contains(ch) {
            return word
        }
        var flag:Bool = true
        var result:String = ""
        for w in word {
            if flag {
                result = String(w) + result
            } else {
                result = result + String(w)
            }
            if w == ch && flag {
                flag = false
            }
        }
        return result
    }
}
```

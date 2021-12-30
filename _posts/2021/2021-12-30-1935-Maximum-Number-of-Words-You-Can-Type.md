---
layout: post
title: 1935. Maximum Number of Words You Can Type
categories: [leetcode]
---
#### QUESTION:
There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.

Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.

 

__Example 1:__
```
Input: text = "hello world", brokenLetters = "ad"
Output: 1
Explanation: We cannot type "world" because the 'd' key is broken.
```
__Example 2:__
```
Input: text = "leet code", brokenLetters = "lt"
Output: 1
Explanation: We cannot type "leet" because the 'l' and 't' keys are broken.
```
__Example 3:__
```
Input: text = "leet code", brokenLetters = "e"
Output: 0
Explanation: We cannot type either word because the 'e' key is broken.
 ```

__Constraints:__
```
1 <= text.length <= 104
0 <= brokenLetters.length <= 26
text consists of words separated by a single space without any leading or trailing spaces.
Each word only consists of lowercase English letters.
brokenLetters consists of distinct lowercase English letters.
```
#### EXPLANATION:
逻辑比较简单, 直接看代码就成

#### SOLUTION:
```swift
class Solution {
    func canBeTypedWords(_ text: String, _ brokenLetters: String) -> Int {
        var count:Int = 0
        var textArr:[String] = text.components(separatedBy: " ")
        for index in textArr.indices {
            for ch in brokenLetters {
                if textArr[index].contains(ch) {
                    count += 1
                    break
                }
            }
        }
        return textArr.count - count
    }
}
```

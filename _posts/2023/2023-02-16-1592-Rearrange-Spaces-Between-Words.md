---
layout: post
title: 1592. Rearrange Spaces Between Words
categories: [leetcode]
---
#### QUESTION:
You are given a string text of words that are placed among some number of spaces. Each word consists of one or more lowercase English letters and are separated by at least one space. It's guaranteed that text contains at least one word.

Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end, meaning the returned string should be the same length as text.

Return the string after rearranging the spaces.

 

__Example 1:__
```
Input: text = "  this   is  a sentence "
Output: "this   is   a   sentence"
Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
```
__Example 2:__
```
Input: text = " practice   makes   perfect"
Output: "practice   makes   perfect "
Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. We place this extra space at the end of the string.
 ```

__Constraints:__
```
1 <= text.length <= 100
text consists of lowercase English letters and ' '.
text contains at least one word.
```
#### EXPLANATION:

easy的题目, 只需要模拟出来即可. 只要注意边界条件即可.

#### SOLUTION:
```swift
class Solution {
    func reorderSpaces(_ text: String) -> String {
        var count = 0
        for ch in text {
            if ch == " " {
                count += 1
            }
        }
        var words = text.split(separator: " ")
        var result = ""
        var separatorCount = words.count == 1 ? count : count/(words.count - 1)
        var remainCount = words.count == 1 ? count : count % (words.count - 1)
        for i in 0..<words.count - 1 {
            var tmp = words[i]
            for _ in 1...separatorCount {
                tmp = tmp + " "
            }
            result = result + tmp
        }
        var last = words.last!
        for _ in 0..<remainCount {
            last = last + " "
        }
        return result + last
    }
}
```

---
layout: post
title: 2129. Capitalize the Title
categories: [leetcode]
---
#### QUESTION:
You are given a string title consisting of one or more words separated by a single space, where each word consists of English letters. Capitalize the string by changing the capitalization of each word such that:

If the length of the word is 1 or 2 letters, change all letters to lowercase.
Otherwise, change the first letter to uppercase and the remaining letters to lowercase.
Return the capitalized title.

 

__Example 1:__
```
Input: title = "capiTalIze tHe titLe"
Output: "Capitalize The Title"
Explanation:
Since all the words have a length of at least 3, the first letter of each word is uppercase, and the remaining letters are lowercase.
```
__Example 2:__
```
Input: title = "First leTTeR of EACH Word"
Output: "First Letter of Each Word"
Explanation:
The word "of" has length 2, so it is all lowercase.
The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
```
__Example 3:__
```
Input: title = "i lOve leetcode"
Output: "i Love Leetcode"
Explanation:
The word "i" has length 1, so it is lowercase.
The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
```
 

__Constraints:__
```
1 <= title.length <= 100
title consists of words separated by a single space without any leading or trailing spaces.
Each word consists of uppercase and lowercase English letters and is non-empty.
```
#### EXPLANATION:

比较简单了, for循环之后进行判断, 如果小于或等于2, 那么就都改成小写, 否则就改成首字母大写. 存在一个arr里. 最后用join在一起, 用空格作为separator.

#### SOLUTION:
```swift
class Solution {
    func capitalizeTitle(_ title: String) -> String {
        var arr = title.split(separator: " ")
        var result:[String] = []
        for a in arr {
            if a.count <= 2 {
                result.append(a.lowercased())
            } else {
                result.append(a.capitalized)
            }
        }
        return result.joined(separator: " ")
    }
}
```

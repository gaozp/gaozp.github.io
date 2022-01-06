---
layout: post
title: 1957. Delete Characters to Make Fancy String
categories: [leetcode]
---
#### QUESTION:
A fancy string is a string where no three consecutive characters are equal.

Given a string s, delete the minimum possible number of characters from s to make it fancy.

Return the final string after the deletion. It can be shown that the answer will always be unique.

 

__Example 1:__
```
Input: s = "leeetcode"
Output: "leetcode"
Explanation:
Remove an 'e' from the first group of 'e's to create "leetcode".
No three consecutive characters are equal, so return "leetcode".
```
__Example 2:__
```
Input: s = "aaabaaaa"
Output: "aabaa"
Explanation:
Remove an 'a' from the first group of 'a's to create "aabaaaa".
Remove two 'a's from the second group of 'a's to create "aabaa".
No three consecutive characters are equal, so return "aabaa".
```
__Example 3:__
```
Input: s = "aab"
Output: "aab"
Explanation: No three consecutive characters are equal, so return "aab".
 ```

__Constraints:__
```
1 <= s.length <= 105
s consists only of lowercase English letters.
```
#### EXPLANATION:
其实是一道简单题. 思路如下:按顺序遍历, 字母最多连续出现两个, 否则就需要丢弃. 最后再排在一起. 那么按照这个思路, 我们就可以这样.  

```
[
    [a,a],
    [b],
    [a,a]
]
```

#### SOLUTION:
```swift
class Solution {
    func makeFancyString(_ s: String) -> String {
        var pre:Character = " "
        var resultArr:[[String]] = [[]]
        for item in s {
            if item != pre {
                resultArr.append([String(item)])
                pre = item
            } else {
                if resultArr.last!.count < 2 {
                    resultArr[resultArr.count-1].append(String(item))
                }
            }
        }
        var result:String = ""
        for itemI in resultArr {
            for itemJ in itemI {
                result += itemJ
            }
        }
        return result
    }
}
```

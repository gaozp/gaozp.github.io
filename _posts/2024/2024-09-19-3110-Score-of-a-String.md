---
layout: post
title: 3110. Score of a String
categories: [leetcode]
---
#### QUESTION:
You are given a string s. The score of a string is defined as the sum of the absolute difference between the ASCII values of adjacent characters.

Return the score of s.

 

__Example 1:__
```
Input: s = "hello"

Output: 13
```

__Explanation:__

The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' = 108, 'o' = 111. So, the score of s would be |104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13.

__Example 2:__
```
Input: s = "zaz"

Output: 50
```

__Explanation:__

The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So, the score of s would be |122 - 97| + |97 - 122| = 25 + 25 = 50.

 

Constraints:

2 <= s.length <= 100
s consists only of lowercase English letters.
#### EXPLANATION:

比较简单, 一个for循环就可以解决问题了. 

#### SOLUTION:
```swift
class Solution {
    func scoreOfString(_ s: String) -> Int {
        var result: Int = 0
        let chs:[Character] = Array(s)
        for i in 0...chs.count-2 {
            result += abs(Int(chs[i].asciiValue!) - Int(chs[i+1].asciiValue!))
        }
        return result
    }
}
```

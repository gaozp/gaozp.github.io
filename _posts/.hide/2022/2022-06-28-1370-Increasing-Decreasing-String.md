---
layout: post
title: 1370. Increasing Decreasing String
categories: [leetcode]
---
#### QUESTION:
You are given a string s. Reorder the string using the following algorithm:

Pick the smallest character from s and append it to the result.
Pick the smallest character from s which is greater than the last appended character to the result and append it.
Repeat step 2 until you cannot pick more characters.
Pick the largest character from s and append it to the result.
Pick the largest character from s which is smaller than the last appended character to the result and append it.
Repeat step 5 until you cannot pick more characters.
Repeat the steps from 1 to 6 until you pick all characters from s.
In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.

Return the result string after sorting s with this algorithm.

 

__Example 1:__
```
Input: s = "aaaabbbbcccc"
Output: "abccbaabccba"
Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
After steps 4, 5 and 6 of the first iteration, result = "abccba"
First iteration is done. Now s = "aabbcc" and we go back to step 1
After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
```
__Example 2:__
```
Input: s = "rat"
Output: "art"
Explanation: The word "rat" becomes "art" after re-ordering it with the mentioned algorithm.
```

__Constraints:__
```
1 <= s.length <= 500
s consists of only lowercase English letters.
```
#### EXPLANATION:

easy的题目， 直接用一个数组去装对应的个数。 while循环来查看需要循环的次数 。 两个for循环来保证正向和反向各查找一次即可。

#### SOLUTION:
```swift
class Solution {
    func sortString(_ s: String) -> String {
        var result:String = ""
        let arr = Array(s)
        var arrContent = Array(repeating: 0, count: 26)
        for ch in arr {
            arrContent[Int(ch.asciiValue!) - 97] += 1
        }
        while result.count < arr.count {
            for index in arrContent.indices {
                if arrContent[index] != 0 {
                    result += String(UnicodeScalar(index + 97)!)
                    arrContent[index] -= 1
                }
            }
            for index in arrContent.indices.reversed() {
                if arrContent[index] != 0 {
                    result += String(UnicodeScalar(index + 97)!)
                    arrContent[index] -= 1
                }
            }
        }
        return result
    }
}
```

---
layout: post
title: 1704. Determine if String Halves Are Alike
categories: [leetcode]
---
#### QUESTION:
You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.

 

__Example 1:__
```
Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
```
__Example 2:__
```
Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
 ```

__Constraints:__
```
2 <= s.length <= 1000
s.length is even.
s consists of uppercase and lowercase letters.
```
#### EXPLANATION:

easy的题目， 一个for循环进行判断即可。

#### SOLUTION:
```swift
class Solution {
    func halvesAreAlike(_ s: String) -> Bool {
        var vowels:[String] = ["a", "e", "i", "o", "u", "A", "E", "I", "O", "U"]
        var countA:Int = 0
        var countB:Int = 0
        let arr = Array(s)
        for index in 0...arr.count-1 {
            if vowels.contains(String(arr[index])) {
                if index + 1 <= (arr.count / 2) {
                    countA += 1
                } else {
                    countB += 1
                }
            }
        }
        return countA == countB
    }
}
```

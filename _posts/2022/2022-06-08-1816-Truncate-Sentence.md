---
layout: post
title: 1816. Truncate Sentence
categories: [leetcode]
---
#### QUESTION:
A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each of the words consists of only uppercase and lowercase English letters (no punctuation).

For example, "Hello World", "HELLO", and "hello world hello world" are all sentences.
You are given a sentence s​​​​​​ and an integer k​​​​​​. You want to truncate s​​​​​​ such that it contains only the first k​​​​​​ words. Return s​​​​​​ after truncating it.

 

__Example 1:__
```
Input: s = "Hello how are you Contestant", k = 4
Output: "Hello how are you"
Explanation:
The words in s are ["Hello", "how" "are", "you", "Contestant"].
The first 4 words are ["Hello", "how", "are", "you"].
Hence, you should return "Hello how are you".
```
__Example 2:__
```
Input: s = "What is the solution to this problem", k = 4
Output: "What is the solution"
Explanation:
The words in s are ["What", "is" "the", "solution", "to", "this", "problem"].
The first 4 words are ["What", "is", "the", "solution"].
Hence, you should return "What is the solution".
```
__Example 3:__
```
Input: s = "chopper is not a tanuki", k = 5
Output: "chopper is not a tanuki"
 ```

__Constraints:__
```
1 <= s.length <= 500
k is in the range [1, the number of words in s].
s consist of only lowercase and uppercase English letters and spaces.
The words in s are separated by a single space.
There are no leading or trailing spaces.
```
#### EXPLANATION:

easy题目, 首先拆分一下, 然后再根据数量拼凑起来就可以.

#### SOLUTION:
```swift
class Solution {
    func truncateSentence(_ s: String, _ k: Int) -> String {
        let array:[Substring] = s.split(separator: " ")
        var result:String = ""
        for index in 1...k {
            result.append(String(array[index - 1]))
            if index != k {
                result.append(" ")
            }
        }
        return result
    }
}
```

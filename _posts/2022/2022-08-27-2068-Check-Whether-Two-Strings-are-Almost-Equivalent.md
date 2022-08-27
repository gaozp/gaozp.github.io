---
layout: post
title: 2068. Check Whether Two Strings are Almost Equivalent
categories: [leetcode]
---
#### QUESTION:
Two strings word1 and word2 are considered almost equivalent if the differences between the frequencies of each letter from 'a' to 'z' between word1 and word2 is at most 3.

Given two strings word1 and word2, each of length n, return true if word1 and word2 are almost equivalent, or false otherwise.

The frequency of a letter x is the number of times it occurs in the string.

 

__Example 1:__
```
Input: word1 = "aaaa", word2 = "bccb"
Output: false
Explanation: There are 4 'a's in "aaaa" but 0 'a's in "bccb".
The difference is 4, which is more than the allowed 3.
```
__Example 2:__
```
Input: word1 = "abcdeef", word2 = "abaaacc"
Output: true
Explanation: The differences between the frequencies of each letter in word1 and word2 are at most 3:
- 'a' appears 1 time in word1 and 4 times in word2. The difference is 3.
- 'b' appears 1 time in word1 and 1 time in word2. The difference is 0.
- 'c' appears 1 time in word1 and 2 times in word2. The difference is 1.
- 'd' appears 1 time in word1 and 0 times in word2. The difference is 1.
- 'e' appears 2 times in word1 and 0 times in word2. The difference is 2.
- 'f' appears 1 time in word1 and 0 times in word2. The difference is 1.
```
__Example 3:__
```
Input: word1 = "cccddabba", word2 = "babababab"
Output: true
Explanation: The differences between the frequencies of each letter in word1 and word2 are at most 3:
- 'a' appears 2 times in word1 and 4 times in word2. The difference is 2.
- 'b' appears 2 times in word1 and 5 times in word2. The difference is 3.
- 'c' appears 3 times in word1 and 0 times in word2. The difference is 3.
- 'd' appears 2 times in word1 and 0 times in word2. The difference is 2.
 ```

__Constraints:__
```
n == word1.length == word2.length
1 <= n <= 100
word1 and word2 consist only of lowercase English letters.
```
#### EXPLANATION:

用两个长度为26的数组来存储两个word的字符出现的次数, 再比较两个数组的每个位置的差值.

#### SOLUTION:
```swift
class Solution {
    func checkAlmostEquivalent(_ word1: String, _ word2: String) -> Bool {
        var word1Arr:[Int] = Array(repeating: 0, count: 26)
        var word2Arr:[Int] = Array(repeating: 0, count: 26)
        for ch in word1 {
            word1Arr[Int(ch.asciiValue!) - 97] += 1
        }
        for ch in word2 {
            word2Arr[Int(ch.asciiValue!) - 97] += 1
        }
        for index in 0...25 {
            if abs(word1Arr[index] - word2Arr[index]) > 3 {
                return false
            }
        }
        return true
    }
}
```

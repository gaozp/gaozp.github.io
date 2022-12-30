---
layout: post
title: 2506. Count Pairs Of Similar Strings
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed string array words.

Two strings are similar if they consist of the same characters.

For example, "abca" and "cba" are similar since both consist of characters 'a', 'b', and 'c'.
However, "abacba" and "bcfd" are not similar since they do not consist of the same characters.
Return the number of pairs (i, j) such that 0 <= i < j <= word.length - 1 and the two strings words[i] and words[j] are similar.

 

__Example 1:__
```
Input: words = ["aba","aabb","abcd","bac","aabc"]
Output: 2
Explanation: There are 2 pairs that satisfy the conditions:
- i = 0 and j = 1 : both words[0] and words[1] only consist of characters 'a' and 'b'. 
- i = 3 and j = 4 : both words[3] and words[4] only consist of characters 'a', 'b', and 'c'. 
```
__Example 2:__
```
Input: words = ["aabb","ab","ba"]
Output: 3
Explanation: There are 3 pairs that satisfy the conditions:
- i = 0 and j = 1 : both words[0] and words[1] only consist of characters 'a' and 'b'. 
- i = 0 and j = 2 : both words[0] and words[2] only consist of characters 'a' and 'b'.
- i = 1 and j = 2 : both words[1] and words[2] only consist of characters 'a' and 'b'.
```
__Example 3:__
```
Input: words = ["nba","cba","dba"]
Output: 0
Explanation: Since there does not exist any pair that satisfies the conditions, we return 0.
```
 

__Constraints:__
```
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consist of only lowercase English letters.
```
#### EXPLANATION:

首先将每个string的字母算出来, 用set即可. 然后排序装入arr里. 这样每个string都被算出来了, 然后进行比对即可.

#### SOLUTION:
```swift
class Solution {
    func similarPairs(_ words: [String]) -> Int {
        var set:Set<Character> = Set()
        var arr:[Set<Character>] = []
        var result = 0
        for word in words {
            for ch in word {
                set.insert(ch)
            }
            set = Set(set.sorted())
            arr.append(set)
            set = Set()
        }
        for i in 0..<arr.count - 1 {
            for j in i+1..<arr.count {
                if arr[i] == arr[j] {
                    result += 1
                }
            }
        }
        return result
    }
}
```

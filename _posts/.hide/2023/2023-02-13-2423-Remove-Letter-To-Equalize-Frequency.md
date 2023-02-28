---
layout: post
title: 2423. Remove Letter To Equalize Frequency
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.

Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.

Note:

The frequency of a letter x is the number of times it occurs in the string.
You must remove exactly one letter and cannot chose to do nothing.
 

__Example 1:__
```
Input: word = "abcc"
Output: true
Explanation: Select index 3 and delete it: word becomes "abc" and each character has a frequency of 1.
```
__Example 2:__
```
Input: word = "aazz"
Output: false
Explanation: We must delete a character, so either the frequency of "a" is 1 and the frequency of "z" is 2, or vice versa. It is impossible to make all present letters have equal frequency.
```
 

__Constraints:__
```
2 <= word.length <= 100
word consists of lowercase English letters only.
```
#### EXPLANATION:

首先将所有的数量都放到一个dic中, 再进行模拟每次减一的操作查看是否达到了平衡即可.

#### SOLUTION:
```swift
class Solution {
    func equalFrequency(_ word: String) -> Bool {
        var dic: [Character:Int] = [:]
        for w in word {
            dic[w] = dic[w,default: 0] + 1
        }
        for (key,value) in dic {
            dic[key] = value - 1
            if equalFrequencyHelper(&dic) {
                return true
            }
            dic[key] = value
        }
        return false
    }
    func equalFrequencyHelper(_ dic: inout [Character: Int]) -> Bool {
        var num = -1
        for (key,value) in dic {
            if value == 0 {
                continue
            }
            if num == -1 {
                num = value
                continue
            }
            if value > 0 && value != num {
                return false
            }
        }
        return true
    }
}
```

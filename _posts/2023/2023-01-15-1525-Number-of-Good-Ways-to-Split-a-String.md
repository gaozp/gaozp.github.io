---
layout: post
title: 1525. Number of Good Ways to Split a String
categories: [leetcode]
---
#### QUESTION:
You are given a string s.

A split is called good if you can split s into two non-empty strings sleft and sright where their concatenation is equal to s (i.e., sleft + sright = s) and the number of distinct letters in sleft and sright is the same.

Return the number of good splits you can make in s.

 

__Example 1:__
```
Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good. 
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
```
__Example 2:__
```
Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").
```
 

__Constraints:__
```
1 <= s.length <= 105
s consists of only lowercase English letters.
```
#### EXPLANATION:

题意理解, 既可以知道左右两边的set相等. 但是, 有可能一个数在左边也在右边. 那么就只能通过count去标记. 所以就改成要以key的count来判断. 那么就写出来:   
1. 将整个s放在字典里, 以ch为key, count为value  
2. 接着遍历, 通过每次另外一个dic来保存左边的key和count  
3. 将右边的减少对应ch, 如果为0, 那么就remove对应的key  
4. 比对两边的key的count, 如果一样, 就说明是good. 否则就不是  
5. 将result结果返回即可.  

#### SOLUTION:
```swift
class Solution {
    func numSplits(_ s: String) -> Int {
        var dic:Dictionary<Character, Int> = [:]
        for ch in s {
            dic[ch, default: 0] += 1
        }
        var left:Dictionary<Character, Int> = [:]
        var result = 0
        for ch in s {
            left[ch, default: 0] += 1
            dic[ch] = dic[ch]! - 1
            if dic[ch] == 0 {
                dic.removeValue(forKey: ch)
            }
            if left.keys.count == dic.keys.count {
                result += 1
            }
        }
        return result
    }
}
```

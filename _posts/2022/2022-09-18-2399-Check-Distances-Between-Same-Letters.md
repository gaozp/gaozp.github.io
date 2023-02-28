---
layout: post
title: 2399. Check Distances Between Same Letters
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed string s consisting of only lowercase English letters, where each letter in s appears exactly twice. You are also given a 0-indexed integer array distance of length 26.

Each letter in the alphabet is numbered from 0 to 25 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25).

In a well-spaced string, the number of letters between the two occurrences of the ith letter is distance[i]. If the ith letter does not appear in s, then distance[i] can be ignored.

Return true if s is a well-spaced string, otherwise return false.

 

__Example 1:__
```
Input: s = "abaccb", distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: true
Explanation:
- 'a' appears at indices 0 and 2 so it satisfies distance[0] = 1.
- 'b' appears at indices 1 and 5 so it satisfies distance[1] = 3.
- 'c' appears at indices 3 and 4 so it satisfies distance[2] = 0.
Note that distance[3] = 5, but since 'd' does not appear in s, it can be ignored.
Return true because s is a well-spaced string.
```
__Example 2:__
```
Input: s = "aa", distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: false
Explanation:
- 'a' appears at indices 0 and 1 so there are zero letters between them.
Because distance[0] = 1, s is not a well-spaced string.
 ```

__Constraints:__
```
2 <= s.length <= 52
s consists only of lowercase English letters.
Each letter appears in s exactly twice.
distance.length == 26
0 <= distance[i] <= 50
```
#### EXPLANATION:

easy的题目, 用dic去标记位置. 如果没有, 则表明是第一次出现, 那么就直接记录即可. 第二次出现的时候, 需要用第二次的位置减去第一次的位置, 就得到了距离. 最后再对距离和distance进行对比就可以得到结果.

#### SOLUTION:
```swift
class Solution {
    func checkDistances(_ s: String, _ distance: [Int]) -> Bool {
        var dic:Dictionary<Character,Int> = [:]
        var arr:[Character] = Array(s)
        for index in arr.indices {
            var ch:Character = arr[index]
            if dic[ch] == nil {
                dic[ch] = index
            } else {
                dic[ch] = index - dic[ch]! - 1
            }
        }
        
        for index in 0...25 {
            var ch:Character = Character(UnicodeScalar(97 + index)!)
            if (dic[ch] != nil) {
                if (dic[ch] != distance[index]) {
                    return false
                }
            }
        }
        return true
    }
}
```

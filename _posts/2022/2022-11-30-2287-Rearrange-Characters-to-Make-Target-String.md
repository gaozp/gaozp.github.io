---
layout: post
title: 2287. Rearrange Characters to Make Target String
categories: [leetcode]
---
#### QUESTION:
You are given two 0-indexed strings s and target. You can take some letters from s and rearrange them to form new strings.

Return the maximum number of copies of target that can be formed by taking letters from s and rearranging them.

 

__Example 1:__
```
Input: s = "ilovecodingonleetcode", target = "code"
Output: 2
Explanation:
For the first copy of "code", take the letters at indices 4, 5, 6, and 7.
For the second copy of "code", take the letters at indices 17, 18, 19, and 20.
The strings that are formed are "ecod" and "code" which can both be rearranged into "code".
We can make at most two copies of "code", so we return 2.
```
__Example 2:__
```
Input: s = "abcba", target = "abc"
Output: 1
Explanation:
We can make one copy of "abc" by taking the letters at indices 0, 1, and 2.
We can make at most one copy of "abc", so we return 1.
Note that while there is an extra 'a' and 'b' at indices 3 and 4, we cannot reuse the letter 'c' at index 2, so we cannot make a second copy of "abc".
```
__Example 3:__
```
Input: s = "abbaccaddaeea", target = "aaaaa"
Output: 1
Explanation:
We can make one copy of "aaaaa" by taking the letters at indices 0, 3, 6, 9, and 12.
We can make at most one copy of "aaaaa", so we return 1.
```
 

__Constraints:__
```
1 <= s.length <= 100
1 <= target.length <= 10
s and target consist of lowercase English letters.
```
#### EXPLANATION:

看题意的时候就发现会有重复字母出现的情况, 那么就需要用s里的字母数除以target里的字母数, 这样才能得到需要几个. 确定了思路之后, 就可以开始.   
1. 首先将两个字符都映射到字典里  
2. 遍历target的字典, 用s的字典除以target的字典  
3. 在除的过程中, 获取到能整除的最小值即为最终结果  

#### SOLUTION:
```swift
class Solution {
    func rearrangeCharacters(_ s: String, _ target: String) -> Int {
        var dic: Dictionary<Character, Int> = [:]
        var result: Int?
        for ch in s {
            dic[ch, default: 0] += 1
        }
        var tarDic: Dictionary<Character, Int> = [:]
        for ch in target {
            tarDic[ch, default: 0] += 1
        }
        for (key, value) in tarDic {
            if result == nil {
                result = dic[key, default: 0] / value
            } else {
                result = min(result!, dic[key, default: 0] / value)
            }
        }
        return result!
    }
}
```

---
layout: post
title: 2085. Count Common Words With One Occurrence
categories: [leetcode]
---
#### QUESTION:
Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.

 

__Example 1:__
```
Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
Output: 2
Explanation:
- "leetcode" appears exactly once in each of the two arrays. We count this string.
- "amazing" appears exactly once in each of the two arrays. We count this string.
- "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
- "as" appears once in words1, but does not appear in words2. We do not count this string.
Thus, there are 2 strings that appear exactly once in each of the two arrays.
```
__Example 2:__
```
Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
Output: 0
Explanation: There are no strings that appear in each of the two arrays.
```
__Example 3:__
```
Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
Output: 1
Explanation: The only string that appears exactly once in each of the two arrays is "ab".
```
 

__Constraints:__
```
1 <= words1.length, words2.length <= 1000
1 <= words1[i].length, words2[j].length <= 30
words1[i] and words2[j] consists only of lowercase English letters.
```
#### EXPLANATION:

easy的题目, 用一个字典来装数量, 用一个数组来装两个word中出现的次数

#### SOLUTION:
```swift
class Solution {
    func countWords(_ words1: [String], _ words2: [String]) -> Int {
        var tmp:Int = 1
        var dic:Dictionary<String,[Int]> = [:]
        for string in words1 {
            if (dic[string] == nil) {
                dic[string] = [1]
            } else {
                dic[string]![0] += 1
            }
        }
        
        for string in words2 {
            if dic[string] == nil {
                dic[string] = [0 , 1]
            } else {
                if (dic[string]!.count == 1) {
                    dic[string]?.append(1)
                } else {
                    dic[string]![1] += 1
                }
            }
        }
        var result = 0
        for dictionary in dic.enumerated() {
            if dictionary.element.value.count == 2 &&  dictionary.element.value[0] == 1 && dictionary.element.value[1] == 1 {
                result += 1
            }
        }
        return result
    }
}
```

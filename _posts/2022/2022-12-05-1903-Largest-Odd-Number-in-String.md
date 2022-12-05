---
layout: post
title: 1903. Largest Odd Number in String
categories: [leetcode]
---
#### QUESTION:
You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.

A substring is a contiguous sequence of characters within a string.

 

__Example 1:__
```
Input: num = "52"
Output: "5"
Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
```
__Example 2:__
```
Input: num = "4206"
Output: ""
Explanation: There are no odd numbers in "4206".
```
__Example 3:__
```
Input: num = "35427"
Output: "35427"
Explanation: "35427" is already an odd number.
```
 

__Constraints:__
```
1 <= num.length <= 105
num only consists of digits and does not contain any leading zeros.
```
#### EXPLANATION:

思路就比较明确, 不需要进行排列组合, 那么只需要判断最后一位是不是奇数即可. 判断最后一位是不是奇数就比较容易, 判断出来了进行截断即可.

#### SOLUTION:
```swift
class Solution {
    func largestOddNumber(_ num: String) -> String {
        var _num = num

        for n in String(num.reversed()) {
            guard Int(String(n))! & 1 == 0 else { break }
            _num.removeLast()
        }

        return _num
    }
}
```

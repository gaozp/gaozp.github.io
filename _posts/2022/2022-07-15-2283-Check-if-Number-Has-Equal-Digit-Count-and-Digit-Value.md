---
layout: post
title: 2283. Check if Number Has Equal Digit Count and Digit Value
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed string num of length n consisting of digits.

Return true if for every index i in the range 0 <= i < n, the digit i occurs num[i] times in num, otherwise return false.

 

__Example 1:__
```
Input: num = "1210"
Output: true
Explanation:
num[0] = '1'. The digit 0 occurs once in num.
num[1] = '2'. The digit 1 occurs twice in num.
num[2] = '1'. The digit 2 occurs once in num.
num[3] = '0'. The digit 3 occurs zero times in num.
The condition holds true for every index in "1210", so return true.
```
__Example 2:__
```
Input: num = "030"
Output: false
Explanation:
num[0] = '0'. The digit 0 should occur zero times, but actually occurs twice in num.
num[1] = '3'. The digit 1 should occur three times, but actually occurs zero times in num.
num[2] = '0'. The digit 2 occurs zero times in num.
The indices 0 and 1 both violate the condition, so return false.
```
 

__Constraints:__
```
n == num.length
1 <= n <= 10
num consists of digits.
```
#### EXPLANATION:

easy的题目， 按照题目的逻辑写出来即可。

#### SOLUTION:
```swift
class Solution {
    func digitCount(_ num: String) -> Bool {
        var dic:Dictionary<String,Int> = [:]
        var arr:[Character] = Array(num)
        for item in arr {
            if dic[String(item)] == nil {
                dic[String(item)] = 1
            } else {
                dic[String(item)] = dic[String(item)]! + 1
            }
        }
        for index in arr.indices {
            var count:Int = dic[String(index)] == nil ? 0 : dic[String(index)]!
            if Int(String(arr[index])) != count {
                return false
            }
        }
        return true
    }
}
```

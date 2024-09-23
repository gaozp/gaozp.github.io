---
layout: post
title: 2578. Split With Minimum Sum
categories: [leetcode]
---
#### QUESTION:
Given a positive integer num, split it into two non-negative integers num1 and num2 such that:

The concatenation of num1 and num2 is a permutation of num.
In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal to the number of occurrences of that digit in num.
num1 and num2 can contain leading zeros.
Return the minimum possible sum of num1 and num2.

Notes:

It is guaranteed that num does not contain any leading zeros.
The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.
 

__Example 1:__
```
Input: num = 4325
Output: 59
Explanation: We can split 4325 so that num1 is 24 and num2 is 35, giving a sum of 59. We can prove that 59 is indeed the minimal possible sum.
```
__Example 2:__
```
Input: num = 687
Output: 75
Explanation: We can split 687 so that num1 is 68 and num2 is 7, which would give an optimal sum of 75.
```
 

__Constraints:__
```
10 <= num <= 109
```

#### EXPLANATION:

一道easy的题目, 思路就比较容易. 只需要将所有的数字进行排序, 然后按着顺序添加在两个数的末尾. 这样得到的两个数就是最小的数. 这样两者加起来也是最小的.   
可以用一个flag来进行标记加入num1还是num2后面. 也可以用两个数的长度来进行.   

#### SOLUTION:
```swift
class Solution {
    func splitNum(_ num: Int) -> Int {
        var sorted: [Character] = Array(String(num)).sorted()
        var flag: Bool = true
        var num1: String = ""
        var num2: String = ""
        for i in 0...sorted.count-1 {
            if flag {
                num1 += String(sorted[i])
            } else {
                num2 += String(sorted[i])
            }
            flag = !flag
        }
        return Int(num1)!+Int(num2)!
    }
}
```

---
layout: post
title: 2566. Maximum Difference by Remapping a Digit
categories: [leetcode]
---
#### QUESTION:
You are given an integer num. You know that Danny Mittal will sneakily remap one of the 10 possible digits (0 to 9) to another digit.

Return the difference between the maximum and minimum values Danny can make by remapping exactly one digit in num.

Notes:

When Danny remaps a digit d1 to another digit d2, Danny replaces all occurrences of d1 in num with d2.
Danny can remap a digit to itself, in which case num does not change.
Danny can remap different digits for obtaining minimum and maximum values respectively.
The resulting number after remapping can contain leading zeroes.
We mentioned "Danny Mittal" to congratulate him on being in the top 10 in Weekly Contest 326.
 

__Example 1:__
```
Input: num = 11891
Output: 99009
Explanation: 
To achieve the maximum value, Danny can remap the digit 1 to the digit 9 to yield 99899.
To achieve the minimum value, Danny can remap the digit 1 to the digit 0, yielding 890.
The difference between these two numbers is 99009.
```
__Example 2:__
```
Input: num = 90
Output: 99
Explanation:
The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
Thus, we return 99.
```
 

__Constraints:__
```
1 <= num <= 108
```
#### EXPLANATION:

最大的数其实也就是最大位的那个非9的数. 然后统一改一下即可. 最小的那个自然就是最大位数的那个数就行.

#### SOLUTION:
```swift
class Solution {
    func minMaxDifference(_ num: Int) -> Int {
        var max: String = ""
        var tmpMax: Character = " "
        var min: String = ""
        var tmpMin: Character = " "
        var stringNum: [Character] = Array(String(num))
        var alreadyFound: Bool = false
        for index in 0...stringNum.count-1 {
            if stringNum[index] != "9" && !alreadyFound {
                tmpMax = stringNum[index]
                alreadyFound = true
            }
            if stringNum[index] == tmpMax {
                max = max + "9"
            } else {
                max = max + String(stringNum[index])
            }
            if index == 0 {
                tmpMin = stringNum[index]
            }
            if stringNum[index] == tmpMin {
                min = min + "0"
            } else {
                min = min + String(stringNum[index])
            }
        }
        return Int(max)! - Int(min)!
    }
}
```

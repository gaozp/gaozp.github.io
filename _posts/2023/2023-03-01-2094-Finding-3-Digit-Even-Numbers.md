---
layout: post
title: 2094. Finding 3-Digit Even Numbers
categories: [leetcode]
---
#### QUESTION:
You are given an integer array digits, where each element is a digit. The array may contain duplicates.

You need to find all the unique integers that follow the given requirements:

The integer consists of the concatenation of three elements from digits in any arbitrary order.
The integer does not have leading zeros.
The integer is even.
For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.

Return a sorted array of the unique integers.

 

__Example 1:__
```
Input: digits = [2,1,3,0]
Output: [102,120,130,132,210,230,302,310,312,320]
Explanation: All the possible integers that follow the requirements are in the output array. 
Notice that there are no odd integers or integers with leading zeros.
```
__Example 2:__
```
Input: digits = [2,2,8,8,2]
Output: [222,228,282,288,822,828,882]
Explanation: The same digit can be used as many times as it appears in digits. 
In this example, the digit 8 is used twice each time in 288, 828, and 882. 
```
__Example 3:__
```
Input: digits = [3,7,5]
Output: []
Explanation: No even integers can be formed using the given digits.
```
 

__Constraints:__
```
3 <= digits.length <= 100
0 <= digits[i] <= 9
```
#### EXPLANATION:

直接三个for循环就可以了. 注意几个判断可以优化速度的.

#### SOLUTION:
```swift
class Solution {
    func findEvenNumbers(_ digits: [Int]) -> [Int] {
        var result: Set<Int> = Set()
        for i in 0...digits.count-1 {
            if digits[i] == 0 {
                continue
            }
            for j in 0...digits.count-1 {
                if i == j {
                    continue
                }
                for m in 0...digits.count-1 {
                    if m == j || m == i {
                        continue
                    }
                    if digits[m] % 2 != 0 {
                        continue
                    }
                    var tmp = Int(String(digits[i])+String(digits[j])+String(digits[m]))
                    if tmp! % 2 == 0 {
                        result.insert(tmp!)
                    }
                }
            }
        }
        return Array(result.sorted())
    }
}
```

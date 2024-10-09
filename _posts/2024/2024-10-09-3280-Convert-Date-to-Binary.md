---
layout: post
title: 3280. Convert Date to Binary
categories: [leetcode]
---
#### QUESTION:
You are given a string date representing a Gregorian calendar date in the yyyy-mm-dd format.

date can be written in its binary representation obtained by converting year, month, and day to their binary representations without any leading zeroes and writing them down in year-month-day format.

Return the binary representation of date.

 

__Example 1:__
```
Input: date = "2080-02-29"

Output: "100000100000-10-11101"

Explanation:

100000100000, 10, and 11101 are the binary representations of 2080, 02, and 29 respectively.
```
__Example 2:__
```
Input: date = "1900-01-01"

Output: "11101101100-1-1"

Explanation:

11101101100, 1, and 1 are the binary representations of 1900, 1, and 1 respectively.
```
 

__Constraints:__
```
date.length == 10
date[4] == date[7] == '-', and all other date[i]'s are digits.
The input is generated such that date represents a valid Gregorian calendar date between Jan 1st, 1900 and Dec 31st, 2100 (both inclusive).
```
#### EXPLANATION:

这道题思路就很清晰:  
1. 将date拆分出来  
2. 将拆分出来的单个转换成二进制  
3. 再将转换出来的二进制拼装  

#### SOLUTION:
```swift
class Solution {
    func convertDateToBinary(_ date: String) -> String {
        return date.components(separatedBy: "-")
            .compactMap(Int.init)
            .map { i in
                String(i, radix: 2)
            }.joined(separator: "-")
    }
}
```

---
layout: post
title: 2259. Remove Digit From Number to Maximize Result
categories: [leetcode]
---
#### QUESTION:
You are given a string number representing a positive integer and a character digit.

Return the resulting string after removing exactly one occurrence of digit from number such that the value of the resulting string in decimal form is maximized. The test cases are generated such that digit occurs at least once in number.

 

__Example 1:__
```
Input: number = "123", digit = "3"
Output: "12"
Explanation: There is only one '3' in "123". After removing '3', the result is "12".
```
__Example 2:__
```
Input: number = "1231", digit = "1"
Output: "231"
Explanation: We can remove the first '1' to get "231" or remove the second '1' to get "123".
Since 231 > 123, we return "231".
```
__Example 3:__
```
Input: number = "551", digit = "5"
Output: "51"
Explanation: We can remove either the first or second '5' from "551".
Both result in the string "51".
```
 

__Constraints:__
```
2 <= number.length <= 100
number consists of digits from '1' to '9'.
digit is a digit from '1' to '9'.
digit occurs at least once in number.
```
#### EXPLANATION:

将所有的组合都取出来, 放在数组里进行排序即可.

#### SOLUTION:
```swift
class Solution {
    func removeDigit(_ number: String, _ digit: Character) -> String {
        var arr: [String] = []
        var copy = number
        for (i, num) in number.enumerated() {
            if num == digit {
                let index = number.index(number.startIndex, offsetBy: i)
                copy.remove(at: index)
                arr.append(copy)
                copy = number
            }
        }
        return arr.sorted(by: >)[0]
    }
}
```

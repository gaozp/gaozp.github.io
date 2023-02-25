---
layout: post
title: 2303. Calculate Amount Paid in Taxes
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed 2D integer array brackets where brackets[i] = [upperi, percenti] means that the ith tax bracket has an upper bound of upperi and is taxed at a rate of percenti. The brackets are sorted by upper bound (i.e. upperi-1 < upperi for 0 < i < brackets.length).

Tax is calculated as follows:

The first upper0 dollars earned are taxed at a rate of percent0.
The next upper1 - upper0 dollars earned are taxed at a rate of percent1.
The next upper2 - upper1 dollars earned are taxed at a rate of percent2.
And so on.
You are given an integer income representing the amount of money you earned. Return the amount of money that you have to pay in taxes. Answers within 10-5 of the actual answer will be accepted.

 

__Example 1:__
```
Input: brackets = [[3,50],[7,10],[12,25]], income = 10
Output: 2.65000
Explanation:
Based on your income, you have 3 dollars in the 1st tax bracket, 4 dollars in the 2nd tax bracket, and 3 dollars in the 3rd tax bracket.
The tax rate for the three tax brackets is 50%, 10%, and 25%, respectively.
In total, you pay $3 * 50% + $4 * 10% + $3 * 25% = $2.65 in taxes.
```
__Example 2:__
```
Input: brackets = [[1,0],[4,25],[5,50]], income = 2
Output: 0.25000
Explanation:
Based on your income, you have 1 dollar in the 1st tax bracket and 1 dollar in the 2nd tax bracket.
The tax rate for the two tax brackets is 0% and 25%, respectively.
In total, you pay $1 * 0% + $1 * 25% = $0.25 in taxes.
```
__Example 3:__
```
Input: brackets = [[2,50]], income = 0
Output: 0.00000
Explanation:
You have no income to tax, so you have to pay a total of $0 in taxes.
```
 

__Constraints:__
```
1 <= brackets.length <= 100
1 <= upperi <= 1000
0 <= percenti <= 100
0 <= income <= 1000
upperi is sorted in ascending order.
All the values of upperi are unique.
The upper bound of the last tax bracket is greater than or equal to income.
```
#### EXPLANATION:

其实还是比较简单的, 模拟出这个过程即可.

#### SOLUTION:
```swift
class Solution {
    func calculateTax(_ brackets: [[Int]], _ income: Int) -> Double {
        var result = 0.0
        var income = income
        for index in 0...brackets.count-1 {
            if income <= 0 {
                break
            }
            var tmp = 0
            if index == 0 {
                tmp = brackets[index][0]
            } else {
                tmp = brackets[index][0] - brackets[index - 1][0]
            }
            if income - tmp < 0 {
                tmp = income
            }
            result = result + Double(tmp * brackets[index][1])/100
            income -= tmp
        }
        return result
    }
}
```

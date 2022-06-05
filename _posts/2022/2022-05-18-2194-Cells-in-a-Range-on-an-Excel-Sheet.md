---
layout: post
title: 2194. Cells in a Range on an Excel Sheet
categories: [leetcode]
---
#### QUESTION:
A cell (r, c) of an excel sheet is represented as a string "<col><row>" where:

<col> denotes the column number c of the cell. It is represented by alphabetical letters.
For example, the 1st column is denoted by 'A', the 2nd by 'B', the 3rd by 'C', and so on.
<row> is the row number r of the cell. The rth row is represented by the integer r.
You are given a string s in the format "<col1><row1>:<col2><row2>", where <col1> represents the column c1, <row1> represents the row r1, <col2> represents the column c2, and <row2> represents the row r2, such that r1 <= r2 and c1 <= c2.

Return the list of cells (x, y) such that r1 <= x <= r2 and c1 <= y <= c2. The cells should be represented as strings in the format mentioned above and be sorted in non-decreasing order first by columns and then by rows.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2022/02/08/ex1drawio.png)
```
Input: s = "K1:L2"
Output: ["K1","K2","L1","L2"]
Explanation:
The above diagram shows the cells which should be present in the list.
The red arrows denote the order in which the cells should be presented.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2022/02/09/exam2drawio.png)
```
Input: s = "A1:F1"
Output: ["A1","B1","C1","D1","E1","F1"]
Explanation:
The above diagram shows the cells which should be present in the list.
The red arrow denotes the order in which the cells should be presented.
 ```

__Constraints:__
```
s.length == 5
'A' <= s[0] <= s[3] <= 'Z'
'1' <= s[1] <= s[4] <= '9'
s consists of uppercase English letters, digits and ':'.
```
#### EXPLANATION:
这个也没有什么特别可说的, 两个for循环就可以得到遍历结果了.
#### SOLUTION:
```java
class Solution {
    func cellsInRange(_ s: String) -> [String] {
        var result:[String] = []
        var substrings:[Substring] = s.split(separator: ":")
        var minCol:Character = Array(String(substrings[0]))[0]
        var maxCol:Character = Array(String(substrings[1]))[0]
        var minRow:Character = Array(String(substrings[0]))[1]
        var maxRow:Character = Array(String(substrings[1]))[1]
        for i in minCol.asciiValue!...maxCol.asciiValue! {
            let col = String(UnicodeScalar(UInt8(i)))
                for j in Int(String(minRow))!...Int(String(maxRow))! {
                    result.append(col+String(j))
                }

        }
        return result
    }
}
```
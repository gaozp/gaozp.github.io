---
layout: post
title: 2125. Number of Laser Beams in a Bank
categories: [leetcode]
---
#### QUESTION:
Anti-theft security devices are activated inside a bank. You are given a 0-indexed binary string array bank representing the floor plan of the bank, which is an m x n 2D matrix. bank[i] represents the ith row, consisting of '0's and '1's. '0' means the cell is empty, while'1' means the cell has a security device.

There is one laser beam between any two security devices if both conditions are met:

The two devices are located on two different rows: r1 and r2, where r1 < r2.
For each row i where r1 < i < r2, there are no security devices in the ith row.
Laser beams are independent, i.e., one beam does not interfere nor join with another.

Return the total number of laser beams in the bank.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2021/12/24/laser1.jpg)
```
Input: bank = ["011001","000000","010100","001000"]
Output: 8
Explanation: Between each of the following device pairs, there is one beam. In total, there are 8 beams:
 * bank[0][1] -- bank[2][1]
 * bank[0][1] -- bank[2][3]
 * bank[0][2] -- bank[2][1]
 * bank[0][2] -- bank[2][3]
 * bank[0][5] -- bank[2][1]
 * bank[0][5] -- bank[2][3]
 * bank[2][1] -- bank[3][2]
 * bank[2][3] -- bank[3][2]
Note that there is no beam between any device on the 0th row with any on the 3rd row.
This is because the 2nd row contains security devices, which breaks the second condition.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2021/12/24/laser2.jpg)
```
Input: bank = ["000","111","000"]
Output: 0
Explanation: There does not exist two devices located on two different rows.
 ```

__Constraints:__
```
m == bank.length
n == bank[i].length
1 <= m, n <= 500
bank[i][j] is either '0' or '1'.
```
#### EXPLANATION:

题目虽然是medium, 但是还是很简单的, 就是排列组合那套. 只要将对应的相邻的两组相乘把结果加起来即可. 注意边界情况就行.

#### SOLUTION:
```swift
class Solution {
    func numberOfBeams(_ bank: [String]) -> Int {
        var result:Int = 0
        var countArray:[Int] = []
        var lineCount:Int = 0
        for bk in bank {
            var count = 0
            bk.forEach({ (ch) in
                if ch == "1" {
                    count += 1
                }
            })
            if count > 0 {
                lineCount += 1
            }
            countArray.append(count)
        }
        if lineCount <= 1 {
            return 0
        }
        var pre:Int = -1
        for count in countArray {
            if count != 0 {
                if pre != -1 {
                    result += pre * count
                }
                pre = count
            }
        }
        return result
    }
}
```

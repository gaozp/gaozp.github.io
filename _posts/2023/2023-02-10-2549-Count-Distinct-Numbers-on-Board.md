---
layout: post
title: 2549. Count Distinct Numbers on Board
categories: [leetcode]
---
#### QUESTION:
You are given a positive integer n, that is initially placed on a board. Every day, for 109 days, you perform the following procedure:

For each number x present on the board, find all numbers 1 <= i <= n such that x % i == 1.
Then, place those numbers on the board.
Return the number of distinct integers present on the board after 109 days have elapsed.

__Note:__

Once a number is placed on the board, it will remain on it until the end.
% stands for the modulo operation. For example, 14 % 3 is 2.
 

__Example 1:__
```
Input: n = 5
Output: 4
Explanation: Initially, 5 is present on the board. 
The next day, 2 and 4 will be added since 5 % 2 == 1 and 5 % 4 == 1. 
After that day, 3 will be added to the board because 4 % 3 == 1. 
At the end of a billion days, the distinct numbers on the board will be 2, 3, 4, and 5. 
```
__Example 2:__
```
Input: n = 3
Output: 2
Explanation: 
Since 3 % 2 == 1, 2 will be added to the board. 
After a billion days, the only two distinct numbers on the board are 2 and 3. 
```
 

__Constraints:__
```
1 <= n <= 100
```
#### EXPLANATION:

这其实是一道数学题.  
首先,第一天我们肯定可以得到n  
第二天, 我们可以得到n-1, 因为 n % (n-1) = 1  
第三天, 我们可以得到n-2, 因为前一天我们已经得到了n-1, 那么(n-1)%(n-2) = 1  
按这个逻辑下去, 在n天之后, 我们一定可以得到2 -> n的所有数.  

#### SOLUTION:
```swift
class Solution {
    func distinctIntegers(_ n: Int) -> Int {
        return max(n-1, 1)
    }
}
```

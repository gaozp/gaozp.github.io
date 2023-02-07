---
layout: post
title: 2558. Take Gifts From the Richest Pile
categories: [leetcode]
---
#### QUESTION:
You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the following:

Choose the pile with the maximum number of gifts.
If there is more than one pile with the maximum number of gifts, choose any.
Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.
Return the number of gifts remaining after k seconds.

 

__Example 1:__
```
Input: gifts = [25,64,9,4,100], k = 4
Output: 29
Explanation: 
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.
```
__Example 2:__
```
Input: gifts = [1,1,1,1], k = 4
Output: 4
Explanation: 
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile. 
That is, you can't take any pile with you. 
So, the total gifts remaining are 4.
```
 

__Constraints:__
```
1 <= gifts.length <= 103
1 <= gifts[i] <= 109
1 <= k <= 103
```
#### EXPLANATION:

easy的题目, 每次进行排序后对最后一个进行处理即可.

#### SOLUTION:
```swift
class Solution {
    func pickGifts(_ gifts: [Int], _ k: Int) -> Int {
        var gifts = gifts
        for _ in 1...k {
            gifts = gifts.sorted()
            gifts[gifts.count - 1] = Int(Double(gifts[gifts.count-1]).squareRoot())
        }
        return gifts.reduce(0, +)
    }
}
```

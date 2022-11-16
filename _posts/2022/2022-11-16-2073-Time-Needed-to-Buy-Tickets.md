---
layout: post
title: 2073. Time Needed to Buy Tickets
categories: [leetcode]
---
#### QUESTION:
There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.

You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].

Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.

Return the time taken for the person at position k (0-indexed) to finish buying tickets.

 

__Example 1:__
```
Input: tickets = [2,3,2], k = 2
Output: 6
Explanation: 
- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.
```
__Example 2:__
```
Input: tickets = [5,1,1,1], k = 0
Output: 8
Explanation:
- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
- In the next 4 passes, only the person in position 0 is buying tickets.
The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
```
 

__Constraints:__
```
n == tickets.length
1 <= n <= 100
1 <= tickets[i] <= 100
0 <= k < n
```
#### EXPLANATION:

while循环模拟买票操作, 只要tickets[k]!= 0 的时候, 就一直买票. 当买票到尾部的时候, 就继续从0开始即可.

#### SOLUTION:
```swift
class Solution {
    func timeRequiredToBuy(_ tickets: [Int], _ k: Int) -> Int {
        var tickets = tickets
        var result: Int = 0
        var tmpIndex: Int = 0
        while tickets[k] != 0 {
            if (tickets[tmpIndex] != 0) {
                tickets[tmpIndex] -= 1
                result += 1
            }
            tmpIndex = tmpIndex + 1 == tickets.count ? 0 : tmpIndex + 1
        }
        return result
    }
}
```

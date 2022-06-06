---
layout: post
title: 1817. Finding the Users Active Minutes
categories: [leetcode]
---
#### QUESTION:
You are given the logs for users' actions on LeetCode, and an integer k. The logs are represented by a 2D integer array logs where each logs[i] = [IDi, timei] indicates that the user with IDi performed an action at the minute timei.

Multiple users can perform actions simultaneously, and a single user can perform multiple actions in the same minute.

The user active minutes (UAM) for a given user is defined as the number of unique minutes in which the user performed an action on LeetCode. A minute can only be counted once, even if multiple actions occur during it.

You are to calculate a 1-indexed array answer of size k such that, for each j (1 <= j <= k), answer[j] is the number of users whose UAM equals j.

Return the array answer as described above.

 

__Example 1:__
```
Input: logs = [[0,5],[1,2],[0,2],[0,5],[1,3]], k = 5
Output: [0,2,0,0,0]
Explanation:
The user with ID=0 performed actions at minutes 5, 2, and 5 again. Hence, they have a UAM of 2 (minute 5 is only counted once).
The user with ID=1 performed actions at minutes 2 and 3. Hence, they have a UAM of 2.
Since both users have a UAM of 2, answer[2] is 2, and the remaining answer[j] values are 0.
```
__Example 2:__
```
Input: logs = [[1,1],[2,2],[2,3]], k = 4
Output: [1,1,0,0]
Explanation:
The user with ID=1 performed a single action at minute 1. Hence, they have a UAM of 1.
The user with ID=2 performed actions at minutes 2 and 3. Hence, they have a UAM of 2.
There is one user with a UAM of 1 and one with a UAM of 2.
Hence, answer[1] = 1, answer[2] = 1, and the remaining values are 0.
```
 

__Constraints:__
```
1 <= logs.length <= 104
0 <= IDi <= 109
1 <= timei <= 105
k is in the range [The maximum UAM for a user, 105].
```
#### EXPLANATION:

首先用一个set去装一个user的uam, 因为set可以自动帮我们进行去重. 再用一个dic去装user和set的关联.   
最后一个遍历. 将对应的count位置的结果进行+1. 遍历结束就得到了最终的结果.

#### SOLUTION:
```swift
class Solution {
    func findingUsersActiveMinutes(_ logs: [[Int]], _ k: Int) -> [Int] {
        var dic:Dictionary = [Int:Set<Int>]()
        for log in logs {
            var set:Set<Int> = Set()
            if dic[log[0]] != nil {
                set = dic[log[0]]!
            }
            set.insert(log[1])
            dic[log[0]] = set
        }
        var result:[Int] = Array(repeating: 0, count: k)
        for key in dic.keys {
            result[dic[key]!.count-1] += 1
        }
        return result
    }
}
```

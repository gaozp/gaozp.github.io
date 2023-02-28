---
layout: post
title: 2225. Find Players With Zero or One Losses
categories: [leetcode]
---
#### QUESTION:
You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.

Return a list answer of size 2 where:

answer[0] is a list of all players that have not lost any matches.
answer[1] is a list of all players that have lost exactly one match.
The values in the two lists should be returned in increasing order.

Note:

You should only consider the players that have played at least one match.
The testcases will be generated such that no two matches will have the same outcome.
 

__Example 1:__
```
Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
```
__Example 2:__
```
Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].
```
 

__Constraints:__
```
1 <= matches.length <= 105
matches[i].length == 2
1 <= winneri, loseri <= 105
winneri != loseri
All matches[i] are unique.
```
#### EXPLANATION:

首先将每个player的输赢都算出来. 然后排序后, 再查看每个player输的次数即可.

#### SOLUTION:
```swift
class Solution {
    func findWinners(_ matches: [[Int]]) -> [[Int]] {
        var dic: Dictionary<Int, [Int]> = [:]
        for match in matches {
            var tmpWin = dic[match[0], default: [0, 0]]
            tmpWin[0] += 1
            dic[match[0]] = tmpWin
            var tmpLose = dic[match[1], default: [0, 0]]
            tmpLose[1] += 1
            dic[match[1]] = tmpLose
        }
        let sorted = dic.sorted { a, b in
            a.key < b.key
        }
        var result:[[Int]] = Array(repeating: [], count: 2)
        for d in sorted.enumerated() {
            if d.element.value[1] == 0 {
                result[0].append(d.element.key)
            } else if d.element.value[1] == 1 {
                result[1].append(d.element.key)
            }
        }
        return result
    }
}
```

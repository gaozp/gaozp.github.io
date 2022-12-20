---
layout: post
title: 841. Keys and Rooms
categories: [leetcode]
---
#### QUESTION:
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

 

__Example 1:__
```
Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation: 
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
```
__Example 2:__
```
Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
```
 

__Constraints:__
```
n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
```
#### EXPLANATION:

用一个keys来记录当前已经有的钥匙, 用一个visited来记录已经走过的房间, 在钥匙用光之后, 如果还没有走完全部房间, 就说明有房间走不了了. 为了避免重复, 已经走过的房间,也就是有钥匙的房间就不需要再走了.

#### SOLUTION:
```swift
class Solution {
    func canVisitAllRooms(_ rooms: [[Int]]) -> Bool {
        var keys:[Int] = rooms[0]
        var visited = Set<Int>()
        visited.insert(0)
        while !keys.isEmpty {
            var tmpKey = keys.removeFirst()
            if !visited.contains(tmpKey) {
                keys.append(contentsOf: rooms[tmpKey])
                visited.insert(tmpKey)
            }
        }
        return visited.count == rooms.count
    }
}
```

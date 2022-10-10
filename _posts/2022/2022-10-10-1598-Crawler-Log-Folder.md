---
layout: post
title: 1598. Crawler Log Folder
categories: [leetcode]
---
#### QUESTION:
The Leetcode file system keeps a log each time some user performs a change folder operation.

The operations are described below:

"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
"./" : Remain in the same folder.
"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.

The file system starts in the main folder, then the operations in logs are performed.

Return the minimum number of operations needed to go back to the main folder after the change folder operations.

 

__Example 1:__

![](https://assets.leetcode.com/uploads/2020/09/09/sample_11_1957.png)
```
Input: logs = ["d1/","d2/","../","d21/","./"]
Output: 2
Explanation: Use this change folder operation "../" 2 times and go back to the main folder.
```
__Example 2:__

![](https://assets.leetcode.com/uploads/2020/09/09/sample_22_1957.png)
```
Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
Output: 3
```
__Example 3:__
```
Input: logs = ["d1/","../","../","../"]
Output: 0
```
 

__Constraints:__
```
1 <= logs.length <= 103
2 <= logs[i].length <= 10
logs[i] contains lowercase English letters, digits, '.', and '/'.
logs[i] follows the format described in the statement.
Folder names consist of lowercase English letters and digits.
```
#### EXPLANATION:

easy的题目, 一个for循环就能解出结果.

#### SOLUTION:
```kotlin
class Solution {
    fun minOperations(logs: Array<String>): Int {
        var level = 0
        logs.forEach { 
            when(it) {
                "../" -> level = Math.max(0,level - 1)
                "./" -> {}
                else -> level++
            }
        }
        return level
    }
}
```

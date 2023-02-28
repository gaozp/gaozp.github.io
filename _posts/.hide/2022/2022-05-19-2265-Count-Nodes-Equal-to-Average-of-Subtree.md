---
layout: post
title: 2265. Count Nodes Equal to Average of Subtree
categories: [leetcode]
---
#### QUESTION:
Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.
 

__Example 1:__
![](https://assets.leetcode.com/uploads/2022/03/15/image-20220315203925-1.png)
```
Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2022/03/26/image-20220326133920-1.png)
```
Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
```
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 1000
#### EXPLANATION:
果然medium的题目就需要想一下了. 首先看到题目, 首先想到的肯定是需要后序遍历, 这样才能正确的遍历完所有节点. 那么问题就来到了, 怎么保存节点数量和sum呢. 可以直接用数组来标记, 第0位表示当前的节点数量, 第1位表示当前的sum. 这样从后序遍历时, 每个节点的左右树的节点数量和和就都能拿到, 再加上自己的, 就可以了. 同时还可以将自己返回, 作为下一步的计算基础. 
#### SOLUTION:
```java
class Solution {
    var averageOfSubtreeResult:Int = 0;
    func averageOfSubtree(_ root: TreeNode?) -> Int {
        averageOfSubtreeHelper(root)
        return averageOfSubtreeResult;
    }
    
    func averageOfSubtreeHelper(_ root: TreeNode?) -> [Int] {
        if root == nil {
            return [0,0]
        }
        var left:[Int] = averageOfSubtreeHelper(root?.left)
        var right:[Int] = averageOfSubtreeHelper(root?.right)
        var sum:Int = left[1] + right[1] + root!.val
        var nodesCount:Int = left[0] + right[0] + 1
        if (sum/nodesCount == root?.val) {
            averageOfSubtreeResult += 1
        }
        return [nodesCount,sum]
    }
}
```

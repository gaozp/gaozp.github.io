---
layout: post
title: 1325. Delete Leaves With a Given Value
categories: [leetcode]
---
#### QUESTION:
Given a binary tree root and an integer target, delete all the leaf nodes with value target.

Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you cannot).

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/01/09/sample_1_1684.png)

```
Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2020/01/09/sample_2_1684.png)

```
Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]
```
__Example 3:__
![](https://assets.leetcode.com/uploads/2020/01/15/sample_3_1684.png)

```
Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with value (target = 2) are removed at each step.
```
 

__Constraints:__
```
The number of nodes in the tree is in the range [1, 3000].
1 <= Node.val, target <= 1000
```
#### EXPLANATION:

首先看到题目的第一个思路就是一个后序遍历, 同时也需要知道, 需要在上一个节点进行删除操作, 所以就需要把结果返回给上一层. 所以就得到了helper的思路: 返回当前节点是否要删除. 

#### SOLUTION:
```swift
class Solution {
    func removeLeafNodes(_ root: TreeNode?, _ target: Int) -> TreeNode? {
        var result:Bool = removeLeafNodesHelper(root, target)
        return result ? nil : root
    }
    
    func removeLeafNodesHelper(_ root: TreeNode?, _ target: Int) -> Bool {
        if root == nil {
            return false
        }
        var left:Bool = removeLeafNodesHelper(root?.left, target)
        var right:Bool = removeLeafNodesHelper(root?.right, target)
        if left {
            root?.left = nil
        }
        if right {
            root?.right = nil
        }
        if root?.val == target && root?.left == nil && root?.right == nil {
            return true
        } else {
            return false
        }
    }
}
```

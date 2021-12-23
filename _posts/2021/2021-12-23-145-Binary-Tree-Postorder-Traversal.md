---
layout: post
title: 145. Binary Tree Postorder Traversal
categories: [leetcode]
---
#### QUESTION:
Given the root of a binary tree, return the postorder traversal of its nodes' values.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg)
```
Input: root = [1,null,2,3]
Output: [3,2,1]
```
__Example 2:__
```
Input: root = []
Output: []
```
__Example 3:__
```
Input: root = [1]
Output: [1]
```

__Constraints:__
```
The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
```
#### EXPLANATION:
二叉树的后序遍历, 没什么特别可说的, 比较基础了.

#### SOLUTION:
```swift
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public var val: Int
 *     public var left: TreeNode?
 *     public var right: TreeNode?
 *     public init() { self.val = 0; self.left = nil; self.right = nil; }
 *     public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
 *     public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
 *         self.val = val
 *         self.left = left
 *         self.right = right
 *     }
 * }
 */
class Solution {
    func postorderTraversal(_ root: TreeNode?) -> [Int] {
        var result:[Int] = []
        postorderTraversalHelper(root, array: &result)
        return result
    }
    func postorderTraversalHelper(_ root: TreeNode?, array: inout [Int]) {
        if (root != nil) {
            postorderTraversalHelper(root?.left, array: &array)
        }
        if (root != nil) {
            postorderTraversalHelper(root?.right, array: &array)
        }
        if (root != nil) {
            array.append(root!.val)
        }
    }
}
```

---
layout: post
title: 144. Binary Tree Preorder Traversal
categories: [leetcode]
---
#### QUESTION:
Given the root of a binary tree, return the preorder traversal of its nodes' values.


__Example 1:__
![](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)
```
Input: root = [1,null,2,3]
Output: [1,2,3]
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
__Example 4:__
![](https://assets.leetcode.com/uploads/2020/09/15/inorder_5.jpg)
```
Input: root = [1,2]
Output: [1,2]
```
__Example 5:__
![](https://assets.leetcode.com/uploads/2020/09/15/inorder_4.jpg)
```
Input: root = [1,null,2]
Output: [1,2]
```

__Constraints:__

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 

Follow up: Recursive solution is trivial, could you do it iteratively?
#### EXPLANATION:
前序遍历, 也没有什么需要说的. 只是需要注意swift的extension的应用. 具体可以看[官方文档](https://docs.swift.org/swift-book/LanguageGuide/Extensions.html)
#### SOLUTION:
```swift
extension TreeNode {
    func preTraversal(value: (Int) -> Void) {
        value(val)
        left?.preTraversal(value: value)
        right?.preTraversal(value: value)
    }
}
class Solution {
    
    func preorderTraversal(_ root: TreeNode?) -> [Int] {
        var values: [Int] = []
        root?.preTraversal { result in
            values.append(result)
        }
        return values
    }
}
```

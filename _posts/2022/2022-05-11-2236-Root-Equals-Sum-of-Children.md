---
layout: post
title: 2236. Root Equals Sum of Children
categories: [leetcode]
---
#### QUESTION:
You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.

Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.

 __Example 1:__
 ![](https://assets.leetcode.com/uploads/2022/04/08/graph3drawio.png)

 ```
 Input: root = [10,4,6]
Output: true
Explanation: The values of the root, its left child, and its right child are 10, 4, and 6, respectively.
10 is equal to 4 + 6, so we return true.
 ```
  __Example 2:__
 ![](https://assets.leetcode.com/uploads/2022/04/08/graph3drawio-1.png)
```
Input: root = [5,3,1]
Output: false
Explanation: The values of the root, its left child, and its right child are 5, 3, and 1, respectively.
5 is not equal to 3 + 1, so we return false.
```

__Constraints:__
```
The tree consists only of the root, its left child, and its right child.
-100 <= Node.val <= 100
```
#### EXPLANATION:
因为题目中已经限定了必然有3个节点, 那么我们可以直接unwrap.

#### SOLUTION:
```swift
class Solution {
    func checkTree(_ root: TreeNode?) -> Bool {
        return root!.val == (root!.left!.val + root!.right!.val);
    }
}
```

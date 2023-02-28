---
layout: post
title: 1448. Count Good Nodes in Binary Tree
categories: [leetcode]
---
#### QUESTION:
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/04/02/test_sample_1.png)

```
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2020/04/02/test_sample_2.png)

```
Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
```
__Example 3:__
```
Input: root = [1]
Output: 1
Explanation: Root is considered as good.
```
 

__Constraints:__
```
The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
```
#### EXPLANATION:

虽然是medium的题目, 但是只要用一个数组去存储对应的顺序, 再通过对于的max来获取到当前值是不是最大的值即可.

#### SOLUTION:
```swift
class Solution {
    func goodNodes(_ root: TreeNode?) -> Int {
        var arr:[Int] = []
        return goodNodesHelper(root, arr: &arr)
    }
    
    func goodNodesHelper(_ root: TreeNode?, arr: inout [Int]) -> Int {
        arr.append(root!.val)
        var result:Int = 0
        if arr.max() == root!.val {
            result = 1
        }
        var left:Int = 0
        if ((root?.left) != nil) {
            left = goodNodesHelper(root?.left, arr: &arr)
        }
        var right:Int = 0
        if ((root?.right) != nil) {
            right = goodNodesHelper(root?.right, arr: &arr)
        }
        arr.removeLast()
        return result + left + right
    }
}
```

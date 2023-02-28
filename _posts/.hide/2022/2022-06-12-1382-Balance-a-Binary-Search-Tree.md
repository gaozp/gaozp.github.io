---
layout: post
title: 1382. Balance a Binary Search Tree
categories: [leetcode]
---
#### QUESTION:
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2021/08/10/balance1-tree.jpg)
```
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2021/08/10/balanced2-tree.jpg)
```
Input: root = [2,1,3]
Output: [2,1,3]
```

__Constraints:__
```
The number of nodes in the tree is in the range [1, 10^4].
1 <= Node.val <= 10^5
```
#### EXPLANATION:

使用了投机取巧的方式, 既然已经是二叉树了. 那么就可以按照大小顺序取出来. 再通过二分法的方式, 重新创建node去构建二叉树.   
其实也可以通过旋转的方式实现 . 这个可以后面再补充. 

#### SOLUTION:
```swift
class Solution {
    func balanceBST(_ root: TreeNode?) -> TreeNode? {
        var arr:[Int] = []
        getBlanceBSTArray(root: root,arr: &arr);
        return getBalanceBSTAVL(arr, 0, arr.count-1)
    }
    
    func getBlanceBSTArray(root:TreeNode?, arr:inout [Int]) {
        if root == nil {
            return
        }
        getBlanceBSTArray(root: root?.left, arr: &arr)
        arr.append(root!.val)
        getBlanceBSTArray(root: root?.right, arr: &arr)
    }
    
    func getBalanceBSTAVL(_ arr:[Int], _ left:Int, _ right:Int) -> TreeNode? {
        if left > right {
            return nil
        }
        var mid = left + (right - left) / 2
        var root = TreeNode(arr[mid])
        root.left = getBalanceBSTAVL(arr, left, mid - 1)
        root.right = getBalanceBSTAVL(arr, mid+1, right)
        return root
    }
}
```

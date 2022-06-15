---
layout: post
title: 1305. All Elements in Two Binary Search Trees
categories: [leetcode]
---
#### QUESTION:
Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2019/12/18/q2-e1.png)
```
Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2019/12/18/q2-e5-.png)
```
Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
```
 

__Constraints:__
```
The number of nodes in each tree is in the range [0, 5000].
-10^5 <= Node.val <= 10^5
```
#### EXPLANATION:

看似medium的题目, 其实是easy. 直接遍历两棵树, 添加到array中, 返回对array的排序即可.

#### SOLUTION:
```swift
class Solution {
    func getAllElements(_ root1: TreeNode?, _ root2: TreeNode?) -> [Int] {
        var result:[Int] = []
        getAllElementsHelper(root1, array: &result)
        getAllElementsHelper(root2, array: &result)
        return result.sorted()
    }
    
    func getAllElementsHelper(_ root:TreeNode?, array:inout [Int]) {
        if root==nil {
            return
        }
        getAllElementsHelper(root?.left, array: &array)
        array.append(root!.val)
        getAllElementsHelper(root?.right, array: &array)
    }
}
```

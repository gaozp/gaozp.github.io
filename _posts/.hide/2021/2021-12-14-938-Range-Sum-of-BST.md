---
layout: post
title: 938. Range Sum of BST
categories: [leetcode]
---
#### QUESTION:
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/11/05/bst1.jpg)
```
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
```
__Example 2:__

![](https://assets.leetcode.com/uploads/2020/11/05/bst2.jpg)
```
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
```
 

__Constraints:__
```
The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.
```
#### EXPLANATION:
也没有什么特别可说的.  
1. 将所有值都取出来放在一个数组中
2. 对数组中的值进行范围判断
3. 如果在范围中就进行累加操作
#### SOLUTION:
```swift
class Solution {
    func rangeSumBST(_ root: TreeNode?, _ low: Int, _ high: Int) -> Int {
        var result:Int = 0;
        var vals:[Int] = []
        rangeSumBSTHelper(root, result: &vals)
        vals.forEach{ val in
            if (val >= low && val <= high) {
                result += val
            }
        }
        return result
    }

    func rangeSumBSTHelper(_ root: TreeNode?, result: inout [Int]) {
        if (root == nil) {
            return
        }
        result.append(root!.val)
        rangeSumBSTHelper(root?.left, result: &result)
        rangeSumBSTHelper(root?.right, result: &result)
    }
}
```

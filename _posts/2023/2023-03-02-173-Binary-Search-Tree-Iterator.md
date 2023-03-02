---
layout: post
title: 173. Binary Search Tree Iterator
categories: [leetcode]
---
#### QUESTION:
Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

 

__Example 1:__

```
Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]



BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False
```
 

__Constraints:__
```
The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 106
At most 105 calls will be made to hasNext, and next.
```
 

__Follow up:__
```
Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
```
#### EXPLANATION:

其实就是一个二叉树的中序遍历.

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

class BSTIterator {

        var arr: [TreeNode] = []
        
        var index: Int = 0
        
        init(_ root: TreeNode?) {
            initHelper(root)
        }
        
        func initHelper(_ root: TreeNode?) {
            if root == nil {
                return
            }
            initHelper(root?.left)
            arr.append(root!)
            initHelper(root?.right)
        }
        
        func next() -> Int {
            let tmp = arr[index].val
            index += 1
            return tmp
        }
        
        func hasNext() -> Bool {
            return index < arr.count
        }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * let obj = BSTIterator(root)
 * let ret_1: Int = obj.next()
 * let ret_2: Bool = obj.hasNext()
 */
```

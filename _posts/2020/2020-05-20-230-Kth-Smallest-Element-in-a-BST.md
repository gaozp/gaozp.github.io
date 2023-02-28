---
layout: post
title: 230. Kth Smallest Element in a BST
categories: [leetcode]
---
#### QUESTION:
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

**Note:**
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

**Example 1:**
```
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
```
**Example 2:**
```
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```
**Follow up:** 
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
#### EXPLANATION:
看完题目，寻找到第k小的数，那么就肯定会想到需要把二叉树摊平，那么摊平了又有好多种方法，既然是BST，那么就肯定是前序遍历才能将整个BST变成一个有序的数组或者集合。再通过这个集合查找一下就可以。同时我们又可以想，如果查找的是第一个，我们还有必要将整个树都摊平了吗，其实就是没有必要了，只要找到第k个就可以了。  
思路：  
1. 定义两个值，一个用来表示摊平到第几个数，一个用来表示最后的结果
2. 采用先序遍历的方式，遍历树
3. 每次遍历到root时，就将索引进行+1
4. 当索引为k的时候，表示已经遍历到了第k个小的值
5. 当索引比k大时，则可以直接返回

#### SOLUTION:
```java
class Solution {
    public  int kthSmallestResult = 0;
    public  int kthSmallestIndex = 0;
    public  int kthSmallest(TreeNode root, int k) {
        if(root==null) return 0;
        kthSmallest(root.left,k);
        kthSmallestIndex++;
        if(kthSmallestIndex == k)
            kthSmallestResult = root.val;
        if(kthSmallestIndex < k)
            kthSmallest(root.right,k);
        return kthSmallestResult;
    }
}
```

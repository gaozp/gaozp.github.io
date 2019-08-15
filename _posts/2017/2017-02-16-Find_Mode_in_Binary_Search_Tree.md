---
layout: post
title: 501. Find Mode in Binary Search Tree
categories: [leetcode]
---

#### QUESTION:

Given a binary search tree (BST) with duplicates, find all the [mode(s)](https://en.wikipedia.org/wiki/Mode_(statistics)) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

- The left subtree of a node contains only nodes with keys **less than or equal to** the node's key.
- The right subtree of a node contains only nodes with keys **greater than or equal to** the node's key.
- Both the left and right subtrees must also be binary search trees.

For example:

Given BST [1,null,2,2],

   1

    \

     2

    /

   2

return [2].

**Note:** If a tree has more than one mode, you can return them in any order.

**Follow up:** Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

#### EXPLANATION:

1.遍历二叉树

2.如果与前面一个数相同则count+1，否则count= 1；

3.如果count大于max说明出现了更多的数，则清除数组，更新最多的数

4.修改prev的数。



这样写的目的只有一个原因：因为这是一个BST，而且不能前序遍历或者后序遍历，只有中序遍历才能保证遍历的顺序是按照大小来的。

#### SOLUTION:

```java
public class Solution {
    Integer prev = null;
    int count = 1;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        ArrayList<Integer> resultList = new ArrayList<>();
        findModeHelper(root,resultList);
        int[] resultarray = new int[resultList.size()];
        for(int i = 0;i<resultarray.length;i++){
            resultarray[i] = resultList.get(i);
        }
        return resultarray;
    }
    
    private void findModeHelper(TreeNode root, List<Integer> result) {
        if(root==null) return;
        findModeHelper(root.left,result);
        if(prev!=null){
            if(root.val == prev){
                count++;
            }else{
                count = 1;
            }
        }
        if(count > max){
            max = count;
            result.clear();
            result.add(root.val);
        }else if(count == max){
            result.add(root.val);
        }
        prev = root.val;
        findModeHelper(root.right,result);
    }
}
```


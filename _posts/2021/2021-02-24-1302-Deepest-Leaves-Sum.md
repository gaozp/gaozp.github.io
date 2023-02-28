---
layout: post
title: 1302. Deepest Leaves Sum
categories: [leetcode]
---
#### QUESTION:
Given a binary tree, return the sum of values of its deepest leaves.
 

__Example 1:__

![](https://assets.leetcode.com/uploads/2019/07/31/1483_ex1.png)
```
Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
```

__Constraints:__
```
The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
```
#### EXPLANATION:
这个题目当看到的时候,就会立刻想到是层序遍历的题目. 但是层序遍历的话,如果用list去做,只能知道后面有没有结果,而不能确切的知道当前在哪一层,是不是最后一层.所以我们需要用两个list去做, 交替使用,这样就可以明确的知道当前在哪一层了.  
1. 创建两个list, listA,listB
2. 将第一个节点放入listA, 如果第一个节点有子节点, 那么我们就将子节点放入listB
3. 遍历listB,同时计算总和, 将b中节点的子节点放入listA
4. 当listA和listB都没有节点的时候,说明就是最后一层, 那么返回最后一次计算的结果就可以

#### SOLUTION:
```java
class Solution {
    fun deepestLeavesSum(root: TreeNode?): Int {
        root?: return 0
        var arrayA : ArrayList<TreeNode> = ArrayList()
        var arrayB : ArrayList<TreeNode> = ArrayList()
        var result = 0
        arrayA.add(root)
        while (!arrayA.isEmpty() || !arrayB.isEmpty()) {
            result = 0;
            var arrayI : ArrayList<TreeNode> = ArrayList()
            var arrayJ : ArrayList<TreeNode> = ArrayList()
            if (arrayA.isEmpty()) {
                arrayI = arrayB
                arrayJ = arrayA
            }
            if (arrayB.isEmpty()) {
                arrayI = arrayA
                arrayJ = arrayB
            }
            val iterator = arrayI.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                result += next.`val`
                iterator.remove()
                if (next.left!=null) arrayJ.add(next.left!!)
                if (next.right!=null) arrayJ.add(next.right!!)
            }
        }
        return result
    }
}
```

---
layout: post
title: 437. Path Sum III
---

#### QUESTION:

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

**Example:**

```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
```

#### EXPLANATION:

还是利用第二个测试中的stack，进行反向求和，因为每次都能确定一个线路，所以反向求和就可以获取到正确的值，然后进行累加即可。

#### SOLUTION:

```java
public class Solution {
    public int pathSumIIIResult = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return pathSumIIIResult;
        Stack<Integer> stack = new Stack<>();
        pathSumIII(root,sum,stack);
        return pathSumIIIResult;
    }
    
    public void pathSumIII(TreeNode root,int sum , Stack<Integer> stack){
        stack.push(root.val);
        int he = 0;
        for(int i = stack.size()-1;i>=0;i--){
            int value = stack.elementAt(i);
            he+=value;
            if(he == sum)
                pathSumIIIResult++;
        }
        if(root.left != null) pathSumIII(root.left,sum,stack);
        if(root.right != null) pathSumIII(root.right,sum,stack);
        stack.pop();
    }
}
```


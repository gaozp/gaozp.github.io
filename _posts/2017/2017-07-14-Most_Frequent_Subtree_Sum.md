---
layout: post
title: 508. Most Frequent Subtree Sum
---

#### QUESTION:

Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

**Examples 1**
Input:

```
  5
 /  \
2   -3

```

return [2, -3, 4], since all the values happen only once, return all of them in any order.

**Examples 2**
Input:

```
  5
 /  \
2   -5

```

return [2], since 2 happens twice, however -5 only occur once.

**Note:** You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

#### EXPLANATION:

思路就是先求出所有节点的和，然后再对数据进行处理。

求出所有节点的和这个是基本的操作了，那么就没有必要多赘述。

后面的数据处理需要注意的有可能会有出先相同频率的多个数，就是这样。

#### SOLUTION:

```JAVA
public class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
        Hashtable<Integer,Integer> table = new Hashtable();
        findFrequentTreeSumHelper(root,table);
        Iterator<Integer> iterator = table.keySet().iterator();
        ArrayList<Integer> mostFrequentkey = new ArrayList<>();
        int mostFrequentValue = 0;
        while (iterator.hasNext()){
            int key = iterator.next();
            int value = table.get(key);
            if(value == mostFrequentValue){
                mostFrequentkey.add(key);
            }else if(Math.max(mostFrequentValue,value)==value){
                mostFrequentkey.clear();
                mostFrequentkey.add(key);
                mostFrequentValue = value;
            }
        }
        int[] result = new int[mostFrequentkey.size()];
        for(int i = 0;i<result.length;i++){
            result[i] = mostFrequentkey.get(i);
        }
        return result;
    }
    
    public int findFrequentTreeSumHelper(TreeNode root,Hashtable<Integer,Integer> table){
        if(root == null) return 0;
        int left=0,right = 0;
        if(root.left != null)
            left = findFrequentTreeSumHelper(root.left,table);
        if(root.right != null)
            right = findFrequentTreeSumHelper(root.right,table);
        int sum = left+right+root.val;
        table.put(sum,table.getOrDefault(sum,0)+1);
        return sum;
    }
}
```


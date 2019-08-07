---
layout: post
title: 1104. Path In Zigzag Labelled Binary Tree
---
#### QUESTION:
In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.

![img](https://assets.leetcode.com/uploads/2019/06/24/tree.png)

Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.

 
Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]
 

Constraints:v

1 <= label <= 10^6

#### EXPLANATION:

由题意可知：找到对应的label，然后对应的label的index/2一直到index=1的时候，对应的值就是所要求的的结果。
那么思路就可以这样：  
1.首先将1000001个数按对应的顺序放在数组里
2.找到label的index  
3.不停的index/2 ,将对应位置的值添加到结果中

#### SOLUTION:
```JAVA
    static int[] pathInZigZagTreeArray = new int[1000001];
    static {
        //填充数组
        int index = 0;
        int sum = 1;
        boolean turn = false;
        while (sum<1048577){
            int tmp = 1;
            for(;index<sum && index <1000001;index++){
                //正序则直接添加
                if(turn) pathInZigZagTreeArray[index] = index;
                else{
                    //反序就添加反方向值
                    pathInZigZagTreeArray[index] = sum - tmp;
                    tmp++;
                }
            }
            turn = !turn;
            sum = sum*2;
        }
    }

    public static List<Integer> pathInZigZagTree(int label) {
        ArrayList<Integer> result = new ArrayList<>();
        if(label == 1){result.add(label);return  result;}
        int index= 0;
        //找到对应的index，这个地方是可以通过2的次方进行优化查找速率的
        while (pathInZigZagTreeArray[index]!=label) index++;
        while (index>0){
            result.add(0,pathInZigZagTreeArray[index]);
            index/=2;
        }
        return result;
    }
```


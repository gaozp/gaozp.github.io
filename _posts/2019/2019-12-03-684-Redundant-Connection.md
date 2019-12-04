---
layout: post
title: 684. Redundant Connection
categories: [leetcode]
---
#### QUESTION:
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \\
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
#### EXPLANATION:
这还是一个union-find的题目，union-find的算法一书中就说过，如果两个点本身就连在一起，那么我们就不将他们连在一起。而这个题目也就是将原本就连在一起的进行拆分。  
思路：首先采用union的方式，将连接的点都连接在一起，如1，2两点，那么就都改成2，2，直到找到一个本身就连接在一起的点，也就是本来是（n,n）的点，就说明这个是需要remove的地方。  
逻辑：  
1.找到数组中最大的数max  
2.创建一个长度为max+1数组，并且初始化每个位置为当前位置值  
3.对edges进行连线操作，并且设置对应的parent  
4.如果找到当前的两点的parent就是相同的，说明两者本来就连在一起，就是我们需要找的值  
5.一直找到最后一对，返回即可  
#### SOLUTION:
```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int max = 0;
        for(int i = 0;i<edges.length;i++){
            for(int x: edges[i]){
                if(x>max)max = x;
            }
        }
        int[] index = new int[max+1];
        int[] result = new int[2];
        for(int i = 0;i<index.length;i++) index[i] = i;
        for(int i = 0;i<edges.length;i++){
            if(findRedundantConnectionHelper(index,edges[i])) result = edges[i];
        }
        return result;
    }
    public static boolean findRedundantConnectionHelper(int[] index,int[] pair){
        int a = pair[0];
        int b = pair[1];
        int ida = index[a];
        int idb = index[b];
        if(index[a] == index[b]) return true;
        for(int i = 0;i<index.length;i++){
            if(index[i]==ida) index[i] = idb;
        }
        return false;
    }
}
```

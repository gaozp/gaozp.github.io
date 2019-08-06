---
layout: post
title: 797. All Paths From Source to Target
---
#### QUESTION:
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
#### EXPLANATION:

这道题目其实挺容易就是题意比较难理解，不过理解题意也是一种思考理解能力吧。  
题目大意是graph的第i个数组，表示i这个点指向的所有点。求能从0->graph.length-1的所有路径。这样是不是简单多了。  
思路也很容易，因为都是从第0个数组开始，依次顺着往下走就可以了，完全采用穷举法。  
1.获取到第i个数组。  
2.指向几个节点就copy几个数组，将本节点添加到数组中。  
3.查看是否是尾节点，如果是就添加到结果中。否则回到1，但是取出的是指向的节点数组。  

#### SOLUTION:
```java
class Solution {
    List<List<Integer>> allPathsSourceTargetResult = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
       allPathsSourceTargetHelper(graph,0,new ArrayList<>());
        return allPathsSourceTargetResult; 
    }
    
    public void allPathsSourceTargetHelper(int[][] graph,int index,ArrayList<Integer> list){
        int[] to = graph[index];
        for(int i = 0;i<to.length;i++){
            ArrayList<Integer> copy = new ArrayList<>();
            copy.addAll(list);
            if(index==0) copy.add(0);
            copy.add(to[i]);
            if(to[i]==graph.length-1) allPathsSourceTargetResult.add(copy);
            else allPathsSourceTargetHelper(graph,to[i],copy);
        }
    }
}
```

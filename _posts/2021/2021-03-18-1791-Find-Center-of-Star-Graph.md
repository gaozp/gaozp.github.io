---
layout: post
title: 1791. Find Center of Star Graph
categories: [leetcode]
---
#### QUESTION:

There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2021/02/24/star_graph.png)
```

Input: edges = [[1,2],[2,3],[4,2]]
Output: 2
Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
```
__Example 2:__
```
Input: edges = [[1,2],[5,1],[1,3],[1,4]]
Output: 1
 ```

__Constraints:__
```
3 <= n <= 105
edges.length == n - 1
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
The given edges represent a valid star graph.
```
#### EXPLANATION:
直接用空间换时间. 在星星中间的,肯定是被链接的数量最多的, 所以只需要进行一遍遍历,将链接的两端都进行累加. 然后再找到链接数量最多的.这个就是我们要找到的星星的中心了.
#### SOLUTION:
```java
class Solution {
    fun findCenter(edges: Array<IntArray>): Int {
        var indexs = IntArray(edges.size+2)
        edges.forEach { array -> 
            indexs[array[0]]++
            indexs[array[1]]++
        }
        var result = IntArray(2);
        indexs.forEachIndexed { index, i -> 
            if (i > result[1]) {
                result[0] = index
                result[1] = i
            }
        }
        return result[0]
    }
}
```

---
layout: post
title: 886. Possible Bipartition
categories: [leetcode]
---
#### QUESTION:
given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

**Example 1:**

```
Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
```
**Example 2:**
```
Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
```
**Example 3:**
```
Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
``` 

**Note:**
```
1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
```

#### EXPLANATION:
首先我们可以知道的是只能分成两个组，那么一对dislike的只能在不同的组里。我们可以首先将所有点的关系建立起来，比如，如果1，2讨厌，那么必然1，2属于不同的组，我们就可以将他们分在不同的组。所以第一步就是将他们的关系建立起来。然后我们假设第一个点在分组0中，那么所有1dislike的都是1，而1dislike的都是0.而如果此时出现了冲突，则说明出现了不可能只有2组的情况。也就是depth-first-search的算法。  
思路：  
1. 首先对dislike进行建立图的操作.
2. 然后从第一个点开始进行分组操作，假设第一个分在了0组
3. 进行dfs操作，将1所有的dislike的点分在1组，同时将1的dislike的dislike分在0组。
4. 用一个map来记录n的颜色
5. 如果有冲突，则说明是无法进行分组的，没有冲突说明分组成功

#### SOLUTION:
```java
class Solution {

    ArrayList<Integer>[] possibleBipartitionGraph;
    HashMap<Integer,Integer> possibleBipartitionColor = new HashMap<>();
    public boolean possibleBipartition(int N, int[][] dislikes) {
        possibleBipartitionGraph = new ArrayList[N+1];
        for(int i = 1;i<possibleBipartitionGraph.length;i++) possibleBipartitionGraph[i] = new ArrayList<Integer>();
        for(int i = 0;i<dislikes.length;i++){
            possibleBipartitionGraph[dislikes[i][0]].add(dislikes[i][1]);
            possibleBipartitionGraph[dislikes[i][1]].add(dislikes[i][0]);
        }
        for(int i = 1;i<=N;++i){
            if(!possibleBipartitionColor.containsKey(i) && !possibleBipartitionHelper(i,0))
                return false;
        }
        return true;
    }

    public boolean possibleBipartitionHelper(int i,int c){
        if(possibleBipartitionColor.containsKey(i))
            return possibleBipartitionColor.get(i) == c;
        possibleBipartitionColor.put(i,c);

        for(int node :  possibleBipartitionGraph[i]){
            if(!possibleBipartitionHelper(node,c^1))
                return false;
        }
        return true;
    }
}
```


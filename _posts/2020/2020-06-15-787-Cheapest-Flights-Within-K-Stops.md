---
layout: post
title: 787. Cheapest Flights Within K Stops
categories: [leetcode]
---
#### QUESTION:
There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

**Example 1:**
```
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:
```
![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png) 

The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
**Example 2:**
```
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:
```
![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png) 

The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 

**Constraints:**
```
The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
```
#### EXPLANATION:
其实这道题的解法挺多的，有dfs，bfs等等，我这里采用的dfs的算法。首先我们肯定需要先把对应的图给画出来。那么画图选择的数据结构就需要自己去理解。我这边选择的是HashMap数组，数组的index序列号则表示src，而map的key则表示dst，而value则表示cost，这样就可以进行dfs的遍历了。但是也是有两个限制条件是可以进行算法的优化。  
1. k也就是edge必须是>0的 
2. cost也必须必当前的cost小才行

有了这两个限制条件，就可以将算法的复杂度进行有效的缩减了。  
思路：
1. 首先进行图的绘制： 创建hashmap数组，数组的index表示src，hashmap的key表示dst，而value表示price
2. 对当前的图进行dfs，src就是graph[i]就是对应的所有edge
3. 如果当前选择的edge超过了k那么就可以直接跳过这次
4. 如果当前src=dst，则说明到达了终点，可以对结果进行更新
5. 否则就继续进行dfs，如果当前的cost已经超过了给定的结果，那么就可以直接跳过，来节省算法复杂度


#### SOLUTION:
```java
class Solution {
    public int findCheapestPriceResult = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        HashMap<Integer,Integer>[] graph = new HashMap[n];
        for(int i = 0 ;i< flights.length;i++){
            if(graph[flights[i][0]] == null) graph[flights[i][0]] = new HashMap<>();
            HashMap<Integer,Integer> tmp = graph[flights[i][0]];
            tmp.put(flights[i][1],flights[i][2]);
        }
        findCheapestPriceHelper(graph,src,dst,K+1,0);
        return findCheapestPriceResult==Integer.MAX_VALUE?-1:findCheapestPriceResult;  
    }
    
    public void findCheapestPriceHelper(HashMap<Integer,Integer>[] graph,int src, int dst, int K,int cost){
        if(K<0) return;
        if(src==dst) {
            findCheapestPriceResult = Math.min(findCheapestPriceResult,cost);
            return;
        }
        HashMap<Integer, Integer> map = graph[src];
        if(map == null) return;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(cost + entry.getValue() > findCheapestPriceResult) continue;
            findCheapestPriceHelper(graph,entry.getKey(),dst,K-1,cost+entry.getValue());
        }
    }
}
```

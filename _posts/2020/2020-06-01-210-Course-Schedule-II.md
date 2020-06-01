---
layout: post
title: 210. Course Schedule II
categories: [leetcode]
---
#### QUESTION:
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
#### EXPLANATION:
因为今天的month test是翻转二叉树，也就是homebrew的作者是面试谷歌被卡主的题目，之前做过了，所以今天继续巩固一下拓扑排序的算法。  
题意和上一个一样，只是需要打印出对应的顺序就行，注意考虑到其中的边界情况，也就是如果不能全部学完，就返回空数组就行，而不用返回可以学的。  
思路： 
1. 创建int[]和list用来标记入度的个数和出度的node
2. 循环遍历条件，将对应的node的入度进行++，同时对出度进行标记
3. 找出入度为0的节点，放入到queue中
4. 当queue不为空的时候，取出该节点，并将该节点所指向的节点入度都减一
5. 如果出度的节点入度为0，那么也可以放入到queue中
6. 重复3-5步即可获取到最终的结果 

**需要注意的是，如果其中有环形的依赖，那么需要返回空数组，和题意有关。** 

#### SOLUTION:
```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDgree = new int[numCourses];
        ArrayList<Integer>[] index = new ArrayList[numCourses];
        for(int i = 0;i<numCourses;i++) index[i] = new ArrayList<>();
        for(int i = 0;i<prerequisites.length;i++){
            index[prerequisites[i][1]].add(prerequisites[i][0]);
            inDgree[prerequisites[i][0]]++;
        }
        ArrayList<Integer> result =new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0;i<inDgree.length;i++) if(inDgree[i]==0) queue.offer(i);

        while (!queue.isEmpty()){
            int curr = queue.poll();
            result.add(curr);
            for(int child : index[curr]){
                inDgree[child]--;
                if(inDgree[child]==0) queue.offer(child);
            }
        }
        if(result.size()!=numCourses) return new int[0];
        int[] resultArray = new int[result.size()];
        for(int i = 0 ;i< result.size();i++) resultArray[i] = result.get(i);
        return resultArray;
    }
}
```

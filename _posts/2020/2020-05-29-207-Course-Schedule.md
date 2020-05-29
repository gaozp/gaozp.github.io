---
layout: post
title: 207. Course Schedule
categories: [leetcode]
---
#### QUESTION:
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

**Example 1:**
```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
```
**Example 2:**

```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

```
**Constraints:**

```
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
```
#### EXPLANATION:
这道题目看起来是medium的，但是其实如果你了解了拓扑排序，这道题目就很容易了。拓扑排序此处就不多展开讲了。  
思路：  
1. 构建对应的图
2. 进行拓扑排序
3. 判断排序的size是不是等于结果
4. 如果等于结果则说明可以全部学完，否则则不可以

#### SOLUTION:
```java
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] index = new ArrayList[numCourses];
        for(int i = 0;i<index.length;i++) index[i] = new ArrayList<>();
        for(int i = 0;i<prerequisites.length;i++)
            index[prerequisites[i][0]].add(prerequisites[i][1]);
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> sort = new ArrayList<>();
        for(int i = 0;i<index.length; i++) if(index[i].size()==0){
            queue.add(i);
            index[i] = null;
        }
        while (!queue.isEmpty()){
            int poll = queue.poll();
            sort.add(poll);
            for(int i = 0;i<index.length;i++){
                if (index[i] == null) continue;
                Iterator<Integer> iterator = index[i].iterator();
                while (iterator.hasNext()){
                    Integer next = iterator.next();
                    if(next==poll) iterator.remove();
                }
                if (index[i].size()==0) {
                    ((ArrayDeque<Integer>) queue).push(i);
                    index[i] = null;
                }
            }

        }
        return sort.size() == numCourses;
    }
```

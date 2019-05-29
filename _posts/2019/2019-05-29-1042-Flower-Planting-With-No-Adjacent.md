---
layout: post
title: 1042. Flower Planting With No Adjacent
---

#### QUESTION:

You have `N` gardens, labelled `1` to `N`.  In each garden, you want to plant one of 4 types of flowers.

`paths[i] = [x, y]` describes the existence of a bidirectional path from garden `x` to garden `y`.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return **any** such a choice as an array `answer`, where `answer[i]`is the type of flower planted in the `(i+1)`-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

**Example 1:**

```
Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
```

**Example 2:**

```
Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
```

**Example 3:**

```
Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
```

**Note:**

- `1 <= N <= 10000`
- `0 <= paths.size <= 20000`
- No garden has 4 or more paths coming into or leaving it.
- It is guaranteed an answer exists.

#### EXPLANATION:

可以采用贪心算法。如果他没有连接的guarden，那么就说明他可以设置为1，否则就是按照设置。

#### SOLUTION:

```java
class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] result = new int[N];
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) map.put(i, new HashSet<>());
        for (int[] p : paths) {
            map.get(p[0] - 1).add(p[1] - 1);
            map.get(p[1] - 1).add(p[0] - 1);
        }//计算出所有连接的花园
        for(int i = 0;i<N;i++){//分别依次进行填充
            int[] flowers = new int[5];
            HashSet<Integer> sets = map.get(i);
            for(int j : sets){
                int flower = result[j];//得到连接的花园是否有填充，并且填充的颜色是什么。
                flowers[flower] = 1;//将已经使用的颜色进行标记
            }
            for(int f=4;f>0;--f){
                if(flowers[f]==0)//贪心算法获取到最小能使用的颜色。
                    result[i] = f;
            }
        }
        return result;
    }
}
```


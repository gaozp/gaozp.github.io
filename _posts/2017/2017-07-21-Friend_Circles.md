---
layout: post
title: 547. Friend Circles
---

#### QUESTION:

There are **N** students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a **direct** friend of B, and B is a **direct** friend of C, then A is an **indirect** friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a **N\*N** matrix **M** representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are **direct** friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

**Example 1:**

```
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.

```

**Example 2:**

```
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

```

**Note:**

1. N is in range [1,200].
2. M[i][i] = 1 for all students.
3. If M[i][j] = 1, then M[j][i] = 1.

#### EXPLANATION:

其实这个是一个典型的并查集的问题，就是union find问题。

1.首先遍历数组，如果遇到1，那么就说明两者是有关系的，就可以建立关系，比如就是将他们统一成j的数。

2.再接着遍历，如果又有相同的，那么就将之前所有i的数都统一变成j的数

3.这样，最后数组中存在的数种类就是最后的个数，并且对应的相同数字的序号是在同一个关系圈中的。

#### SOLUTION:

```JAVA
public class Solution {
    public int[] findCircleNums;
    public int findCircleNum(int[][] M) {
        findCircleNums = new int[M[0].length];
        for (int i = 0; i < findCircleNums.length; i++) findCircleNums[i] = i;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (i == j) continue;
                if (M[i][j] == 1) {
                    if (isConnected(i, j)) continue;
                    Union(i, j);
                }
            }
        }
        HashSet<Integer> result = new HashSet<>();
        for(int i = 0;i<findCircleNums.length;i++ ) result.add(findCircleNums[i]);
        return result.size();
    }
    public boolean isConnected(int p, int q) {
        return findCircleNums[p] == findCircleNums[q];
    }

    public void Union(int p, int q) {
        for (int i = 0; i < findCircleNums.length; i++) {
            if (findCircleNums[i] == findCircleNums[p])
                findCircleNums[i] = findCircleNums[q];
        }
    }
    
}
```


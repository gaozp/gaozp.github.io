---
layout: post
title: 997. Find the Town Judge
---

#### QUESTION:

In a town, there are `N` people labelled from `1` to `N`.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

1. The town judge trusts nobody.
2. Everybody (except for the town judge) trusts the town judge.
3. There is exactly one person that satisfies properties 1 and 2.

You are given `trust`, an array of pairs `trust[i] = [a, b]`representing that the person labelled `a` trusts the person labelled `b`.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return `-1`.

**Example 1:**

```
Input: N = 2, trust = [[1,2]]
Output: 2
```

**Example 2:**

```
Input: N = 3, trust = [[1,3],[2,3]]
Output: 3
```

**Example 3:**

```
Input: N = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
```

**Example 4:**

```
Input: N = 3, trust = [[1,2],[2,3]]
Output: -1
```

**Example 5:**

```
Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
Output: 3
```

**Note:**

1. `1 <= N <= 1000`
2. `trust.length <= 10000`
3. `trust[i]` are all different
4. `trust[i][0] != trust[i][1]`
5. `1 <= trust[i][0], trust[i][1] <= N`

#### EXPLANATION:

我的解法是：每个人都可以投给别人，那么就可以计算出每个人投给别人的票，和自己获得的票。

用一个二维数组来保存两个值。

当只有自己获得所有人信任，同时自己不信任任何其他人的时候，才能返回该值。

但是在细想之后，你会发现，如果这个人投票给了其他人，那么他就不能是法官。那么就可以节省很多判断。

#### SOLUTION:

```java
class Solution {
    public int findJudge(int N, int[][] trust) {
        if(trust.length==0) return 1;
        int[][] result = new int[N+1][2];
        for(int i=0;i<trust.length;i++){
            result[trust[i][0]][0]++;//信任别人个数
            result[trust[i][1]][1]++;//被信任个数
        }
        for(int i = 0;i<result.length;i++){
            if(result[i][0]==0 && result[i][1]==N-1) return i;
        }
        return -1;
    }
}
```


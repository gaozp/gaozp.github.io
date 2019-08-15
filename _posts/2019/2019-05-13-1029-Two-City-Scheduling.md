---
layout: post
title: 1029. Two City Scheduling
categories: [leetcode]
---

#### QUESTION:

There are `2N` people a company is planning to interview. The cost of flying the `i`-th person to city `A` is `costs[i][0]`, and the cost of flying the `i`-th person to city `B` is `costs[i][1]`.

Return the minimum cost to fly every person to a city such that exactly `N` people arrive in each city.

**Example 1:**

```
Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
```

**Note:**

1. `1 <= costs.length <= 100`
2. It is guaranteed that `costs.length` is even.
3. `1 <= costs[i][0], costs[i][1] <= 1000`

#### EXPLANATION:

本身就是贪心算法的应用。

就是说如果两者差别最大的就选择对应的。

那么步骤就是：

1.计算出每个interviewer去两个城市的差值B-A。

2.进行排序。

3.如果是负值就去b城市，否则就去a城市。

#### SOLUTION:

```java
//ac的2ms的解
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] - o1[1]) - (o2[0] - o2[1]);
            }
        });

        int total = 0;
        int n = costs.length / 2;
        for (int i = 0; i < n; i++) {
            total += costs[i][0] + costs[i + n][1];
        }
        return total;
    }
}
//我的方法，思路完全一样，但是我却使用了51ms
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int[][] result = new int[costs.length][2];
        for(int i = 0;i<costs.length;i++){
            result[i][0] = i;
            result[i][1] = costs[i][1]-costs[i][0];
        }
        Arrays.sort(result,(a,b)->{
            return a[1]-b[1];
        });
        int sum = 0;
        for(int i = 0;i<result.length;i++){
            if(i<result.length/2) sum+=costs[result[i][0]][1];
            else sum+=costs[result[i][0]][0];
        }
        return sum;  
    }
}
```


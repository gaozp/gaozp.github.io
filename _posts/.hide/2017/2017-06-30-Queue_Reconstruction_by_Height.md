---
layout: post
title: 406. Queue Reconstruction by Height
categories: [leetcode]
---

#### QUESTION:

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers `(h, k)`, where `h` is the height of the person and `k` is the number of people in front of this person who have a height greater than or equal to `h`. Write an algorithm to reconstruct the queue.

**Note:**
The number of people is less than 1,100.

**Example**

```
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```

#### EXPLANATION:

1.首先是将数组进行排序，将数组由大到小排序，如果数字相同那么就将数字小的排在前面，那么第一轮就可以确定最大数的位置。

2.接着就可以继续第二大数的排序，比如第二个数是6.1，那么就将6插在1的位置上，同时将其他位数后推。

如此下去即可

#### SOLUTION:

```JAVA
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if(o1[0]==o2[0])
                return o1[1]-o2[1];
            return o2[0]-o1[0];
        });

        int n = people.length;
        ArrayList<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++)
            tmp.add(people[i][1], new int[]{people[i][0], people[i][1]});

        int[][] res = new int[people.length][2];
        int i = 0;
        for (int[] k : tmp) {
            res[i][0] = k[0];
            res[i++][1] = k[1];
        }


        return res;
    }
}
```


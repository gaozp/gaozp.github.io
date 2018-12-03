---
layout: post
title: 849. Maximize Distance to Closest Person
---

#### QUESTION:

In a row of `seats`, `1` represents a person sitting in that seat, and `0`represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

**Example 1:**

```
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
```

**Example 2:**

```
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
```

**Note:**

1. `1 <= seats.length <= 20000`
2. `seats` contains only 0s or 1s, at least one `0`, and at least one `1`.

#### EXPLANATION:

我这边的解法其实是分为两步

1.从左往右，计算距离左边的人最远距离

2.从右往左，计算距离右边的人最远的距离

3.然后计算两者之中的小值

4.计算整个数组中的最大值则是最优解。

#### SOLUTION:

```java
class Solution {
    public int maxDistToClosest(int[] seats) {
        int[] distance = new int[seats.length];
        Arrays.fill(distance,Integer.MAX_VALUE);
        int seat = -1;
        for(int i = 0;i<seats.length;i++){
            int tmp = seats[i];
            if(tmp == 1){
                seat = i;
                distance[i]=0;
            }else if(seat != -1){
                distance[i] = i-seat;
            }
        }
        seat = seats.length;
        for(int j = seats.length-1;j>=0;j--){
            int tmp = seats[j];
            if(tmp ==1){
                seat = j;
                distance[j]=0;
            }else if(seat != seats.length){
                distance[j] = Math.min(distance[j],seat-j);
            }
        }
        Arrays.sort(distance);
        return distance[seats.length-1];
    }
}
```


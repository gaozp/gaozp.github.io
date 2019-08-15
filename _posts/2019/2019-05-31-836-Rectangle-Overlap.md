---
layout: post
title: 836. Rectangle Overlap
categories: [leetcode]
---

#### QUESTION:

A rectangle is represented as a list `[x1, y1, x2, y2]`, where `(x1, y1)` are the coordinates of its bottom-left corner, and `(x2, y2)` are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two (axis-aligned) rectangles, return whether they overlap.

**Example 1:**

```
Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true
```

**Example 2:**

```
Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false
```

**Notes:**

1. Both rectangles `rec1` and `rec2` are lists of 4 integers.
2. All coordinates in rectangles will be between `-10^9 `and`10^9`.

#### EXPLANATION:

判断两个矩形是否相交。我采用的方法是，如果两个矩形相交，那么必然是会有重合的矩形存在，那么这个矩形存在的结果就是符合矩形规则的。

当然还有很多其他的方式，这个是一道完全的数学题目。

#### SOLUTION:

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int minx = Math.max(rec1[0],rec2[0]);
        int miny = Math.max(rec1[1],rec2[1]);
        int maxx = Math.min(rec1[2],rec2[2]);
        int maxy = Math.min(rec1[3],rec2[3]);
        if(minx>=maxx || miny>=maxy)
            return false;
        return true;
    }
}
```


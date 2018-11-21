---
layout: post
title: 832. Flipping an Image
---

#### QUESTION:

Given a binary matrix `A`, we want to flip the image horizontally, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.  For example, flipping `[1, 1, 0]` horizontally results in `[0, 1, 1]`.

To invert an image means that each `0` is replaced by `1`, and each `1`is replaced by `0`. For example, inverting `[0, 1, 1]` results in `[1, 0, 0]`.

**Example 1:**

```
Input: [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
```

**Example 2:**

```
Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
```

**Notes:**

- `1 <= A.length = A[0].length <= 20`
- `0 <= A[i][j] <= 1`

#### EXPLANATION:

其实也就是拆分为两个步骤：

1.将数组revert

2.将数组invert

两个步骤是可以合在一起的，所以在revert的同时进行invert。具体可以看代码。

同时也可以采用双指针的方式进行。

#### SOLUTION:

```java
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i = 0;i<A.length;i++){
            int[] tmp = A[i];
            for (int j = 0;j<tmp.length/2;j++){
                int swap = tmp[j];
                tmp[j] = 1-tmp[tmp.length-1-j];
                tmp[tmp.length-1-j] = 1-swap;
            }
            if(tmp.length%2 == 1)
                tmp[tmp.length/2] = 1-tmp[tmp.length/2];
        }
        return A;
    }
}
```




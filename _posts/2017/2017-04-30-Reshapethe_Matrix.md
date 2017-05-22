---
layout: post
title: 566. Reshape the Matrix
---

#### QUESTION:

In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two **positive** integers **r** and **c** representing the **row** number and **column**number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same **row-traversing** order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

**Example 1:**

```
Input: 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
Output: 
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.

```

**Example 2:**

```
Input: 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
Output: 
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.

```

**Note:**

1. The height and width of the given matrix is in range [1, 100].
2. The given r and c are all positive.

#### EXPLANATION

其实就是计算面积的，然后再重新填充。

刚开始的时候以为不用保存原始的数据，所以直接使用了count++的方式，后来才发现使用原始数据。当时也没有想到有啥更好的办法，于是一股脑儿将数据都输入进去然后再输出出来。

然后看了下别人的submit，发现原来数据总数是一定的，那么就可以遍历数据挨个填充即可。

确实优雅和只需要一个循环。

#### SOLUTION:

```java
public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length * nums[0].length != r * c) return nums;
        int[][] result = new int[r][c];
        Queue input = new LinkedList<Integer>();
        for(int i = 0;i<nums.length;i++){
            for(int j = 0;j<nums[i].length;j++){
                input.add(nums[i][j]);
            }
        }
        for(int i = 0;i<r;i++){
            for(int j =0;j<c;j++){
                result[i][j] = (int) input.remove();
            }
        }
        return result;
    }
}
//我看到比较好的解决方法
public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = m > 0 ? nums[0].length : 0;
        if (r * c != m * n)
            return nums;
        int[][] reshaped = new int[r][c];
        for (int i=0; i<m*n; i++)
            reshaped[i/c][i%c] = nums[i/n][i%n];
        return reshaped;
    }
}
```


---
layout: post
title: 64. Minimum Path Sum
categories: [leetcode]
---

#### QUESTION:

Given a *m* x *n* grid filled with non-negative numbers, find a path from top left to bottom right which *minimizes* the sum of all numbers along its path.

**Note:** You can only move either down or right at any point in time.

**Example 1:**

```
[[1,3,1],
 [1,5,1],
 [4,2,1]]
```

Given the above grid map, return 

```
7
```

. Because the path 1→3→1→1→1 minimizes the sum.

#### EXPLANATION:

这是一道动态规划的题目。

首先我们从左上角开始想：第一个位置（0，0）没有办法的，肯定就是（0，0），那么（0，1）也没有办法只能是从左边过来的也就是f(0,1) = grid(0,0)+grid(0,1)

那么第一排我们就能够算出来是

1，4，5

那么到了第二排  1只有可能是从上面过来

2，\*,\*

那么到了5，5就有可能从上面或者右边过来，从上面来就是4+5 = 9，从右边来就是2+5=7，那我们当然是选择7了。

1也是同理，从上面来就是6，从左边来就是8，选择6。第二排就算出来了

2，7，6

第三排也是同理。

那么我们就可以得到一个公式：

if(i==0&&j==0)

f(i,j) = grid(0,0)

else{

f(i,j) = grid(i,j)+min(f(i-1,j),f(i,j-1))

}

既然递推公式都已经出来了，那么就很容易计算了。

#### SOLUTION:

```JAVA
class Solution {
    public int minPathSum(int[][] grid) {
        int[][] result = new int[grid.length][grid[0].length];
        result[0][0] = grid[0][0];
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[i].length;j++){
                int tmp = grid[i][j];
                int m =0,n=0;
                if(i-1<0&&j-1<0){
                    result[i][j] = tmp;
                }else if(i-1>=0 && j-1<0){
                    m = result[i-1][j];
                    result[i][j] = tmp+m;
                }else if(j-1>=0 && i-1<0){
                    n = result[i][j-1];
                    result[i][j] = tmp+n;
                }else {
                    m = result[i-1][j];
                    n = result[i][j-1];
                    result[i][j] = tmp+Math.min(m,n);
                }
            }
        }
        return result[grid.length-1][grid[0].length-1];
    }
}
```


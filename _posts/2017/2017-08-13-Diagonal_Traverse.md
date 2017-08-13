---
layout: post
title: 498. Diagonal Traverse
---

#### QUESTION:

Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

**Example:**

```
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

```

#### EXPLANATION:

一开始看的时候很难看出规律，但是如果这样看到。

第一排数是0，0

第二排的数是0，1 ————1，0

第三排的数是2，0—————1，1—————0，2

这样或许你就能看出来了，一共是多少排呢，其实就是m+n-1排。

那再把那些无效的数字排除掉就可以了。

#### SOLUTION:

```JAVA
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length==0) return new int[0];
        int m = matrix.length,n = matrix[0].length;
        int[] result = new int[m*n];
        int index = 0;boolean flag=false;
        for(int i = 0;i<m+n-1;i++){
            if(flag){
                for(int j = 0;j<=i;j++){
                    if(j>=m || i-j>=n) continue;
                    result[index++] = matrix[j][i-j];
                }
            }else{
                for(int j = i;j>=0;j--){
                    if(j>=m || i-j>=n) continue;
                    result[index++] = matrix[j][i-j];
                }
            }
            flag = !flag;
        }
        return result;
    }
```


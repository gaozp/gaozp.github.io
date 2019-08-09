---
layout: post
title: 861. Score After Flipping Matrix
---
#### QUESTION:
We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
#### EXPLANATION:

第一眼看到题目也很懵，但是细想一下，要达到最优解。那么第一位必然是1，因为只有1才能保证这一排是最大的。然后从第二列开始，如果1的数量小于0，那么就需要翻转。这样就能得到最优解了。  
逻辑:
1.首先判断第一位来确定是否需要翻转这一行  
2.从第二列开始，计算每一列0和1的数量，来确定是否要翻转这一列  
3.将得到的结果进行累加

#### SOLUTION:
```JAVA
class Solution {
    public int matrixScore(int[][] A) {
        for(int i = 0;i<A.length;i++)
            if(A[i][0]==0) matrixScoreRow(A,i);
        for( int i =1;i<A[0].length;i++){
            int count0 = 0;
            for(int j = 0;j<A.length;j++) if(A[j][i]==0) count0++;
            if(A.length-count0<count0) matrixScoreColum(A,i);
        }
        int result = 0;
        for(int i = 0;i<A.length;i++)
           result += matrixScoreRowBinary(A[i]);
        return result;
    }
    
    public static int matrixScoreRowBinary(int[] A){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<A.length;i++) sb.append(A[i]);
        return Integer.parseInt(sb.toString(),2);
    }

    public static void matrixScoreRow(int[][] A,int row){
        for(int i = 0;i<A[0].length;i++) A[row][i] = Math.abs(A[row][i]-1);
    }

    public static void matrixScoreColum(int[][] A,int colum){
        for(int i = 0;i<A.length;i++) A[i][colum] = Math.abs(A[i][colum]-1);
    }

}
```

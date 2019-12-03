---
layout: post
title: 959. Regions Cut By Slashes
categories: [leetcode]
---
#### QUESTION:
In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \\, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

 

Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:
![](https://assets.leetcode.com/uploads/2018/12/15/1.png)
Example 2:

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:
![](https://assets.leetcode.com/uploads/2018/12/15/2.png)
Example 3:

Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:
![](https://assets.leetcode.com/uploads/2018/12/15/3.png)
Example 4:

Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:
![](https://assets.leetcode.com/uploads/2018/12/15/4.png)
Example 5:

Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:
![](https://assets.leetcode.com/uploads/2018/12/15/5.png)
 

Note:

1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.
#### EXPLANATION:
这道题目需要掌握的是union-find的并查集，算是数据结构也算是算法的一种吧。如果能够掌握了union-find，那么这道题目其实是没有难度的。  
思路：  
1.首先将每个方块分成4等分，上下左右4个三角形  
2.对每个小三角形进行赋值，123456789。。。。  
3.对每个小方框进行合并，如果是'\\'，则说明上面和右面合并，'/'则说明上左合并。' '则表示四个都合并成一个，合并用相同的数字，也就是parent表示  
4.然后再对所有的小方块进行一次union，也就是将前后左右的小方块进行union。恢复成相同的数字。这样，同一个区域的就是相同的数字了。  
5.遍历小方块，将数字都放入set中，不重复的set中的size就是最后的结果
#### SOLUTION:
```java
class Solution {
    public int regionsBySlashes(String[] grid) {
        int[][][] index = new int[grid.length][grid[0].toCharArray().length][4];
        int count = 0;
        for(int i = 0;i<grid.length;i++){
            char[] chars = grid[i].toCharArray();
            for(int j = 0;j<chars.length;j++,count++){
                index[i][j][0] = count*4+0;
                index[i][j][1] = count*4+1;
                index[i][j][2] = count*4+2;
                index[i][j][3] = count*4+3;
                switch (chars[j]){
                    case '\\':
                        index[i][j][2] = index[i][j][0];
                        index[i][j][3] = index[i][j][1];
                        break;
                    case '/':
                        index[i][j][1] = index[i][j][0];
                        index[i][j][3] = index[i][j][2];
                        break;
                    case ' ':
                        index[i][j][1] = count*4+0;
                        index[i][j][2] = count*4+0;
                        index[i][j][3] = count*4+0;
                        break;
                }
            }
        }
        for(int i = 0;i<index.length;i++){
            for(int j = 0;j<index[i].length;j++){
                for(int m = 0;m<4;m++){
                    switch (m){
                        case 0:
                            if(i-1>=0)
                                regionsBySlashesHelper(index,index[i][j][0],index[i-1][j][3]);
                            break;
                        case 1:
                            if(j-1>=0)
                                regionsBySlashesHelper(index,index[i][j][1],index[i][j-1][2]);
                            break;
                        case 2:
                            if(j+1<index[i].length)
                                regionsBySlashesHelper(index,index[i][j][2],index[i][j+1][1]);
                            break;
                        case 3:
                            if(i+1<index.length)
                                regionsBySlashesHelper(index,index[i][j][3],index[i+1][j][0]);
                            break;
                    }

                }
            }
        }
        HashSet<Integer> result = new HashSet<>();
        for(int[][] arr1 : index){
            for(int[] arr2: arr1){
                for(int a : arr2)
                    result.add(a);
            }
        }
        return result.size();
    }
    public static void regionsBySlashesHelper(int[][][] index,int x,int y){
        for(int i = 0;i<index.length;i++){
            for(int j = 0;j<index[i].length;j++){
                for(int m = 0;m<4;m++){
                    if(index[i][j][m]==x) index[i][j][m] = y;
                }
            }
        }

    }
}
```

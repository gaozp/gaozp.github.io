---
layout: post
title: 999. Available Captures for Rook
---

#### QUESTION:

On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.

The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.

Return the number of pawns the rook can capture in one move.

**Example 1:**

![img](https://assets.leetcode.com/uploads/2019/02/20/1253_example_1_improved.PNG)

```
Input: 
[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
Output: 3
Explanation: 
In this example the rook is able to capture all the pawns.
```

**Example 2:**

![img](https://assets.leetcode.com/uploads/2019/02/19/1253_example_2_improved.PNG)

```
Input: 
[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
Output: 0
Explanation: 
Bishops are blocking the rook to capture any pawn.
```

**Example 3:**

![img](https://assets.leetcode.com/uploads/2019/02/20/1253_example_3_improved.PNG)

```
Input: 
[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
Output: 3
Explanation: 
The rook can capture the pawns at positions b5, d6 and f5.
```

**Note:**

1. `board.length == board[i].length == 8`
2. `board[i][j]` is either `'R'`, `'.'`, `'B'`, or `'p'`
3. There is exactly one cell with `board[i][j] == 'R'`

#### EXPLANATION:

题目的意思就是R这个棋子可以横竖直线走动，并且可以吃掉p棋子。

那么就可以分解成

1.得到R的位置。

2.检索R的前后左右位置

3.如果有p那么result就+1

#### SOLUTION:

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int[] index = numRookCapturesHelper(board);
        int i = index[0];
        int j = index[1];
        int tmp = i-1, result = 0;
        while (tmp >= 0) {
            if(board[tmp][j]=='p') {result++;break;}
            if(board[tmp][j]=='B') break;
            tmp--;
        }
        tmp = i+1;
        while (tmp<8){
            if(board[tmp][j]=='p') {result++;break;}
            if(board[tmp][j]=='B') break;
            tmp++;
        }
        tmp = j-1;
        while (tmp>=0){
            if(board[i][tmp]=='p') {result++;break;}
            if(board[i][tmp]=='B') break;
            tmp--;
        }
        tmp = j+1;
        while (tmp<8){
            if(board[i][tmp]=='p') {result++;break;}
            if(board[i][tmp]=='B') break;
            tmp++;
        }
        return result;
    }
    public static int[] numRookCapturesHelper(char[][] board){
        for(int i = 0;i<board.length;i++){
            for(int j =0;j<board[i].length;j++){
                if(board[i][j]=='R')
                    return new int[]{i,j};
            }
        }
        return new int[]{};
    }
}
```


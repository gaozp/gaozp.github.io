---
layout: post
title: 419. Battleships in a Board
---

#### QUESTION:

Given an 2D board, count how many battleships are in it. The battleships are represented with `'X'`s, empty slots are represented with `'.'`s. You may assume the following rules:

- You receive a valid board, made of only battleships or empty slots.
- Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape `1xN` (1 row, N columns) or `Nx1` (N rows, 1 column), where N can be of any size.
- At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

**Example:**

```
X..X
...X
...X

```

In the above board there are 2 battleships.

**Invalid Example:**

```
...X
XXXX
...X

```

This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

**Follow up:**
Could you do it in **one-pass**, using only **O(1) extra memory** and **without modifying** the value of the board?

#### EXPLANATION:

其实一开始想到的逻辑就是：

1.碰到一个x，那么就将他后面的所有x都换成。同时将他这一列的所有x都换成。

这样就相当于所有的ship都缩成了一个点，那么就很容易可以算出来了。

紧接着的followup就更容易了，不要缩成点。因为每次计算的时候，如果达到一个x，如果他的上一列这也是x，或者这一列前面也是x，那么说明这个x已经被计算过了。具体的可以看代码逻辑。

#### SOLUTION:

```JAVA
public class Solution {
    public int countBattleships(char[][] board) {
        int count = 0;
        for(int i = 0;i<board.length;i++){
            char[] tmp = board[i];
            for(int j = 0;j<tmp.length;j++){
                char value = tmp[j];
                if(value == 'X'){
                    int x = j+1;
                    while (x<tmp.length && tmp[x]=='X'){
                        tmp[x] = '.';
                        x = x+1;
                    }
                    x = i+1;
                    while (x <board.length && board[x][j]== 'X'){
                        board[x][j] = '.';
                        x = x+1;
                    }
                    count++;
                }
            }
        }
        return count;
    }
}

public class Solution {
    public int countBattleships(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '.') continue;
                if(i > 0 && board[i-1][j] == 'X') continue;
                if(j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }
        
        return count;
    }
}
```


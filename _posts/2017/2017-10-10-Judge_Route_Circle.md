---
layout: post
title: 657. Judge Route Circle
categories: [leetcode]
---

#### QUESTION:

Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to **the original place**.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are `R` (Right), `L`(Left), `U` (Up) and `D` (down). The output should be true or false representing whether the robot makes a circle.

**Example 1:**

```
Input: "UD"
Output: true

```

**Example 2:**

```
Input: "LL"
Output: false
```

#### EXPLANATION:

这个也没有什么好多说的，其实就是计算上下的数量和左右的数量是否相等，如果相等，就说明回到了原点，如果不相等，那就说明了已经走了指定的距离。

#### SOLUTION:

```JAVA
class Solution {
    public boolean judgeCircle(String moves) {
        int x =0,y=0;
        for(char c:moves.toCharArray()){
            switch (c){
                case 'U':
                    y+=1;
                    break;
                case 'D':
                    y-=1;
                    break;
                case 'L':
                    x-=1;
                    break;
                case 'R':
                    x+=1;
                    break;
            }
        }
        return x==0&&y==0;
    }
}
```


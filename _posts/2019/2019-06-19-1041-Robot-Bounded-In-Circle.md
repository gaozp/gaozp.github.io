---
layout: post
title: 1041. Robot Bounded In Circle
categories: [leetcode]
---

#### QUESTION：
On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

 

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 

Note:

1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
#### EXPLANATION：

这是一个有技巧的题目，trick的地方是如果是一个圈的话有两种情况：
1.经过第一次指令后，回到了0，0的位置，那不管方向如何，最后都会回到0，0.
2.经过第一次指令后，到达了另外一个位置，但是方向不同，那么经过4次后，就会回到同样的位置。
既然条件有了，那么就可以写算法了。
向不同的方向行进其实就是：坐标向不同地方加减就可以。
然后方向是一个循环，将上下左右编成顺序，那么l，r就是在顺序上左右移动。
这样就可以获取到第一次指令后的位置，根据上面的条件进行判断。

#### SOLUTION：
```java
class Solution {
    public boolean isRobotBounded(String instructions) {
        char[] chars = instructions.toCharArray();
        int[] right = new int[]{1,0};
        int[] left = new int[]{-1,0};
        int[] up = new int[]{0,1};
        int[] down = new int[]{0,-1};
        int[][] direction = new int[4][2];
        direction[0] = up;
        direction[1] = right;
        direction[2] = down;
        direction[3] = left;
        int index = 0;
        int[] start = new int[]{0,0};
        for(int i = 0;i<chars.length;i++){
            if('G'==chars[i]) {
                start[0]+=direction[index][0];
                start[1]+=direction[index][1];
            }else if ('L'==chars[i]){
                if(index==0) index=3;
                else index--;
            }else if ('R'==chars[i]){
                if(index==3)index=0;
                else index++;
            }
        }
        return start[0] == 0 && start[1] == 0 || index > 0;
    }
}
```
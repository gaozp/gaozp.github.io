---
layout: post
title: 874. Walking Robot Simulation
categories: [leetcode]
---
#### QUESTION:
A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:

-2: turn left 90 degrees
-1: turn right 90 degrees
1 <= x <= 9: move forward x units
Some of the grid squares are obstacles. 

The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])

If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)

Return the square of the maximum Euclidean distance that the robot will be from the origin.

 

Example 1:

Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: robot will go to (3, 4)
Example 2:

Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 

Note:

0 <= commands.length <= 10000
0 <= obstacles.length <= 10000
-30000 <= obstacle[i][0] <= 30000
-30000 <= obstacle[i][1] <= 30000
The answer is guaranteed to be less than 2 ^ 31.
#### EXPLANATION:

思路比较简单：
就是每次进行移动后，进行计算面积。难点就是在移动的步骤。
需要注意的一点是，你是不能移动到障碍物上的，比如（0，5）有一个障碍物，你向上只能走到（0，4），即使5-4=1.
那么步骤就是：
1.先将方向的移动进行定义
2.将每一步进行移动
3.计算每次移动后的结果，并与之前结果进行比较
4.返回结果
细说一下移动：
1.四个方向就不说了，该怎么移动都有想法
2.如何判断本次移动的最大步数，那就是判断移动的路径上有没有障碍物，没有就是command的步数，否则就是能够移动的步数。
3.障碍物的判断就是在路径前方的，后面的就不需要进行关注了。

#### SOLUTION:
```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
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

        int result = 0;
        for(int i = 0;i<commands.length;i++){
            int command = commands[i];
            switch (command){
                case -2:
                    if(index==0) index=3;
                    else index--;
                    break;
                case -1:
                    if(index==3)index=0;
                    else index++;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    int move = command;
                    if(index==0){
                        for(int j=0;j<obstacles.length;j++){
                            if(obstacles[j][0]==start[0]&&obstacles[j][1]>start[1]) {
                                int step = obstacles[j][1] - start[1] -1;
                                move = Math.min(step,move);
                            }
                        }
                    }else if(index==1) {
                        for(int j = 0;j<obstacles.length;j++){
                            if(obstacles[j][1]==start[1]&& obstacles[j][0]>start[0]){
                                int step = obstacles[j][0]-start[0]-1;
                                move = Math.min(step,move);
                            }
                        }
                    }else if(index==2){
                        for(int j=0;j<obstacles.length;j++){
                            if(obstacles[j][0]==start[0]&&obstacles[j][1]<start[1]) {
                                int step = start[1] - obstacles[j][1] -1;
                                move = Math.min(step,move);
                            }
                        }
                    }else {
                        for(int j = 0;j<obstacles.length;j++){
                            if(obstacles[j][1]==start[1]&& obstacles[j][0]<start[0]){
                                int step = start[0]-obstacles[j][0]-1;
                                move = Math.min(step,move);
                            }
                        }
                    }
                    if(move>0){
                        start[0] = start[0]+direction[index][0]*move;
                        start[1] = start[1]+direction[index][1]*move;
                    }
                    result = Math.max(result,start[0]*start[0]+start[1]*start[1]);
                    break;
            }
        }
        return result;
    }
}
```

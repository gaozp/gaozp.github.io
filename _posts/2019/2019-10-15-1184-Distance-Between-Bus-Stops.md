---
layout: post
title: 1184. Distance Between Bus Stops
categories: [leetcode]
---
#### QUESTION:
A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.

The bus goes along both directions i.e. clockwise and counterclockwise.

Return the shortest distance between the given start and destination stops.

 

Example 1:
![](https://assets.leetcode.com/uploads/2019/09/03/untitled-diagram-1.jpg)


Input: distance = [1,2,3,4], start = 0, destination = 1
Output: 1
Explanation: Distance between 0 and 1 is 1 or 9, minimum is 1.
 

Example 2:
![](https://assets.leetcode.com/uploads/2019/09/03/untitled-diagram-1-1.jpg)


Input: distance = [1,2,3,4], start = 0, destination = 2
Output: 3
Explanation: Distance between 0 and 2 is 3 or 7, minimum is 3.
 

Example 3:
![](https://assets.leetcode.com/uploads/2019/09/03/untitled-diagram-1-2.jpg)


Input: distance = [1,2,3,4], start = 0, destination = 3
Output: 4
Explanation: Distance between 0 and 3 is 6 or 4, minimum is 4.
 

Constraints:

1 <= n <= 10^4
distance.length == n
0 <= start, destination < n
0 <= distance[i] <= 10^4
#### EXPLANATION:
题意配合图片也很容易理解，那么一开始就可以将distance想象成一个环形链表，那么要做的只是计算两次相遇的距离。但是这样的话就会使用多余的空间和时间，所以最好的方式还是使用环形链表的思想来做。假设环形的总距离是num，而start->destination之间的距离是a,那么逆向的距离b就是num-a，然后再求两者的小者。  
思路：  
1.循环遍历，从start开始，因为start计算的是start->start+1的距离，所以需要加上  
2.循环到destination，因为destination是destination->destination+1的距离，所以不需要加上  
3.这样我们就计算出来了之间的距离a  
4.另外一个距离就是num-a  
5.计算两者的小的返回结果  
需要注意的是题目中可能会出现start<destination的情况出现。所以需要先将start赋值两者中的小的，而destination赋值比较大的情况。

#### SOLUTION:
```JAVA
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int clock = 0;
        int counterclock = 0;
        int min = Math.min(start,destination);
        int max = Math.max(start,destination);
        for(int i = 0;i<distance.length;i++){
            if(i<min || i>=max){
                clock+=distance[i];
            }else{
                counterclock+=distance[i];
            }
        }
        return Math.min(counterclock,clock);
    }
}
```
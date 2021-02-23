---
layout: post
title: 1769. Minimum Number of Operations to Move All Balls to Each Box
categories: [leetcode]
---
#### QUESTION:
You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.

 

__Example 1:__
```
Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
```
__Example 2:__
```
Input: boxes = "001011"
Output: [11,8,5,4,3,4]
 ```

__Constraints:__
```
n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.
```
#### EXPLANATION:
这道题目虽然是medium的题目, 其实只是因为它需要两次循环, 从难度上来将,根本算不上中等的题目. 这道题目只需要推导出对应的公式就可以:   
首先我们想: 从1的位置上往0的位置上进行搬运, 需要搬几次? 答案是 有几个球就搬几次boxes[1] 次  
那么我们再想: 从i的位置上网0的位置上搬运,需要搬几次? 答案是 有几个位置搬几次 boxes[i] * (i-0)  
那么我们就获取到了对应的结果, 将所有的球都搬到i的位置上,就需要将每个位置的球都搬到i位置上. 也就是 boxes[0..n-1] * ([0..n-1] - i)  
这就是最后的公式了, 通过这个公式, 我们就可以很容易的写出对应的算法.
#### SOLUTION:
```java
class Solution {
    fun minOperations(boxes: String): IntArray {
        var boxesArray: IntArray = IntArray(boxes.length)
        var result: IntArray = IntArray(boxes.length)
        for (c in boxes.toCharArray().withIndex())
            boxesArray[c.index] = c.value - '0'

        for (boxi in boxesArray.withIndex()) {
            var sum = 0;
            for (boxj in boxesArray.withIndex()) {
                if (boxi.index == boxj.index) continue
                sum += boxj.value * Math.abs(boxj.index - boxi.index)
            }
            result[boxi.index] = sum
        }
        return result
    }
}
```

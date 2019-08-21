---
layout: post
title: 1103. Distribute Candies to People
categories: [leetcode]
---
#### QUESTION:
We distribute some number of candies, to a row of n = num_people people in the following way:

We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.

Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.

This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).

Return an array (of length num_people and sum candies) that represents the final distribution of candies.

 

Example 1:

Input: candies = 7, num_people = 4
Output: [1,2,3,1]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0,0].
On the third turn, ans[2] += 3, and the array is [1,2,3,0].
On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
Example 2:

Input: candies = 10, num_people = 3
Output: [5,2,3]
Explanation: 
On the first turn, ans[0] += 1, and the array is [1,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0].
On the third turn, ans[2] += 3, and the array is [1,2,3].
On the fourth turn, ans[0] += 4, and the final array is [5,2,3].
 

Constraints:

1 <= candies <= 10^9
1 <= num_people <= 1000
#### EXPLANATION:

这道题目粗看，认为可以直接进行枚举，一个一个的填坑就行，但是还是担心会不会有timeout的情况。想想其实这种简单耗时的操作给计算机做是正好的。于是思路就是:  
1. 从第一个点开始填1一直往后，并且用一个tmp来记录当前需要发几个  
2. 直到candies<0的情况发生  
3. 如果当前的candies-tmp小于0，说明已经光了，那么就需要把candies给这个人  
4. 在index超过num_people的时候，我们需要重头开始

#### SOLUTION:
```java
class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int tmp = 1,index = 0;
        while (candies>0){
            if(candies-tmp>=0)
                result[index] += tmp;
            else
                result[index] += candies;
            candies-=tmp;
            tmp++;
            index++;
            if(index==num_people) index=0;
        }
        return result;
    }
}
```

---
layout: post
title: 989. Add to Array-Form of Integer
categories: [leetcode]
---
#### QUESTION:
For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

 

Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: A = [2,7,4], K = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: A = [2,1,5], K = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10000000000
 

Note：

1 <= A.length <= 10000
0 <= A[i] <= 9
0 <= K <= 10000
If A.length > 1, then A[0] != 0


#### EXPLANATION:

首先看到A的长度在10000以上我们就知道，int类型的值肯定就是不行了，那么如果用longint或者其他数据类型，也不一定能够满足。那么就不能采用这种先转化成数字再进行算术，然后再转化成数组的形式。
然后我们在看到solution的返回值时，可以知道，需要返回的是一个list集合。那么就比较容易了。就采用最基本的算术加法就可以，然后把每一位的结果放在最前面即可。
算术的加法就是：
int remain = (numberA + numberB + carry) % 10;
int carry = (numberA + numberB + carry) /10;

#### SOLUTION:
```
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        ArrayList<Integer> list = new ArrayList<>();
        char[] KArray = (K+"").toCharArray();
        int length = Math.max(A.length,KArray.length);
        int carry = 0;
        for(int i = 1;i<=length;i++){
            int numberA = 0,numberK = 0;
            if(A.length-i>=0) numberA = A[A.length-i];
            if(KArray.length-i>=0) numberK = Integer.parseInt(KArray[KArray.length-i]+"");
            int number = numberA+numberK+carry;
            int tmp = number%10;
            carry = number/10;
            list.add(0,tmp);
        }
        if(carry!=0) list.add(0,carry);
        return list;
    }
}
```
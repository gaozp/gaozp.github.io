---
layout: post
title: 1089. Duplicate Zeros
categories: [leetcode]
---
#### QUESTION:


Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place, do not return anything from your function.

Example 1:

Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]
 

Note:

1 <= arr.length <= 10000
0 <= arr[i] <= 9

#### EXPLANATION:

理解题意：复制0的个数，同时其他位往右移动。保证arr长度不变。
首先肯定不能全部0都完成，这样其实多做了很多工作，只需要arr长度就可以。同时因为还需要保证arr是fixed，所以只能通过复制一个数组来标记才行。
算法：
1.两个指针从头开始
2.当遇到0的时候，arr的指针走两格，同时都为0

#### SOLUTION:
```JAVA
    public static void duplicateZeros(int[] arr) {
        int[] result = new int[arr.length];
        int index = 0;
        for(int i = 0;i<result.length;i++){
            if(arr[index]!=0){
                result[i] = arr[index];
            }else{
                result[i]=0;
                if(i+1<result.length){
                    result[i+1]=0;
                    i = i+1;
                }
            }
            index++;
        }
        for(int i = 0;i<result.length;i++) arr[i] = result[i];
    }
```
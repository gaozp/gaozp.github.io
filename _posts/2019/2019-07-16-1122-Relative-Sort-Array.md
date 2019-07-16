---
layout: post
title: 1122. Relative Sort Array
---
#### QUESTION:
Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

 

Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
 

Constraints:

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
Each arr2[i] is distinct.
Each arr2[i] is in arr1.
#### EXPLANATION:

题目大意:按照arr2的顺序对arr1进行排序，剩下的按自然排序放在末尾
思路：
1.按arr2的顺序进行arr1的整理
2.如果位置不对就直接进行swap
3.到最后对剩下的进行排序

#### SOLUTION:
```JAVA
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index = 0;
        for(int i = 0;i<arr2.length;i++){
            int tmp = index ;
            for(int j=tmp;j<arr1.length;j++){
                if(arr1[j]==arr2[i]) {
                    swapArray(arr1,j,index);
                    index++;
                }
            }
        }
        Arrays.sort(arr1,index,arr1.length);
        return arr1;
    }
    
    public static void swapArray(int[] nums,int a , int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
```

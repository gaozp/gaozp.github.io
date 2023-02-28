---
layout: post
title: 1207. Unique Number of Occurrences
categories: [leetcode]
---
#### QUESTION:
Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.

 

Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:

Input: arr = [1,2]
Output: false
Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true
 

Constraints:

1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
#### EXPLANATION:
这个题目好像也没有可以特别可以说的。逻辑就是比较简单：  
1.首先分别计算出每个数字出现的次数  
2.计算出现的次数是否有重复  
#### SOLUTION:
```JAVA
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<arr.length;i++)
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);

        ArrayList<Integer> result = new ArrayList<>();
        Collection<Integer> values = map.values();
        for(int val:values){
            if(result.contains(val)) return false;
            result.add(val);
        }
        return true;
    }
}
```
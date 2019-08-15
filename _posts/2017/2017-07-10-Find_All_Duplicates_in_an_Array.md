---
layout: post
title: 442. Find All Duplicates in an Array
categories: [leetcode]
---

#### QUESTION:

Given an array of integers, 1 ≤ a[i] ≤ *n* (*n* = size of array), some elements appear **twice** and others appear **once**.

Find all the elements that appear **twice** in this array.

Could you do it without extra space and in O(*n*) runtime?

**Example:**

```
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
```

#### EXPLANATION:

第一个解法也算是一个ac解吧，但是并不是一个只有O(1)空间复杂度和O(n)时间复杂度的解法，这个解法比较容易想到，排序后如果和前一个是相同的就添加结果。但是已有的排序算法是不足以达到O(n)的时间复杂度的。

所以就有了第二个解法，把对应的index的数换成负数，如果这个数已经出现过了，那么这个位置的数就应该是负数，加上index+1即是结果。其实就是将元数据进行了改变用来标记已经出现的数字。

#### SOLUTION:

```JAVA
public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        int pre = 0;
        for(int i = 0;i<nums.length;i++){
            if(i==0){
                pre = nums[i];
                continue;
            }
            if(nums[i]==pre){
                result.add(nums[i]);
            }
            if(nums[i]!=pre)
                pre = nums[i];

        }
        return result;
    }
  
      public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res=new ArrayList<>();
        for (int i=0;i<nums.length;++i){
            int index=Math.abs(nums[i])-1;
            if(nums[index]<0){
                res.add(index+1);
            }
            nums[index]=-nums[index];
        }
        return res;
    }
}
```


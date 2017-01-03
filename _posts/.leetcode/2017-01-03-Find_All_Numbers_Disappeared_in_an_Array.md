---
layout: post
title: 448. Find All Numbers Disappeared in an Array
---

#### QUESTION:

Given an array of integers where 1 ≤ a[i] ≤ *n* (*n* = size of array), some elements appear twice and others appear once.

Find all the elements of [1, *n*] inclusive that do not appear in this array.

Could you do it without extra space and in O(*n*) runtime? You may assume the returned list does not count as extra space.

**Example:**

**Input:**

[4,3,2,7,8,2,3,1]

**Output:**

[5,6]

#### EXPLANATION:

这个地方采用的是正负位置标记法的解决办法，首先创建一个都是负数的数组，然后将原数组中的元素都填充到这个负数数组中，最后再判断其中的负数位置就是缺少的元素了。

#### SOLUTION:

```
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = nums.length;
        int[] num = new int[n];
        Arrays.fill(num,-1);
        for(int i = 0;i < n;i++)
            num[nums[i] - 1] = nums[i];
        for(int i = 0;i<n;i++){
            if(num[i]==-1){
                result.add(i+1);
            }
        }
        return result;
    }
```


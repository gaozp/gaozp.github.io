---
layout: post
title: move zeros
categories: [leetcode]
---
#### QUESTION:
给一个数字[2,1,0,4,5,0,3], 把0移动到最后，其他元素顺序保持不变。要求时间复杂度O(n) 空间复杂度O(1)

#### EXPLANATION:

其实难点就是在于时间复杂度是O(n),怎么样才能是on呢，那么只能是进行一次循环。
那么你就需要两个指针，一个用来指0位置，一个用来指非0位置，遇到的话就将两者进行交换。
这样的话就可以确定只使用了一次循环。

#### SOLUTION:
```JAVA
    public static int[] moveZero(int[] nums){
        int zeroPoint = 0;
        int nonZeroPoint = 0;
        while(true){
            while(zeroPoint<nums.length && nums[zeroPoint]!=0) zeroPoint++;
            if(zeroPoint>=nums.length) break;

            if(zeroPoint>=nonZeroPoint) nonZeroPoint = zeroPoint+1;

            while (nonZeroPoint<nums.length&& nums[nonZeroPoint]==0) nonZeroPoint++;
            if(nonZeroPoint>=nums.length) break;

            swapArray(nums,zeroPoint,nonZeroPoint);
            nonZeroPoint++;
        }
        return nums;
    }
```
---
layout: post
title: 581. Shortest Unsorted Continuous Subarray
categories: [leetcode]
---

#### QUESTION:

Given an integer array, you need to find one **continuous subarray** that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the **shortest** such subarray and output its length.

**Example 1:**

```
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

```

**Note:**

1. Then length of the input array is in range [1, 10,000].
2. The input array may contain duplicates, so ascending order here means **<=**.

#### EXPLANATION:

+ 第一个解法

  第一个解法比较简单，其实就是先排序之后再看一下不相同的位置。

+ 第二个解法

  1.由前向后决定end的位置，因为最后的位置肯定应该是最大值，如果不是的话，那么就确定end就是当前位置。

  2.右后向前决定start的位置，与上面相反，就可以确定出start的位置，最后进行处理就可以了。

#### SOLUTION:

```java
public class Solution {
  // SOLUTION 1:
    public static int findUnsortedSubarray(int[] nums) {
        if(nums.length == 0|| nums.length ==1) return 0;
        int[] copy = Arrays.copyOf(nums,nums.length);
        Arrays.sort(copy);
        int m = -1,n = -2;
        for(int i = 0;i<nums.length;i++){
            if(copy[i]!=nums[i]){
                if(m == -1)
                    m = i;
                n = i;
            }
        }
        return n-m+1;
    }
  //SOLUTION 2:
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length == 0|| nums.length ==1) return 0;

        int n = nums.length,start = -1,end = -2,max = nums[0],min = nums[n-1];
        for(int i = 1;i<nums.length;i++){
            max = Math.max(max,nums[i]);
            min = Math.min(min,nums[n-i-1]);
            if(nums[i]<max) end = i;
            if(min<nums[n-i-1]) start = n-i-1;
        }
        return end-start+1;
    }
}
```


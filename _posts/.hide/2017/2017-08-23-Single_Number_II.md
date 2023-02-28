---
layout: post
title: 137. Single Number II
categories: [leetcode]
---

#### QUESTION:

Given an array of integers, every element appears *three* times except for one, which appears exactly once. Find that single one.

**Note:**
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

#### EXPLANATION:

还是昨天的思路，每一位都加起来，可以算出一共的1的个数，然后用这些1去除以3，这样的话就能够得出那个独特的数的二进制。然后再转换回来就可以了。

#### SOLUTION:

```JAVA
class Solution {
    public int singleNumber(int[] nums) {
        int[] mask = new int[32];
        for (int j = 0; j < nums.length; j++) {
            for (int i = 0; i < 32; i++) {
                mask[i] += (nums[j]>>i) &1;
            }
        }
        int res = 0;
        for(int i = 31;i>=0;i--){
            res += (mask[i]%3)<<i;
        }
        return res;
    }
}
```


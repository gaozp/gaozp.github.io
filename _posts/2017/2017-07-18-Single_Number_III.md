---
layout: post
title: 260. Single Number III
---

#### QUESTION:

Given an array of numbers `nums`, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given `nums = [1, 2, 1, 3, 2, 5]`, return `[3, 5]`.

**Note**:

1. The order of the result is not important. So in the above example, `[5, 3]` is also correct.
2. Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

#### EXPLANATION:

这次的情况是有两个数只出现了一次，那么就需要将这两个数区分出来就可以，区分的关键就在于找到两个数的不同。这里用到的方法就是

1.异或所有数。这样得到的就是3^5=6

2.然后再得出补位，再&，就能得到最右边一位不同的

3.然后再将所有数进行异或

如 3的二进制 是 011， 5的二进制是 101 所以他们异或出来是110也就是6；

6的补位就是010 那么他们再&一下，那么就是010，就可以得到最右边一位不同的数了。就可以区分出对应的两个数了。

#### SOLUTION:

```JAVA
public class Solution {
    public int[] singleNumber(int[] nums) {
        int[] ans = new int[2];
        int diff = 0;
        for(int num : nums) {
            diff ^= num;
        }
        diff &= -diff;//解决方法的关键，key point
        for(int num : nums) {
            if((num & diff) == 0) {
                ans[0] ^= num;
            }else {
                ans[1] ^= num;
            }
        }
        return ans;
    }
}
```


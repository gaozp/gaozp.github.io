---
layout: post
title: 594. Longest Harmonious Subsequence
---

#### QUESTION:

We define a harmonious array is an array where the difference between its maximum value and its minimum value is **exactly** 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible [subsequences](https://en.wikipedia.org/wiki/Subsequence).

**Example 1:**

```
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

```

**Note:** The length of the input array will not exceed 20,000.

#### EXPLANATION:

这是我第一次提交打败了100%的人，真的有点开心哈。

其实一开始的思路是放在hashtable中，但是没想到提交后发现 -1，1，2的hash顺序竟然是2，-1，1.这就很尴尬了，没有办法，只能想其他的办法。

于是找到了下面的思路：

1.获取当前key的个数count

2.与前一个对比（先判断是不是1）

3.将自己作为前一个

#### SOLUTION:

```java
public class Solution {
    public int findLHSHelper(int[] nums,int key,int index){
        int count = 0;
        for(int i = index;i<nums.length;i++){
            if(nums[i]==key)
                count++;
            if(nums[i]!=key)
                break;
        }
        return count;
    }
    public int findLHS(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        Arrays.sort(nums);
        int index = 0;
        int pre = nums[0];
        Integer preCount = null;
        int result = 0;
        while (index < nums.length) {
            int key = nums[index];
            int count = findLHSHelper(nums, key, index);
            index += count;

            if (preCount == null || Math.abs(pre - key) > 1) {
                preCount = count;
                pre = key;
                continue;
            }

            result = Math.max(result, preCount + count);
            preCount = count;
            pre = key;
        }
        return result;
    }
}
```


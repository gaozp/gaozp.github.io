---
layout: post
title: 27. Remove Element
---

#### QUESTION:

Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

**Example:**
Given input array *nums* = `[3,2,2,3]`, *val* = `3`

Your function should return length = 2, with the first two elements of *nums* being 2.

#### EXPLANATION:

1.两个指针开始进行，pre指针用来寻找val，last指针用来寻找!val。

2.找到后两者互换

3.判断pre指针的数字是否是val如果不是说明数量需要+1，如果是就说明前一个就是最后的结果。

#### SOLUTION:

```java
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int pre = 0;
        int last = nums.length - 1;
        while (pre < last) {
            while (nums[pre] != val && pre < last) pre += 1;
            while (nums[last] == val && last > pre) last -= 1;
            switchIndex(nums, pre, last);
        }
        if (nums[pre] != val)
            return pre+1;
        return pre;
    }
    
    public void switchIndex(int[] nums,int indexa,int indexb){
        int tmp = nums[indexa];
        nums[indexa] = nums[indexb];
        nums[indexb] = tmp;
    }
}
```


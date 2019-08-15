---
layout: post
title: 665. Non-decreasing Array
categories: [leetcode]
---

#### QUESTION:

Given an array with `n` integers, your task is to check if it could become non-decreasing by modifying **at most** `1` element.

We define an array is non-decreasing if `array[i] <= array[i + 1]` holds for every `i` (1 <= i < n).

**Example 1:**

```
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

```

**Example 2:**

```
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.

```

**Note:** The `n` belongs to [1, 10,000].

#### EXPLANATION:

思路的话可以看这两个例子：

2，3，3，2，4 这个地方需要修改成2，3，3，3，4

1，1，2，4，2这个地方需要修改4成2

就是可以知道会有两种情况，一种是：需要将这个数提高到和前面的数一样，还有一种就是需要把这个数降低到和前面数一样。

这个取决于什么呢，取决于：前面有几个数比当前数大。

那么思路就出来了：

1.遍历数组

2.遇到比前面数小的数时，判断前面有几个数比当前数小

3.如果超过两个，那么就将该数修改为前面的数

4.如果只有1个，那么就将前面的数修改为该数

遍历结束后查看修改的次数。

#### SOLUTION:

```JAVA
class Solution {
    public boolean checkPossibility(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            int tmp = 0;
            while (stack.size()!=0 && stack.peek() >nums[i]){
                stack.pop();
                tmp++;
            }
            if(tmp>=2){
                nums[i] = nums[i-1];
                result++;
            }else if(tmp==1){
                result++;
            }
            stack.push(nums[i]);
        }
        return result<2;
    }
}
```


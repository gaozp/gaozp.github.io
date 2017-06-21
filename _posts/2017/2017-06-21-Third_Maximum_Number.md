---
layout: post
title: 414. Third Maximum Number
---

#### QUESTION:

------

Given a **non-empty** array of integers, return the **third** maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

**Example 1:**

```
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.

```

**Example 2:**

```
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

```

**Example 3:**

```
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
```

#### EXPLANATION:

其实就是取出每个数和对应的比较，然后进行顺序推下去的赋值。

再需要注意的就是 long型，因为在int型的值在testcase中有一个是包含了这个值的，所以不能直接用int类型的进行匹配。

#### SOLUTION:

```JAVA
public class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            int num = nums[i];
            if(num>third){
                if(num>second){
                    if(num>first){
                        third = second;
                        second = first;
                        first = num;
                    }else if(num!=first){
                        third = second;
                        second = num;
                    }
                }else if(num!=second){
                    third = num;
                }
            }
        }
        return third == Long.MIN_VALUE ? (int) first : (int)third;
    }
}
```


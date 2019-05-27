---
layout: post
title: 1051. Height Checker
---

#### QUESTION:

Students are asked to stand in non-decreasing order of heights for an annual photo.

Return the minimum number of students not standing in the right positions.  (This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)

**Example 1:**

```
Input: [1,1,4,2,1,3]
Output: 3
Explanation: 
Students with heights 4, 3 and the last 1 are not standing in the right positions.
```

**Note:**

1. `1 <= heights.length <= 100`
2. `1 <= heights[i] <= 100`

#### EXPLANATION:

这个题目就很简单了，只需要进行排序，排序之后对比原数组，不对的数字就说明位置需要进行改变。

#### SOLUTION:

```java
class Solution {
    public int heightChecker(int[] heights) {
       int result = 0;
        int[] ints = Arrays.copyOf(heights, heights.length);
        Arrays.sort(heights);
        for(int i = 0;i<heights.length;i++){
            if(heights[i]!=ints[i])result++;
        }
        return result; 
    }
}
```


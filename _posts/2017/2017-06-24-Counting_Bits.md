---
layout: post
title: 338. Counting Bits
categories: [leetcode]
---

#### QUESTION:

Given a non negative integer number **num**. For every numbers **i** in the range **0 ≤ i ≤ num** calculate the number of 1's in their binary representation and return them as an array.

**Example:**
For `num = 5` you should return `[0,1,1,2,1,2]`.

**Follow up:**

- It is very easy to come up with a solution with run time **O(n\*sizeof(integer))**. But can you do it in linear time **O(n)** /possibly in a single pass?
- Space complexity should be **O(n)**.
- Can you do it like a boss? Do it without using any builtin function like **__builtin_popcount** in c++ or in any other language.

#### EXPLANATION:

java从1.5版本之后有了一个bitcount方法，这样就可以直接算出对应的1的数量了。

#### SOLUTION:

```JAVA
public class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i = 0;i<=num;i++){
            result[i] = Integer.bitCount(i);
        }
        return result;
    }
}

public static int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
```


---
layout: post
title: 693. Binary Number with Alternating Bits
---

#### QUESTION:

Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

**Example 1:**

```
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101

```

**Example 2:**

```
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.

```

**Example 3:**

```
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.

```

**Example 4:**

```
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.
```

#### EXPLANATION:

其实就比较简单了，但是最快的应该还是用位运算吧。最后一位和1进行&操作，然后结果再右移。

#### SOLUTION:

```JAVA
class Solution {
    public boolean hasAlternatingBits(int n) {
        if(n==0) return false;
        if(n==1) return true;
        String s = Integer.toBinaryString(n);
        char pre = s.charAt(0);
        for(int i =1;i<s.length();i++){
            if(s.charAt(i)==pre)
                return false;
            pre = s.charAt(i);
        }
        return true;
    }
}
```


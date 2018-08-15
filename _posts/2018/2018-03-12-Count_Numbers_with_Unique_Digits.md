---
layout: post
title: 357. Count Numbers with Unique Digits
---

#### QUESTION:

Given a **non-negative** integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

**Example:**
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding `[11,22,33,44,55,66,77,88,99]`)

**Credits:**
Special thanks to [@memoryless](https://discuss.leetcode.com/user/memoryless) for adding this problem and creating all test cases.

#### EXPLANATION:

其实是一个很正常的动态规划的+回溯的算法：

这样算：

f(0)=1

f(1) = 10

f(2)=9*9 +f(1) = 91

f(3)=9\*9\*8+f(2)

f(4)=9\*9\*8\*7+f(3)

f(n)=9\*9\*8\*7…\*0+f(n-1)

所以最后的时候就没有办法是不重复的了。

所以这样伪代码写出来之后，再写算法的话就会很简单了。

#### SOLUTION:

```JAVA
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        int res = 10;
        int remain = 9;
        int uniqueDigits = 9;
        while (n-- > 1 && remain > 0) {
            uniqueDigits = uniqueDigits * remain;
            res += uniqueDigits;
            remain--;
        }
        return res;
    }
}
```


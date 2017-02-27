---
layout: post
title: 342. Power of Four
---

#### QUESTION:

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

**Example:**
Given num = 16, return true. Given num = 5, return false.

**Follow up**: Could you solve it without loops/recursion?

**Credits:**
Special thanks to [@yukuairoy ](https://leetcode.com/discuss/user/yukuairoy)for adding this problem and creating all test cases.

#### EXPLANATION:

就是只要余数不是0的时候，就一直除以4，到最后就可以了。

#### SOLUTION:

```java
public boolean isPowerOfFour(int num) {
        if(num == 0) return false;
        while(num>0 && num % 4 == 0){
            num = num>>2;
        }
        return num == 1;
    }
```


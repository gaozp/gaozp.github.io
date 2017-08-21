---
layout: post
title: 397. Integer Replacement
---

#### QUESTION:

Given a positive integer *n* and you can do operations as follow:

1. If *n* is even, replace *n* with `*n*/2`.
2. If *n* is odd, you can replace *n* with either `*n* + 1` or `*n* - 1`.

What is the minimum number of replacements needed for *n* to become 1?

**Example 1:**

```
Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1

```

**Example 2:**

```
Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
```

#### EXPLANATION:

每次遇到奇数的时候是需要+1还是减一呢？这个问题是这道题目的关键。

至于是选择+1还是减一，其实是需要看+1或者-1的结果中二进制位的1的个数，如果个数变少了，那么就说明后面需要+1或者-1的操作就变少了。

所以就可以判断Integer.bitCount来当做依据。但是这个也有一个问题。

3这个数字是唯一的例外。因为两者相等，但是3+1进了一位，需要4除以2再除以2，需要多一步除2的操作，而3-1只需要除以2就可以.

所以我们也可以将进位后的length作为依据。

#### SOLUTION:

```JAVA
class Solution {
    public int integerReplacement(int n) {
        int result = 0;
        while (n!=1){
            if(n%2!=0){
                int a = n+1 ,b = n-1;
                int x = Integer.bitCount(a);
                int y = Integer.bitCount(b);
                int e = Integer.toBinaryString(a).length();
                int f = Integer.toBinaryString(b).length();

                if(x<y) n = a;
                else if(x>y)n=b;
                else {
                    if(e<=f) n = a;
                    else n=b;
                }

                result++;
            }
            n=n>>>1;
            result++;
        }
        return result;
    }
}
```


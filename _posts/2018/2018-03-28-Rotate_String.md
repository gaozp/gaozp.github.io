---
layout: post
title: 796. Rotate String
categories: [leetcode]
---

#### QUESTION:

We are given two strings, `A` and `B`.

A *shift on A* consists of taking string `A` and moving the leftmost character to the rightmost position. For example, if `A = 'abcde'`, then it will be `'bcdea'` after one shift on `A`. Return `True` if and only if `A` can become `B` after some number of shifts on `A`.

```
Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
```

**Note:**

- `A` and `B` will have length at most `100`.

#### EXPLANATION:

这个是easy的题目，直接看逻辑就好了

不过我看到一个简便的办法

tmp = A+A

A.contains(B);

#### SOLUTION:

```JAVA
class Solution {
    public boolean rotateString(String A, String B) {
        if(A.equals(B)) return true;
        if(A.length()!=B.length()) return false;
        int time = 0;
        while (time<A.length()){
            String tmp = shiftString(A);
            if(tmp.equals(B)) return true;
            A = tmp;
            time++;
        }
        return false;
    }
    public String shiftString(String A){
        return A.substring(1,A.length())+A.charAt(0);
    }
}
```


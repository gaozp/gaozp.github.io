---
layout: post
title: 686. Repeated String Match
categories: [leetcode]
---
#### QUESTION:
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.
#### EXPLANATION:

题目大意:求出最小的重复A数量，能够包含B，没有就返回-1.
考虑下，大概会分成两种情况，
1.既B是从A的第一个字符开始，这样只要长度大于等于B的长度就可以
2.B是从Ade中间字符开始，那么就需要再进行一次添加。

#### SOLUTION:
```java
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.toString().length()<B.length()){
            sb.append(A);
            count++;
        }
        if(!sb.toString().contains(B)){
            sb.append(A);
            if(!sb.toString().contains(B)) return -1;
            else return count+1;
        }else return count;
    }
}
```

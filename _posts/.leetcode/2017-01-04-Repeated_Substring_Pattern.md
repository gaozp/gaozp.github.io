---
layout: post
title: 459. Repeated Substring Pattern
---

#### QUESTION:

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

**Example 1:**

**Input:** "abab"

**Output:** True

**Explanation:** It's the substring "ab" twice.

**Example 2:**

**Input:** "aba"

**Output:** False

**Example 3:**

**Input:** "abcabcabcabc"

**Output:** True

**Explanation:** It's the substring "abc" four times. (And the substring "abcabc" twice.)

#### EXPLANATION:

首先重复的肯定是大于等于两个的，所以可以从中间开始算作能够重复的最大字符串，然后再逐步递减。如果无法整除的话肯定就是无法重复的，所以在整除的基础上，将**截取的字符串*对应的倍数**生成整个字符串，再与元字符串进行比对。如果循环都走完了，说明没有可以重复的字符串。

#### SOLUTION:

```java
    public boolean repeatedSubstringPattern(String str) {
        int n  = str.length();
        for(int i =n/2;i>=1;i--){
            if(n%i==0){
                String substring = str.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j = 0;j<n/i;j++){
                    sb.append(substring);
                }
                if(sb.toString().equals(str)) return true;
            }
        }
        return false;
    }
```


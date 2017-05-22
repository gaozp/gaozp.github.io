---
layout: post
title: 438. Find All Anagrams in a String
---

#### QUESTION:

Given a string **s** and a **non-empty** string **p**, find all the start indices of **p**'s anagrams in **s**.

Strings consists of lowercase English letters only and the length of both strings **s** and **p** will not be larger than 20,100.

The order of output does not matter.

**Example 1:**

```
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

```

**Example 2:**

```
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

#### EXPLANATION:

时间复杂度O(mn)的就不说了，刚开始我也是直接使用的O(mn)的方式进行提交了，然后获得了一个timelimited的错误，其实写的时候也想到了就是并不是每次移动都需要重新遍历一遍 p的string。只要记住每次移动的窗口添加的start的数是否在移动的end的时候减去了就可以。

1.首先在循环开始之前先遍历整个p，初始化数组。

2.然后再移动end，如果end正好是p中的字母，数组也正好填平了，那就说明start是可行的。没填平就继续挖个坑留给start来填。

3.移动start，如果是p中的字母则进行count+1操作。

#### SOLUTION:

```java
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] chars = new int[26];
        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() < p.length())
            return result;
        for (char c : p.toCharArray())
            chars[c-'a']++;

        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            if (end - start == p.length() && chars[s.charAt(start++)-'a']++ >= 0)
                count++;
            if (--chars[s.charAt(end++)-'a'] >= 0)
                count--;
            if (count == 0)
                result.add(start);
        }

        return result;
    }
}
```


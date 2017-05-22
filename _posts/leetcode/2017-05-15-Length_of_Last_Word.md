---
layout: post
title: 58. Length of Last Word
---

#### QUESTION:

Given a string *s* consists of upper/lower-case alphabets and empty space characters `' '`, return the length of last word in the string.

If the last word does not exist, return 0.

**Note:** A word is defined as a character sequence consists of non-space characters only.

For example, 
Given *s* = `"Hello World"`,
return `5`.

#### EXPLANATION:

说真的，真的没有什么可以解释的，只是要多注意下length是0的情况。

#### SOLUTION:

```JAVA
public class Solution {
    public int lengthOfLastWord(String s) {
        String[] split = s.split(" ");
        return split.length==0?0:split[split.length-1].length();
    }
}
```


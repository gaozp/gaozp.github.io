---
layout: post
title: 205. Isomorphic Strings
---

#### QUESTION:

Given two strings **s** and **t**, determine if they are isomorphic.

Two strings are isomorphic if the characters in **s** can be replaced to get **t**.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given `"egg"`, `"add"`, return true.

Given `"foo"`, `"bar"`, return false.

Given `"paper"`, `"title"`, return true.

**Note:**
You may assume both **s** and **t** have the same length.

#### EXPLANATION:

好吧，其实看代码就可以了，判断是否包含，不包含就添加，否则判断是否相等。

但是在不包含的时候还得判断该 值是否已经被其他的绑定，如果绑定了，那么就说明重复了。

直接就可以returnfalse。

#### SOLUTION:

```java
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        Hashtable<Character, Character> reflectTable = new Hashtable<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            Object value = reflectTable.get(key);
            if (value == null) {
                char tValue = t.charAt(i);
                boolean containValue = reflectTable.containsValue(tValue);
                if(containValue)
                    return false;
                reflectTable.put(key, t.charAt(i));
            } else {
                if ((char) value != t.charAt(i))
                    return false;
            }
        }
        return true;
    }
}
```


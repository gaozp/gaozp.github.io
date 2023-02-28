---
layout: post
title: 917. Reverse Only Letters
categories: [leetcode]
---

#### QUESTION:

Given a string `S`, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

**Example 1:**

```
Input: "ab-cd"
Output: "dc-ba"
```

**Example 2:**

```
Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
```

**Example 3:**

```
Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"
```

**Note:**

1. `S.length <= 100`
2. `33 <= S[i].ASCIIcode <= 122` 
3. `S` doesn't contain `\` or `"`

#### EXPLANATION:

其实看到题目就会想到，这个一定是采用double pointer的方式。进行一个start，一个end的指针，当两者都找到合适的值的时候，然后进行交换操作。

#### SOLUTION:

```java
class Solution {
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int start = 0;
        int end = chars.length-1;
        while (start<end){
            while ((chars[start] < 65 || (chars[start] > 90 && chars[start] < 97) || chars[start] > 122)&&start<end) start++;
            while ((chars[end] < 65 || (chars[end] > 90 && chars[end] < 97) || chars[end] > 122)&&end>start) end--;
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;end--;
        }
        return new String(chars);
    }
}
```






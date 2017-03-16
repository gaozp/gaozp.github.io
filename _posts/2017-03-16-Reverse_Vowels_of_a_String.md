---
layout: post
title: 345. Reverse Vowels of a String
---

#### QUESTION:

Write a function that takes a string as input and reverse only the vowels of a string.

**Example 1:**
Given s = "hello", return "holle".

**Example 2:**
Given s = "leetcode", return "leotcede".

**Note:**
The vowels does not include the letter "y".

#### EXPLANATION:

思路就比较简单：

1.从前往后找到元音字母，从后往前找到元音字母。

2.进行交换操作。



其实就是使用了快速排序的思想。

#### SOLUTION:

```java
public class Solution {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int low = 0;
        int high = chars.length - 1;
        Predicate pre = o -> {
            if(o.equals('a')||o.equals('e')||o.equals('i')||o.equals('o')||o.equals('u')||o.equals('A')||o.equals('E')||o.equals('I')||o.equals('O')||o.equals('U'))
                return true;
            return false;
        };
        while(low<=high){
            char tmp = ' ';
            while(!pre.test(chars[low])&&low<high){
                low++;
            }
            tmp = chars[low];
            while(!pre.test(chars[high])&&low<high){
                high--;
            }
            chars[low] = chars[high];
            chars[high] = tmp;
            low++;high--;
        }
        return new String(chars);
    }
}
```


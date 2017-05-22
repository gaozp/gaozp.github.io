---
layout: post
title: 500. Keyboard Row
---

#### QUESTION:

Given a List of words, return the words that can be typed using letters of **alphabet** on only one row's of American keyboard like the image below.

![img](https://leetcode.com/static/images/problemset/keyboard.png)

**Example 1:**

**Input:** ["Hello", "Alaska", "Dad", "Peace"]

**Output:** ["Alaska", "Dad"]

**Note:**

1. You may use one character in the keyboard more than once.
2. You may assume the input string will only contain letters of alphabet.

#### EXPLANATION:

1.首先将每一排的字母都对应起来。

2.将每个字符串的每个字母都进行索引，如果和前面的index不对应的话，那就说明不在同一排。

3.结束之后确定该字母是否在同一排。

#### SOLUTION:

```java
public static String[] findWords(String[] words) {
        Hashtable<Character,Integer> row = new Hashtable();
        String[] str_index = new String[]{"qwertyuiop","asdfghjkl","zxcvbnm"};
        for(int i = 0;i<str_index.length;i++){
            String line = str_index[i];
            for(char ch: line.toCharArray()){
                row.put(ch,i);
            }
        }

        ArrayList<String> result = new ArrayList();
        for(String word:words){
            char[] chars = word.toLowerCase().toCharArray();
            int index = row.get(chars[0]);
            for(int i = 0;i<chars.length;i++){
                int row_index = (int) row.get(chars[i]);
                if(row_index!=index){
                    index = -1;
                    break;
                }
            }
            if(index!=-1)
                result.add(word);
        }
        return result.toArray(new String[0]);
    }
```


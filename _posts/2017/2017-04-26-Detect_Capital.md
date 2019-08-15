---
layout: post
title: 520. Detect Capital
categories: [leetcode]
---


#### QUESTION:

Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

1. All letters in this word are capitals, like "USA".
2. All letters in this word are not capitals, like "leetcode".
3. Only the first letter in this word is capital if it has more than one letter, like "Google".

Otherwise, we define that this word doesn't use capitals in a right way.

**Example 1:**

```
Input: "USA"
Output: True

```

**Example 2:**

```
Input: "FlaG"
Output: False

```

**Note:** The input will be a non-empty word consisting of uppercase and lowercase latin letters.

#### EXPLANATION:

其实一开始就想过用正则表达式，但是想想正则表达式的效率，还是算了。实施结果也证明确实不是特别理想。

也看了其他人的解决办法。但是也是没有特别快的。

因为其他人都需要无论如何都需要遍历整个数组。如果是一个正确的单词的话，那么效率是一样的，但是如果是一个不正确的单词的话，那么错误发生的越早，越早返回的效率是最高的。

所以就写了下面的逻辑，ac 88%的解决方案。

1.首先判断第一个字母是大小写，小写就说明后面的字母都必须是小写。

2.如果是大写则判断后面第二个字母是大小写，小写则说明后面字母都必须是小写。

3.如果是大写则说明后面字母必须是大写。

#### SOLUTION:

```java
public class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length()==1) return true;
        int char0 = word.charAt(0);
        if(char0>=97){
            for(int i = 1;i<word.length();i++)
                if(word.charAt(i)<97) return false;
        } else{
            int char1 = word.charAt(1);
            if(char1>=97){
                for(int i =2;i<word.length();i++){
                    if(word.charAt(i)<97) return false;
                }
            }else{
                for(int i =2;i<word.length();i++){
                    if(word.charAt(i)>=97) return false;
                }
            }
        }
        return true;
    }
}
```


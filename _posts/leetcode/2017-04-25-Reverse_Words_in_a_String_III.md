---
layout: post
title: 557. Reverse Words in a String III
---

#### QUESTION:

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

**Example 1:**

```
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

```

**Note:** In the string, each word is separated by single space and there will not be any extra space in the string.

#### EXPLANATION:

本来第一开始想到的是第一种解法，就是先split之后，然后每个单词都reverse然后再拼起来，这样的话其实就是有了两次的循环，第一次的split，第二次的每个单词都reverse一次。

后来就想到了第二种，只需要循环一次。标记每个单词的开头和结尾，然后翻转，这样的话其实就比第一种少了一个split，但是感觉时间却减少了很多。

#### SOLUTION:

```java
public class Solution {
    public String reverseWords(String s) {
        String[] splits = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String split : splits){
            sb.append(reverseWordsHelper(split)+" ");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }
    
    public String reverseWordsHelper(String split){
        String result = "";
        for(int i = split.length()-1;i>=0;i--){
            result = result + split.charAt(i);
        }
        return result;
    }
}

public class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i]!=' '){
                int j = i;
                while (j < chars.length && chars[j]!=' ')
                    j++;
                reverseWordHelper(chars,i,j-1);
                i = j;
            }
        }
        return new String(chars);
    }
    
    public void reverseWordHelper(char[] chars,int a,int b){
        while (a<b){
            switchIndex(chars,a,b);
            a++;b--;
        }
    }
    public void switchIndex(char[] chars,int a,int b){
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }
}
```


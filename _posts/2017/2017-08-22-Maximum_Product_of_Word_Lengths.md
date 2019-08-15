---
layout: post
title: 318. Maximum Product of Word Lengths
categories: [leetcode]
---

#### QUESTION:

Given a string array `words`, find the maximum value of `length(word[i]) * length(word[j])` where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

**Example 1:**

Given `["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]`
Return `16`
The two words can be `"abcw", "xtfn"`.

**Example 2:**

Given `["a", "ab", "abc", "d", "cd", "bcd", "abcd"]`
Return `4`
The two words can be `"ab", "cd"`.

**Example 3:**

Given `["a", "aa", "aaa", "aaaa"]`
Return `0`
No such pair of words.

#### EXPLANATION:

题目的思路也很简单：

1.找出不相同的两个字符串

2.将他们的length相乘。

问题就出在了找出不相同的两个字符串。这个地方可以用一个很巧妙的想法，因为int类型是32位的，而字母一共是26个，所以是可以装下的。那么如果有这个字母，我们就在对应的位置上标记1.这样判断两个字符串是不是有相同的字母，就可以直接用数字进行与&操作，如果是0，说明就是没有字母是相同的。

#### SOLUTION:

```JAVA
class Solution {
    public int maxProduct(String[] words) {
        if(words == null || words.length==0) return 0;
        int[] mask = new int[words.length];
        for(int i = 0;i<words.length;i++){
            String tmp = words[i];
            for(int j = 0;j<tmp.length();j++){
                mask[i] |= 1<< (tmp.charAt(j)-'a');
            }
        }

        int result = 0;
        for(int i = 0;i<words.length;i++){
            for(int j = i+1;j<words.length;j++){
                if((mask[i] & mask[j]) == 0 && words[i].length()*words[j].length() > result)
                    result = words[i].length()*words[j].length();
            }
        }
        return result;
    }
}
```


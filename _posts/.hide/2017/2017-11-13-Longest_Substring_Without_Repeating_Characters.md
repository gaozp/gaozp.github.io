---
layout: post
title: 3. Longest Substring Without Repeating Characters
categories: [leetcode]
---

#### QUESITON:

Given a string, find the length of the **longest substring** without repeating characters.

**Examples:**

Given `"abcabcbb"`, the answer is `"abc"`, which the length is 3.

Given `"bbbbb"`, the answer is `"b"`, with the length of 1.

Given `"pwwkew"`, the answer is `"wke"`, with the length of 3. Note that the answer must be a **substring**, `"pwke"` is a *subsequence*and not a substring.

#### EXPLANATION:

算法相当于一个list，然后往里面不停的加东西，如果有重复的，就把重复的地方截取出来作为新的list。

#### SOLUTION:

```JAVA
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<=1) return s.length();
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        int result = 0;
        for(int i = 0;i<chars.length;i++){
            int index = list.indexOf(chars[i]);
            list.add(chars[i]);
            if(index==-1){
                result = Math.max(result,list.size());
            }else {
                ArrayList<Character> tmplist =new ArrayList<>();
                for(int j = list.size()-1;j>index;j--){
                    tmplist.add(0,list.get(j));
                }
                list = tmplist;
                result = Math.max(result,list.size());
            }

        }
        return result;
    }
}
```

```python
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        tmp = ""
        result = 0
        for i in range(0,len(s),):
            index = tmp.find(s[i])
            tmp = tmp+s[i]
            if index != -1 :
                tmp = tmp[index+1:]
            result = max(result,len(tmp))
        return result
```


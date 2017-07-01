---
layout: post
title: 125. Valid Palindrome
---

#### QUESTION:

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
`"A man, a plan, a canal: Panama"` is a palindrome.
`"race a car"` is *not* a palindrome.

**Note:**
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

#### EXPLANATION:

其实挺容易的，只要用双向指针就行，注意特殊清空就ok。

#### SOLUTION:

```JAVA
public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return true;
        String ns = s.toLowerCase();
        char[] chars = ns.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for(char c : chars){
            if((c>='a'&& c<='z') || (c>='0' && c<='9') || (c>='A' && c<='Z'))
                list.add(c);
        }
        Object[] objects = list.toArray();
        int pre = 0;int after = objects.length-1;
        while (pre<after){
            char head = (char) objects[pre];
            char end = (char) objects[after];
            if(head!=end)
                return false;
            pre++;
            after--;
        }
        return true;
    }
}
```


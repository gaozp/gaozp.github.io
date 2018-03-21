---
layout: post
title: 131. Palindrome Partitioning
---

#### QUESTION:

Given a string *s*, partition *s* such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of *s*.

For example, given *s* = `"aab"`,
Return

```
[
  ["aa","b"],
  ["a","a","b"]
]
```

#### EXPLANATION:

还是一个backtracking的问题。首先是

用aab来举例的话。

a是一个palindrome，所以把剩下的进行判断，那就是ab

ab中的a是一个palindrome，剩下的b进行判断

b也是palindrome，所以是一个正解，添加到集合中。

这个时候回到第一个a，剩下的ab不是palindrome，所以a,ab不是正解。

aa,b也是一个正解，aab不是正解。

由上面的步骤可以总结归纳得到。

#### SOLUTION:

```JAVA
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> subset = new ArrayList<>();
        partitionHelper(s,result,subset,0);
        return result;
    }
    public void partitionHelper(String s,List<List<String>> result,List<String> subset,int start) {
        if(start == s.length()) result.add(new ArrayList<>(subset));
        else{
            for(int i = start;i<s.length();i++){
                String tmp = s.substring(start,i+1);
                if(isPalindrome(tmp)){
                    subset.add(tmp);
                    partitionHelper(s,result,subset,i+1);
                    subset.remove(subset.size()-1);
                }
            }
        }
    }
    public static boolean isPalindrome(String s) {
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


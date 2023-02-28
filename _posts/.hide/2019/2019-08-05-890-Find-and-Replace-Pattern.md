---
layout: post
title: 890. Find and Replace Pattern
categories: [leetcode]
---
#### QUESTION:
You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern. 

You may return the answer in any order.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
 

Note:

1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20
#### EXPLANATION:

题目大意就是求映射关系，并且也已经将特殊情况写了下来。
那么就是需要对应  
1.首先拿到pattern对应的word，同时将word中对应的字母集成pattern中的
2.依次对应  
3.如果pattern中已经有了对应，但是word中的对应却不是pattern中的对应，那么就说明不符合  


#### SOLUTION:
```java
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        ArrayList<String> result = new ArrayList<>();
        w: for(int i = 0;i<words.length;i++){
            if(words[i].length()!=pattern.length()) continue;
            char[] chars = words[i].toCharArray();
            int[] map1 = new int[26];
            Arrays.fill(map1,-1);
            int[] map2 = new int[26];
            Arrays.fill(map2,-1);
            char[] pat = pattern.toCharArray();
            for(int j = 0;j<pat.length;j++){
                 char patt = pat[j];
                 char c = chars[j];
                 if(map1[patt-'a']==-1) map1[patt-'a'] = c-'a';
                 if(map2[c-'a']==-1) map2[c-'a'] = patt-'a';
                 if(map1[patt-'a']!=c-'a' || map2[c-'a']!=patt-'a') continue w;
            }
            result.add(words[i]);
        }
        return result;
    }
}
```

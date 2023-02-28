---
layout: post
title: 819. Most Common Word
categories: [leetcode]
---

#### QUESTION:

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

```
Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
```

 

**Note:**

- `1 <= paragraph.length <= 1000`.
- `1 <= banned.length <= 100`.
- `1 <= banned[i].length <= 10`.
- The answer is unique, and written in lowercase (even if its occurrences in `paragraph` may have uppercase symbols, and even if it is a proper noun.)
- `paragraph` only consists of letters, spaces, or the punctuation symbols `!?',;.`
- Different words in `paragraph` are always separated by a space.
- There are no hyphens or hyphenated words.
- Words only consist of letters, never apostrophes or other punctuation symbols.

#### EXPLANATION:

这个题目就比较容易了

1.将所有的字符串先分开。

2.进行计数，这里可以边计数边去除标点符号

3.将计数的结果进行排序

4.将排序的结果和banned数组进行比对

#### SOLUTION:

```JAVA
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] strings = paragraph.split(" ");
        TreeMap<String,Integer> map= new TreeMap<>();
        for(int i = 0;i<strings.length;i++){
            String tmp  = strings[i].toLowerCase();
            char c = tmp.charAt(tmp.length()-1);
            if(c==',' ||c =='.'||c=='?' ||c=='!'||c=='\''||c==';') tmp = tmp.substring(0,tmp.length()-1);
            if(map.get(tmp)==null) map.put(tmp,0);
            map.put(tmp,map.get(tmp)+1);
        }
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        for(int i = list.size()-1;i>=0;i--){
            if(!contains(list.get(i).getKey(),banned)) return list.get(i).getKey();
        }
        return "";
    }
    public boolean contains(String s,String[] banned){
        for(int i = 0;i<banned.length;i++){
            if(banned[i].equals(s)) return true;
        }
        return false;
    }

}
```


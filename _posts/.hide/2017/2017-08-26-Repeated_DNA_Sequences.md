---
layout: post
title: 187. Repeated DNA Sequences
categories: [leetcode]
---

#### QUESTION:

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

```
Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
```

#### EXPLANATION:

我思路就很简单：

1.计算出所有组合的数量

2.去除小于1的

这样的话时间复杂度也是O(n)预计也是可以ac的。

#### SOLUTION:

```JAVA
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(int i = 0;i<s.length()-9;i++){
            String tmp = s.substring(i,i+10);
            map.put(tmp,map.getOrDefault(tmp,0)+1);
        }
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            if(next.getValue()<=1)
                iterator.remove();
        }
        return new ArrayList<>(map.keySet());
    }
}
```


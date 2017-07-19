---
layout: post
title: 451. Sort Characters By Frequency
---

#### QUESTION:

Given a string, sort it in decreasing order based on the frequency of characters.

**Example 1:**

```
Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

```

**Example 2:**

```
Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

```

**Example 3:**

```
Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
```

#### EXPLANATION:

1.先求出数量

2.然后对数量进行排序

3.最后再组成结果

#### SOLUTION:

```JAVA
public class Solution {
    public String frequencySort(String s) {
        Hashtable<Character,Integer> table = new Hashtable<>();
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++) table.put(chars[i],table.getOrDefault(chars[i],0)+1);
        ArrayList<Character> alkeys = new ArrayList<>(table.keySet());
        Collections.sort(alkeys, (o1, o2) -> table.get(o2)-table.get(o1));
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<alkeys.size();i++){
            Character key = alkeys.get(i);
            for(int j = 0;j<table.get(key);j++) sb.append(key);
        }
        return sb.toString();
    }
}
```


---
layout: post
title: 937. Reorder Log Files
categories: [leetcode]
---

#### QUESTION:

You have an array of `logs`.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric *identifier*.  Then, either:

- Each word after the identifier will consist only of lowercase letters, or;
- Each word after the identifier will consist only of digits.

We will call these two varieties of logs *letter-logs* and *digit-logs*.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

**Example 1:**

```
Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
```

**Note:**

1. `0 <= logs.length <= 100`
2. `3 <= logs[i].length <= 100`
3. `logs[i]` is guaranteed to have an identifier, and a word after the identifier.

#### EXPLANATION:

题目大意就是将字符串进行排序，目标是第二位是数字就直接按照原顺序，是字符则按照字典顺序

1.将字符串数组进行拆分，分成数字和字母，数字直接按照原顺序

2.对字母的list进行排序，依据就是每个字符的字典顺序

#### SOLUTION:

```java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
                ArrayList<String> letter = new ArrayList<>();
        ArrayList<String> digit = new ArrayList<>();
        String[] result = new String[logs.length];
        for(int i = 0;i<logs.length;i++){
            String log = logs[i];
            String[] splits = log.split(" ");
            if(splits[1].charAt(0)>=48 && splits[1].charAt(0)<=57)
                digit.add(log);
            else
                letter.add(log);

        }
        Collections.sort(letter, (o1, o2) -> {
            String[] s1 = o1.split(" ");
            String[] s2 = o2.split(" ");
            int len1 = s1.length;
            int len2 = s2.length;
            for (int i = 1; i < Math.min(len1, len2); i++) {
                if (!s1[i].equals(s2[i])) {
                    return s1[i].compareTo(s2[i]);
                }
            }
            return 0;
        });
        for(int i = 0;i<logs.length;i++){
            if(i<letter.size())
                result[i] = letter.get(i);
            else
                result[i] = digit.get(i-letter.size());
        }
        return result;
    }
}
```


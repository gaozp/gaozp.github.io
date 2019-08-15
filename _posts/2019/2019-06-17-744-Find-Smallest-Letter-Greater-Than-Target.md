---
layout: post
title: 744. Find Smallest Letter Greater Than Target
categories: [leetcode]
---
#### QUESTION:
Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.
#### EXPLANATION:

首先需要审题的是：1.已经是一个顺序数组了。2.并且是首尾相连的数组。
那么就可以这么考虑：既然首尾相连，那么我们就直接使用两个数组就好了。
所以简化成：
1.将所有字符放在数组中，同时在+26的位置也添加上
2.寻找target后的第一个字符
3.返回就可以

#### SOLUTION：
```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int[] index = new int[52];
        for(char c:letters){
            index[c-'a'] +=1;
            index[c-'a'+26] +=1;
        }
        int cursor = target-'a'+1;
        while (index[cursor]==0)
            cursor++;
        return (char)('a'+cursor%26);
    }
}
```
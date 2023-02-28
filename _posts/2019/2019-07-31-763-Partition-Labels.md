---
layout: post
title: 763. Partition Labels
categories: [leetcode]
---
#### QUESTION:
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
#### EXPLANATION:

题意：将这个字符拆分成尽可能多的段，每个字母只可能出现在一个段中。
可以采用画图的方式，画出相应的线段其实就很容易发现了  
1.首先算出每个字符最后出现的位置，这样可以知道每次这个字符出现，都必须要划线到那个位置  
2.进行循环，依次查看最后出现的位置，并标记最后的位置  
3.如果当前的位置就是最后的位置，因为需要分成尽可能多的位置，那么就可以将该位置进行切割  
4.最后再计算每一段的长度  

#### SOLUTION:
```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] count = new int[26];
        char[] chars = S.toCharArray();
        for(int i = 0;i<chars.length;i++) count[chars[i]-'a'] = i;
        ArrayList<Integer> index = new ArrayList<>();
        int last = count[chars[0]-'a'];
        for(int i = 0;i<chars.length;i++){
            last = Math.max(last,count[chars[i]-'a']);
            if(i == last) index.add(i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0;i<index.size();i++){
            if(i == 0) result.add(index.get(0)+1);
            else{
                result.add(index.get(i)-index.get(i-1));
            }
        }
        return result;
    }
}
```
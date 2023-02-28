---
layout: post
title: 1160. Find Words That Can Be Formed by Characters
categories: [leetcode]
---
#### QUESTION:
You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

 

Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: 
The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
Example 2:

Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: 
The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 

Note:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
All strings contain lowercase English letters only.
#### EXPLANATION:

这题就比较简单，一眼看过去就可以知道必须是需要记录chars的内容的，那么通过什么去记录，如果是通过hashset或者arraylist的话，在减少了之后还需要重新进行填充，那么就可以采用数组的形式，毕竟题目也直说了只包含小写字母。  
逻辑：  
1. 将chars的个数填充到数组中  
2. 遍历words，然后对单个元素进行-1操作，如果有字符没法减，那么就说明改字符串不合适  
3. 全部减完的则说明包含，将长度添加到result中  

#### SOLUTION:
```JAVA
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] template = new int[26];
        for(char c:chars.toCharArray()) template[c-'a']++;
        int result = 0;
        w: for(int i = 0;i<words.length;i++){
            int[] tmp = Arrays.copyOf(template, 26);
            for(char c:words[i].toCharArray()){
                if(tmp[c-'a']-1<0) continue w;
                else tmp[c-'a']--;
            }
            result+= words[i].length();
        }
        return result;
    }
}
```
---
layout: post
title: 953. Verifying an Alien Dictionary
categories: [leetcode]
---

#### QUESTION:

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different `order`. The `order` of the alphabet is some permutation of lowercase letters.

Given a sequence of `words` written in the alien language, and the `order` of the alphabet, return `true` if and only if the given `words` are sorted lexicographicaly in this alien language.

**Example 1:**

```
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
```

**Example 2:**

```
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
```

**Example 3:**

```
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
```

**Note:**

1. `1 <= words.length <= 100`
2. `1 <= words[i].length <= 20`
3. `order.length == 26`
4. All characters in `words[i]` and `order` are english lowercase letters.

#### EXPLANATION:

两种方式，明显第二种其实是更优雅的。

首先需要建立一个新的map来记录新的字符串的位置。map[字符]=位置

第二步再计算每个字符串是否是按照位置来的。map[需要确认的字符]得到新map的位置，然后比较两者的位置顺序。

#### SOLUTION:

```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        char[] orders = order.toCharArray();
        for(int i =1;i<words.length;i++){
            if(!isAlienSortedHelper(words[i-1],words[i],orders)) return false;
        }
        return true;
    }
    
    public static boolean isAlienSortedHelper(String A,String B,char[] order){
        char[] charsA = A.toCharArray();
        char[] charsB = B.toCharArray();
        int index = 0;
        while (index<charsA.length||index<charsB.length){
            char tmpA = ' ';
            char tmpB = ' ';
            if(index<=charsA.length-1)
                tmpA = charsA[index];
            if(index<=charsB.length-1)
                tmpB = charsB[index];
            if(tmpA==' '&&tmpB!=' '){
                return true;
            }else if(tmpA!=' '&&tmpB==' '){
                return false;
            }else{
                int indexA =-1;
                int indexB =-1;
                for(int i = 0;i<order.length;i++){
                    if(order[i]==tmpA)
                        indexA=i;
                    if(order[i]==tmpB)
                        indexB=i;
                    if(indexA!=-1&&indexB!=-1&&indexA<indexB) return true;
                    if(indexA!=-1&&indexB!=-1&&indexA>indexB) return false;
                    if(indexA!=-1&&indexB!=-1&&indexA==indexB) break;
                }
            }
            index++;
        }
        return true;
    }
}

class Solution {
    int[] mapping = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++) {
            mapping[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!small(words[i], words[i+1])) return false;
        }
        return true;
    }
    public boolean small(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; i++) {
            //这里无论是大于还是小于都能马上知道是不是正确sort，
            if (mapping[s1.charAt(i) - 'a'] != mapping[s2.charAt(i) - 'a']) {
                return mapping[s1.charAt(i) - 'a'] < mapping[s2.charAt(i) - 'a'];
            }
        }
        //排除两个单词前面prefix都一样，但是其中一个单词更加长
        return (n <= m);
    }
}

```


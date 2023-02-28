---
layout: post
title: 1408. String Matching in an Array
categories: [leetcode]
---
#### QUESTION:
Given an array of string words. Return all strings in words which is substring of another word in any order. 

String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].

 

__Example 1:__
```
Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
```
__Example 2:__
```
Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
```
__Example 3:__
```
Input: words = ["blue","green","bu"]
Output: []
```
 

__Constraints:__
```
1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
It's guaranteed that words[i] will be unique.
```
#### EXPLANATION:

easy的题目, 用两个for循环去进行匹配就可以得到对应的结果. 

#### SOLUTION:
```java
class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < words.length ; i++) {
            for(int j = 0 ; j < words.length; j++ ) {
                if (i != j) {
                    if (words[j].contains(words[i]) && !result.contains(words[i])) {
                        result.add(words[i]);
                    }
                }
            }
        }
        return result;
    }
}
```

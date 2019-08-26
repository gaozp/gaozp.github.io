---
layout: post
title: 1170. Compare Strings by Frequency of the Smallest Character
categories: [leetcode]
---
#### QUESTION:
Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:

Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 

Constraints:

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.

#### EXPLANATION:

这道题目需要计算出每个字符的最小字符的数目，然后再进行对比就可以。  
步骤如下：  
1.计算出queries的每个字符串的对应fs的值，并放在数组queriesresult中  
2.计算出words的每个字符串对应的fs的值，并放在数组wordsresult中  
3.对queriesresult进行遍历，同时对wordsresult进行遍历，如果words大于queries，那么就可以把count++  
4.将每个queriesresult的结果添加到数组中  

#### SOLUTION:
```JAVA
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queriesResult = new int[queries.length];
        int[] wordsResult = new int[words.length];
        for(int i = 0;i<queries.length;i++) queriesResult[i] = numSmallerByFrequencyHelper(queries[i]);
        for(int i = 0;i<words.length;i++) wordsResult[i] = numSmallerByFrequencyHelper(words[i]);
        ArrayList<Integer> indexs = new ArrayList<>();
        for(int i = 0;i<queriesResult.length;i++){
            int count = 0;
            for(int j = 0;j<wordsResult.length;j++){
                if(queriesResult[i]<wordsResult[j]) count++;
            }
            indexs.add(count);
        }
        int[] result = new int[indexs.size()];
        for(int i = 0;i<indexs.size();i++) result[i] = indexs.get(i);
        return result;
    }
    public static int numSmallerByFrequencyHelper(String strings) {
        char tmp = 'z' + 1;
        int count = 0;
        for (char c : strings.toCharArray()) {
            if (c < tmp) {
                tmp = c;
                count = 1;
            } else if (c == tmp) count++;
        }
        return count;
    }
}
```
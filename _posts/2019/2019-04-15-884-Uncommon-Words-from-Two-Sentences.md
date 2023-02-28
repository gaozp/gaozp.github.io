---
layout: post
title: 884. Uncommon Words from Two Sentences
categories: [leetcode]
---

#### QUESTION:

We are given two sentences `A` and `B`.  (A *sentence* is a string of space separated words.  Each *word* consists only of lowercase letters.)

A word is *uncommon* if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. 

You may return the list in any order.

**Example 1:**

```
Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
```

**Example 2:**

```
Input: A = "apple apple", B = "banana"
Output: ["banana"]
```

**Note:**

1. `0 <= A.length <= 200`
2. `0 <= B.length <= 200`
3. `A` and `B` both contain only spaces and lowercase letters.

#### EXPLANATION:

这题也没有什么特别简单的方法。

一看题目就知道需要使用hashmap来计算出现的数量了。

然后再查看一下出现的数量，只有1次的情况才能加入到最后的结果。

因为如果一个字符串出现了2次那么就有2种可能：1.在同一个句子中。2.在两个句子中。这两种情况都不行。

出现了3次就同样有两种可能：1.3个出现在1个句子中。2.1个出现在1个句子中，另外两个出现在另外两个句子中。这两种情况也不行。

继续往下演算，其实也就只有出现1次的情况可以。所以将他加入到结果中。

#### SOLUTION:

```JAVA
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        HashMap<String, Integer> mapA = uncommonFromSentencesHelper(A);
        HashMap<String, Integer> mapB = uncommonFromSentencesHelper(B);
        Iterator<Map.Entry<String, Integer>> iteratorA = mapA.entrySet().iterator();
        String result = "";
        while (iteratorA.hasNext()){
            Map.Entry<String, Integer> next = iteratorA.next();
            if(mapB.get(next.getKey())!=null || next.getValue()!=1) {
                iteratorA.remove();
                mapB.remove(next.getKey());
            }else if(next.getValue()==1) {
                result=result+ next.getKey()+" ";
            }
        }
        Iterator<Map.Entry<String, Integer>> iteratorB = mapB.entrySet().iterator();
        while (iteratorB.hasNext()){
            Map.Entry<String, Integer> next = iteratorB.next();
            if(mapA.get(next.getKey())!=null || next.getValue()!=1){
                iteratorB.remove();
            }else if(next.getValue()==1) {
                result=result+next.getKey()+" ";
            }
        }
        return  result.isEmpty()?new String[]{}:result.split(" ");
    }
    public static HashMap<String,Integer> uncommonFromSentencesHelper(String A){
        HashMap<String,Integer> result = new HashMap<>();
        String[] splits = A.split(" ");
        for(int i = 0;i<splits.length;i++){
            result.put(splits[i],result.getOrDefault(splits[i],0)+1);
        }
        return result;
    }
}
```


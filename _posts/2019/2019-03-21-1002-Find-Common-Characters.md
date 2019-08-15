---
layout: post
title: 1002. Find Common Characters
categories: [leetcode]
---

#### QUESTION:

Given an array `A` of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list **(including duplicates)**.  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.

You may return the answer in any order.

**Example 1:**

```
Input: ["bella","label","roller"]
Output: ["e","l","l"]
```

**Example 2:**

```
Input: ["cool","lock","cook"]
Output: ["c","o"] 
```

**Note:**

1. `1 <= A.length <= 100`
2. `1 <= A[i].length <= 100`
3. `A[i][j]` is a lowercase letter

#### EXPLANATION:

这道题目的意思就是找出每个字符中的相同的字符。

那么就需要计算出每个字符串中每个字符出现的次数，那么就自然而然的想到了map的数据结构。那么就容易想到。

首先是计算出第一个的所有字符出现的次数。

然后遍历整个数组，查看第二个，第三个。。。

将第一个数每个字符的个数和后面的进行相交，取最小值，就是最少出现的次数。

那么最后就可以得到结果了。

查看了一下最后的ac解，基本也是这个思路。但是别人使用的是数组，26位数组相当于a-z,那么数组的获取就会比hashmap的获取难度减少很多，所以时间复杂度降低了很多。最快的仅仅需要4ms。

#### SOLUTION:

```java
class Solution {
    public List<String> commonChars(String[] A) {
        ArrayList<String> result = new ArrayList<>();
        if(A.length==0) return result;
        HashMap<Character,Integer> first = commonCharsHelper(A[0]);
        for(int i = 1;i<A.length;i++){
            String nTmp = A[i];
            HashMap<Character,Integer> tmp = commonCharsHelper(nTmp);
            Iterator<Character> iterator = first.keySet().iterator();
            while (iterator.hasNext()){
                Character next = iterator.next();
                first.put(next,Math.min(first.get(next),tmp.getOrDefault(next,0)));
            }
        }

        Iterator<Map.Entry<Character, Integer>> iterator = first.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            if(next.getValue()!=0){
                for(int i =0;i<next.getValue();i++){
                    result.add(next.getKey().toString());
                }
            }
        }
        return result;
    }
    
    public static HashMap<Character,Integer> commonCharsHelper(String s){
        HashMap<Character,Integer> result = new HashMap<>();
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            char tmp = chars[i];
            result.put(tmp,result.getOrDefault(tmp,0)+1);
        }
        return result;
    }
}
```


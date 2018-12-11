---
layout: post
title: 893. Groups of Special-Equivalent Strings
---

#### QUESTION:

You are given an array `A` of strings.

Two strings `S` and `T` are *special-equivalent* if after any number of *moves*, S == T.

A *move* consists of choosing two indices `i` and `j` with `i % 2 == j % 2`, and swapping `S[i]` with `S[j]`.

Now, a *group of special-equivalent strings from A* is a non-empty subset S of `A` such that any string not in S is not special-equivalent with any string in S.

Return the number of groups of special-equivalent strings from `A`.

**Example 1:**

```
Input: ["a","b","c","a","c","c"]
Output: 3
Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]
```

**Example 2:**

```
Input: ["aa","bb","ab","ba"]
Output: 4
Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
```

**Example 3:**

```
Input: ["abc","acb","bac","bca","cab","cba"]
Output: 3
Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]
```

**Example 4:**

```
Input: ["abcd","cdab","adcb","cbad"]
Output: 1
Explanation: 1 group ["abcd","cdab","adcb","cbad"]
```

**Note:**

- `1 <= A.length <= 1000`
- `1 <= A[i].length <= 20`
- All `A[i]` have the same length.
- All `A[i]` consist of only lowercase letters

#### EXPLANATION:

题目比较难以理解，题目的意思就是，一组string里面每个string有相同的长度，那么替换其中的偶数位，或者奇数位，如果能够拼凑出相同的字符，那么他们就算一组。

理解了题目的意思之后就可以将问题简化成，计算每个string的奇数位和偶数位置每个字符的个数。

这里就需要map来进行组合，同时，为了区分奇偶，可以选择变成两个map，或者由于题目指定了，当前的字符串中只存在小写字母，那么我们就可以用大写字母来标记奇数位置。

最后将获得的结果进行保存，因为相同的组合相当于一个组，那么我们只能保存一个，这里就需要用到set的数据结构。

那么代码如下：

#### SOLUTION:

```java
class Solution {
    public int numSpecialEquivGroups(String[] A) {
        Set<Map<Integer,Integer>> result = new HashSet<>();
        for(int i = 0;i<A.length;i++){
            char[] chars = A[i].toCharArray();
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int j = 0;j<chars.length;j+=2){
                map.put(chars[j]+0,map.get(chars[j]+0)==null?1:map.get(chars[j]+0)+1);
            }
            for(int j = 1;j<chars.length;j+=2){
                map.put(chars[j]-32,map.get(chars[j]-32)==null?1:map.get(chars[j]-32)+1);
            }
            result.add(map);
        }
        return result.size();
    }
}
```


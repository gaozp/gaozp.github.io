---
layout: post
title: 821. Shortest Distance to a Character
categories: [leetcode]
---

#### QUESTION:

Given a string `S` and a character `C`, return an array of integers representing the shortest distance from the character `C` in the string.

**Example 1:**

```
Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
```

 

**Note:**

1. `S` string length is in `[1, 10000].`
2. `C` is a single character, and guaranteed to be in string `S`.
3. All letters in `S` and `C` are lowercase.

#### EXPLANATION:

我刚开始拿到题目的时候想的就很简单。

1.遍历数组，如果遇到c了，那么就向两边扩散。从0开始累加

2.如果碰到距离另外一个反而更近的情况，那么就说明这次循环可以结束了。

#### SOLUTION:

```java
class Solution {
    public int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
        Arrays.fill(result,Integer.MAX_VALUE);
        for(int i = 0;i<S.length();i++){
            if(S.charAt(i)==C){
                for(int j=i;j>=0;j--){
                    if(result[j]>=i-j){
                        result[j] = i-j;
                    }else break;
                }
                for(int j=i;j<S.length();j++){
                    if(result[j]>=j-i){
                        result[j] = j-i;
                    }else break;
                }
            }
        }
        return result;
    }
}
```


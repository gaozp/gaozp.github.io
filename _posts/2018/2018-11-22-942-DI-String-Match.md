---
layout: post
title: 942. DI String Match
---

#### QUESTION:

Given a string `S` that **only** contains "I" (increase) or "D" (decrease), let `N = S.length`.

Return **any** permutation `A` of `[0, 1, ..., N]` such that for all `i = 0, ..., N-1`:

- If `S[i] == "I"`, then `A[i] < A[i+1]`
- If `S[i] == "D"`, then `A[i] > A[i+1]`

**Example 1:**

```
Input: "IDID"
Output: [0,4,1,3,2]
```

**Example 2:**

```
Input: "III"
Output: [0,1,2,3]
```

**Example 3:**

```
Input: "DDI"
Output: [3,2,0,1]
```

**Note:**

1. `1 <= S.length <= 10000`
2. `S` only contains characters `"I"` or `"D"`.

#### EXPLANATION:

从例子中的一直升序可以看到，

1，2，3都是从小到大往上排的

而降序也是如此

那我们就可以推断出：升序需要从小的那边开始，而降序需要从另外一边开始。

#### SOLUTION:

```java
class Solution {
    public int[] diStringMatch(String S) {
        int start = 0;
        int end = S.length();
        int[] result = new int[S.length()+1];
        for(int i = 0;i<S.length();i++){
            char c = S.charAt(i);
            switch (c){
                case 'I':
                    result[i] = start;
                    start++;
                    break;
                case 'D':
                    result[i] = end;
                    end--;
                    break;
            }
        }
        result[result.length-1] = S.charAt(S.length()-1)=='I'?start:end;
        return result;
    }
}
```


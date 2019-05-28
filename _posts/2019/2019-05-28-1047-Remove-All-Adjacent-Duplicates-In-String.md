---
layout: post
title: 1047. Remove All Adjacent Duplicates In String
---

#### QUESTION:

Given a string `S` of lowercase letters, a *duplicate removal*consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

**Example 1:**

```
Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca". 
```

**Note:**

1. `1 <= S.length <= 20000`
2. `S` consists only of English lowercase letters.

#### QUESTION:

就是需要不停的找出重复的相邻的两个字符。但是新的字符串也是需要继续寻找的。那么怎么样才能算是停止呢。

就是从开始找到结束都没有找到。那么就可以使用双循环嵌套。

第一重无限循环是为了表示我们也不知道字符串可以进行裁剪多少次。

第二重循环是为了确认我们已经能够找到最后而没有发现结果。

具体看代码。

#### SOLUTION:

```java
class Solution {
    public String removeDuplicates(String S) {
        w:while (true){
            i:for (int i = 0;i<S.length()-1;i++){
                if(S.charAt(i)==S.charAt(i+1)){
                    if(i+2<=S.length()-1)
                        S=S.substring(0,i)+S.substring(i+2,S.length());
                    else
                        S=S.substring(0,i);
                    continue w;
                }
            }
            break w;
        }
        return S;
    }
}
```


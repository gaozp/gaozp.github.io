---
layout: post
title: 784. Letter Case Permutation
categories: [leetcode]
---

#### QUESTION:

Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

```
Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]

```

**Note:**

- `S` will be a string with length at most `12`.
- `S` will consist only of letters or digits.

#### EXPLANATION:

其实这是一个典型的回溯型的算法。

回溯型算法有一个公式：

总结一下，伪代码就是：

```java
void dfs(层数){
if(条件){
    输出；
}
else{
    左子树的处理；
    dfs(层数+1)；
    右子树的处理；
    dfs(层数+1)；
}
}

```

对的，就是这样的。所以按照这个公式套上去就可以。

#### SOLUTION:

```JAVA
class Solution {
    public ArrayList<String> letterCaseResult = new ArrayList<>();
    public List<String> letterCasePermutation(String S) {
        char[] chars = S.toCharArray();
        letterCasePermutationHelper("",chars,0);
        return letterCaseResult;
    }
    
    public void letterCasePermutationHelper(String s,char[] cs,int index) {
        int tmp = index;
        if(index==cs.length){
            letterCaseResult.add(s);
            return;
        }else{
            if(cs[index]<65||(cs[index]>90)&&(cs[index]<97)||cs[index]>122){
                s+=cs[index];
                letterCasePermutationHelper(s,cs,tmp+1);
            }else{
                String stmp = s+ cs[index];
                letterCasePermutationHelper(stmp,cs,tmp+1);
                String stmp2;
                if(cs[index]>96)
                    stmp2 =s+(char)(cs[index]-32);
                else
                    stmp2 =s+(char)(cs[index]+32);
                letterCasePermutationHelper(stmp2,cs,tmp+1);
            }

        }
    }
}
```


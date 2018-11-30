---
layout: post
title: 830. Positions of Large Groups
---

#### QUESTION:

In a string `S` of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like `S = "abbxxxxzyy"` has the groups `"a"`, `"bb"`, `"xxxx"`, `"z"` and `"yy"`.

Call a group *large* if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.

**Example 1:**

```
Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
```

**Example 2:**

```
Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
```

**Example 3:**

```
Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
```

**Note:**  `1 <= S.length <= 1000`

#### EXPLANATION:

只要把逻辑理清楚了就行：

1.首先把第一位拿出来 作为pre，同时记录位置。

2.将后面一位与前面比较，如果不同则进行位置比较，相同则重复该步骤

3.如果位置相隔3位，那么就添加在结果中。不超过3位就修改pre和标记位置

4.最后一个位置需要单独处理。因为最后不会有比较

#### SOLUTION:

```java
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        char[] chars = S.toCharArray();
        char pre = chars[0];
        int index = 0;
        ArrayList result = new ArrayList();
        for(int i = 1;i<chars.length;i++){
            if(chars[i]!=pre){
                if(i-index>=3){
                    ArrayList tmp = new ArrayList<Integer>();
                    tmp.add(index);
                    tmp.add(i-1);
                    result.add(tmp);
                }
                pre = chars[i];
                index=i;
            }
        }
        if(chars.length-index>=3){
            ArrayList tmp = new ArrayList<Integer>();
            tmp.add(index);
            tmp.add(chars.length-1);
            result.add(tmp);
        }
        return result;
    }
}
```


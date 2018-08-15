---
layout: post
title: 38. Count and Say
---

#### QUESTION:

The count-and-say sequence is the sequence of integers beginning as follows:
`1, 11, 21, 1211, 111221, ...`

`1` is read off as `"one 1"` or `11`.
`11` is read off as `"two 1s"` or `21`.
`21` is read off as `"one 2`, then `one 1"` or `1211`.

Given an integer *n*, generate the *n*th sequence.

Note: The sequence of integers will be represented as a string.

#### EXPLANATION:

其实只要理解了题目的意思也是很好解决的。

1.当是1的时候就是1，2的时候是1个1，3的时候是2个1，4的时候1个2和1个1

2.所以直接选择循环，循环到n，数出所有的数就可

#### SOLUTION:

```java
public class Solution {
    public String countAndSay(int n) {
        int i = 2;
        String result = "1";
        StringBuilder builder;
        while (i<=n){
            builder = new StringBuilder();
            char[] chars = result.toCharArray();
            char pre = chars[0];int count =0;
            for(char ch:chars){
                if(ch==pre){
                    count++;
                }else{
                    builder.append(count);
                    builder.append(pre);
                    pre = ch;
                    count = 1;
                }
            }
            i++;
            builder.append(count);
            builder.append(pre);
            result = builder.toString();
        }
        return result;
    }
}
```


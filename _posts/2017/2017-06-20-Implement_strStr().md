---
layout: post
title: 28. Implement strStr()
categories: [leetcode]
---

#### QUESTION:

Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

#### EXPLANATION:

其实倒是可以直接用系统api的但是还是想想自己实现好了。

1.获取到相同的首字母

2.然后依次判断是否是相同的。

3.判断到结尾的时候判断是否是完整，是就返回index，否则继续下一位。



其实需要注意的地方是添加注释的地方了。

#### SOLUTION:

```java
public class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        int pre = 0;
        int after = 0;
        while (pre<=haystack.length()-needle.length()){//这个地方，因为index+needle.length()的值是不能大于总和的，所以只需要判断到特定的index就可以。否则会出现time limit的错误
            if(haystack.charAt(pre)==needle.charAt(0)){
                while (after<needle.length()){
                    if(haystack.charAt(pre+after)==needle.charAt(after)) after++;
                    else break;
                }
                if (after == needle.length()) return pre;
                else  after = 0;
            }
            pre++;
        }
        return -1;
    }
}

public class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        char first = needle.charAt(0);
        int max = haystack.length()-needle.length();

        for(int i = 0;i<=max;i++){
            if(haystack.charAt(i)!=first)
                while (++i<=max && haystack.charAt(i)!=first);

            if(i<=max){
                int j = i+1;
                int end = j+needle.length()-1;
                for(int k = 1;j<end&&haystack.charAt(j)==needle.charAt(k);j++,k++);

                if(j==end)
                    return i;
            }
        }
        return -1;
    }
}
```


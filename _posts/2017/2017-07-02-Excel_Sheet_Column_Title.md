---
layout: post
title: 168. Excel Sheet Column Title
---

#### QUESTION:

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

```
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
```

#### EXPLANATION:

其实是和算数的进制是一样的，使用同样的算法就是可以的了。

但是其实需要注意的是从1开始的，所以每次都需要减去1才能算出正确的数字。

#### SOLUTION:

```JAVA
public class Solution {
    public String convertToTitle(int n) {
       String[] map = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder sb = new StringBuilder();
        n-- ;
        int index = -1;
        int remain = 0;
        while (index != 0) {
            index = n/26;
            remain = n%26;
            sb.insert(0, map[remain]);
            n = n/26-1;
        }
        return sb.toString();
    }
}
```


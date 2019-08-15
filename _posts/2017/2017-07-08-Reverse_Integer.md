---
layout: post
title: 7. Reverse Integer
categories: [leetcode]
---

#### QUESTION:

Reverse digits of an integer.

**Example1:** x = 123, return 321
**Example2:** x = -123, return -321

**Note:**
The input is assumed to be a 32-bit signed integer. Your function should **return 0 when the reversed integer overflows**.

#### EXPLANATION:

其实思路都是挺简单的，就是把每一位都拿出来重新组装即可。

#### SOLUTION:

```JAVA
public class Solution {
    public int reverse(int x) {
        StringBuilder sb = new StringBuilder();
        long remain = 0;
        long index = -1;
        boolean flag = false;
        long newValue = (long)x;
        if(Math.abs(newValue)!=x){
            flag = true;
            newValue = Math.abs((long)x);
        }
        while (index != 0){
            index = newValue/10;
            remain = newValue%10;
            sb.append(remain);
            newValue = newValue/10;
        }
        if(flag)
            sb.insert(0,"-");
        long value = Long.parseLong(sb.toString());
        if(value>Integer.MAX_VALUE || value<Integer.MIN_VALUE)
            return 0;
        return (int)value;
    }
}
```

```python
class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        if(len(str(x))==1) : return x
        reverse = ""
        flag = True if x>0 else False
        index = 0 if flag else 1
        for i in range(index ,len(str(x)),1):
            reverse = str(x)[i]+reverse
        result = long(reverse) if flag else -long(reverse)
        if result > 2147483647 or result < -2147483648 :
            return 0
        return int(result)

```


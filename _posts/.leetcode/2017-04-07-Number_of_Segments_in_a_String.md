---
layout: post
title: 434. Number of Segments in a String
---

#### QUESTION:

Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any **non-printable** characters.

**Example:**

```
Input: "Hello, my name is John"
Output: 5
```

#### EXPLANATION:

其实我一开始使用的是第一种方法，后来看了discuss才发现原来第二种才是更简洁的，第一种比第二种多了一个for循环，但是却是第一个使用的时间更少。

所以，效率和美观选择哪个，也很难，如果计算机性能已经达到了，那么我觉得还是选择第二种美观的方式会更加好一点。如果没有达到的话，也许写一点复杂的代码提高性能也是一个关键。

#### SOLUTION:

```java
//方法一
public class Solution {
    public int countSegments(String s) {
        String[] split = s.split(" +");
        int count = 0;
        for(String str: split){
            if(str.equals(""))
                count++;
        }
        return split.length-count;
    }
}

//方法二
public static int CountSegments(String s) {
        return ("x "+s).split(" +").length-1;
    }


```


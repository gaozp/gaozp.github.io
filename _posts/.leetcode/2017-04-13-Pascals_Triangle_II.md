---
layout: post
title: 119. Pascal's Triangle II
---

#### QUESTION:

Given an index *k*, return the *k*th row of the Pascal's triangle.

For example, given *k* = 3,
Return `[1,3,3,1]`.

**Note:**
Could you optimize your algorithm to use only *O*(*k*) extra space?

#### EXPLANATION:

本来我一开始也是采用的循环和递归的方式进行的。但是后来在百科上发现了这个算法。我就照着百科的方式写了一下，0ms，打败了99.9%的提交，简直可怕。

但是比较不爽的一点就是s会超过int的最大值，所以需要使用double类型，最后再进行强转。

#### SOLUTION:

```java
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<>();
        double s = 1;
        for(int i = 0;i<=rowIndex;i++){
            if(i==0||i==rowIndex){
                result.add(1);
                continue;
            }
            s = (rowIndex-i+1)*s/i;
            result.add((int)s);
        }
        return result;
    }
}
```


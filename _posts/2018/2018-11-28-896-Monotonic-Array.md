---
layout: post
title: 896. Monotonic Array
---

#### QUESTION:

An array is *monotonic* if it is either monotone increasing or monotone decreasing.

An array `A` is monotone increasing if for all `i <= j`, `A[i] <= A[j]`.  An array `A` is monotone decreasing if for all `i <= j`, `A[i] >= A[j]`.

Return `true` if and only if the given array `A` is monotonic.

**Example 1:**

```
Input: [1,2,2,3]
Output: true
```

**Example 2:**

```
Input: [6,5,4,4]
Output: true
```

**Example 3:**

```
Input: [1,3,2]
Output: false
```

**Example 4:**

```
Input: [1,2,4,5]
Output: true
```

**Example 5:**

```
Input: [1,1,1]
Output: true
```

**Note:**

1. `1 <= A.length <= 50000`
2. `-100000 <= A[i] <= 100000`

#### EXPLANATION:

首先我们可以考虑到数组具体是升序的还是降序的，还有平等的，所以boolean类型的值不太合适，选用int类型的值来表示升降序。

1.如果一直是升序，出现降序就直接返回false

2.如果一直是降序，出现升序就直接返回false

那么代码就出来了。

#### SOLUTION:

```java
class Solution {
    public boolean isMonotonic(int[] A) {
        if(A.length<=0)return true;
        int order = 0;
        int pre = A[0];
        for(int i = 1;i<A.length;i++){
            int tmp = A[i];
            if(tmp>pre && (order==0 || order == 1)){
                order = 1;
                pre = tmp;
                continue;
            }else if(tmp<pre && (order==0 || order==-1)){
                order=-1;
                pre=tmp;
                continue;
            }else if(tmp == pre){
                pre=tmp;
                continue;
            }
            return false;
        }
        return true;
    }
}
```




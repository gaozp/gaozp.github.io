---
layout: post
title: 976. Largest Perimeter Triangle
categories: [leetcode]
---

#### QUESTION:

Given an array `A` of positive lengths, return the largest perimeter of a triangle with **non-zero area**, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return `0`.

**Example 1:**

```
Input: [2,1,2]
Output: 5
```

**Example 2:**

```
Input: [1,2,1]
Output: 0
```

**Example 3:**

```
Input: [3,2,3,4]
Output: 10
```

**Example 4:**

```
Input: [3,6,2,3]
Output: 8
```

**Note:**

1. `3 <= A.length <= 10000`
2. `1 <= A[i] <= 10^6`

#### EXPLANATION:

首先需要确认三角形能形成的基本条件：

1.两边之和大于第三边。

2.两边之差小于第三边。

那么就很简单的进行遍历就可以了。但是需要注意的一个点是：需要的是最大值。那么我们就可以从最末尾开始遍历。

符合条件的第一个，那么就是最大值了。

#### SOLUTION:

```java
class Solution {
    public int largestPerimeter(int[] A) {
        int result = 0;
        Arrays.sort(A);
        w: for(int i = A.length-1;i>=0;i--){
            m:for(int j = i-1;j>=0;j--){
                i:for(int m = j-1;m>=0;m--){
                    int a = A[i];
                    int b = A[j];
                    int c = A[m];
                    if(a>=b+c) break m;
                    if(largestPerimeterHelper(a,b,c)){
                        result = Math.max(result,a+b+c);
                        break w;
                    }
                }
            }
        }
        return result;
    }
    
    
    public static boolean largestPerimeterHelper(int a,int b,int c){
        if( (a+b>c&&Math.abs(a-b)<c) || (a+c>b && Math.abs(a-c)<b) || (b+c>a||Math.abs(b-c)<a)  )
            return true;
        else return false;
    }
}
```








---
layout: post
title: 1005. Maximize Sum Of Array After K Negations
---

#### QUESTION:

Given an array `A` of integers, we **must** modify the array in the following way: we choose an `i` and replace `A[i]` with `-A[i]`, and we repeat this process `K` times in total.  (We may choose the same index `i` multiple times.)

Return the largest possible sum of the array after modifying it in this way.

**Example 1:**

```
Input: A = [4,2,3], K = 1
Output: 5
Explanation: Choose indices (1,) and A becomes [4,-2,3].
```

**Example 2:**

```
Input: A = [3,-1,0,2], K = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
```

**Example 3:**

```
Input: A = [2,-3,-1,5,-4], K = 2
Output: 13
Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
```

**Note:**

1. `1 <= A.length <= 10000`
2. `1 <= K <= 10000`
3. `-100 <= A[i] <= 100`

#### EXPLANATION:

我还是使用的是比较蠢的方法。简单来说就是永远旋转最小的值。

1.首先肯定是需要将所有的负数转正数，从最小的负数开始

2.如果负数的个数大于k，那么说明已经尽力了

3.如果负数的个数小于k，那么说明还需要进行正数的转换，那么也是转换最小的值。

同时，这次学会了一个数据结构：priorityqueue。

#### SOLUTION:

```java
class Solution {
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int index = 0;
        while (index<A.length && K>0){
            if(A[index]<0) {
                A[index] = Math.abs(A[index]);
                K--;
                index++;
            }else break;
        }
        if(K==0) return IntStream.of(A).sum();
        Arrays.sort(A);
        int time = K%2;
        if(time==1) A[0] = -A[0];
        return IntStream.of(A).sum();
    }
}



class Solution {
    public int largestSumAfterKNegations(int[] A, int K) {
		PriorityQueue<Integer> p=new PriorityQueue<Integer>();
		int totalSum=0;
		for(int a:A)
		{
			p.add(a);
			totalSum+=a;
		}
		
		for(int i=0;i<K&&!p.isEmpty();i++)
		{
			int t=p.poll();
			totalSum-=t;
			totalSum+=-t;
			p.add(-t);
		}
		return totalSum;
        
    }
}
```


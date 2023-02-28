---
layout: post
title: 985. Sum of Even Numbers After Queries
categories: [leetcode]
---

#### QUESTION:

We have an array `A` of integers, and an array `queries` of queries.

For the `i`-th query `val = queries[i][0], index = queries[i][1]`, we add val to `A[index]`.  Then, the answer to the `i`-th query is the sum of the even values of `A`.

*(Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)*

Return the answer to all queries.  Your `answer` array should have `answer[i]` as the answer to the `i`-th query.

**Example 1:**

```
Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
Output: [8,6,2,4]
Explanation: 
At the beginning, the array is [1,2,3,4].
After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
```

**Note:**

1. `1 <= A.length <= 10000`
2. `-10000 <= A[i] <= 10000`
3. `1 <= queries.length <= 10000`
4. `-10000 <= queries[i][0] <= 10000`
5. `0 <= queries[i][1] < A.length`

#### EXPLANATION:

题目乍看起来挺唬人的，其实理解了逻辑就很简单了。

主要分成四个可能

1.本来是奇数，加上一个偶数，结果还是奇数——不需要改变结果，保持之前的结果。

2.本来是奇数，加上一个奇数，变成了偶数——需要将之前的结果加上这个偶数的结果。

3.本来是偶数，加上一个奇数，变成了奇数——将之前的结果减去这个偶数。

4.本来是偶数，加上一个偶数，变成了偶数——将之前的结果减去本来的偶数，加上后来的偶数，也就是最后的偶数。

但是还是需要记住，每次都得保存当前值。同时需要考虑第一个数的情况。

可以优化的地方就是将第一个数单独算出来，这样就不用每次都进行是否是0的判断了。

#### SOLUTION:

```JAVA
    public static int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[A.length];
        int sum = 0;
        for(int i =0;i<A.length;i++){
            if(A[i]%2==0) sum+= A[i];
        }
        for(int i=0;i<queries.length;i++){
            int value = queries[i][0];
            int index = queries[i][1];
            int tmp = A[index]+value;
            if(tmp%2==0 && A[index]%2!=0) {
                if(i==0) result[i]=sum+tmp;
                else result[i] = result[i-1]+tmp;
            }else if(A[index]%2==0 && tmp%2!=0){
                if(i==0) result[i] = sum-A[index];
                else result[i] = result[i-1]-A[index];
            }else if(A[index]%2==0 && tmp%2==0){
                if(i==0) result[i] = sum-A[index]+tmp;
                else  result[i] = result[i-1]-A[index]+tmp;
            }else {
                if(i==0) result[0] = sum;
                else result[i] = result[i-1];
            }
            A[index] = tmp;
        }
        return result;
    }
```


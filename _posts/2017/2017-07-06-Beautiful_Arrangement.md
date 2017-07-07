---
layout: post
title: 526. Beautiful Arrangement
---

#### QUESTION:

Suppose you have **N** integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these **N** numbers successfully if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:

1. The number at the ith position is divisible by **i**.
2. **i** is divisible by the number at the ith position.

Now given N, how many beautiful arrangements can you construct?

**Example 1:**

```
Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

```

**Note:**

1. **N** is a positive integer and will not exceed 15.

#### EXPLANATION:

回溯算法的问题，具体的回溯算法定义可以参考[这个百科](https://en.wikipedia.org/wiki/Backtracking)，里面有一个通用代码块：

```
procedure bt(c)
  if reject(P,c) then return
  if accept(P,c) then output(P,c)
  s ← first(P,c)
  while s ≠ Λ do
    bt(s)
    s ← next(P,s)
```

就是回溯算法的基本使用方法。

一个一个位置的确定数字，如果满足的情况下，那么就继续确定下一个位置。直到超过了位置总数为止。

我们需要一个count来计算符合条件的解，一个list来记录已经使用的数字，这样就可以得到一个valid的方法。然后进行递归循环，得到最终的解的个数。

注意注释的地方，如果这个循环结束了，我们需要将之前添加恢复到未添加状态。

#### SOLUTION:

```JAVA
public class Solution {
    int count = 0;
    public ArrayList<Integer> countArrangementList = new ArrayList<>();
    public int countArrangement(int N) {
        for(int i = 1;i<=N;i++){
            countArrangementList.clear();
            if(isValid(1,i)){
                countArrangementList.add(i);
                countArrangementHelper(N,2);
            }
        }
        return count;
    }
    public void countArrangementHelper(int N,int index){
        if(index>N){
            count++;
            return;
        }
        for(int i = 1;i<=N;i++){
            if(isValid(index,i)){
                countArrangementList.add(i);
                countArrangementHelper(N,index+1);
                countArrangementList.remove(countArrangementList.size()-1);//在添加了之后，需要将添加的恢复到原来状态。
            }
        }
    }
    public boolean isValid(int index,int N){
        if((index%N==0 || N%index == 0) && !countArrangementList.contains(N))
            return true;
        return false;
    }
}
```


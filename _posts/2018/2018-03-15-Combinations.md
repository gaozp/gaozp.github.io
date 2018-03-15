---
layout: post
title: 77. Combinations
---

#### QUESTION:

Given two integers *n* and *k*, return all possible combinations of *k* numbers out of 1 ... *n*.

For example,
If *n* = 4 and *k* = 2, a solution is:

```
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

#### EXPLANATION:

这个依然是一个backtracking的问题，和之前的其实很一样

backtracking的公式是

if(someconditions)

​	result add 

else

​	node.left

​	node.right

所以这个算法题就显而易见了，同时需要注意的是，这个循环只能往后进行循环，而不能一直重复循环。

#### SOLUTION:

```JAVA
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        combineHelper(result,subset,n,k,1);
        return result;
    }
    public void combineHelper(List<List<Integer>> result,List<Integer> subset,int n,int k,int start){
        if(subset.size()==k){
            result.add(new ArrayList<>(subset));
        }else{
            for(int i = start;i<=n;i++){
                subset.add(i);
                combineHelper(result,subset,n,k,i+1);
                subset.remove(subset.size()-1);
            }
        }
    }
}
```


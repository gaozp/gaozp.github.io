---
layout:post
title:46. Permutations
---

#### QUESTION:

Given a collection of **distinct** numbers, return all possible permutations.

For example,
`[1,2,3]` have the following permutations:

```
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

#### EXPLANATION:

最近一直在写backtracking的问题，这个问题也是典型的backtracking。

判断每一个node可以存在的node，然后重复这一步骤，直到用完所有的数字就可以。

#### SOLUTION:

```JAVA
class Solution {
    List<List<Integer>> permuteResult = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            integers.add(nums[i]);
        }
        permuteHelper(integers,tmp);
        return permuteResult;
    }
    public void permuteHelper(List<Integer> list,List<Integer> tmp){
        if(list.size()==0){
            permuteResult.add(new ArrayList<>(tmp));
        }else{
            for(int i = 0;i<list.size();i++){
                Integer remove = list.remove(i);
                tmp.add(remove);
                permuteHelper(list,tmp);
                tmp.remove(tmp.size()-1);
                list.add(i,remove);
            }
        }
    }
}
```


---
layout: post
title: 40. Combination Sum II
---

#### QUESTION:

Given a collection of candidate numbers (**C**) and a target number (**T**), find all unique combinations in **C** where the candidate numbers sums to **T**.

Each number in **C** may only be used **once** in the combination.

**Note:**

- All numbers (including target) will be positive integers.
- The solution set must not contain duplicate combinations.

For example, given candidate set `[10, 1, 2, 7, 6, 1, 5]` and target `8`, 
A solution set is: 

```
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

#### EXPLANATION:

也是backtracking的问题，首先是两点：

1.每个数字只可以用一次，那么我们只能从当前位置往后找

2.结果中不能重复，那么我们可以先排序，如果那种和前一个一样的，我们就可以认为是重复的了。

#### SOLUTION:

```JAVA
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if(candidates ==null || candidates.length==0) return result;
        Arrays.sort(candidates);
        combinationSum2Helper(candidates,target,subset,result,0);
        return result;
    }
    
    public void combinationSum2Helper(int[] nums,int target,List<Integer> subset,List<List<Integer>> result,int start){
        if(target==0){
            result.add(new ArrayList<>(subset));
        }else if(target>0){
            for(int i = start;i<nums.length;i++){
                if(i>start && nums[i]==nums[i-1]) continue;
                subset.add(nums[i]);
                combinationSum2Helper(nums,target-nums[i],subset,result,i+1);
                subset.remove(subset.size()-1);
            }
        }
    }
}
```


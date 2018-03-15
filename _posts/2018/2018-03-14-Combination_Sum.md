---
layout: post
title: 39. Combination Sum
---

#### QUESTION:

Given a **set** of candidate numbers (**C**) **(without duplicates)** and a target number (**T**), find all unique combinations in **C** where the candidate numbers sums to **T**.

The **same** repeated number may be chosen from **C** unlimited number of times.

**Note:**

- All numbers (including target) will be positive integers.
- The solution set must not contain duplicate combinations.

For example, given candidate set `[2, 3, 6, 7]` and target `7`, 
A solution set is: 

```
[
  [7],
  [2, 2, 3]
]

```

#### EXPLANATION:

和昨天的一样，也是backtracking的问题，但是这次需要注意的是，这次并不是从当前的位置向后移，而是最少也是在当前位置。

所以每次需要将当前的index传入到下一次的循环中就可以。

#### SOLUTION:

```JAVA
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if(candidates ==null || candidates.length==0) return result;
        combinationSumHelper(candidates,target,subset,result,0);
        return result;
    }
    public void combinationSumHelper(int[] nums,int target,List<Integer> subset,List<List<Integer>> result,int start){
        if(target==0){
            result.add(new ArrayList<>(subset));
        }else if(target>0){
            for(int i = start;i<nums.length;i++){
                subset.add(nums[i]);
                combinationSumHelper(nums,target-nums[i],subset,result,i);
                subset.remove(subset.size()-1);
            }
        }
    }
}
```


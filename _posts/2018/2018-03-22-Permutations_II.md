---
layout: post
title: 47. Permutations II
---

#### QUESTION:

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
`[1,1,2]` have the following unique permutations:

```
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

#### EXPLANATION:

首先是我们计算那种可以有重复结果的，那么就很简单了。你需要一个标记位置来标记当前位置是否是已经使用的了。

那再来计算不可以有重复结果的。

1.首先进行排序

2.排序后进行判断，如果和之前一个相同，并且前一个也被使用了。



hashset的方式也可以直接去重，但是hashset的压栈其实是需要进行hash值的比对的，其实是一个挺耗时的操作。

#### SOLUTION:

```JAVA
//这个是使用了hashset来进行排重的操作
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<>();
        List<Integer> subset = new ArrayList<>();
        permuteUniqueHelper(result,subset,nums,new boolean[nums.length]);
        List<List<Integer>> nResult = new ArrayList<>();
        result.forEach(a->nResult.add(a));
        return nResult;
    }
    public void permuteUniqueHelper(HashSet<List<Integer>> result,List<Integer> subset,int[] nums,boolean[] bools){
        if(subset.size()==nums.length){
            result.add(new ArrayList<>(subset));
        }else {
            for(int i = 0;i<nums.length;i++){
                if(bools[i]) continue;
                subset.add(nums[i]);
                bools[i] = true;
                permuteUniqueHelper(result,subset,nums,bools);
                subset.remove(subset.size()-1);
                bools[i]=false;
            }
        }
    }
}
//这个是采用先排序，然后计算当前位置是否是已经重复的进行判断的。
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(nums, ans, new ArrayList<>(), new boolean[nums.length]);
        return ans;
    }
    public void backtracking(int[] nums, List<List<Integer>> ans, List<Integer> now, boolean[] used) {
        if (now.size() == nums.length)
            ans.add(new ArrayList<> (now));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1])
                    continue;
                used[i] = true;
                now.add(nums[i]);
                backtracking(nums, ans, now, used);
                used[i] = false;
                now.remove(now.size() - 1);
            }  
        }
    }
}
```


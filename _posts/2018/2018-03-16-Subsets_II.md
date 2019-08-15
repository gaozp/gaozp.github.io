---
layout: post
title: 90. Subsets II
categories: [leetcode]
---

#### QUESTION:

Given a collection of integers that might contain duplicates, **nums**, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

For example,
If **nums** = `[1,2,2]`, a solution is:

```
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

#### EXPLANATION:

之前有解过那种不重复数字的问题，那么这个时候就可以解决重复的问题了。

如何解决重复呢。首先就是去重，然后再判断如果和前一个一样，那么就不再进行操作了。

#### SOLUTION:

```JAVA
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        dfsWithDupHelper(nums, 0, subset, result);
        return result;
    }
    public static void dfsWithDupHelper(int[] nums, int start, List<Integer> subset, List<List<Integer>> result){
        result.add(new ArrayList<Integer>(subset));

        for (int i = start; i < nums.length; i++) {
            if(i>start && nums[i]==nums[i-1]) continue;
            subset.add(nums[i]);
            dfsWithDupHelper(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }

}
```


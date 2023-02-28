---
layout: post
title: 347. Top K Frequent Elements
categories: [leetcode]
---

#### QUESTION:

Given a non-empty array of integers, return the **k** most frequent elements.

For example,
Given `[1,1,1,2,2,3]` and k = 2, return `[1,2]`.

**Note: **

- You may assume *k* is always valid, 1 ? *k* ? number of unique elements.
- Your algorithm's time complexity **must be** better than O(*n* log *n*), where *n* is the array's size.

#### EXPLANATION:

思路就是获取到键值对，然后进行排序，对排序结果取前k位即可。

#### SOLUTION:

```JAVA
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        ArrayList<Integer> alkey = new ArrayList<>(map.keySet());
        Collections.sort(alkey, (o1, o2) -> map.get(o2)-map.get(o1));
        for(int i = 0;i<k;i++) result.add(alkey.get(i));
        return result;
    }
}
```


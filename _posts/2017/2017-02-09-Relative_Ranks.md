---
layout: post
title: 506. Relative Ranks
categories: [leetcode]
---

#### QUESTION:

Given scores of **N** athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

**Example 1:**

**Input:** [5, 4, 3, 2, 1]

**Output:** ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]

**Explanation:** The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 

For the left two athletes, you just need to output their relative ranks according to their scores.

**Note:**

1. N is a positive integer and won't exceed 10,000.
2. All the scores of athletes are guaranteed to be unique.

#### EXPLANATION:

好吧，我一开始的ac解只打败了2.6的提交。我的想法是：

1.首先进行冒泡排序，每次排序都会出一个最大值，这样就可以将所有的数字与他对应的排名对应起来了。

2.然后按照顺序去除对应的排名就可以了。

但是时间复杂度是O(n^2)，空间复杂度因为要创建一个新的数组来记录之前的顺序。

#### SOLUTION:

```java
public String[] findRelativeRanks(int[] nums) {
        int[] orginal = Arrays.copyOf(nums,nums.length);
        Hashtable<Integer,String> result = new Hashtable<>();
        for(int i = 0;i<nums.length-1;i++){
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    switchIndex(nums, j, j + 1);
                }
            }
            result.put(nums[nums.length-1-i],i+1+"");
        }
        result.put(nums[0],nums.length+"");
        String[] str_result = new String[nums.length];
        for(int i = 0;i<orginal.length;i++){
            str_result[i] = result.get(orginal[i]);
            if(str_result[i].equals("1")){
                str_result[i] = "Gold Medal";
            }else if(str_result[i].equals("2")){
                str_result[i] = "Silver Medal";
            }else if(str_result[i].equals("3")){
                str_result[i] = "Bronze Medal";
            }
        }
        return str_result;
    }
```


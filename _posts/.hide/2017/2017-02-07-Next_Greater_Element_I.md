---
layout: post
title: 496. Next Greater Element I
categories: [leetcode]
---



#### QUESTION:

You are given two arrays **(without duplicates)** nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number **x** in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

**Example 1:**

**Input:** **nums1** = [4,1,2], **nums2** = [1,3,4,2].

**Output:** [-1,3,-1]

**Explanation:**

    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.

    For number 1 in the first array, the next greater number for it in the second array is 3.

    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

**Example 2:**

**Input:** **nums1** = [2,4], **nums2** = [1,2,3,4].

**Output:** [3,-1]

**Explanation:**

    For number 2 in the first array, the next greater number for it in the second array is 3.

    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

**Note:**

1. All elements in nums1 and nums2 are unique.
2. The length of both nums1 and nums2 would not exceed 1000.

#### EXPLANATION:

其实好的解法还是勇士stack堆进行解决，具体的代码就在下面。

#### SOLUTION:

```java
//我自己写的ac的解法，比较繁琐
if(findNums==null||findNums.length<0) return null;
        int[] result = new int[findNums.length];
        Arrays.fill(result,-1);
        for(int i = 0 ;i<findNums.length;i++){
            for(int j = 0;j<nums.length;j++){
                if(findNums[i]==nums[j]){
                    if(j+1<nums.length){
                        for(int m = j+1;m<nums.length;m++){
                            if(nums[m]>findNums[i]){
                                result[i] = nums[m];
                                break;
                            }
                        }
                    }else {
                        result[i]=-1;
                    }
                    break;
                }
            }
        }
        return result;


//看了discuss中别人使用的stack进行写的
public static int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }
        for (int i = 0; i < findNums.length; i++)
            findNums[i] = map.getOrDefault(findNums[i], -1);
        return findNums;
    }
```


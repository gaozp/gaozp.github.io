---
layout: post
title: 384. Shuffle an Array
categories: [leetcode]
---

#### QUESTION:

Shuffle a set of numbers without duplicates.

**Example:**

```
// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
```

#### EXPLANATION:

也没有什么特别想法。

首先获取一个随机数，然后取出来。接着在剩下的获取第二个随机数。

#### SOLUTION:

```JAVA
public class Solution {
        int[] instance;
        Random random;
        public Solution(int[] nums) {
            this.instance = nums;
            random = new Random(nums.length);
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return instance;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0;i<instance.length;i++) list.add(instance[i]);
            int[] result = new int[instance.length];
            for(int i = 0;i<result.length;i++){
                result[i] = list.remove(random.nextInt(instance.length-i));
            }
            return result;
        }
}
```


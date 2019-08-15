---
layout: post
title: 599. Minimum Index Sum of Two Lists
categories: [leetcode]
---

#### QUESTION:

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their **common interest** with the **least list index sum**. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

**Example 1:**

```
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".

```

**Example 2:**

```
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).

```

**Note:**

1. The length of both lists will be in the range of [1, 1000].
2. The length of strings in both lists will be in the range of [1, 30].
3. The index is starting from 0 to the list length minus 1.
4. No duplicates in both lists.

#### EXPLANATION:

其实这道题就是按部就班的来，先将list1的数据都存入，然后再用list2的数据进行比对，如果相等，同时index+i的值也小于上次标记的值，那么就可以替换结果。

这里我觉得可能会出现0-5，5-0这种情况，也就是会有两种答案或者3种答案的情况，所以我在此处添加了==的情况。

#### SOLUTION:

```java
public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        int value = Integer.MAX_VALUE;
        HashSet<String> result = new HashSet<>();
        Hashtable<String,Integer> table = new Hashtable<>();
        for(int i = 0;i<list1.length;i++){
            String tmp = list1[i];
            table.put(tmp,i);
        }

        for(int i = 0;i<list2.length;i++){
            String tmp = list2[i];
            int index = table.getOrDefault(tmp,-1);
            if(index != -1 && index+i<value){
                result.clear();
                result.add(tmp);
                value = index+i;
            }
            if(index != -1 && index+i==value){
                result.add(tmp);
            }
        }
        String[] stringResult = new String[result.size()];
        Iterator<String> iterator = result.iterator();
        for(int i = 0;i<result.size();i++){
            stringResult[i] = iterator.next();
        }
        return stringResult;
    }
}
```


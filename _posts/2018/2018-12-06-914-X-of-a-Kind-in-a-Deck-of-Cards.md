---
layout: post
title: 914. X of a Kind in a Deck of Cards
---

#### QUESTION:

In a deck of cards, each card has an integer written on it.

Return `true` if and only if you can choose `X >= 2` such that it is possible to split the entire deck into 1 or more groups of cards, where:

- Each group has exactly `X` cards.
- All the cards in each group have the same integer.

**Example 1:**

```
Input: [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
```

**Example 2:**

```
Input: [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.
```

**Example 3:**

```
Input: [1]
Output: false
Explanation: No possible partition.
```

**Example 4:**

```
Input: [1,1]
Output: true
Explanation: Possible partition [1,1]
```

**Example 5:**

```
Input: [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2]
```


**Note:**

1. `1 <= deck.length <= 10000`
2. `0 <= deck[i] < 10000`

#### EXPLANATION:

这道题，其实思路挺简单的，但是难在实现。

首先需要将问题转换，这个问题可以转换成：一组数（这组数就是每个数字的个数），求一个公约数能够被所有的数整除。因为如果能被整除，那么就能分成对应的组数。

1.计算出所有数字的数量

2.找出那个公约数

是吧，第一个很简单，找出数字的数量，就是for循环累加。

但是第二步，找出那个公约数就是需要考虑的了。

最大公约数可以使用欧几里得算法，也就是辗转相除法。

#### SOLUTION:

```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length<2) return false;
        Map<Integer,Integer> table = new HashMap<>();
        for(int i = 0;i<deck.length;i++) table.put(deck[i],table.get(deck[i])==null?1:table.get(deck[i])+1);
        Set<Map.Entry<Integer, Integer>> entries = table.entrySet();
        int smallest = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : entries) smallest = Math.min(smallest, entry.getValue());
        ArrayList<Integer> list = hasGroupsSizeXHelper(smallest);
        if (smallest < 2 || list.size() == 0) return false;
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i);
            boolean tmp = true;
            for (Map.Entry<Integer, Integer> entry : entries) {
                if (entry.getValue() % index != 0){
                    tmp = false;
                    break;
                }
            }
            if(tmp) return true;
        }
        return false;
    }
    public static ArrayList<Integer> hasGroupsSizeXHelper(int n){
        ArrayList<Integer> integers = new ArrayList<>();
        if(n <= 3){
            integers.add(n);
            return integers;
        }
        integers.add(n);
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i == 0) {
               integers.add(i);
               integers.add(n/i); 
            }
        }
        return integers;
    }

}
```


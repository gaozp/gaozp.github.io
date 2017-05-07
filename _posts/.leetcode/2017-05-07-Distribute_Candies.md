---
layout: post
title: 575. Distribute Candies
---

#### QUESTION:

Given an integer array with **even** length, where different numbers in this array represent different **kinds** of candies. Each number means one candy of the corresponding kind. You need to distribute these candies **equally** in number to brother and sister. Return the maximum number of **kinds** of candies the sister could gain.

**Example 1:**

```
Input: candies = [1,1,2,2,3,3]
Output: 3
Explanation:
There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
The sister has three different kinds of candies. 

```

**Example 2:**

```
Input: candies = [1,1,2,3]
Output: 2
Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
The sister has two different kinds of candies, the brother has only one kind of candies. 

```

**Note:**

1. The length of the given array is in range [2, 10,000], and will be even.
2. The number in given array is in range [-100,000, 100,000].

#### EXPLANATION:

就是平分给两个人，但是姐姐要拿到品种不同的个数。

分析如下：首先拿到姐姐可以获得多少个，再获取到一共有多少种蜡烛。

1.如果姐姐获取到的数量大于蜡烛种类，那么就算她每个拿一个，后面也必然有重复的，所以只能获取到蜡烛种类的种类。

2.如果姐姐获取到的输啦小于蜡烛种类，那么姐姐就只能获取到能够拿的数量。

#### SOLUTION:

```java
public class Solution {
    public int distributeCandies(int[] candies) {
        int canGet = candies.length/2;
        Hashtable<Integer,Integer> candiesType = new Hashtable<>();
        for(int i = 0;i<candies.length;i++){
            int kind = candies[i];
            if(!candiesType.containsKey(kind))
                candiesType.put(kind,0);
            int value = candiesType.get(kind)+1;
            candiesType.put(kind,value);
        }
        int kind = candiesType.keySet().size();
        return canGet>kind?kind:canGet;
    }
}
```


---
layout: post
title: 605. Can Place Flowers
---

#### QUESTION:

Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number **n**, return if **n**new flowers can be planted in it without violating the no-adjacent-flowers rule.

**Example 1:**

```
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True

```

**Example 2:**

```
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False

```

**Note:**

1. The input array won't violate no-adjacent-flowers rule.
2. The input array size is in the range of [1, 20000].
3. **n** is a non-negative integer which won't exceed the input array size.

#### EXPLANATION:

其实思路真的挺好想的，但是当时在本地调试的时候加了一行打印的，平白无故就多了200ms，删掉了之后就可以达到15ms左右了。

思路就是判断前后两个花盆是否有栽种即可。

#### SOLUTION:

```java
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                int pre = i == 0 ? 0 : flowerbed[i - 1];
                int after = i == flowerbed.length-1 ? 0 : flowerbed[i + 1];

                if (pre == 0 && after == 0) {
                    flowerbed[i] = 1;
                    n--;
                    i++;//因为栽了一颗，后面一盆肯定就是可以确定的是0了
                }
            }
            if (n <= 0)
                return true;
        }
        return false;
    }
}
```


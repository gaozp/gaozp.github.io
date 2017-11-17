---
layout: post
title: 717. 1-bit and 2-bit Characters
---

#### QUESITON:

We have two special characters. The first character can be represented by one bit `0`. The second character can be represented by two bits (`10` or `11`).

Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.

**Example 1:**

```
Input: 
bits = [1, 0, 0]
Output: True
Explanation: 
The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.

```

**Example 2:**

```
Input: 
bits = [1, 1, 1, 0]
Output: False
Explanation: 
The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.

```

**Note:**

`1 <= len(bits) <= 1000`.

`bits[i]` is always `0` or `1`.

#### EXPLANATION:

看题目就可以知道逻辑了

1.如果当前是1，那么就必须要往后走两步

2.如果是0，那么就需要往后走1步



如果最后是0那么就说明是true；

#### SOLUTION:

```JAVA
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        return isOneBitCharacterHelper(bits,0);
    }
    public boolean isOneBitCharacterHelper(int[] bits , int index){
        if (index > bits.length - 1) return false;
        if (index == bits.length - 1 && bits[index] == 0) return true;
        if (bits[index] == 1) return isOneBitCharacterHelper(bits, index + 2);
        if (bits[index] == 0) return isOneBitCharacterHelper(bits, index + 1);
        return false;
    }
}
```


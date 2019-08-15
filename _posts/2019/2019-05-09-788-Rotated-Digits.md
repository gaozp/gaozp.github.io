---
layout: post
title: 788. Rotated Digits
categories: [leetcode]
---

#### QUESTION:

X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number `N`, how many numbers X from `1` to `N` are good?

```
Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
```

**Note:**

- N  will be in range `[1, 10000]`.

#### EXPLANATION:

其实这题算法也很明显，但是也很明显是有坑的。就是你不能一直计算，否则时间复杂度一定就不能通过了。那么可以借用leetcode的一个特点，那就是所有的case都是在一次进行计算的。所以静态变量会一直保持在这次case中。

所以可以用一个静态数组来保存一些已经计算过的值。那么这样就可以了。

#### SOLUTION:

```java
class Solution {
    static int[] rotatedDigitsIndex = new int[10001];
    public int rotatedDigits(int N) {
        int result = 0;
        for(int i = 1;i<=N;i++){
            if(rotatedDigitsIndex[i]==1){
                result++;
                continue;
            }else if(rotatedDigitsIndex[i]==0){
                String number = i+"";
                if((number.contains("2")||number.contains("5")||number.contains("6")||number.contains("9"))
                        &&(!number.contains("3")&&!number.contains("4")&&!number.contains("7")&&!number.contains("3"))){
                    result++;
                    rotatedDigitsIndex[i] = 1;
                }else{
                    rotatedDigitsIndex[i] = -1;
                }
            }
        }
        return result;
    }
}
```


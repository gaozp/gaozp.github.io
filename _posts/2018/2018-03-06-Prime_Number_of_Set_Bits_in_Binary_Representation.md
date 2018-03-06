---
layout: post
title: 762. Prime Number of Set Bits in Binary Representation
---

#### QUESTION:

Given two integers `L` and `R`, find the count of numbers in the range `[L, R]` (inclusive) having a prime number of set bits in their binary representation.

(Recall that the number of set bits an integer has is the number of `1`s present when written in binary. For example, `21` written in binary is `10101` which has 3 set bits. Also, 1 is not a prime.)

**Example 1:**

```
Input: L = 6, R = 10
Output: 4
Explanation:
6 -> 110 (2 set bits, 2 is prime)
7 -> 111 (3 set bits, 3 is prime)
9 -> 1001 (2 set bits , 2 is prime)
10->1010 (2 set bits , 2 is prime)

```

**Example 2:**

```
Input: L = 10, R = 15
Output: 5
Explanation:
10 -> 1010 (2 set bits, 2 is prime)
11 -> 1011 (3 set bits, 3 is prime)
12 -> 1100 (2 set bits, 2 is prime)
13 -> 1101 (3 set bits, 3 is prime)
14 -> 1110 (3 set bits, 3 is prime)
15 -> 1111 (4 set bits, 4 is not prime)

```

**Note:**

1. `L, R` will be integers `L <= R` in the range `[1, 10^6]`.
2. `R - L` will be at most 10000.

#### EXPLANATION:

这个题目的解法也就很简单了

1.获取到当前数字的一共有多少个1 （可以使用Integer的bitcount方法），当然也可以使用%1的方式，然后右移一位的方式。

2.得到了bitcount其实就已经很容易可以计算出是不是质数了。

然后计算一下就可以了。

#### SOLUTION:

```JAVA
class Solution {
    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        for(int i = L;i<=R;i++){
            int bitCount = Integer.bitCount(i);
            if(isPrime(bitCount))
                result++;
        }
        return result;
    }
    public static boolean isPrime(int n){
        if (n <= 3) {
            return n > 1;
        }

        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i == 0)
                return false;
        }
        return true;
    }
}
```


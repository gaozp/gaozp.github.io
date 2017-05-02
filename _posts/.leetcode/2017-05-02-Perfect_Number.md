---
layout: post
title: 507. Perfect Number
---

#### QUESTION:

We define the Perfect Number is a **positive** integer that is equal to the sum of all its **positive** divisors except itself.

Now, given an 

integer

 n, write a function that returns true when it is a perfect number and false when it is not.

**Example:**

```
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14

```

**Note:** The input number **n** will not exceed 100,000,000. (1e8)

#### EXPLANATION:

其实一开始想到的是for循环里面最大的数是除以2，但是达到了时间上限，所以也就没有办法了。后来再想到25这个数以及9这个数的时候，想到了也许平方根的是最快的，但是需要加上除数和结果。所以就写了下面最普通的算法。

看了下答案，差不多有三种解法：

1.int类型的数也就那么几个，只要判断下是否是这几个就行；

2.Euclid-Euler Theorem 具体可以看[这个链接](https://en.wikipedia.org/wiki/Euclid%E2%80%93Euler_theorem)，我也把解法写在下面吧。

3.最普通的解法，也就是下面写的

#### SOLUTION:

```java
public class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num < 0 || num == 1) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0){
                sum+=i;
                sum+=num/i;
            }
        }
        return sum == num;
    }
}

// euclid-euler theorem解法
    public boolean checkPerfectNumber1(int num) {
        int[] primes = new int[]{2,3,5,7,13,17,19,31};
        for (int prime : primes) {
            if(perfectNumber(prime) == num) {
                return true;
            }
        }

        return false;
    }
    private int perfectNumber(int prime) {
        return (1 << (prime-1)) * ((1 << prime) - 1);
    }
```


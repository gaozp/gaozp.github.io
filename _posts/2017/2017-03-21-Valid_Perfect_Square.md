---
layout: post
title: 367. Valid Perfect Square
categories: [leetcode]
---

#### QUESTION:

Given a positive integer *num*, write a function which returns True if *num* is a perfect square else False.

**Note:** **Do not** use any built-in library function such as `sqrt`.

**Example 1:**

```
Input: 16
Returns: True

```

**Example 2:**

```
Input: 14
Returns: False

```

**Credits:**
Special thanks to [@elmirap](https://discuss.leetcode.com/user/elmirap) for adding this problem and creating all test cases.

#### EXPLANATION:

1.其实一开始我使用的是注释的方法，也算是初步的二分法吧，因为减少了一般的计算量，也算是一个ac解。

2.后面可以使用二分法进行计算

3.也可以使用一个规律就是平方都是1+3+5+7.。。这个规律

4.最后也是最重要的就是 有一个[牛顿迭代算法](https://zh.wikipedia.org/wiki/%E7%89%9B%E9%A1%BF%E6%B3%95#.E7.AC.AC.E4.BA.8C.E4.B8.AA.E4.BE.8B.E5.AD.90)，其实然后我发现了一个更重要的[文章](http://blog.csdn.net/wangxiaojun911/article/details/18203333)。算是对牛顿算法的改良。

牛顿算法公式就是：

![](https://wikimedia.org/api/rest_v1/media/math/render/svg/6929060731e351c465426e37567abe5ee13d65d9)

1.首先我们取的就是num/2的值作为第一个参数。第一个解的平方肯定是大于num的，所以我们需要一直往下面求解直到达到一个临界值。此时这个临界值就应该是解。

可以把t带入到公式中，就可以发现 x(t+1) = t-(t*t-num)/(2t),通过化简就可以得到t = (t + num/2)/2。

#### SOLUTION:

```java
public boolean isPerfectSquare(int num) {
        // if(num == 0|| num ==1) return true;
        // for(int i = 1;i<=num/2;i++){
        //     if(i*i==num)
        //         return true;
        // }
        // return false;
        
        int i = 1;
     while (num > 0) {
         num -= i;
         i += 2;
     }
     return num == 0;
  
  
  	//牛顿算法：
  		int t = num/2;
        while (t*t>num){
            t = (t + num/2)/2;
        }
        return t*t == num;
    }
```


---
layout: post
title: 650. 2 Keys Keyboard
categories: [leetcode]
---

#### QUESTION:

Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

1. `Copy All`: You can copy all the characters present on the notepad (partial copy is not allowed).
2. `Paste`: You can paste the characters which are copied **last time**.

Given a number `n`. You have to get **exactly** `n` 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get `n` 'A'.

**Example 1:**

```
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
```

**Note:**

1. The `n` will be in the range [1, 1000].

#### EXPLANATION:

这个题目也是动态规划的题目 ，可以这样想

1.f(1) = 0

f(2) = f(1)+1+1  一次为copy，一次为paste

f(3) = f(1)+1+1+1 一次为copy，一次为paste，paste

f（4）就有很多种了，可以一直paste就是4次，还有就是f(4) = f(2)+1+1因为f（2）加一次copy再加一次paste

这个时候就有点眉目了：

f(n) = f(n/i)+n/i

这个就是递推公式，有了这个递推公式其实就很容易写出对应的结果了。

我本身的写法是只计算了 math.sqr(n)步，这样是可以减少步骤，但是hashtable的添加和减少，其实也是变相的增加了时间复杂度，看了下别人的计算方法，就是简单O(N)，也是可以的。

#### SOLUTION:

```JAVA
class Solution {
    public int minSteps(int n) {
        if(n <= 1) {
            return 0;
        }
        
        int result = n;
        for(int i = n - 1; i > 1; i--) {
            if(n % i == 0) {
                result = Math.min(result, minSteps(n / i) + i);
            }
        }
        return result;
    }
}

import java.util.*;
class Solution {
    public int minSteps(int n) {
        return minStepsHelper(n);
    }
    
    public static int minStepsHelper(int n) {
        if(n==1) return 0;
        if(n==2) return 2;
        if(n==3) return 3;
        if(n==4) return 4;
        if(n==5) return 5;
        Iterator<Map.Entry<Integer, Integer>> iterator = helper(n).entrySet().iterator();
        int result = Integer.MAX_VALUE;
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            int a =Integer.MAX_VALUE;
            int b =Integer.MAX_VALUE;
            if(next.getKey()!=1){
                a = minStepsHelper(next.getKey())+next.getValue();
                b = minStepsHelper(next.getValue())+next.getKey();
                result = Math.min(result,a>b?b:a);
            }else {
                result = Math.min(result,minStepsHelper(next.getKey())+next.getValue());
            }
        }
        return result;
    }
    public static Hashtable<Integer,Integer> helper(int n){
        Hashtable<Integer,Integer> result = new Hashtable<>();
        for(int i = 1;i<=Math.sqrt(n);i++){
            if(n%i==0) result.put(i,n/i);
        }
        return result;
    }

}
```


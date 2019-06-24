---
layout: post
title: 970. Powerful Integers
---
#### QUESTION:
Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.

Return a list of all powerful integers that have value less than or equal to bound.

You may return the answer in any order.  In your answer, each value should occur at most once.

 

Example 1:

Input: x = 2, y = 3, bound = 10
Output: [2,3,4,5,7,9,10]
Explanation: 
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2
Example 2:

Input: x = 3, y = 5, bound = 15
Output: [2,4,6,8,10,14]
 

Note:

1 <= x <= 100
1 <= y <= 100
0 <= bound <= 10^6
#### EXPLANATION:

想法其实就是很简单：只要能在和的范围之内就可以。
基本就可以算成是：powx+powy 小于bound就行
那么就可以使用双循环的方式来进行
因为只需要1次，那么就可以使用set来进行。

#### SOLUTION:
```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> seen = new HashSet<>();
        for (int m = 1; m <= bound; m *= x) {
            for (int n = 1; m + n <= bound; n *= y) {
                seen.add(m + n);
                if (y == 1) break;
            }
            if (x == 1) break;
        }
        return new ArrayList<>(seen);
    }
}
//哈哈，我的方法比较丑陋，前面的结果只需要1ms，而我的需要4ms。
    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
        double sum = 1;int tmp = 0;
        List<Double> powX = new ArrayList<>();
        if(x!=1){
            while (sum<bound){
                powX.add(sum);
                tmp++;
                sum = Math.pow(x,tmp);
            }
        }else powX.add(1.0);
        sum = 1;tmp = 0;
        List<Double> powY = new ArrayList<>();
        if(y!=1){
            while (sum<bound){
                powY.add(sum);
                tmp++;
                sum = Math.pow(y,tmp);
            }
        }else powY.add(1.0);
        HashSet<Double> set = new HashSet<>();
        for(int i = 0;i<powX.size();i++){
            in:for(int j = 0;j<powY.size();j++){
                double res = powX.get(i)+powY.get(j);
                if(res > bound) break in;
                set.add(res);
            }
        }
        List<Integer> result = new ArrayList<>();
        Iterator<Double> iterator = set.iterator();
        while (iterator.hasNext()) {
            Double next = iterator.next();
            result.add(Integer.parseInt((next+"").substring(0,(next + "").indexOf('.'))));
        }
        return result;
    }
```
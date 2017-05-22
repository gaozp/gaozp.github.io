---
layout: post
title: 202. Happy Number
---

#### QUESTION:

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

**Example: **19 is a happy number

- 12 + 92 = 82
- 82 + 22 = 68
- 62 + 82 = 100
- 12 + 02 + 02 = 1



#### EXPLANATION:

其实只要查一下就知道happynumber的规律了，就是总会在4上循环。可以直接判断如果n=4的话就说明已经是死循环了。但是这个地方如果我们一直没有证明的话其实也是有其他办法的，就是将所有的值都添加到hashtable中，如果有重复的出现，那就说明进入循环了，这样就可以了。

#### SOLUTION:

```java
	public boolean isHappy(int n){
        HashSet<Integer> set = new HashSet<>();
        while (n!=1){
            if(set.contains(n)){
                return false;
            }else{
                set.add(n);
                int sum = 0;
                char[] chars = Integer.toString(n).toCharArray();
                for(char c : chars){
                    sum += Integer.parseInt(Character.toString(c))*Integer.parseInt(Character.toString(c));
                }
                n = sum;
            }
        }
        return true;
    }
```


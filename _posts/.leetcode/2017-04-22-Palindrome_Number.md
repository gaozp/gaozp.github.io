---
layout: post
title: 9. Palindrome Number
---

#### QUESTION:

Determine whether an integer is a palindrome. Do this without extra space.

[click to show spoilers.](https://leetcode.com/problems/palindrome-number/#)

**Some hints:**Could negative integers be palindromes? (ie, -1)If you are thinking of converting the integer to string, note the restriction of using extra space.You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?There is a more generic way of solving this problem.

#### EXPLANATION:

一开始没有理解需要额外空间的含义，其实是只要不是随着复杂度的增加而增加的空间就不算额外空间了，那样这样就很简单了，只需要不停的对10取余，然后重新组成一个新的数，看这两个数是否相等就行，具体看代码就可以。

#### SOLUTION:

```java
public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)return false;
        int rev = 0;int y = x;
        while (y!=0){
            rev = rev*10 +y%10;
            y/=10;
        }
        return x==rev;
    }
}
```


---
layout: post
title: 405. Convert a Number to Hexadecimal
---

#### QUESTION:

Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, [two’s complement](https://en.wikipedia.org/wiki/Two%27s_complement) method is used.

**Note:**

1. All letters in hexadecimal (a-f) must be in lowercase.
2. The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
3. The given number is guaranteed to fit within the range of a 32-bit signed integer.
4. You **must not use any method provided by the library** which converts/formats the number to hex directly.

**Example 1:**

Input:

26

Output:

"1a"

**Example 2:**

Input:

-1

Output:

"ffffffff"

#### EXPLANATION:

代码就是我的解决办法了，也是4位4位的进行数据的整理，也看了discuss中别人的ac解，确实是位运算的话会比较好一点。效率也会高一点。

#### SOLUTION:

```java
public String toHex(int num) {
        char[] numstring = Integer.toBinaryString(num).toCharArray();
        StringBuilder result = new StringBuilder();
        int index = numstring.length;
        while(index>0){
            double sum = 0;
            for(int i = 0;i<4;i++){
                sum = index-1>=0 ? sum+Integer.parseInt(""+numstring[index-1]) * Math.pow(2,i) : sum;
                index--;
            }
            result.insert(0,Integer.toHexString((int)sum));
        }
        return result.toString();
    }

public String toHex(int num) {
        String[] map = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
        String result = "";
        while(num!=0){
            result = map[(num&15)]+result;
            num  = num >>> 4;
        }
        return result;
    }

```


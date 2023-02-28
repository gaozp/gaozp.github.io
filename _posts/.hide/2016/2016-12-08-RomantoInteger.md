---
layout: post
title: 13. Roman to Integer
categories: [leetcode]
---



#### QUESTION:

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

#### EXPLANATION:

算法其实很简单，按位数去读取，如果该数字小于前一个数字就加上该数，如果大于前一个数字，则需要加上该数字，再前去前一个数字的两倍。

关于罗马数字的[维基百科](https://en.wikipedia.org/wiki/Roman_numerals)，只要理解了罗马数字的逻辑，这个算法其实是简单的。

#### SOLUTION:

```java
private static enum Roma{
        I('I',1),V('V',5),X('X',10),L('L',50),C('C',100),D('D',500),M('M',1000);

        private int value;
        private char name;
        Roma(char name,int i) {
            this.name = name;
            this.value = i;
        }

        public static Roma getRoma(char c) {
            switch (c){
                case 'I':
                    return I;
                case 'V':
                    return V;
                case 'X':
                    return X;
                case 'L':
                    return L;
                case 'C':
                    return C;
                case 'D':
                    return D;
                case 'M':
                    return M;
            }
            return I;
        }

    }
    public int romanToInt(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return result;
        result += Roma.getRoma(chars[0]).value;
        for (int i = 1; i < chars.length; i++) {
            int value_before = Roma.getRoma(chars[i - 1]).value;
            int value_after = Roma.getRoma(chars[i]).value;
            if (value_after > value_before) {
                result = result + value_after - value_before * 2;
            } else {
                result += value_after;
            }

        }
        return result;
    }
```


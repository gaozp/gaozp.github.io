---
layout: post
title: 8.String to Integer (atoi)
---

#### QUESTION:

Implement atoi to convert a string to an integer.

**Hint:** Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

**Notes:** It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

**Update (2015-02-10):**
The signature of the `C++` function had been updated. If you still see your function signature accepts a `const char *` argument, please click the reload button  to reset your code definition.

[spoilers alert... click to show requirements for atoi.](https://leetcode.com/problems/string-to-integer-atoi/description/#)

Requirements for atoi:

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

#### EXPLANATION:

这个题目其实挺简单的，只要你能够完全了解其中的所有限制条件。

但是也许正是这个限制条件多，所以被选择成了medium。

#### SOLUTION:

```python
class Solution(object):
    def myAtoi(self, x):
        """
        :type str: str
        :rtype: int
        """
        x = x.strip()
        flag = 1;index = 0
        result = '0'
        if len(x)>0 and x[0]=='-':
            flag=-1
            index=1
        if len(str(x))>0 and x[0]=='+':
            index=1
        for i in range(index,len(x),1):
            if x[i].isdigit() :
                result = result + x[i]
            else :
                break
        result = result.lstrip('0')
        result = result.lstrip('0')
        if len(result)>0 :
            result = long(result)*flag
            if result > 2147483647 : return 2147483647
            if result < -2147483648 : return -2147483648
        return 0 if len(str(result))==0 else int(result)

```




---
layout: post
title: 461. Hamming Distance
categories: [leetcode]
---

#### QUESTION:

The [Hamming distance](https://en.wikipedia.org/wiki/Hamming_distance) between two integers is the number of positions at which the corresponding bits are different.

Given two integers `x` and `y`, calculate the Hamming distance.

**Note:**
0 ≤ `x`, `y` < 231.

**Example:**

```
Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ?   ?

The above arrows point to positions where the corresponding bits are different.
```

#### EXPLANATION:

1.转换成bit位，然后进行查看就可以了。



还有一种方法就是异或的时候就可以进行异或处理，那么这一位上就是是否相同的了，然后右移算出每一位的结果。

#### SOLUTION:

```JAVA
public class Solution {
    public int hammingDistance(int x, int y) {
        String ba = Integer.toBinaryString(x);
        String bb = Integer.toBinaryString(y);
        int result = 0, i = ba.length(), j = bb.length();
        while (i > 0 || j > 0) {
            i--;
            j--;
            char cha, chb;
            if (i < 0) cha = '0';
            else cha = ba.charAt(i);
            if (j < 0) chb = '0';
            else chb = bb.charAt(j);
            if (cha != chb)
                result++;
        }
        return result;
    }
  
      public static int totalHammingDistanceHelper(int a, int b) {
        if (a == b) return 0;
        int xor = a^b;
        int res = 0;
        while (xor != 0) {
            res += xor&1;
            xor >>= 1;
        }
        return res;
    }

}
```



```python
class Solution(object):
    def hammingDistance(self, x, y):
        """
        :type x: int
        :type y: int
        :rtype: int
        """
        stra = str(bin(x))
        strb = str(bin(y))
        indexa = len(stra)
        indexb = len(strb)
        i = indexa-1
        j=indexb-1
        result = 0
        while(i>1 or j>1):
            chara = '0'
            charb = '0'
            if(i>1) :chara = stra[i]
            if(j>1) :charb = strb[j]
            if(chara != charb) :
                result+=1
            i-=1
            j-=1
        return result
    
    '''当然还有一行的解法'''
    def hammingDistance(self,x,y):
        return bin(x^y).conut('1')
```


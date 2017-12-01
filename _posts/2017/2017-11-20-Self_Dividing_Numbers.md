---
layout: post
title: 728. Self Dividing Numbers
---

#### QUESTION:

A *self-dividing number* is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because `128 % 1 == 0`, `128 % 2 == 0`, and `128 % 8 == 0`.

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.

**Example 1:**

```
Input: 
left = 1, right = 22
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]

```

**Note:**

The boundaries of each input argument are `1 <= left <= right <= 10000`.

#### EXPLANATION:

逻辑就是很简单了

1.取出每一个数字

2.取出每一个数字的每一位数字。

3.进行取余操作，判断是否为0和余数，如果是，那么不加入结果。

#### SOLUTION:

```JAVA
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = left;i<=right;i++){
            int tmp = i;
            boolean flag = false;
            char[] chars = Integer.toString(tmp).toCharArray();
            for(int j = 0;j<chars.length;j++){
                int divid = Integer.parseInt(chars[j]+"");
                if(divid == 0 || tmp % divid != 0){
                    flag = true;
                    break;
                }
            }
            if(!flag) result.add(i);
        }
        return result;
    }
}
```

```python
class Solution(object):
    def selfDividingNumbers(self, left, right):
        """
        :type left: int
        :type right: int
        :rtype: List[int]
        """
        result = []
        for i in range(left,right+1,1):
            tmp = i
            flag = 0
            for j in range(0,len(str(tmp))):
                index =  str(tmp)[j]
                if int(index) == 0 :
                    flag = 1
                    break
                if tmp%int(index) != 0 :
                    flag = 1
                    break
            if(flag==0):
                result.append(i)
        return result
  
```


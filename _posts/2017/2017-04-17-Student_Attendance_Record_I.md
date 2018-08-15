---
layout: post
title: 551. Student Attendance Record I
---

#### QUESTION:

You are given a string representing an attendance record for a student. The record only contains the following three characters:

1. **'A'** : Absent.
2. **'L'** : Late.
3. **'P'** : Present.

A student could be rewarded if his attendance record doesn't contain **more than one 'A' (absent)** or **more than two continuous 'L' (late)**.

You need to return whether the student could be rewarded according to his attendance record.

**Example 1:**

```
Input: "PPALLP"
Output: True

```

**Example 2:**

```
Input: "PPALLL"
Output: False
```

#### EXPLANATION:

其实我一开始想到的就是遍历数组，然后筛选出acount和lcount数量，最后再进行检查。然后在自己写测试用例的时候发现，如果后面很多都是p的话，其实会造成很多的性能浪费，所以，在for循环之后进行了条件判断。这样在如果string不是很长的时候或许是有性能浪费的。

如果是正常情况的话，学生其实应该是p的多，大部分应该都是true可以获得嘉奖的，但是因为是leetcode，所以很明显应该是false的多，所以最后的耗时是9ms。

然后看了一下discuss中的，发现其他人都是使用的正则表达式，正则表达式可以让代码很容易被看懂，但是我也测试了下性能，发现需要24ms，所以孰轻孰重还是看你取舍了。

#### SOLUTION:

```java
public class Solution {
    public boolean checkRecord(String s) {
        char[] chars = s.toCharArray();
        int aCount =0,lCount = 0;
        for(char ch : chars){
            switch (ch) {
                case 'A':
                    aCount ++;
                    lCount = 0;
                    break;
                case 'P':
                    lCount = 0;
                    break;
                case 'L':
                    lCount++;
                    break;
            }
            if(aCount>1 || lCount>2)
                return false;
        }
        return true;
    }
}


public class Solution {
    public boolean checkRecord(String s) {
        return !s.matches(".*LLL.*|.*A.*A.*");
    }
}
```






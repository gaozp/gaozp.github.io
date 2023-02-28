---
layout: post
title: 696. Count Binary Substrings
categories: [leetcode]
---

#### QUESTION:

Give a string `s`, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

**Example 1:**

```
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

Notice that some of these substrings repeat and are counted the number of times they occur.

Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
```

**Example 2:**

```
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
```

**Note:**

`s.length` will be between 1 and 50,000.

`s` will only consist of "0" or "1" characters.

#### EXPLANATION:

我的ac解是8ms的

思路是：

首先找到不同的点，然后向两边进行扩展，找到不同的就说明不行了。

思路清晰了，代码其实就很容易些了。

#### SOLUTION:

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int count = 0;
        int count1 = 1;
        int count2 = 0;
        char[] arr = s.toCharArray();
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == arr[i - 1]) {
                count1++;
                if(count2 >= count1) count++;
            }
            else{
                count++;
                count2 = count1;
                count1 = 1;
            }
        }
        return count;
    }
}

    public static int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for(int i = 1;i<chars.length;i++){
            if(chars[i]!=chars[i-1]){
                int j = 0;
                while (i-j-1>=0 && i+j<=chars.length-1){
                    if(chars[i-1]==chars[i-j-1] && chars[i]==chars[i+j]){
                        result++;
                        j++;
                    }else break;
                }
            }
        }
        return result;
    }
```


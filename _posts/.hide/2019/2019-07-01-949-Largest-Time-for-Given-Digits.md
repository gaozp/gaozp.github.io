---
layout: post
title: 949. Largest Time for Given Digits
categories: [leetcode]
---
#### QUESTION:
Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""
 

Note:

A.length == 4
0 <= A[i] <= 9
#### EXPLANATION:

因为是只有4个数，可以采用穷举法来进行
1.穷举出所有可能的时间
2.进行排序
3.返回最终结果
其实难点就在于穷举出所有可能的时间，也需要分为两种：
1.00：00-19：59
2.20：00-23：59
可以得到规律：
当第一位是2时，第二位必须小于4，第三位必须小于6.
当第一位是0或1时，第二位则没有限制，第三位还是限制必须小于6.

#### SOLUTION:
```JAVA
class Solution {
    public String largestTimeFromDigits(int[] A) {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0;i<4;i++){
            for(int j = 0;j<4;j++){
                if(j==i) continue;
                for(int m = 0;m<4;m++){
                    if(m==i|m==j)continue;
                    for(int n = 0;n<4;n++){
                        if(n==i|n==j|n==m)continue;
                        if((A[i]==2 && A[j]<4 && A[m]<6)
                                ||((A[i]==0 | A[i]==1 )&& A[m]<6)){
                            result.add(A[i]+""+A[j]+":"+A[m]+A[n]);
                        }
                    }
                }
            }
        }
        Collections.sort(result);
        if(result.size()>0){
            return result.get(result.size()-1);
        }else return "";
    }
}
```
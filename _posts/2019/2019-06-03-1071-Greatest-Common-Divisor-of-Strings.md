---
layout: post
title: 1071. Greatest Common Divisor of Strings
---
#### QUESTION:
For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""
 

Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.

#### EXPLANATION:
我的想法就是：
1.先分别计算出两个str的最小T
2.如果这两个t不相等，那么就直接返回“”；
3.相等的话就需要计算出两者的最大公约数
4.最大公约数就是我们需要的结果
#### SOLUTION:
```
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (str1.isEmpty()) return str2;
        else if (str2.isEmpty()) return str1;
        else if (str1.length() >= str2.length()) {
            if (!str1.startsWith(str2)) return "";
            return gcdOfStrings(str1.substring(str2.length()), str2);
        } else {
            return gcdOfStrings(str2, str1);
        }
    }
}

    public static String gcdOfStrings(String str1, String str2) {
        String s1 = gcdOfStringsHelper(str1);
        String s2 = gcdOfStringsHelper(str2);
        if(!s1.equals(s2)) return "";
        int tmp1 = str1.length()/s1.length();
        int tmp2 = str2.length()/s2.length();
        String result = "";int count = 0;
        for(int i = Math.min(tmp1,tmp2);i>=1;i--){
            if(tmp1%i==0 && tmp2%i == 0) {
                count = i;
                break;
            }
        }
        for(int i = count;i>=1;i--){
            result+=s1;
        }
        return result;
    }

    public static String gcdOfStringsHelper(String val){
        int start = 0,index = 1,end = val.length()-1;
        w: while (index<val.length()){
            if(val.charAt(index)==val.charAt(start)) {
                if(val.length()%index == 0){
                    for (int i = 0 ;i<val.length()/index;i++){
                        if(!val.substring(0,index).equals(val.substring(index*i,index*(i+1)))){
                            index++;
                            continue w;
                        }
                    }
                    return val.substring(0,index);
                }
            }
            index++;
        }
        return val.substring(0,index);
    }
```
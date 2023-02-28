---
layout: post
title: 299. Bulls and Cows
categories: [leetcode]
---
#### QUESTION:
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
#### EXPLANATION:
公牛母牛计划，需要注意的是需要先计算公牛的值再计算母牛的值，否则按顺序就会出错如“1122”“1222”按顺序的答案就是3A1B但其实是3A0B。所以我们需要先计算公牛的值。  
逻辑：  
1. 定义个数组来表示对应的数字的个数  
2. 首先将secert中数字的个数获取到  
3. for循环去获取bull的值，同时将之前的个数进行递减  
4. for循环去获取cow的值，同时将之前的个数进行递减  
5. 将结果组合返回  
#### SOLUTION:
```JAVA
class Solution {
    public String getHint(String secret, String guess) {
        int[] numbers = new int[10];
        for(int i = 0;i<secret.length();i++) numbers[secret.charAt(i)-'0']++;
        int aCount =0,bCount = 0;
        for(int i = 0;i<guess.length();i++){
            if(secret.charAt(i)==guess.charAt(i)&&numbers[guess.charAt(i)-'0']>0){
                aCount++;
                numbers[guess.charAt(i)-'0']--;
            }
        }
        for(int i = 0;i<guess.length();i++){
            if(secret.charAt(i)!=guess.charAt(i)&&numbers[guess.charAt(i)-'0']>0){
                bCount++;
                numbers[guess.charAt(i)-'0']--;
            }
        }
        return aCount+"A"+bCount+"B";
    }
}
```
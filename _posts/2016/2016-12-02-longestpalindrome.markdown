---
layout: post
title: 409. Longest Palindrome
categories: [leetcode]
---

####QUESTION:
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.


####EXPLANATION:

这就是一个比较简单的算法，就是计算出对应的每个字母的数量，然后偶数的字母肯定是可以组成回环字符串的，奇数字母的话-1就是对应的能够组成的回环字符串。如果最后还剩下字符的话，那么就可以添加在中间，组成奇数回环字符串。但是写完了之后，发现网上的所有算法都和我不相同，同时有一个复杂为O(n)的[算法](https://www.akalin.com/longest-palindrome-linear-time)。


####SOLUTION:

    
    public int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch:chars){
            if(null==map.get(ch)){
                map.put(ch,1);
            }else{
                int value = map.get(ch) + 1;
                map.put(ch,value);
            }
        }
        int count = 0;
        Iterator<Character> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            Character next = iterator.next();
            if(map.get(next)%2==0){
                count+=map.get(next);
                iterator.remove();
            }else{
                count+= map.get(next)/2 *2;
            }
        }
        if(map.size()!=0)
            count++;
        return count;
    }
    


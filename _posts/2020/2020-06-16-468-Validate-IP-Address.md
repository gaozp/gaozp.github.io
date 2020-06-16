---
layout: post
title: 468. Validate IP Address
categories: [leetcode]
---
#### QUESTION:
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

**Note:** You may assume there is no extra space or special characters in the input string.

**Example 1:**
```
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
```
**Example 2:**
```
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
```
**Example 3:**
```
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
```
#### EXPLANATION:

其实这道题目难的并不是对应的算法，而是其中的各种边界情况的考虑，同时，其实各种边界情况的考虑也是写代码必须要考虑到的点，所以这其实也是一个考察的方向。也可以考察做题者的耐心和细心程度。  
对应的需要考虑的点我都进行了注释了，考虑到这些点之后就很容易ac了。
#### SOLUTION:
```java
class Solution {
    public String validIPAddress(String IP) {
// 1. 需要考虑split的limit参数，不能去除空参数
        String[] v4s = IP.split("\\.",-1);
        boolean isV4 = true,isV6 = true;
// 2. 判断当前ipv4或者v6能否进行截取，并且截取的个数是否正确
        if(IP.chars().filter(ch->ch=='.').count()==3){
// 3. 由于传入的可能是非阿拉伯字母，同样需要进行try catch处理
            try{
                for(String s : v4s){
                    int tmp = Integer.parseInt(s);
// 4. 对 00 开头的情况进行判断
                    if(!String.valueOf(tmp).equals(s)) {
                        isV4 = false;
                        break;
                    }
// 5. 对数字的大小进行判断
                    if(tmp <0 || tmp>255){
                        isV4 = false;
                        break;
                    }
                }
            }catch (NumberFormatException e){
                isV4 = false;
            }
            if(isV4) return "IPv4";
        }
        String[] v6s = IP.split(":",-1);
// 2. 判断当前ipv6能否进行截取，截取的个数是否正确 
        if(IP.chars().filter(ch->ch==':').count()==7){
            try{
                for(String s : v6s){
// 6.判断数据的大小
                    if(s.length()>4){
                        isV6 = false;
                        break;
                    }
// 7. 换算成16进制来进行判断
                    if(Integer.parseInt(s, 16) < 0 || s.charAt(0)=='-'){
                        isV6 = false;
                        break;
                    }
                }
            }catch (NumberFormatException e){
                isV6 = false;
            }
            if(isV6) return "IPv6";
        }
        return "Neither";
    }
}
```

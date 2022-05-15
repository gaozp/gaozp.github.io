---
layout: post
title: 2160. Minimum Sum of Four Digit Number After Splitting Digits
categories: [leetcode]
---
#### QUESTION:
You are given a positive integer num consisting of exactly four digits. Split num into two new integers new1 and new2 by using the digits found in num. Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.

For example, given num = 2932, you have the following digits: two 2's, one 9 and one 3. Some of the possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
Return the minimum possible sum of new1 and new2.

 

__Example 1:__
```
Input: num = 2932
Output: 52
Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
```
__Example 2:__
```
Input: num = 4009
Output: 13
Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc. 
The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 ```

__Constraints:__
```
1000 <= num <= 9999
```
#### EXPLANATION:

题目比较简单, 只要想到, 尽量拆成两个长度相等的, 那就不会出现跨倍数的大数存在. 然后按从小到大,分别给两个数的大单位赋值即可. 

#### SOLUTION:
```java
class Solution {
    func minimumSum(_ num: Int) -> Int {
        var numString:[Character] = String(num).sorted()
        var numA:String = "";
        var numB:String = "";
        var flag:Bool = true;
        for n in numString {
            if (flag) {
                numA = numA.appending(String(n))
            } else {
                numB = numB.appending(String(n))
            }
            flag = !flag
        }
        return Int(numA)! + Int(numB)!;
    }
}
```

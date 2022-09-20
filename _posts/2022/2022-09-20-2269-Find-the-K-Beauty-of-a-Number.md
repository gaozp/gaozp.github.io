---
layout: post
title: 2269. Find the K-Beauty of a Number
categories: [leetcode]
---
#### QUESTION:
The k-beauty of an integer num is defined as the number of substrings of num when it is read as a string that meet the following conditions:

It has a length of k.
It is a divisor of num.
Given integers num and k, return the k-beauty of num.

Note:

Leading zeros are allowed.
0 is not a divisor of any value.
A substring is a contiguous sequence of characters in a string.

 

__Example 1:__
```
Input: num = 240, k = 2
Output: 2
Explanation: The following are the substrings of num of length k:
- "24" from "240": 24 is a divisor of 240.
- "40" from "240": 40 is a divisor of 240.
Therefore, the k-beauty is 2.
```
__Example 2:__
```
Input: num = 430043, k = 2
Output: 2
Explanation: The following are the substrings of num of length k:
- "43" from "430043": 43 is a divisor of 430043.
- "30" from "430043": 30 is not a divisor of 430043.
- "00" from "430043": 0 is not a divisor of 430043.
- "04" from "430043": 4 is not a divisor of 430043.
- "43" from "430043": 43 is a divisor of 430043.
Therefore, the k-beauty is 2.
``` 

__Constraints:__
```
1 <= num <= 109
1 <= k <= num.length (taking num as a string)
```
#### EXPLANATION:

简单的题目, 按照题意写出来即可.

#### SOLUTION:
```swift
class Solution {
    func divisorSubstrings(_ num: Int, _ k: Int) -> Int {
        var result: Int = 0
        var arr:[Character] = Array(String(num))
        for index in 0...arr.count - k {
            var tmpString:String = ""
            for j in index...index + k - 1 {
                tmpString += String(arr[j])
            }
            var tmpNum:Int = Int(tmpString)!
            if tmpNum != 0 && num % tmpNum == 0 {
                result += 1
            }
        }
        return result
    }
}
```

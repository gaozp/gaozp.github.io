---
layout: post
title: 2243. Calculate Digit Sum of a String
categories: [leetcode]
---
#### QUESTION:
You are given a string s consisting of digits and an integer k.

A round can be completed if the length of s is greater than k. In one round, do the following:

Divide s into consecutive groups of size k such that the first k characters are in the first group, the next k characters are in the second group, and so on. Note that the size of the last group can be smaller than k.
Replace each group of s with a string representing the sum of all its digits. For example, "346" is replaced with "13" because 3 + 4 + 6 = 13.
Merge consecutive groups together to form a new string. If the length of the string is greater than k, repeat from step 1.
Return s after all rounds have been completed.

 

__Example 1:__
```
Input: s = "11111222223", k = 3
Output: "135"
Explanation: 
- For the first round, we divide s into groups of size 3: "111", "112", "222", and "23".
  ​​​​​Then we calculate the digit sum of each group: 1 + 1 + 1 = 3, 1 + 1 + 2 = 4, 2 + 2 + 2 = 6, and 2 + 3 = 5. 
  So, s becomes "3" + "4" + "6" + "5" = "3465" after the first round.
- For the second round, we divide s into "346" and "5".
  Then we calculate the digit sum of each group: 3 + 4 + 6 = 13, 5 = 5. 
  So, s becomes "13" + "5" = "135" after second round. 
Now, s.length <= k, so we return "135" as the answer.
```
__Example 2:__
```
Input: s = "00000000", k = 3
Output: "000"
Explanation: 
We divide s into "000", "000", and "00".
Then we calculate the digit sum of each group: 0 + 0 + 0 = 0, 0 + 0 + 0 = 0, and 0 + 0 = 0. 
s becomes "0" + "0" + "0" = "000", whose length is equal to k, so we return "000".
```
 

__Constraints:__
```
1 <= s.length <= 100
2 <= k <= 100
s consists of digits only.
```
#### EXPLANATION:

比较简单, 直接一个for循环, 如果当前index是能被k整除, 那么说明需要添加到tmps上, 否则只需要加上就行. 比较关键的点是, 如果最后不是能整除的, 需要把last的也加上.

#### SOLUTION:
```swift
class Solution {
    func digitSum(_ s: String, _ k: Int) -> String {
        var str:String = s
        while str.count > k {
            var tmp:Int = 0
            var tmpS:String = ""
            for i in 0...str.count - 1 {
                tmp += Int(String(Array(str)[i]))!
                if (i + 1) % k == 0 {
                    tmpS += String(tmp)
                    tmp = 0
                }
            }
            if str.count % k != 0 {
                tmpS += String(tmp)
            }
            str = tmpS
        }
        return str
    }
}
```

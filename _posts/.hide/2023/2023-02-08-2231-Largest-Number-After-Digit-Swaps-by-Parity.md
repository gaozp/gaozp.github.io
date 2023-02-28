---
layout: post
title: 2231. Largest Number After Digit Swaps by Parity
categories: [leetcode]
---
#### QUESTION:
You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).

Return the largest possible value of num after any number of swaps.

 

__Example 1:__
```
Input: num = 1234
Output: 3412
Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
Swap the digit 2 with the digit 4, this results in the number 3412.
Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
```
__Example 2:__
```
Input: num = 65875
Output: 87655
Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
Swap the first digit 5 with the digit 7, this results in the number 87655.
Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
 ```

__Constraints:__
```
1 <= num <= 109
```
#### EXPLANATION:

思路就是, 将最大的数字放在最前面就是最终的结果, 由于限定了只能奇偶互换, 所以, 首先将数字分成奇偶, 然后再按照原数字的奇偶顺序按大小排出来即可.

#### SOLUTION:
```swift
class Solution {
    func largestInteger(_ num: Int) -> Int {
        var odd: [Int] = []
        var even: [Int] = []
        for ch in String(num) {
            let tmp = Int(String(ch))!
            if tmp % 2 == 0 {
                even.append(tmp)
            } else {
                odd.append(tmp)
            }
        }
        var evenArr = even.sorted()
        var oddArr = odd.sorted()
        var result: String = ""
        for ch in String(num) {
            let tmp = Int(String(ch))!
            if tmp % 2 == 0 {
                result += String(evenArr.removeLast())
            } else {
                result += String(oddArr.removeLast())
            }
        }
        return Int(result)!
    }
}
```

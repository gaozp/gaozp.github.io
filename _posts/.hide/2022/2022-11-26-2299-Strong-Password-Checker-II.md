---
layout: post
title: 2299. Strong Password Checker II
categories: [leetcode]
---
#### QUESTION:
A password is said to be strong if it satisfies all the following criteria:

It has at least 8 characters.
It contains at least one lowercase letter.
It contains at least one uppercase letter.
It contains at least one digit.
It contains at least one special character. The special characters are the characters in the following string: "!@#$%^&*()-+".
It does not contain 2 of the same character in adjacent positions (i.e., "aab" violates this condition, but "aba" does not).
Given a string password, return true if it is a strong password. Otherwise, return false.

 

__Example 1:__
```
Input: password = "IloveLe3tcode!"
Output: true
Explanation: The password meets all the requirements. Therefore, we return true.
```
__Example 2:__
```
Input: password = "Me+You--IsMyDream"
Output: false
Explanation: The password does not contain a digit and also contains 2 of the same character in adjacent positions. Therefore, we return false.
```
__Example 3:__
```
Input: password = "1aB!"
Output: false
Explanation: The password does not meet the length requirement. Therefore, we return false.
```
 

__Constraints:__
```
1 <= password.length <= 100
password consists of letters, digits, and special characters: "!@#$%^&*()-+".
```
#### EXPLANATION:

只要按着题目, 将所有的check写出来即可.

#### SOLUTION:
```swift
class Solution {
    func strongPasswordCheckerII(_ password: String) -> Bool {
        if password.count < 8 {
            return false
        }
        var containsLower = false
        var containsUpper = false
        var containsDigital = false
        var containsSpecial = false
        var twoSame = false
        var pre:Character = "."
        for pa in password {
            var asciiVal = pa.asciiValue!
            if asciiVal >= 65 && asciiVal <= 90 {
                containsUpper = true
            }
            if asciiVal >= 97 && asciiVal <= 122 {
                containsLower = true
            }
            if asciiVal >= 48 && asciiVal <= 57 {
                containsDigital = true
            }
            if "!@#$%^&*()-+".contains(pa) {
                containsSpecial = true
            }
            if pre == pa {
                twoSame = true
            }
            pre = pa
        }
        return containsLower && containsUpper && containsDigital && containsSpecial && !twoSame
    }
}
```

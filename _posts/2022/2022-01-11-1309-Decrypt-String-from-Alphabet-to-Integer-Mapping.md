---
layout: post
title: 1309. Decrypt String from Alphabet to Integer Mapping
categories: [leetcode]
---
#### QUESTION:
You are given a string s formed by digits and '#'. We want to map s to English lowercase characters as follows:

Characters ('a' to 'i') are represented by ('1' to '9') respectively.
Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
Return the string formed after mapping.

The test cases are generated so that a unique mapping will always exist.

 

__Example 1:__
```
Input: s = "10#11#12"
Output: "jkab"
Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
```
__Example 2:__
```
Input: s = "1326#"
Output: "acz"
 ```

__Constraints:__
```
1 <= s.length <= 1000
s consists of digits and the '#' letter.
s will be a valid string such that mapping is always possible.
```
#### EXPLANATION:
这个其实有一个非常取巧的思路, 因为正向的话, 我们需要去查看后面两位是否有#, 但是如果是反向的话, 那么#就在前面. 最后再将结果返回来就可以了.

#### SOLUTION:
```swift
class Solution {
    var alphabeta:[String] = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n",
                                        "o","p","q","r","s","t","u","v","w","x","y","z"]
    var alphabetaRepresent:[String] = ["1","2","3","4","5","6","7","8","9","#01","#11","#21","#31","#41","#51","#61","#71","#81","#91","#02",
                                            "#12","#22","#32","#42","#52","#62"]
    func freqAlphabets(_ s: String) -> String {
        var text:[Character] = s.reversed()
        var index:Int = 0
        var result:String = ""
        while index < text.count {
            var tmp:String = ""
            tmp += text[index].description
            index += 1
            if tmp == "#" {
                tmp += text[index].description
                tmp += text[index+1].description
                index += 2
            }
            result += alphabeta[alphabetaRepresent.index(of: tmp)!]
        }
        return String(result.reversed())
    }
}
```

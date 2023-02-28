---
layout: post
title: 1859. Sorting the Sentence
categories: [leetcode]
---
#### QUESTION:
A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of lowercase and uppercase English letters.

A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging the words in the sentence.

For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.

 

__Example 1:__
```
Input: s = "is2 sentence4 This1 a3"
Output: "This is a sentence"
Explanation: Sort the words in s to their original positions "This1 is2 a3 sentence4", then remove the numbers.
```
__Example 2:__
```
Input: s = "Myself2 Me1 I4 and3"
Output: "Me Myself and I"
Explanation: Sort the words in s to their original positions "Me1 Myself2 and3 I4", then remove the numbers.
 ```

__Constraints:__
```
2 <= s.length <= 200
s consists of lowercase and uppercase English letters, spaces, and digits from 1 to 9.
The number of words in s is between 1 and 9.
The words in s are separated by a single space.
s contains no leading or trailing spaces.
```
#### EXPLANATION:

这道题目也是简单题目, 主要是考察数组的操作.   
1.首先将原来的string进行排序, comparator是字符串的最后一位  
2.然后对排序后的数组进行操作  
3.将最后一位替换成空格,同时添加在result上  
4.如果是数组的最后一个,那么就删除最后一位即可,添加到result上  
5.返回result  

#### SOLUTION:
```swift
class Solution {
    func sortSentence(_ s: String) -> String {
        var result = ""
        var substrings:[Substring] = s.split(separator: " ")
        let sstrings = substrings.sorted{(a1:Substring,a2:Substring) -> Bool in
            return Int(String(Array(String(a1)).last!))! < Int(String(Array(String(a2)).last!))!
        }
        for indice in sstrings.indices {
            var tmpArray = Array(String(sstrings[indice]))
            if indice == sstrings.count-1 {
                tmpArray = tmpArray.dropLast()
            } else {
                tmpArray[tmpArray.count-1] = " "
            }
            result += String(tmpArray)
        }
        return result
    }
}
```

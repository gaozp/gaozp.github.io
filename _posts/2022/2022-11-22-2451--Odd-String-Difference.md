---
layout: post
title: 2451. Odd String Difference
categories: [leetcode]
---
#### QUESTION:
You are given an array of equal-length strings words. Assume that the length of each string is n.

Each string words[i] can be converted into a difference integer array difference[i] of length n - 1 where difference[i][j] = words[i][j+1] - words[i][j] where 0 <= j <= n - 2. Note that the difference between two letters is the difference between their positions in the alphabet i.e. the position of 'a' is 0, 'b' is 1, and 'z' is 25.

For example, for the string "acb", the difference integer array is [2 - 0, 1 - 2] = [2, -1].
All the strings in words have the same difference integer array, except one. You should find that string.

Return the string in words that has different difference integer array.

__Example 1:__
```
Input: words = ["adc","wzy","abc"]
Output: "abc"
Explanation: 
- The difference integer array of "adc" is [3 - 0, 2 - 3] = [3, -1].
- The difference integer array of "wzy" is [25 - 22, 24 - 25]= [3, -1].
- The difference integer array of "abc" is [1 - 0, 2 - 1] = [1, 1]. 
The odd array out is [1, 1], so we return the corresponding string, "abc".
```
__Example 2:__
```
Input: words = ["aaa","bob","ccc","ddd"]
Output: "bob"
Explanation: All the integer arrays are [0, 0] except for "bob", which corresponds to [13, -13].
```
 

__Constraints:__
```
3 <= words.length <= 100
n == words[i].length
2 <= n <= 20
words[i] consists of lowercase English letters.
```
#### EXPLANATION:

虽然是一道easy的题目, 但是逻辑还是挺复杂的.   
1. 将所有word的difference算出来, 放在一个数组里, 这样也可以对应出对应的index  
2. 将对应的difference和index放到一个字典中  
3. 遍历字典找到只有一个的index, 这个index就是我们要找的index  
4. 返回这个words中的index

#### SOLUTION:
```swift
class Solution {
    func oddString(_ words: [String]) -> String {
        var arr: [[Int]] = []
        for word in words {
            var tmp:[Int] = []
            let tmpArr = Array(word)
            for index in tmpArr.indices {
                if (index != 0) {
                    let a:Int = Int(tmpArr[index].asciiValue!)
                    let b:Int = Int(tmpArr[index - 1].asciiValue!)
                    let c = Int(a - b)
                    tmp.append(c)
                }
            }
            arr.append(tmp)
        }
        var dic:Dictionary<[Int], [Int]> = [:]
        for aIndex in arr.indices {
            dic[arr[aIndex], default: []].append(aIndex)
        }
        var resultIndex: Int = -1
        for d in dic {
            if d.value.count == 1 {
                resultIndex = d.value[0]
                break
            }
        }
        return words[resultIndex]
    }
}
```

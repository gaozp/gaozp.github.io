---
layout: post
title: 1286. Iterator for Combination
categories: [leetcode]
---
#### QUESTION:
Design the CombinationIterator class:

CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
next() Returns the next combination of length combinationLength in lexicographical order.
hasNext() Returns true if and only if there exists a next combination.
 

__Example 1:__
```
Input
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
Output
[null, "ab", true, "ac", true, "bc", false]


Explanation
CombinationIterator itr = new CombinationIterator("abc", 2);
itr.next();    // return "ab"
itr.hasNext(); // return True
itr.next();    // return "ac"
itr.hasNext(); // return True
itr.next();    // return "bc"
itr.hasNext(); // return False
 ```

__Constraints:__
```
1 <= combinationLength <= characters.length <= 15
All the characters of characters are unique.
At most 104 calls will be made to next and hasNext.
It is guaranteed that all calls of the function next are valid.
```
#### EXPLANATION:

提示中提到了用bit mask, 那么就用bitmask做一下, 其实简单的是用动态规划的回溯去做会比较简单一点. 

#### SOLUTION:
```swift

class CombinationIterator {

        var result:[String] = []
        var pointer:Int = -1

        init(_ characters: String, _ combinationLength: Int) {
            var string:String = ""
            for i in 1...characters.count {
                string = string + "1"
            }
            var count:Int = Int(string, radix: 2)!
            var chars:[Character] = Array(characters)
            for i in 1...count {
                if i.nonzeroBitCount == combinationLength {
                    var mask:String = String(String(i, radix: 2).reversed())
                    var maskArr:[Character] = Array(mask)
                    var tmpResult:String = ""
                    for j in 0..<mask.count {
                        if (maskArr[j] == "1") {
                            tmpResult = tmpResult +  String(chars[j])
                        }
                    }
                    result.append(tmpResult)
                }
            }
            result = result.sorted()
        }
        
        func next() -> String {
            pointer += 1
            return result[pointer]
        }
        
        func hasNext() -> Bool {
            return pointer < result.count - 1
        }
}
```

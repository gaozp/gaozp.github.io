---
layout: post
title: 2138. Divide a String Into Groups of Size k
categories: [leetcode]
---
#### QUESTION:
A string s can be partitioned into groups of size k using the following procedure:

The first group consists of the first k characters of the string, the second group consists of the next k characters of the string, and so on. Each character can be a part of exactly one group.
For the last group, if the string does not have k characters remaining, a character fill is used to complete the group.
Note that the partition is done so that after removing the fill character from the last group (if it exists) and concatenating all the groups in order, the resultant string should be s.

Given the string s, the size of each group k and the character fill, return a string array denoting the composition of every group s has been divided into, using the above procedure.

 

__Example 1:__
```
Input: s = "abcdefghi", k = 3, fill = "x"
Output: ["abc","def","ghi"]
Explanation:
The first 3 characters "abc" form the first group.
The next 3 characters "def" form the second group.
The last 3 characters "ghi" form the third group.
Since all groups can be completely filled by characters from the string, we do not need to use fill.
Thus, the groups formed are "abc", "def", and "ghi".
```
__Example 2:__
```
Input: s = "abcdefghij", k = 3, fill = "x"
Output: ["abc","def","ghi","jxx"]
Explanation:
Similar to the previous example, we are forming the first three groups "abc", "def", and "ghi".
For the last group, we can only use the character 'j' from the string. To complete this group, we add 'x' twice.
Thus, the 4 groups formed are "abc", "def", "ghi", and "jxx".
 ```

__Constraints:__
```
1 <= s.length <= 100
s consists of lowercase English letters only.
1 <= k <= 100
fill is a lowercase English letter.
```
#### EXPLANATION:

比较简单,首先如果不足分割, 那么久补上对应的fill. 再进行切割就可以.

#### SOLUTION:
```swift
class Solution {
    func divideString(_ s: String, _ k: Int, _ fill: Character) -> [String] {
        var result:[String] = []
        var tmpS:String = s
        if s.count % k != 0 {
            for index in 1...(k - s.count % k) {
                tmpS.append(fill)
            }
        }
        
        for index in stride(from: 0, to: tmpS.count, by: k) {
            result.append((tmpS as NSString).substring(with: NSMakeRange(index, k)))
        }
        return result
    }
}
```

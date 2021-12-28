---
layout: post
title: 1299. Replace Elements with Greatest Element on Right Side
categories: [leetcode]
---
#### QUESTION:
Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.

 

__Example 1:__
```
Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
Explanation: 
- index 0 --> the greatest element to the right of index 0 is index 1 (18).
- index 1 --> the greatest element to the right of index 1 is index 4 (6).
- index 2 --> the greatest element to the right of index 2 is index 4 (6).
- index 3 --> the greatest element to the right of index 3 is index 4 (6).
- index 4 --> the greatest element to the right of index 4 is index 5 (1).
- index 5 --> there are no elements to the right of index 5, so we put -1.
```
__Example 2:__
```
Input: arr = [400]
Output: [-1]
Explanation: There are no elements to the right of index 0.
 ```

__Constraints:__
```
1 <= arr.length <= 104
1 <= arr[i] <= 105
```
#### EXPLANATION:
逻辑比较简单, 就不说了. 这里可以有的一个优化点是, 我这里采用的是每次往数组前面进行添加, 因为数组是固定长度的. 每次需要重新分配. 所以可以有的一个优化点是一开始就将数组的长度固定成arr的长度. 这样直接填充就可以了. 减少了扩容数组的时间. 

#### SOLUTION:
```swift
class Solution {
    func replaceElements(_ arr: [Int]) -> [Int] {
        var result:[Int] = []
        var pre:Int = -1
        for index in stride(from: arr.count-1, through: 0, by: -1) {
            result.insert(pre, at: 0)
            if arr[index] > pre {
                pre = arr[index]
            }
        }
        return result
    }
}
```

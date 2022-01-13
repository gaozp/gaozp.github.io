---
layout: post
title: 1356. Sort Integers by The Number of 1 Bits
categories: [leetcode]
---
#### QUESTION:
You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.

Return the array after sorting it.

 

__Example 1:__
```
Input: arr = [0,1,2,3,4,5,6,7,8]
Output: [0,1,2,4,8,3,5,6,7]
Explantion: [0] is the only integer with 0 bits.
[1,2,4,8] all have 1 bit.
[3,5,6] have 2 bits.
[7] has 3 bits.
The sorted array by bits is [0,1,2,4,8,3,5,6,7]
```
__Example 2:__
```
Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
Output: [1,2,4,8,16,32,64,128,256,512,1024]
Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.
```

__Constraints:__
```
1 <= arr.length <= 500
0 <= arr[i] <= 10^4
```
#### EXPLANATION:
这也是一个简单题目
1. 创建一个二维数组,算出每个数字对应的1的数量
2. 对二维数组进行排序,首先排序1的数量, 再对数字本身进行排序
3. 最后进行数字的提取

#### SOLUTION:
```swift
class Solution {
    func sortByBits(_ arr: [Int]) -> [Int] {
        var bitArr:[[Int]] = []
        var result:[Int] = []
        for item in arr {
            var tmpString:String = String(item,radix: 2)
            var sum:Int = 0;
            tmpString.forEach { c in
                sum += Int(String(c))!
            }
            bitArr.append([item,sum])
        }
        bitArr.sort { arr1, arr2 in
            return arr1[1] < arr2[1] || (arr1[1] == arr2[1] && arr1[0] < arr2[0])
        }
        bitArr.forEach { arr in
            result.append(arr[0])
        }
        return result
    }
}
```

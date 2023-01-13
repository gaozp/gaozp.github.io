---
layout: post
title: 1338. Reduce Array Size to The Half
categories: [leetcode]
---
#### QUESTION:
You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

 

__Example 1:__
```
Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
```
__Example 2:__
```
Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.
```
 

__Constraints:__
```
2 <= arr.length <= 105
arr.length is even.
1 <= arr[i] <= 105
```
#### EXPLANATION:

根据题意, 就可以得到. 将数组按照数量进行排序, 然后看后1/2有几种即可. 这样就是最少的. 思路出来了, 那其他的就没有什么难的了.

#### SOLUTION:
```swift
class Solution {
    func minSetSize(_ arr: [Int]) -> Int {
        var dic:Dictionary<Int, Int> = [:]
        for ar in arr {
            dic[ar] = dic[ar, default: 0] + 1
        }
        let sorted = dic.sorted { a, b in
            a.value < b.value
        }
        var sortArr:[Int] = []
        for sort in sorted {
            for _ in 1...sort.value {
                sortArr.append(sort.key)
            }
        }
        var set = Set<Int>()
        for i in arr.count/2...arr.count-1 {
            set.insert(sortArr[i])
        }
        return set.count
    }
}
```

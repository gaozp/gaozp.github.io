---
layout: post
title: 179. Largest Number
categories: [leetcode]
---
#### QUESTION:
Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

 

__Example 1:__
```
Input: nums = [10,2]
Output: "210"
```
__Example 2:__
```
Input: nums = [3,30,34,5,9]
Output: "9534330"
```
 

__Constraints:__
```
1 <= nums.length <= 100
0 <= nums[i] <= 109
```

#### EXPLANATION:

思路还是比较清晰, 就是判断两个数, 然后排序后组合就可以. 难点就在判断两个数. 
如果每个位置都去进行判断, 那么长度太大, 复杂度太高. 那么这里就需要有一个思路: 
两个数拼起来排序, 那么就可以简化成:
```
a+b > b+a
```
这样就能简单的拿到两者的比较值了, 进行排序就可以直接得到结果. 

#### SOLUTION:
```swift
class Solution {
    func largestNumber(_ nums: [Int]) -> String {
       nums.first{ $0 > 0 } != nil ? nums.map(String.init).sorted { a, b in
            a+b > b+a
        }.joined() : "0"
    }
}
```

---
layout: post
title: 215. Kth Largest Element in an Array
categories: [leetcode]
---
#### QUESTION:
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

You must solve it in O(n) time complexity.

 

__Example 1:__
```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
```
__Example 2:__
```
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
```
 

__Constraints:__
```
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
```

#### EXPLANATION:

#### SOLUTION:
```swift
class Solution {
    func findKthLargest(_ nums: [Int], _ k: Int) -> Int {
        var nums = nums
        var start = 0
        var end = nums.count-1
        
        var index = nums.count-k
        while(start <= end){
            var position = partition(&nums, start, end)
         
            if(position == index){
                return nums[position]
            }else if(position < index){
                start = position + 1
            }else{
                end = position - 1
            }
        }
        return -1
    }
    
    func partition(_ nums: inout [Int], _ start: Int, _ end:  Int) -> Int{
        var index = start
        var pivot = nums[start]
        var start = start
        var end = end
        
        while(start <= end){
            
            while(start <= end && nums[start] <= pivot){
                start += 1
            }
            while(start <= end && nums[end] >= pivot){
                end -= 1
            }
            if(start <= end){
                swap(&nums, start, end)
                start += 1
                end -= 1
            }
        }
        swap(&nums, index, end)
        return end
    }
    
    func swap(_ nums: inout [Int], _ start: Int, _ end: Int){
        let temp = nums[start]
        nums[start]  = nums[end]
        nums[end] = temp
    }
}
```

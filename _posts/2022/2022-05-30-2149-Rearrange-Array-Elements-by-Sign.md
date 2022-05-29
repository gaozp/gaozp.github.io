---
layout: post
title: 2149. Rearrange Array Elements by Sign
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.

You should rearrange the elements of nums such that the modified array follows the given conditions:

Every consecutive pair of integers have opposite signs.
For all integers with the same sign, the order in which they were present in nums is preserved.
The rearranged array begins with a positive integer.
Return the modified array after rearranging the elements to satisfy the aforementioned conditions.

 

__Example 1:__
```
Input: nums = [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
Explanation:
The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions. 
``` 
__Example 2:__
```
Input: nums = [-1,1]
Output: [1,-1]
Explanation:
1 is the only positive integer and -1 the only negative integer in nums.
So nums is rearranged to [1,-1].
 ```

__Constraints:__
```
2 <= nums.length <= 2 * 105
nums.length is even
1 <= |nums[i]| <= 105
nums consists of equal number of positive and negative integers.
```
#### EXPLANATION:

虽然是medium的题目,但是也是比较简单的. 首先将数组拆分成两个数组. 然后再单独进行组合到一起. 不过如果swift的api中有stack数据结构的话, 会更容易点. 这样就可以直接stack popfirst了. 当然也可以自己写一个这样的数据结构. 会比自己主动去维护index容易一点. 

#### SOLUTION:
```swift
class Solution {
    func rearrangeArray(_ nums: [Int]) -> [Int] {
        var tmpOppo:[Int] = []
        var tmpNega:[Int] = []
        var flag:Bool = true
        var result:[Int] = []
        for num in nums {
            if num > 0 {
                tmpOppo.append(num)
            } else {
                tmpNega.append(num)
            }
        }
        var oppoIndex:Int = 0
        var negaIndex:Int = 0
        for _ in 0...nums.count-1 {
            if flag {
                result.append(tmpOppo[oppoIndex])
                oppoIndex += 1
            } else {
                result.append(tmpNega[negaIndex])
                negaIndex += 1
            }
            flag = !flag
        }
        return result
    }
}
```

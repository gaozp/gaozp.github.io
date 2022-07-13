---
layout: post
title: 1475. Final Prices With a Special Discount in a Shop
categories: [leetcode]
---
#### QUESTION:
Given the array prices where prices[i] is the price of the ith item in a shop. There is a special discount for items in the shop, if you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i], otherwise, you will not receive any discount at all.

Return an array where the ith element is the final price you will pay for the ith item of the shop considering the special discount.

 

__Example 1:__
```
Input: prices = [8,4,6,2,3]
Output: [4,2,4,2,3]
Explanation: 
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4. 
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2. 
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4. 
For items 3 and 4 you will not receive any discount at all.
```
__Example 2:__
```
Input: prices = [1,2,3,4,5]
Output: [1,2,3,4,5]
Explanation: In this case, for all items, you will not receive any discount at all.
```
__Example 3:__
```
Input: prices = [10,1,1,6]
Output: [9,0,1,6]
```
 

__Constraints:__
```
1 <= prices.length <= 500
1 <= prices[i] <= 10^3
```
#### EXPLANATION:

easy的题目， 两个for循环就可以。第二个我用得是while循环， 在while循环的时候， 如果价格比当前价格低 ，那么就把折扣算上， 同时跳出循环即可。 

#### SOLUTION:
```swift
class Solution {
    func finalPrices(_ prices: [Int]) -> [Int] {
        var result:[Int] = []
        for index in prices.indices {
            var originPrice:Int = prices[index]
            var tmpIndex = index + 1
            while tmpIndex < prices.count {
                var discountPrice = prices[tmpIndex]
                if (originPrice >= discountPrice) {
                    originPrice = originPrice - discountPrice
                    break
                }
                tmpIndex += 1
            }
            result.append(originPrice)
        }
        return result
    }
}
```

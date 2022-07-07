---
layout: post
title: 1710. Maximum Units on a Truck
categories: [leetcode]
---
#### QUESTION:
You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:

numberOfBoxesi is the number of boxes of type i.
numberOfUnitsPerBoxi is the number of units in each box of the type i.
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.

 

__Example 1:__
```
Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
```
__Example 2:__
```
Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10  
Output: 91
```

__Constraints:__
```
1 <= boxTypes.length <= 1000
1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
1 <= truckSize <= 10^6
```
#### EXPLANATION:

easy的题目， 还是有点意思的。主要就是考虑一个单位能够装下最多的unit， 所以只需要对数组第二位进行排序。 然后反向开始装， 那么就能装到最多的unit了。 

#### SOLUTION:
```swift
class Solution {
    func maximumUnits(_ boxTypes: [[Int]], _ truckSize: Int) -> Int {
        let arr:[[Int]] = boxTypes.sorted { a, b in
            a[1] < b[1]
        }
        var result:Int = 0
        var size:Int = truckSize
        for index in stride(from: arr.count - 1, to: -1, by: -1) {
            var tmp = arr[index]
            if (size > 0) {
                result += size - tmp[0] >= 0 ? tmp[0] * tmp[1] : size * tmp[1]
                size = size - tmp[0]
            }
        }
        return result
    }
}
```

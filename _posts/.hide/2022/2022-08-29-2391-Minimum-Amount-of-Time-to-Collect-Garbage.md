---
layout: post
title: 2391. Minimum Amount of Time to Collect Garbage
categories: [leetcode]
---
#### QUESTION:
You are given a 0-indexed array of strings garbage where garbage[i] represents the assortment of garbage at the ith house. garbage[i] consists only of the characters 'M', 'P' and 'G' representing one unit of metal, paper and glass garbage respectively. Picking up one unit of any type of garbage takes 1 minute.

You are also given a 0-indexed integer array travel where travel[i] is the number of minutes needed to go from house i to house i + 1.

There are three garbage trucks in the city, each responsible for picking up one type of garbage. Each garbage truck starts at house 0 and must visit each house in order; however, they do not need to visit every house.

Only one garbage truck may be used at any given moment. While one truck is driving or picking up garbage, the other two trucks cannot do anything.

Return the minimum number of minutes needed to pick up all the garbage.

 

__Example 1:__
```
Input: garbage = ["G","P","GP","GG"], travel = [2,4,3]
Output: 21
Explanation:
The paper garbage truck:
1. Travels from house 0 to house 1
2. Collects the paper garbage at house 1
3. Travels from house 1 to house 2
4. Collects the paper garbage at house 2
Altogether, it takes 8 minutes to pick up all the paper garbage.
The glass garbage truck:
1. Collects the glass garbage at house 0
2. Travels from house 0 to house 1
3. Travels from house 1 to house 2
4. Collects the glass garbage at house 2
5. Travels from house 2 to house 3
6. Collects the glass garbage at house 3
Altogether, it takes 13 minutes to pick up all the glass garbage.
Since there is no metal garbage, we do not need to consider the metal garbage truck.
Therefore, it takes a total of 8 + 13 = 21 minutes to collect all the garbage.
```
__Example 2:__
```
Input: garbage = ["MMM","PGM","GP"], travel = [3,10]
Output: 37
Explanation:
The metal garbage truck takes 7 minutes to pick up all the metal garbage.
The paper garbage truck takes 15 minutes to pick up all the paper garbage.
The glass garbage truck takes 15 minutes to pick up all the glass garbage.
It takes a total of 7 + 15 + 15 = 37 minutes to collect all the garbage.
 ```

__Constraints:__
```
2 <= garbage.length <= 105
garbage[i] consists of only the letters 'M', 'P', and 'G'.
1 <= garbage[i].length <= 10
travel.length == garbage.length - 1
1 <= travel[i] <= 100
```
#### EXPLANATION:

其实思路蛮简单的, 就是中间有一个点需要注意, 就是如果这个garbage点没有对应的, 那么也需要把distance算上, 这样在下次遇到的时候才能加上. 不然就计算错误了.

#### SOLUTION:
```swift
class Solution {
    func garbageCollection(_ garbage: [String], _ travel: [Int]) -> Int {
        // 用来记录结果
        var resultP:Int = 0
        var resultM:Int = 0
        var resultG:Int = 0
        // 用来记录距离
        var distanceP:Int = 0
        var distanceG:Int = 0
        var distanceM:Int = 0
        
        for index in garbage.indices {
            let gar:String = garbage[index]
            // 每次都需要记录是否发现, 没发现也需要把distance算上, 否则后面再发现还得往前倒
            var foundP:Bool = false
            var foundG:Bool = false
            var foundM:Bool = false
            for g in gar {
                switch g {
                case "P":
                    resultP+=1
                    foundP = true
                    break
                case "G":
                    resultG += 1
                    foundG = true
                    break
                case "M":
                    resultM += 1
                    foundM = true
                    break
                default:
                    break
                }
            }
            if index > 0 {
                distanceG += travel[index - 1]
                distanceM += travel[index - 1]
                distanceP += travel[index - 1]
            }
            if foundM {
                resultM += distanceM
                distanceM = 0
            }
            if foundG {
                resultG += distanceG
                distanceG = 0
            }
            if foundP {
                resultP += distanceP
                distanceP = 0
            }
            
        }
        return resultP + resultG + resultM
    }
}
```

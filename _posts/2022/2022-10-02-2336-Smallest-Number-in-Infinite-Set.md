---
layout: post
title: 2336. Smallest Number in Infinite Set
categories: [leetcode]
---
#### QUESTION:
You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

Implement the SmallestInfiniteSet class:

SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
int popSmallest() Removes and returns the smallest integer contained in the infinite set.
void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 

__Example 1:__
```
Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]


Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
```
 

__Constraints:__
```
1 <= num <= 1000
At most 1000 calls will be made in total to popSmallest and addBack.
```
#### EXPLANATION:

根据限制可以知道, num只有1000个, 那么我们就可以确定数据结构. 用array来进行. 如果pop出去了, 那么就改为false, 然后返回第一个为true的就可以. index就是对应的数字即可.

#### SOLUTION:
```kotlin
class SmallestInfiniteSet() {

        var arr:BooleanArray = BooleanArray(1001 ){ i -> true}

        init {
            arr[0] = false
        }
        
        fun popSmallest(): Int {
            var tmp:Int = 0
            while (!arr[tmp]) {
                tmp++
            }
            arr[tmp] = false
            return tmp
        }

        fun addBack(num: Int) {
            arr[num] = true
        }

}

```

---
layout: post
title: 1603. Design Parking System
categories: [leetcode]
---
#### QUESTION:
Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.

Implement the ParkingSystem class:

ParkingSystem(int big, int medium, int small) Initializes object of the ParkingSystem class. The number of slots for each parking space are given as part of the constructor.
bool addCar(int carType) Checks whether there is a parking space of carType for the car that wants to get into the parking lot. carType can be of three kinds: big, medium, or small, which are represented by 1, 2, and 3 respectively. A car can only park in a parking space of its carType. If there is no space available, return false, else park the car in that size space and return true.
 

__Example 1:__
```
Input
["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
[[1, 1, 0], [1], [2], [3], [1]]
Output
[null, true, true, false, false]
```
__Explanation__
```
ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
parkingSystem.addCar(1); // return true because there is 1 available slot for a big car
parkingSystem.addCar(2); // return true because there is 1 available slot for a medium car
parkingSystem.addCar(3); // return false because there is no available slot for a small car
parkingSystem.addCar(1); // return false because there is no available slot for a big car. It is already occupied.
 ```

__Constraints:__
```
0 <= big, medium, small <= 1000
carType is 1, 2, or 3
At most 1000 calls will be made to addCar
```
#### EXPLANATION:
哈哈,最近在学习kotlin,所以题目都是选择的比较简单的. 这道题目也很简单,就不多说啦.

#### SOLUTION:
```java
class ParkingSystem(big: Int, medium: Int, small: Int) {

    var slots : Array<Int> = arrayOf(big,medium,small)

    fun addCar(carType: Int): Boolean {
        when (carType) {
            1 -> {
                slots[0]--
                return slots[0] >= 0
            }
            2 -> {
                slots[1]--
                return slots[1] >= 0
            }
            3 -> {
                slots[2]--
                return slots[2] >= 0
            }
        }
        return false;
    }

}

```

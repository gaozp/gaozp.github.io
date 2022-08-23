---
layout: post
title: 1700. Number of Students Unable to Eat Lunch
categories: [leetcode]
---
#### QUESTION:
The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.

 

__Example 1:__
```
Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0 
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
```
__Example 2:__
```
Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3
```
 

__Constraints:__
```
1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
```
#### EXPLANATION:

这道题目有一个误导点, 就是如果第一个人拿不到, 就需要排到最后. 但是我们整理下来看一下, 三明治的最上面的不会变, 所以也还是一次遍历过程. 在计算机的角度, 时间复杂度也还是O(n),那么其实完全可以胜率这个步骤. 那么就简单了. for循环去找三明治对应的人, 如果没找到, 说明已经没有办法再循环了. 直接将总人数-找到的人数即可.

#### SOLUTION:
```swift
class Solution {
    func countStudents(_ students: [Int], _ sandwiches: [Int]) -> Int {
        var result:Int = 0
        var students:[Int] = students
        var sandwiches:[Int] = sandwiches
        for sandwich in sandwiches {
            var found:Bool = false
            for index in students.indices {
                if students[index] == sandwich {
                    found = true
                    students.remove(at: index)
                    break
                }
            }
            if found {
                result += 1
            } else {
                break
            }
        }
        
        return sandwiches.count - result
    }
}
```

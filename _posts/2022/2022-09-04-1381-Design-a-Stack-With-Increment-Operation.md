---
layout: post
title: 1381. Design a Stack With Increment Operation
categories: [leetcode]
---
#### QUESTION:
Design a stack which supports the following operations.

Implement the CustomStack class:

CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack or do nothing if the stack reached the maxSize.
void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
int pop() Pops and returns the top of stack or -1 if the stack is empty.
void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, just increment all the elements in the stack.
 

__Example 1:__
```
Input
["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
Output
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
Explanation
CustomStack customStack = new CustomStack(3); // Stack is Empty []
customStack.push(1);                          // stack becomes [1]
customStack.push(2);                          // stack becomes [1, 2]
customStack.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
customStack.push(2);                          // stack becomes [1, 2]
customStack.push(3);                          // stack becomes [1, 2, 3]
customStack.push(4);                          // stack still [1, 2, 3], Don't add another elements as size is 4
customStack.increment(5, 100);                // stack becomes [101, 102, 103]
customStack.increment(2, 100);                // stack becomes [201, 202, 103]
customStack.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
customStack.pop();                            // return 202 --> Return top of the stack 102, stack becomes [201]
customStack.pop();                            // return 201 --> Return top of the stack 101, stack becomes []
customStack.pop();                            // return -1 --> Stack is empty return -1.
 ```

__Constraints:__
```
1 <= maxSize <= 1000
1 <= x <= 1000
1 <= k <= 1000
0 <= val <= 100
At most 1000 calls will be made to each method of increment, push and pop each separately.
```
#### EXPLANATION:

其实也挺简单的, 因为swift的array本身就提供了pop等操作, 所以比较简单, 关键是选择底层数据结构吧, 底层其实最好还是选择array合适一点, 因为需要进行pop的操作, 那么就需要知道前一个, 链表的数据结构也就不太合适了.

#### SOLUTION:
```swift

class CustomStack {

    var maxSize: Int = 0
    var array:[Int] = []
    init(_ maxSize: Int) {
        self.maxSize = maxSize
    }
        
    func push(_ x: Int) {
        if array.count == maxSize {
            return
        }
        array.append(x)
    }
        
    func pop() -> Int {
        return array.popLast() ?? -1 
    }
        
    func increment(_ k: Int, _ val: Int) {
        for index in 0...k-1 {
            if (index <= array.count - 1 ) {
                array[index] += val
            }
        }
    }
}
```

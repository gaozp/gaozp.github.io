---
layout: post
title: 2130. Maximum Twin Sum of a Linked List
categories: [leetcode]
---
#### QUESTION:
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2021/12/03/eg1drawio.png)
```
Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6.
``` 
__Example 2:__
![](https://assets.leetcode.com/uploads/2021/12/03/eg2drawio.png)
```
Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 
```
__Example 3:__
![](https://assets.leetcode.com/uploads/2021/12/03/eg3drawio.png)
```
Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 ```

__Constraints:__
```
The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105
```
#### EXPLANATION:

虽然是medium的题目, 但是swift的数组是可变的. 所以直接便利一遍, 装到数组里. 然后再进行头尾相加就可以了. 

#### SOLUTION:
```swift
class Solution {
    func pairSum(_ head: ListNode?) -> Int {
        var array:[Int] = []
        var tmp:ListNode? = head
        while tmp != nil {
            array.append(tmp!.val)
            tmp = tmp?.next
        }
        var result:Int = 0
        for i in 0...array.count-1 {
            result = max(result, array[i] + array[array.count-1-i])
        }
        return result
    }
}
```

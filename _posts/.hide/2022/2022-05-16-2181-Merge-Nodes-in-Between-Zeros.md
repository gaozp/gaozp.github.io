---
layout: post
title: 2181. Merge Nodes in Between Zeros
categories: [leetcode]
---
#### QUESTION:
You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and end of the linked list will have Node.val == 0.

For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes. The modified list should not contain any 0's.

Return the head of the modified linked list.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2022/02/02/ex1-1.png)
```
Input: head = [0,3,1,0,4,5,2,0]
Output: [4,11]
Explanation: 
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 3 + 1 = 4.
- The sum of the nodes marked in red: 4 + 5 + 2 = 11.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2022/02/02/ex2-1.png)
```
Input: head = [0,1,0,3,0,2,2,0]
Output: [1,3,4]
Explanation: 
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 1 = 1.
- The sum of the nodes marked in red: 3 = 3.
- The sum of the nodes marked in yellow: 2 + 2 = 4.
 ```

__Constraints:__
```
The number of nodes in the list is in the range [3, 2 * 105].
0 <= Node.val <= 1000
There are no two consecutive nodes with Node.val == 0.
The beginning and end of the linked list have Node.val == 0.
```
#### EXPLANATION:
虽然是medium的题目, 但是其实没有多难, 就是每次遇到0将前面的累加的值创建一个node链接上, 这样就可以了. 
#### SOLUTION:
```java
class Solution {
    func mergeNodes(_ head: ListNode?) -> ListNode? {
        var result:ListNode = ListNode(-1)
        var tmpResult:ListNode = result
        var tmp:ListNode? = head
        var tmpVal:Int = 0;
        while tmp != nil {
            if (tmp?.val == 0) {
                tmpResult.next = ListNode(tmpVal)
                tmpResult = tmpResult.next!
                tmpVal = 0
            } else {
                tmpVal += tmp!.val
            }
            tmp = tmp?.next
        }
        return result.next?.next
    }
}
```

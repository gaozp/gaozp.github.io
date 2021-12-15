---
layout: post
title: 147. Insertion Sort List
categories: [leetcode]
---
#### QUESTION:
Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.

The steps of the insertion sort algorithm:

Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
It repeats until no input elements remain.
The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
![](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)

 

__Example 1:__

![](https://assets.leetcode.com/uploads/2021/03/04/sort1linked-list.jpg)
```
Input: head = [4,2,1,3]
Output: [1,2,3,4]
```
__Example 2:__

![](https://assets.leetcode.com/uploads/2021/03/04/sort2linked-list.jpg)
```
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
```
 

__Constraints:__

The number of nodes in the list is in the range [1, 5000].
-5000 <= Node.val <= 5000
#### EXPLANATION:
思路如代码所示, 增加了注释来解释

#### SOLUTION:
```swift
class Solution {
    func insertionSortList(_ head: ListNode?) -> ListNode? {
       if head == nil {
           return nil
       }
       
       let dummy = ListNode(0)
       var cur: ListNode? = head
       var pre: ListNode? = dummy
       var next: ListNode? = nil
       // 新建一条list, 头是dummy, 然后按顺序往后排
       while cur != nil {
           next = cur?.next
           // 按顺序查找当前的cur应该在的位置
           while pre?.next != nil && pre!.next!.val < cur!.val {
               pre = pre?.next
           }
           // 将cur插入到dummy为头的链中
           cur?.next = pre?.next
           pre?.next = cur
           // 将pre重置为dummy头
           pre = dummy
           cur = next
       }
       
       return dummy.next
    }
}
```

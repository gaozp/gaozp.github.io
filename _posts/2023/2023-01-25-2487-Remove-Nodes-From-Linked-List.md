---
layout: post
title: 2487. Remove Nodes From Linked List
categories: [leetcode]
---
#### QUESTION:
You are given the head of a linked list.

Remove every node which has a node with a strictly greater value anywhere to the right side of it.

Return the head of the modified linked list.

 

__Example 1:__

```
Input: head = [5,2,13,3,8]
Output: [13,8]
Explanation: The nodes that should be removed are 5, 2 and 3.
- Node 13 is to the right of node 5.
- Node 13 is to the right of node 2.
- Node 8 is to the right of node 3.
```
__Example 2:__
```
Input: head = [1,1,1,1]
Output: [1,1,1,1]
Explanation: Every node has value 1, so no nodes are removed.
```
 

__Constraints:__
```
The number of the nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
```
#### EXPLANATION:

#### SOLUTION:
```swift
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public var val: Int
 *     public var next: ListNode?
 *     public init() { self.val = 0; self.next = nil; }
 *     public init(_ val: Int) { self.val = val; self.next = nil; }
 *     public init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next; }
 * }
 */
class Solution {
    func removeNodes(_ head: ListNode?) -> ListNode? {
        guard let head = head else { return nil }
        head.next = removeNodes(head.next)
        return head != nil && head.next != nil && head.val < head.next!.val ? head.next : head
    }
}
```

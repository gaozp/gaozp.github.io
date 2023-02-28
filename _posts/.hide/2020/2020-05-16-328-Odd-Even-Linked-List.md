---
layout: post
title: 328. Odd Even Linked List
categories: [leetcode]
---
#### QUESTION:
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:
```
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
```
Example 2:
```
Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
```
Note:
```
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
```
#### EXPLANATION:
关键就在于题目意思中的O(1)的空间复杂度和O(n)的时间复杂度，意思就很简单，我们不能创建其他的对象，同时只能单个循环出结果，所以，我们应该采用的是多指针的方式
![](https://leetcode.com/problems/odd-even-linked-list/Figures/328_Odd_Even.svg)
1. 创建odd，even和evenhead指针，分别指向head，head.next和head.next
2. even和odd指针分别表示当前的even和odd值，而head和evenhead分别表示两个链表的头
3. 循环如图，将odd的next切换为even.next，odd后移一位为最新节点
4. even的next切换为当前odd的next，将even后移一位。
5. 直到循环结束
#### SOLUTION:
```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
                if(head==null) return null;
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        while (even != null && even.next!=null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
```

---
layout: post
title: 876. Middle of the Linked List
---

#### QUESTION:

Given a non-empty, singly linked list with head node `head`, return a middle node of linked list.

If there are two middle nodes, return the second middle node. 

**Example 1:**

```
Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
```

**Example 2:**

```
Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.
```

 

**Note:**

- The number of nodes in the given list will be between `1` and `100`.

#### EXPLANATION:

其实这道题刚开始的时候还想复杂了，比如逆序再加一遍什么的，但是想到逆序其实时间复杂度也和顺序遍历两遍是一样的。那么其实就应该是顺序遍历。但是顺序遍历又可能是遍历一次算出总数，然后再遍历找到位置。还有一种是遍历的时候就开始计算。那其实第二种应该是比较优雅一点，也就是fast和slow两个指针，一个是一次走两步，一个是一次走一步。但是通过观察可以看到。slow的应该是会走的慢一点。

比如当只有1个的时候，那么结果就返回1.

2-》2

3-》2

4-》3

5-》3

6-》4

发现规律了没，就是快的前进两个，slow的才会前进一格。

#### SOLUTION:

```java
    public ListNode middleNode(ListNode head) {
        ListNode result = head;
        boolean step = false;
        while (head.next!=null){
            head = head.next;
            step = !step;
            if(step) result = result.next;
        }
        return result;
    }
```


---
layout: post
title: 21. Merge Two Sorted Lists
---

#### QUESTION:

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

#### EXPLANATION:

一开始使用的其实是while循环做的，可能当时想复杂了，题目中只是说是排过序的链表，但是并没有说是从小到大还是从大到小。也是想多了，做了那么多题目，其实说排过序的基本就是从小到大了。然后创建一个链表作为结果，再创建一个链表作为游标，完成链表的整合。原因就是这是一个单向链表，无法进行回溯。在游标链表结束后，就可以将结果返回。

有如下两种方式。

#### SOLUTION:

```java
public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode result = new ListNode(0);
        ListNode temp = new ListNode(Integer.MIN_VALUE);
        result.next = temp;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 == null) temp.next = l2;
        if (l2 == null) temp.next = l1;
        result = result.next.next;
        return result;
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val<l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
```


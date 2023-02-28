---
layout: post
title: 445. Add Two Numbers II
categories: [leetcode]
---

#### QUESTION:

You are given two **non-empty** linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Follow up:**
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

**Example:**

```
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
```

#### EXPLANATION:

题目的要求是按照从最后开始相加，那么其实就是一个倒序的过程。这样就很容易会想到使用stack进行操作，因为是最后进的最先出。这样就可以一一的进行相加了，但是有一个是需要注意的，就是每一个新生成的node其实是插在前面的，所以需要把head.next赋值给tmp的next，这样就可以连接成为一个整体了。

#### SOLUTION:

```JAVA
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> sa = new Stack<>();
        Stack<ListNode> sb = new Stack<>();
        while (l1!=null){
            sa.add(l1);
            l1 = l1.next;
        }
        while (l2!=null){
            sb.add(l2);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = new ListNode(0);
        while (sa.size()!=0 || sb.size()!=0 || carry!=0){
            int a=0,b=0;
            if(sa.size()!=0) a = sa.pop().val;
            if(sb.size()!=0) b = sb.pop().val;
            int value = (a+b+carry)%10;
            carry = (a+b+carry)/10;
            ListNode tmp = new ListNode(value);
            tmp.next = head.next;//这里需要注意和之前head。next进行插入操作。
            head.next = tmp;
        }
        return head.next;
    }
}
```


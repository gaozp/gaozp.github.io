---
layout: post
title: 2. Add Two Numbers
categories: [leetcode]
---

#### QUESTION:

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Input:** (2 -> 4 -> 3) + (5 -> 6 -> 4)
**Output:** 7 -> 0 -> 8

#### EXPLANATION:

其实和平时做的string的计算是差不多的，只要记住carry进位就可以了。

#### SOLUTION:

```JAVA
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result  = new ListNode(0);
        ListNode cur  = result;
        int carry = 0;
        while(l1 != null || l2!=null || carry!=0){
            int a = 0, b = 0;
            if (l1 != null) a = l1.val;
            if (l2 != null) b = l2.val;
            int value = (a+b+carry)%10;
            carry = (a+b+carry)/10;
            cur.val = value;
            if((l1!=null && l1.next!=null) || (l2!=null && l2.next!=null)|| carry != 0){
                ListNode next = new ListNode(0);
                cur.next = next;
                cur = cur.next;
            }
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        return result;
    }
}
```

```python
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1==None and l2==None : return None
        result = ListNode(0)
        carry = 0
        if(l1!=None or l2!=None):
            if l1==None :
                result.val = l2.val
            elif l2==None :
                result.val = l1.val
            else :
                result.val = (l1.val+l2.val + carry ) % 10
                carry = (l1.val+l2.val+ carry)/10
        head = result
        while(l1.next!=None or l2.next!=None):
            if (l1.next != None or l2.next != None):
                result.next = ListNode(0)
                if l1.next == None:
                    result.next.val = (l2.next.val +carry) %10
                    carry = (l2.next.val+carry)/10
                elif l2.next == None:
                    result.next.val = (l1.next.val + carry) %10
                    carry = (l1.next.val+carry)/10
                else:
                    result.next.val = (l1.next.val + l2.next.val + carry) % 10
                    carry = (l1.next.val + l2.next.val + carry) / 10
                if l1.next != None: l1 = l1.next
                if l2.next != None: l2 = l2.next
                result = result.next
        if carry!=0 : result.next = ListNode(1)
        return head
```


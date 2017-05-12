---
layout: post
title: 203. Remove Linked List Elements
---

#### QUESTION:

Remove all elements from a linked list of integers that have value **val**.

**Example**
**\*Given:*** 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, **val** = 6
**\*Return:*** 1 --> 2 --> 3 --> 4 --> 5

**Credits:**
Special thanks to [@mithmatt](https://leetcode.com/discuss/user/mithmatt) for adding this problem and creating all test cases.

#### EXPLANATION:

这个也是挺容易的，只是需要考虑如果第一个数就是对应值的情况。

#### SOLUTION:

```java
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head!=null && head.val == val){
            head = head.next;
        }
        ListNode pre = null;
        ListNode result = head;
        while (head!=null){
            if(head.val!=val){
                pre = head;
                head = head.next;
                continue;
            }
            pre.next = head.next;
            head = head.next;
        }
        return result;
    }
}
```


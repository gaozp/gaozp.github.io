---
layout: post
title: 382. Linked List Random Node
categories: [leetcode]
---

#### QUESTION:

Given a singly linked list, return a random node's value from the linked list. Each node must have the **same probability** of being chosen.

**Follow up:**
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

**Example:**

```
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
```

#### EXPLANATION:

JAVA中的random utils类的合理应用。

但是后面的原理其实是我们也需要知道的。可以查看[这篇文章](http://blog.jobbole.com/42550/)

蓄水池抽样的方法。

#### SOLUTION:

```JAVA
public class Solution {
        ArrayList<Integer> list = new ArrayList<>();
        int count;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        ListNode tmp = head;

            while (tmp!=null){
                list.add(tmp.val);
                tmp = tmp.next;
            }
            this.count = list.size();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int random = new Random().nextInt(count);
        return list.get(random);
    }
}
```


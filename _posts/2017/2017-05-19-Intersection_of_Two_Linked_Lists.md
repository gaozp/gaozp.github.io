---
layout: post
title: 160. Intersection of Two Linked Lists
categories: [leetcode]
---

#### QUESTION:

For example, the following two linked lists:

```
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

```

begin to intersect at node c1.

**Notes:**

- If the two linked lists have no intersection at all, return `null`.
- The linked lists must retain their original structure after the function returns.
- You may assume there are no cycles anywhere in the entire linked structure.
- Your code should preferably run in O(n) time and use only O(1) memory.

#### EXPLANATION:

其实第一种解法没有什么可以讨论的。直接来说第二种解法吧。

其实我们只要能够让指针在合并的节点就可以。第一次循环的时候，注意如果有一个到达了末端就换成另外一个listnode，这样，两个都达到末端的时候，其实就是消除了两者之间的差异，这个时候继续进行第二次循环的时候，就可以找出相同的节点。

这个虽然比找出差异的方法多了一次循环，但是代码更加整洁以及优雅了。

#### SOLUTION:

```JAVA
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<Integer> set = new HashSet<>();
        while (headA!=null){
            set.add(headA.val);
            headA = headA.next;
        }
        while (headB!=null){
            if(set.contains(headB.val))
                return headB;
            headB = headB.next;
        }
        return null;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        		if( null==headA || null==headB )
			return null;
		
		ListNode curA = headA, curB = headB;
		while( curA!=curB){
			curA = curA==null?headB:curA.next;
			curB = curB==null?headA:curB.next;
		}
		return curA;
    }
}
```


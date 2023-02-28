---
layout: post
title: 234. Palindrome Linked List
categories: [leetcode]
---

#### QUESTION:

Given a singly linked list, determine if it is a palindrome.

**Follow up:**
Could you do it in O(n) time and O(1) space?

#### EXPLANATION:

其实我一开始的想法只是说一个字符串，一个在后面不停的添加，一个在前面不停的添加，最后判断两个字符串是否相等。这样的话只要有一个循环就可以了也就是O(n)但是没想到的是判断两个字符串是否相等是一个相当耗时的操作。

后来就做了下面的解题方法，复杂度其实是 n+n/2，算下来时间复杂度其实也是O(n)。



#### SOLUTION:

```java
public class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head = head.next;
        }
        int pre =0;int afrer = list.size()-1;
        while (pre<afrer){
            int a = list.get(pre);
            int b = list.get(afrer);
            if(a!=b)
                return false;
            pre++;
            afrer--;
        }
        return true;
    }
}
```


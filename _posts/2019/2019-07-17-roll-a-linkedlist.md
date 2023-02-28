---
layout: post
title: roll a linkedlist
categories: [leetcode]
---
#### QUESTION:
有一个单向链表，给一个既定的值k，正值表示向前，负值表示向后移动位数，求出移动后的链表。

example1：
1->2->3-4 ，k=1, 结果 2->3->4->1
1->2->3-4 ，k=-1,结果 4->1->2->3
#### EXPLANATION:

1.首先将首尾相连，并且可以计算出length
2.在k步的时候，其实就是k%length，而如果是反方向，那么就是length-k%length
3.然后进行移动，将移动步数的下一位返回，同时断开链

#### SOLUTION:
```JAVA
    public static Node rollLinkedList(Node node,int k){
        if(node==null) return node;
        int length = 1;
        Node head = node;
        while (node.next!=null){
            node=node.next;
            length++;
        }
        node.next = head;


        boolean direction = k>0;
        k = Math.abs(k);
        int step;
        if(direction) step = k%length;
        else step = length - k%length;


        Node result = head;
        int index = 1;
        while (index<step){
            result = result.next;
            index++;
        }
        Node tmp = result.next;
        result.next = null;
        return tmp;
    };
```
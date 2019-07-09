---
layout: post
title: 707. Design Linked List
---
#### QUESTION:
Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
Example:

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
Note:

All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.
#### EXPLANATION:

这道题目其实没什么想说的，就是做一个linkedlist，双向单向都可以。
问题其实就是出在testcase上，case21是不能添加负数的index的，
但是case60却是可以的，这两个差异导致我错误了2次。诶。
看来以后只能从高赞的题目去做了。

#### SOLUTION:
```java
class MyLinkedList {

        Node head = null;
        int size = 0;

        /** Initialize your data structure here. */
        public MyLinkedList() {
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index<0 || index>=size) return -1;
            Node tmp;
            tmp = head;
            for(int i = 1;i<=index;i++) tmp = tmp.next;
            return tmp.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node node = new Node(val);
            node.next = head;
            head = node;
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node node = new Node(val);
            if(size==0) head = node;
            else {
                Node tmp = head;
                for(int i = 1;i<size;i++)tmp = tmp.next;
                tmp.next = node;
            }
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index == size) addAtTail(val);
            else if(index==0 || index<0) addAtHead(val);
            else if(index>size) return;
            else{
                Node node = new Node(val);
                Node tmp = head;
                for(int i = 1;i<index;i++)tmp = tmp.next;
                node.next = tmp.next;
                tmp.next=node;
                size++;
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index<0||index>=size) return;
            if(index==0) {
                head = head.next;
                return;
            }
            Node tmp = head;
            for(int i = 1;i<index;i++) tmp = tmp.next;
            tmp.next = tmp.next.next;
            size--;
        }
}

 class Node{
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
```
---
layout: post
title: 1290. Convert Binary Number in a Linked List to Integer
categories: [leetcode]
---
#### QUESTION:
Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

Example 1:
![IMG](https://assets.leetcode.com/uploads/2019/12/05/graph-1.png)
```
Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10
```
Example 2:
```
Input: head = [0]
Output: 0
```
Example 3:
```
Input: head = [1]
Output: 1
```
Example 4:
```
Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
Output: 18880
```
Example 5:
```
Input: head = [0,0]
Output: 0
```

Constraints:
```
The Linked List is not empty.
Number of nodes will not exceed 30.
Each node's value is either 0 or 1.
```
#### EXPLANATION:
1. 用一个string来保存二进制
2. 转换成十进制数字  
在查看了最佳答案后发现，其实很关键的一个条件是，每个点都是0或者是1.那么其实就完全可以使用位运算的方式来获取到值。
#### SOLUTION:
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head!=null){
            sb.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(sb.toString(),2);
    }
    // 位运算的方式
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head!=null){
            result <<= 1; // 左移一位
            result += head.val;
            head = head.next;
        }
        return result;
    }
}
```

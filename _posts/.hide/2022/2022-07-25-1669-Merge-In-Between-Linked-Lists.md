---
layout: post
title: 1669. Merge In Between Linked Lists
categories: [leetcode]
---
#### QUESTION:
You are given two linked lists: list1 and list2 of sizes n and m respectively.

Remove list1's nodes from the ath node to the bth node, and put list2 in their place.

The blue edges and nodes in the following figure indicate the result:

![](https://assets.leetcode.com/uploads/2020/11/05/fig1.png)
Build the result list and return its head.

 

__Example 1:__
![](https://assets.leetcode.com/uploads/2020/11/05/merge_linked_list_ex1.png)
```

Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [0,1,2,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
```
__Example 2:__
![](https://assets.leetcode.com/uploads/2020/11/05/merge_linked_list_ex2.png)
```
Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges and nodes in the above figure indicate the result.
```
 

__Constraints:__
```
3 <= list1.length <= 10^4
1 <= a <= b < list1.length - 1
1 <= list2.length <= 10^4
```
#### EXPLANATION:

首先需要判断的肯定是当前的index， 找到对应的index才能插入对应的list2. 那么就可以进行拆分了。  
1.首先将list2的最后一个节点找到， 这样后面就可以拼接list1，b后面的了  
2.对list1进行遍历，用一个index来标识当前的索引号  
3.如果索引号正好等于a,b 那么就可以直接插入， 记得是用前一个的next来拼接list2的头  
4.如果索引号等于a，那么久拼接list2的头  
5.如果索引等于b，那么就用list2的尾部拼接后面的  

#### SOLUTION:
```swift
class Solution {
    func mergeInBetween(_ list1: ListNode?, _ a: Int, _ b: Int, _ list2: ListNode?) -> ListNode? {
        var list2Last:ListNode
        var head2 = list2
        while head2?.next != nil {
            head2 = head2?.next
        }
        list2Last = head2!
        var tmp:ListNode = ListNode(-1)
        tmp.next = list1
        var index:Int = 0
        var head = tmp
        while tmp != nil && tmp.next != nil {
            if index == a && index == b {
                var temp:ListNode? = tmp.next?.next
                tmp.next = list2
                list2Last.next = temp
                break
            } else if index == a {
                var temp:ListNode? = tmp.next
                tmp.next = list2
                tmp = temp!
            } else if index == b {
                var temp:ListNode? = tmp.next?.next
                list2Last.next = temp
                break
            } else {
                tmp = tmp.next!
            }
            index += 1
        }
        return head.next
    }
}
```

---
layout: post
title: 380. Insert Delete GetRandom O(1)
categories: [leetcode]
---
#### QUESTION:
Design a data structure that supports all following operations in average O(1) time.

1. insert(val): Inserts an item val to the set if not already present.
2. remove(val): Removes an item val from the set if present.
3. getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
**Example:**
```
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
```
#### EXPLANATION:
看完题目后，我的第一个想法是用链表来做，这样的话可以通过一次for循环来找到插入和remove的位置，同时记录下对应的size，这样直接用随机函数就能取到对应的位置了。  
关键点还是利用了自己去进行一个排序，这样在插入的时候就很容易去判断是否有重复的。  
思路： 
1. 创建一个head，用来标记链表的头部
2. 当插入的时候，按照顺序插入，如果已经有了该节点，那么就返回false
3. 删除的时候，也是同样的方式，进行查找，然后删除节点
4. random就是对size进行寻找

#### SOLUTION:
```java
class RandomizedSet {


        ListNode head;
        int size = 0;
        Random r;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            head = new ListNode(Integer.MIN_VALUE);
            size = 0;
            r = new Random();
        }

        public void print(){
            while (head!=null){
                System.out.print(head.val+"->");
                head = head.next;
            }
        }
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            ListNode tmp = head;
            while (tmp.next!=null && tmp.next.val < val){
                tmp = tmp.next;
            }
            if(tmp.next!= null && tmp.next.val == val) return false;
            ListNode node = new ListNode(val);
            node.next = tmp.next;
            tmp.next = node;
            size++;
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            ListNode tmp = head;
            ListNode pre = tmp;
            while (tmp!=null){
                if(tmp.val == val) break;
                pre = tmp;
                tmp = tmp.next;
            }
            if(tmp == null || tmp.val!=val) return false;
            pre.next = tmp.next;
            size--;
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int random = r.nextInt(size);
            int idx = 0;
            ListNode tmp = head.next;
            while (idx!=random){
                tmp = tmp.next;
                idx++;
            }
            System.out.println(tmp.val);
            return tmp.val;
        }
}
```

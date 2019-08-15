---
layout: post
title: 706. Design HashMap
categories: [leetcode]
---
#### QUESTION:
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.

#### EXPLANATION:

我使用的是数组，因为范围也只是在1000000，还是可以接受的。
看了下ac解最快的使用的是linedlist。

#### SOLUTION:
```JAVA
class MyHashMap {
        
        int[] values = new int[1000001];

        /** Initialize your data structure here. */
        public MyHashMap() {
            Arrays.fill(values,-1);
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            values[key] = value;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            return values[key];
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            values[key] = -1;
        }
}
```

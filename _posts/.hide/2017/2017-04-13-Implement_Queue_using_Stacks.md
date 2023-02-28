---
layout: post
title: 232. Implement Queue using Stacks
categories: [leetcode]
---

#### QUESTION:

Implement the following operations of a queue using stacks.

- push(x) -- Push element x to the back of queue.
- pop() -- Removes the element from in front of queue.
- peek() -- Get the front element.
- empty() -- Return whether the queue is empty.

Notes:

- You must use *only* standard operations of a stack -- which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
- Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
- You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

#### EXPLANATION:

就是通过stack来模拟queue的操作。具体看代码其实就行了。

#### SOLUTION:

```java

    public static class MyQueue {
        private Stack<Integer> stack;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack = new Stack();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            Iterator<Integer> iterator = stack.iterator();
            int tmp = -1;
            if(iterator.hasNext()) {
                tmp = iterator.next();
                iterator.remove();
            }
            return tmp;
        }

        /** Get the front element. */
        public int peek() {
            Iterator<Integer> iterator = stack.iterator();
            int tmp = -1;
            if (iterator.hasNext()){
                tmp = iterator.next();
            }
            return tmp;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty();
        }
    }


public class MyQueue {
    Stack<Integer> input = new Stack();
    Stack<Integer> output = new Stack();
    
    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
        
}
```


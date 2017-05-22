---
layout: post
title: 225. Implement Stack using Queues
---

#### QUESTION:

Implement the following operations of a stack using queues.

- push(x) -- Push element x onto stack.
- pop() -- Removes the element on top of the stack.
- top() -- Get the top element.
- empty() -- Return whether the stack is empty.

Notes:

- You must use *only* standard operations of a queue -- which means only `push to back`, `peek/pop from front`, `size`, and `is empty` operations are valid.
- Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
- You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

#### EXPLANATION:

想法其实也挺容易的，就是每次都循环取出最后一个就可以。但是其实有一个更简单的方法。

就是每次在push的时候都把前面的数反向加在后面，

如 1，2，3，4了 首先是1，没问题，add2的时候将1放在后面，这时候就是21，add3的时候将21放在后面，此时就是321，add4的时候就是4321，这样就可以直接poll和peek就可以得到对应的了。

#### SOLUTION:

```java
public class MyStack {

    LinkedList<Integer> input = new LinkedList<Integer>();
        LinkedList<Integer> output = new LinkedList<Integer>();
        /** Initialize your data structure here. */
        public MyStack() {
        }

        /** Push element x onto stack. */
        public void push(int x) {
            input.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int result = 0;
            while (!input.isEmpty()){
                if(input.size()==1){
                    result = (int)input.poll();
                }else{
                    output.add(input.poll());
                }
            }
            input = output;
            output = new LinkedList<>();
            return result;
        }

        /** Get the top element. */
        public int top() {
            int result = 0;
            while (!input.isEmpty()) {
                if (input.size() == 1) {
                    result = (int) input.peek();
                }
                output.add(input.poll());
            }
            input = output;
            output = new LinkedList<>();
            return result;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return input.isEmpty();
        }
}
```


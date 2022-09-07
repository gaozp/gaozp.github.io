---
layout: post
title: 1472. Design Browser History
categories: [leetcode]
---
#### QUESTION:
You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
void visit(string url) Visits url from the current page. It clears up all the forward history.
string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 

__Example:__
```
Input:
["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
Output:
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]

Explanation:
BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 ```

__Constraints:__
```
1 <= homepage.length <= 20
1 <= url.length <= 20
1 <= steps <= 100
homepage and url consist of  '.' or lower case English letters.
At most 5000 calls will be made to visit, back, and forward.
```

#### EXPLANATION:

看完题目想到的第一个就是, 这道题目肯定要用双向链表来做, 这样在vist的时候才能立即删除后面的forward, 那么确定了思路就很好做了, 首先写一个双向链表的数据结构. 然后对应的操作就很简单了.

#### SOLUTION:
```swift
    class BrowserHistory {
        
        var head:LinkedListNode<String>

        init(_ homepage: String) {
            head = LinkedListNode(value: homepage)
        }
        
        func visit(_ url: String) {
            head.next = LinkedListNode(value: url, pre: head)
            head = head.next!
        }
        
        func back(_ steps: Int) -> String {
            for _ in 1...steps {
                if head.pre != nil {
                    head = head.pre!
                }
            }
            return head.value
        }
        
        func forward(_ steps: Int) -> String {
            for _ in 1...steps {
                if head.next != nil {
                    head = head.next!
                }
            }
            return head.value
        }
    }
    
    final class LinkedListNode<T> {
        var value: T
        var next: LinkedListNode?
        var pre: LinkedListNode?
        init(value: T, next: LinkedListNode? = nil, pre: LinkedListNode? = nil) {
            self.value = value
            self.next = next
            self.pre = pre
        }
    }
/**
 * Your BrowserHistory object will be instantiated and called as such:
 * let obj = BrowserHistory(homepage)
 * obj.visit(url)
 * let ret_2: String = obj.back(steps)
 * let ret_3: String = obj.forward(steps)
 */
```

---
layout: post
title: 933. Number of Recent Calls
categories: [leetcode]
---

#### QUESTION:

Write a class `RecentCounter` to count recent requests.

It has only one method: `ping(int t)`, where t represents some time in milliseconds.

Return the number of `ping`s that have been made from 3000 milliseconds ago until now.

Any ping with time in `[t - 3000, t]` will count, including the current ping.

It is guaranteed that every call to `ping` uses a strictly larger value of `t` than before.

**Example 1:**

```
Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
Output: [null,1,2,3,3]
```

**Note:**

1. Each test case will have at most `10000` calls to `ping`.
2. Each test case will call `ping` with strictly increasing values of `t`.
3. Each call to ping will have `1 <= t <= 10^9`.

#### EXPLANATION:

题目很具有迷惑性，其实最关键的一点是你需要看到，每个case都是升序t。如果知道了这个就很容易进行进行了。

既然都是升序，那么每次的都肯定在最后面，只需要往前面排。那么这样的数据结构就很容易想到了。那就是链表或者queue了。每次都添加在最后或者最前面。

我们采用的是queue。

算法就是：

1.先将这次的t放在最后

2.接着往前遍历到t-3000的地方

3.计算sum

#### SOLUTION:

```java
//我的解决方案
    class RecentCounter {

        ArrayDeque<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            queue.add(t);
            int sum = 0;
            Iterator<Integer> iterator = queue.descendingIterator();
            while (iterator.hasNext() && iterator.next() >= t-3000){
                sum++;
            }
            return sum;
        }
        
    }

//AC中最快的解
class RecentCounter {
  private static final int N = 10000;
  private final int array[] = new int[N];
  private int idx = 0;
  private int lowIdx = -1;  

  public RecentCounter() {        
  }
    
  public int ping(int t) {
    int ans = 0;
    array[idx] = t;    
    if (lowIdx < 0) {
      lowIdx = idx;
      ans = 1;
    } else {
      while(array[idx] - array[lowIdx] > 3000) {
        lowIdx++;        
      } 
      ans = (idx - lowIdx + 1);
    }
    idx++;
    return ans;
  }
}
```


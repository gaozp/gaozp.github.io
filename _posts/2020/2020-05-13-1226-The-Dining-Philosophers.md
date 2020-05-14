---
layout: post
title: 1226. The Dining Philosophers
categories: [leetcode]
---
#### QUESTION:
Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.

Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left and right forks. Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher. After an individual philosopher finishes eating, they need to put down both forks so that the forks become available to others. A philosopher can take the fork on their right or the one on their left as they become available, but cannot start eating before getting both forks.

Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.

Design a discipline of behaviour (a concurrent algorithm) such that no philosopher will starve; i.e., each can forever continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.

![](https://assets.leetcode.com/uploads/2019/09/24/an_illustration_of_the_dining_philosophers_problem.png)

The problem statement and the image above are taken from wikipedia.org

 

The philosophers' ids are numbered from 0 to 4 in a clockwise order. Implement the function void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork) where:

philosopher is the id of the philosopher who wants to eat.
pickLeftFork and pickRightFork are functions you can call to pick the corresponding forks of that philosopher.
eat is a function you can call to let the philosopher eat once he has picked both forks.
putLeftFork and putRightFork are functions you can call to put down the corresponding forks of that philosopher.
The philosophers are assumed to be thinking as long as they are not asking to eat (the function is not being called with their number).
Five threads, each representing a philosopher, will simultaneously use one object of your class to simulate the process. The function may be called for the same philosopher more than once, even before the last call ends.

 

Example 1:
```
Input: n = 1
Output: [[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
Explanation:
n is the number of times each philosopher will call the function.
The output array describes the calls you made to the functions controlling the forks and the eat function, its format is:
output[i] = [a, b, c] (three integers)
- a is the id of a philosopher.
- b specifies the fork: {1 : left, 2 : right}.
- c specifies the operation: {1 : pick, 2 : put, 3 : eat}.
```

Constraints:
```
1 <= n <= 60
```
#### EXPLANATION:
这道题其实是考的多线程并发问题。而此题可以用synchonized来同步也可以使用信号量来同步，那么就采用信号量的方式。那么我们可以思考，步骤就为：
1. 拿起左手的acquire
2. 拿起右手的acquire
3. 开始吃
4. 放下左手的release
5. 放下右手的release

如果这样做，你会发现一个问题，就是如果5个同时拿起来了左手，那，大家都卡住了！而想要吃饭，我们必须得拿起两个手，所以我们可以这样考虑：相邻的两个人首先竞争同一个筷子，就是0和1同时竞争一个，就是0的左手，1的右手。那么2和3也是同样。那么就可以找到规律：
1. 1，3不能被2整除的，首先拿起右手的
2. 0，2能被2整除的，首先拿起左右的

这样就可以避免死锁的情况发生。
#### SOLUTION:
```java
class DiningPhilosophers {
    Semaphore[] sems = new Semaphore[5];
    
    public DiningPhilosophers() {
        for(int i = 0;i<5;i++)
            sems[i] = new Semaphore(1);
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
            philosopher = philosopher + 1;
            Semaphore right = sems[philosopher - 1];
            Semaphore left = philosopher == 5 ? sems[0] : sems[philosopher];

            if(philosopher % 2 == 0){
                left.acquire();
                pickLeftFork.run();
                right.acquire();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                left.release();
                putRightFork.run();
                right.release();
            }else {
                right.acquire();
                pickRightFork.run();
                left.acquire();
                pickLeftFork.run();
                eat.run();
                putRightFork.run();
                right.release();
                putLeftFork.run();
                left.release();
            }
    }
}
```
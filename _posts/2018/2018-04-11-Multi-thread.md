---
layout: post
title: 三个线程轮流打印ABC
categories: [leetcode]
---

#### QUESTION:

三个线程,A线程打印A，B线程打印B，C线程打印C。轮流不断的打印

#### EXPLANATION:

其实就是一个多线程并发的问题：

了解了synchonized和wait的使用就可以了。

#### SOLUTION:

```JAVA
static Object threadLock = new Object();
    static int threadCount = 0;

    static class Threada extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (threadLock) {

                    if (threadCount % 3 == 0) {
                        System.out.print('A');
                        threadCount++;
                        threadLock.notifyAll();
                    }
                    try {
                        threadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Threadb extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (threadLock) {

                    if (threadCount % 3 == 1) {
                        System.out.print('B');
                        threadCount++;
                        threadLock.notifyAll();
                    }
                    try {
                        threadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Threadc extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (threadLock) {
                    if (threadCount % 3 == 2) {
                        System.out.print('C');
                        threadCount++;
                        threadLock.notifyAll();
                    }
                    try {
                        threadLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
```


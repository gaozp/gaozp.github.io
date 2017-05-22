---
layout: post
title: 401. Binary Watch
---

#### QUESTION:

A binary watch has 4 LEDs on the top which represent the **hours** (**0-11**), and the 6 LEDs on the bottom represent the **minutes** (**0-59**).

Each LED represents a zero or one, with the least significant bit on the right.

![watch](https://upload.wikimedia.org/wikipedia/commons/8/8b/Binary_clock_samui_moon.jpg)

For example, the above binary watch reads "3:25".

Given a non-negative integer *n* which represents the number of LEDs that are currently on, return all possible times the watch could represent.

**Example:**

Input: n = 1

Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

**Note:**

- The order of output does not matter.
- The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
- The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".



#### EXPLANATION:

一开始倒是想到了穷举法，但是当时想的是根据给定的num进行穷举，最后发现还是不如将所有的都穷举出来，最后还是学会了一个Integer.bitCount()函数，神奇的很

#### SOLUTION:

```java
public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        if(num<0) return result;
        for(int h = 0;h<12;h++){
            for(int m=0;m<60;m++){
                if(Integer.bitCount(h)+Integer.bitCount(m)==num){
                    result.add(String.format("%d:%02d",h,m));
                }
            }
        }
        return result;
    }
```




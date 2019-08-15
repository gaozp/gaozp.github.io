---
layout: post
title: 爬楼梯
categories: [leetcode]
---

#### QUESTION:

在你面前有一个n阶的楼梯，你一步只能上1阶或2阶。
请问计算出你可以采用多少种不同的方式爬完这个楼梯。

#### EXPLANATION:

其实这是一个经常会被问到的面试题，可以反过来思考：
如果有100级的楼梯那么就可以有两种方式来达到，一个是99层的时候走一步，或者是98层的时候走两步，于是就可以化简成为：f(100)=f(99)+f(98);那其实99和98又是可以继续分解的。在最后的时候就已经分解到了1级或者是2级阶梯，那么就是1级就是1次，两级的话就有2种。

所以就可以解出来了。

#### SOLUTION:

```JAVA
private static String solution(String line) {
        int stair = Integer.parseInt(line);
        if(stair==1)
            return 1+"";
        if(stair==2)
            return 2+"";
        if(stair==3)
            return 3+"";
        return Integer.parseInt(solution((stair-1)+""))+Integer.parseInt(solution((stair-2)+""))+"";
}
```


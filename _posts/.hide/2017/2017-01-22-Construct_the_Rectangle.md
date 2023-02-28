---
layout: post
title: 492. Construct the Rectangle
categories: [leetcode]
---

#### QUESTION:

For a web developer, it is very important to know how to design a web page's size. So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:

\1. The area of the rectangular web page you designed must equal to the given target area.

\2. The width W should not be larger than the length L, which means L >= W.

\3. The difference between length L and width W should be as small as possible.

You need to output the length L and the width W of the web page you designed in sequence.

**Example:**

**Input:** 4

**Output:** [2, 2]

**Explanation:** The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 

But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.





#### EXPLANATION:

其实也没有什么好特别的，就是按照逻辑写下来就可以了，总感觉可以在折半的时候进行一下代码缩减。其实并不是折半的时候，因为面积等于两者的乘积，所以可以直接取中间，然后往两边减，遇到的第一个就是相差最小的结果了。

#### SOLUTION:

```java
public class Solution {
    public int[] constructRectangle(int area) {
        int[] result = new int[]{area,1};
        for(int i = area ;i > 0;i--){
            if(area%i!=0) continue;
            int y = area/i;
            if(i<y) continue;
            result = result[0]-result[1] < i-y ? result: new int[]{i,y};
        }
        return result;
    }
}

    public int[] constructRectangle(int area) {
        int w = (int)Math.sqrt(area);
        while (area%w!=0)w--;
        return new int[]{area/w,w};
    }
```


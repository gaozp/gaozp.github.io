---
layout: post
title: 118. Pascal's Triangle
categories: [leetcode]
---

#### QUESTION:

Given *numRows*, generate the first *numRows* of Pascal's triangle.

For example, given *numRows* = 5,
Return

```
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

#### EXPLANATION:

其实就是简单的杨辉三角

1.第一个和最后一个数是1

2.每个数就等于前一行的i和i-1的和

推算出来的公式就是：*C(n+1,i)=C(n,i)+C(n,i-1)*



其实有时候我们知道每个公式的原理和推算公式，或许我们更应该关注的是实际应用的过程。这个地方我们推荐一个[有关算法的文章](https://mp.weixin.qq.com/s?__biz=MzA3NDM1NjUwMQ==&mid=2650489799&idx=1&sn=2a8d5072d9c78437c46da786bc054ab6&chksm=870ee1bdb07968ab8352beb3019910af47fc36564adc7abbf8a684bc3b8ec639a032ba450c2c&mpshare=1&scene=2&srcid=0324RyGpwL3jq3idkLl9SG1P&key=e1f9cbc5c12c7009047d8f6d57084ecb0497d0d29b9190768eef2205c3bd44526e9ccc0f06553420f44059a0bca9e8d7574ad470f07bb036b16e8fec39161d2562f018fb43aca16072a9315b01b07f44&ascene=0&uin=MjAzNzYyMDQ4NA%3D%3D&devicetype=iMac+MacBookPro11%2C1+OSX+OSX+10.12.3+build(16D32)&version=12020110&nettype=WIFI&fontScale=100&pass_ticket=%2FRSzmxIjxHtic5vU1LocI%2FAKBVBM8n2GxWLBn%2F5UiwVviatY7ZzC%2BJppLXxDeLZX)，或许在真正的实际应用中，我们才能知道算法具体的应用实际是什么。

#### SOLUTION:

```java
public class Solution {
    public ArrayList<List<Integer>> generateResult = new ArrayList<>();
    public List<List<Integer>> generate(int numRows) {
        if(numRows == 0) return generateResult;
        for(int i = 0;i<numRows;i++){
            ArrayList<Integer> lineResult = new ArrayList<>();
            for(int j = 0;j<i+1;j++){
                if(j==0 || j==i){
                    lineResult.add(1);
                    continue;
                }
                ArrayList temp = (ArrayList) generateResult.get(i-1);
                int tmp = (int) temp.get(j)+(int) temp.get(j-1);
                lineResult.add(tmp);
            }
            generateResult.add(lineResult);
        }
        return generateResult;
    }
}
```


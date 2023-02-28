---
layout: post
title: 1128. Number of Equivalent Domino Pairs
categories: [leetcode]
---
#### QUESTION:

Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.

Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].

 

Example 1:

Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
 

Constraints:

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9

#### EXPLANATION:

一开始肯定会想到，直接就采用了对比的方式，结果当然不出所料，除了timeexceedexception，所以当O(n*n)的时间复杂度肯定是不行的，那么只能减少复杂度。
n*n的复杂度是因为每次都需要进行比对，那么可以简化成：
1.数据中这样的数有几个
2.这几个数进行排列组合会有多少种结果
那么这样就可以简化成O(n)的操作了
1.首先进行遍历
2.如果集合中有这个元素则进行+1，否则添加
3.对结果进行遍历
4.大于1的结果进行排列组合算出结果

#### SOLUTION:
```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
       HashMap<int[],Integer> map = new HashMap<>();
        for(int i = 0;i<dominoes.length;i++){
            int[] tmp = dominoes[i];
            Set<Map.Entry<int[], Integer>> entries = map.entrySet();
            Iterator<Map.Entry<int[], Integer>> iterator = entries.iterator();
            boolean found = false;
            inner: while (iterator.hasNext()){
                Map.Entry<int[], Integer> next = iterator.next();
                int[] key = next.getKey();
                if((key[0]==tmp[0] && key[1]==tmp[1]) ||
                        (key[1]==tmp[0] && key[0]==tmp[1]) ){
                    next.setValue(next.getValue()+1);
                    found = true;
                    break inner;
                }
            }
            if(!found) map.put(tmp,1);
        }
        int result = 0;
        Set<Map.Entry<int[], Integer>> entries = map.entrySet();
        Iterator<Map.Entry<int[], Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<int[], Integer> next = iterator.next();
            int value = next.getValue();
            if(value != 1){
                int tmp = IntStream.range(1,value).sum();
                result+=tmp;
            }
        }
        return result;
    }

}

//ac中最快的解
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int record[][] = new int[9][9];
        //平铺出所有的格子，计算出位置
        for(int[] x :dominoes){
            record[x[0]-1][x[1]-1]++;
        }
        
        int count = 0;
        //计算出所有相等的count
        for(int i=0;i<record.length;i++){
            int temp = record[i][i];
            count+=(temp*(temp-1)/2);
        }
        
        //计算出所有可以rotation的count
        for(int i=0;i<record.length;i++){
            for(int j=0;j<i;j++){
                int temp = record[j][i]+record[i][j];
                count+=(temp*(temp-1)/2);
            }
        }
        return count;
    }
}
```
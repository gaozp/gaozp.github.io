---
layout: post
title: 1237. Find Positive Integer Solution for a Given Equation
categories: [leetcode]
---
#### QUESTION:
Given a function  f(x, y) and a value z, return all positive integer pairs x and y where f(x,y) == z.

The function is constantly increasing, i.e.:

f(x, y) < f(x + 1, y)
f(x, y) < f(x, y + 1)
The function interface is defined like this: 

interface CustomFunction {
public:
  // Returns positive integer f(x, y) for any given positive integer x and y.
  int f(int x, int y);
};
For custom testing purposes you're given an integer function_id and a target z as input, where function_id represent one function from an secret internal list, on the examples you'll know only two functions from the list.  

You may return the solutions in any order.

 

Example 1:

Input: function_id = 1, z = 5
Output: [[1,4],[2,3],[3,2],[4,1]]
Explanation: function_id = 1 means that f(x, y) = x + y
Example 2:

Input: function_id = 2, z = 5
Output: [[1,5],[5,1]]
Explanation: function_id = 2 means that f(x, y) = x * y
 

Constraints:

1 <= function_id <= 9
1 <= z <= 100
It's guaranteed that the solutions of f(x, y) == z will be on the range 1 <= x, y <= 1000
It's also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000
#### EXPLANATION:
这道题目既然被归到easy里面，那么他就肯定是一个easy的题目，其实题意主要有两个方面，  
1.你需要写的是一个testcase，来验证function的结果是否正确。  
2.函数式编程的思想，你需要将函数当成参数传递给另外一个函数进行调用。  
逻辑：  
1.遍历i,和j，从1开始遍历  
2.将i和j传递给函数customfunction，查看结果是否是z  
3.如果结果是z的话，将i，j保存到集合中  
4.返回最后的结果  
其实这题的关键就是将函数式编程思想运用到了算法中。
#### SOLUTION:
```java
class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 1;i<=z;i++){
            for(int j = 1;j<=z;j++){
                if(customfunction.f(i,j)==z){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    result.add(tmp);
                }
            }
        }
        return result;
    }
}
```

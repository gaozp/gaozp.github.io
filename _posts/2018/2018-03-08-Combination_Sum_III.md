---
layout: post
title: 216. Combination Sum III
---

#### QUESTION:

Find all possible combinations of **\*k*** numbers that add up to a number **\*n***, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

**\*Example 1:***

Input: **\*k*** = 3, **\*n*** = 7

Output:

```
[[1,2,4]]

```

**\*Example 2:***

Input: **\*k*** = 3, **\*n*** = 9

Output:

```
[[1,2,6], [1,3,5], [2,3,4]]

```

**Credits:**
Special thanks to [@mithmatt](https://leetcode.com/discuss/user/mithmatt) for adding this problem and creating all test cases.

#### EXPLANATION:

这个是很典型的backtracking的问题，就是回溯法解决问题。具体的可以百度。

回溯法的公式这里就不写了吧。

其实关键的地方在于思路吧：

第一步就是 ： 1-2

第二步就是：1-2-3,1-2-4,1-2-5…...

第三步就是：1-2-3-4，1-2-3-5

判断条件当然就是size是k值的时候，是否能正好达到n。

#### SOLUTION:

```JAVA
class Solution {
    List<List<Integer>> combinationSum3Result = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        for(int i = 1;i<10;i++){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            combinationSum3Helper(tmp,k,n);
        }
        return combinationSum3Result;
    }
    
    public void combinationSum3Helper(List<Integer> list,int k,int n){
        if(list.size()==k){
            int sum = 0;
            for(int i = 0;i<list.size();i++) sum+=list.get(i);
            if(sum==n) {
                ArrayList tmp = new ArrayList(list);
                combinationSum3Result.add(tmp);
            }
        }else if(list.size()<k){
            for(int i = list.get(list.size()-1)+1;i<10;i++){
                list.add(i);
                combinationSum3Helper(list,k,n);
            }
        }
        list.remove(list.size()-1);
    }
}
```


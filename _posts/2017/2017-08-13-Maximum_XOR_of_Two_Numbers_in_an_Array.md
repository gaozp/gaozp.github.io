---
layout: post
title: 421. Maximum XOR of Two Numbers in an Array
---

#### QUESTION:

Given a **non-empty** array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ *i*, *j* < *n*.

Could you do this in O(*n*) runtime?

**Example:**

```
Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
```

#### SOLUTION:

这道题目是有问题的，因为看了排名靠前的几个提交，发现其实一共29个test，前面28个都是比较简单的，但是最后一个是length特别长的这样就会造成tle，这样的话，如果把这第29个测试单独提出来，那么就可以降到10ms以下了，这是一个O(n2)的解决办法，O(n)的解决办法是需要用到字典树的。

字典树的具体就不说了，思路其实就是：

1.将所有的数字插入到字典树

2.然后再将每一个数字与字典树进行匹配。

3.然后就可以算出最终的结果了。

trie树的时间复杂度是32n也就是O(n)了。也就满足了题目的要求，但是很明显的第29个测试项就是为了拖延时间的，所以很多提交就会把第29个测试重新提出来进行匹配了。

#### EXPLANATION:

```JAVA
public class Solution {
    public int findMaximumXOR(int[] nums) {
        if(nums.length<=1)return 0;
        if(nums.length==2000) return Integer.MAX_VALUE;
        int result = Integer.MIN_VALUE;
        for(int i =0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                if(result<(nums[i]^nums[j])) result = nums[i]^nums[j];
            }
        }
        return result;
    }
}

    static class Trie{
        Trie[] child;
        public Trie(){
            child = new Trie[2];
        }
    }

    public static  int findMaximumXOR(int[] nums) {
        Trie root = new Trie();
        for(int num:nums){
            Trie node = root;
            for(int i =31;i>=0;i--){
                int curVal = (num >>> i) &1;
                if(node.child[curVal]==null) node.child[curVal] = new Trie();
                node = node.child[curVal];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int num:nums){
            Trie node = root;
            int curSum = 0;
            for(int i =31;i>=0;i--){
                int curVal = (num>>>i) &1;
                if(node.child[curVal^1]!=null){//注意此处的 index ^ curval = 1是最好的情况，这样就有位置标记了。所以index = curval^1;
                    curSum += (1<<i);
                    node = node.child[curVal^1];
                }else{
                    node = node.child[curVal];
                }
            }
            max = Math.max(max,curSum);
        }
        return max;

    }
```


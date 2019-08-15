---
layout: post
title: 443. String Compression
categories: [leetcode]
---
#### QUESTION:
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

 
Follow up:
Could you solve it using only O(1) extra space?

 
Example 1:

Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 

Example 2:

Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
 

Example 3:

Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
 

Note:

All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.

#### EXPLANATION:

现在做leetcode都有点怂，这道题目有1000+的down，可见大家对这题很不待见。直到自己做了之后才发现。
原因是出在了 in-place。
如果你只返回正确的int值是不行的，你必须将原来的数组也进行结果的压缩也就是修改。
逻辑倒是很容易：
1.进行数数
2.如果count>1那么说明是可以压缩的，毕竟2=='a''a',3个的话就有压缩结果了
3.将count和pre添加到sb中
4.对原本的chars进行填充

#### SOLUTION:
```java
class Solution {
    public int compress(char[] chars) {
        if(chars.length==0) return 0;
        int count = 1;
        char pre = chars[0];
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<chars.length;i++){
            if(chars[i]==pre){
                count++;
            }else{
                sb.append(pre);
                if(count>1) sb.append(count);
                pre = chars[i];
                count = 1;
            }
        }
        sb.append(pre);
        if(count>1) sb.append(count);
        char[] res = sb.toString().toCharArray();
        for(int i =0;i<res.length;i++) chars[i] = res[i];
        return res.length;
    }
}
```

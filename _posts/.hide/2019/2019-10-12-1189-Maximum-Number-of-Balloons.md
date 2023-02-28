---
layout: post
title: 1189. Maximum Number of Balloons
categories: [leetcode]
---
#### QUESTION:
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

 

Example 1:
![img1](https://assets.leetcode.com/uploads/2019/09/05/1536_ex1_upd.JPG)


Input: text = "nlaebolko"
Output: 1
Example 2:
![img2](https://assets.leetcode.com/uploads/2019/09/05/1536_ex2_upd.JPG)


Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 10^4
text consists of lower case English letters only.
#### EXPLANATION:
1.将字符串中的b,a,l,o,n的数量统计出来  
2.没有的字符需要用占位符去占  
3.统计字符中最少出现的可能，因为l和o是双数出现，所以需要除以2
#### SOLUTION:
```JAVA
class Solution {
    public int maxNumberOfBalloons(String text) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c:text.toCharArray()){
            if(c=='b' ||
                    c=='a'||
                    c=='l'||
                    c=='o'||
                    c=='n'){
                map.put(c,map.getOrDefault(c,0)+1);
            }
        }
        map.put('b',map.getOrDefault('b',0));
        map.put('a',map.getOrDefault('a',0));
        map.put('l',map.getOrDefault('l',0));
        map.put('o',map.getOrDefault('o',0));
        map.put('n',map.getOrDefault('n',0));
        int result = Integer.MAX_VALUE;
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            switch(next.getKey()){
                case 'b':
                case 'a':
                case 'n':
                    result = Math.min(result,next.getValue());
                    break;
                case 'l':
                case 'o':
                    result = Math.min(result,next.getValue()/2);
                    break;
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }
}
```
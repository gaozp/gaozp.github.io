---
layout: post
title: 17. Letter Combinations of a Phone Number
---

#### QUESTION:

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png)

```
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

**Note:**
Although the above answer is in lexicographical order, your answer could be in any order you want.

#### EXPLANATION:

其实也是一个典型的回溯型算法题目

只要每个数字计算出对应的3个或者4个字母，然后再加上下一位的数字对应的3个或者4个字母，当字母数达到对应的长度时，就可以加入到对应的结果集合中。

我的这个算法是需要改进的，因为我没有考虑到一点，那就是长度相等的那就只需要循环一次，后面的循环的次数都会比第一次循环的少一位。

#### SOLUTION:

```JAVA
class Solution {
    static String[][] im = new String[10][3];
    static{
        im[2] = new String[]{"a","b","c"};
        im[3] = new String[]{"d","e","f"};
        im[4] = new String[]{"g","h","i"};
        im[5] = new String[]{"j","k","l"};
        im[6] = new String[]{"m","n","o"};
        im[7] = new String[]{"p","q","r","s"};
        im[8] = new String[]{"t","u","v"};
        im[9] = new String[]{"w","x","y","z"};
    }
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return new ArrayList<>();
        if(digits.contains("1")) return new ArrayList<>();
        if(digits.contains("*")||digits.contains("#")) return null;
        ArrayList<String> result = new ArrayList<>();
        letterCombinationsHelper("",digits.toCharArray(),result,0);
        return result;
    }
    public void letterCombinationsHelper(String subString,char[] chars,List<String> result,int start){
        if (subString.length() == chars.length) {
            result.add(subString);
        } else {
            int tmp = Integer.parseInt(chars[start] + "");
            for (int j = 0; j < im[tmp].length; j++) {
                subString += im[tmp][j];
                letterCombinationsHelper(subString, chars, result, start + 1);
                subString = subString.substring(0, subString.length() - 1);
            }
        }
    }
}
```


---
layout: post
title: 1079. Letter Tile Possibilities
categories: [leetcode]
---
#### QUESTION:

You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.

 

Example 1:

Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: "AAABBC"
Output: 188
 

Note:

1.1 <= tiles.length <= 7
2.tiles consists of uppercase English letters.

#### EXPLANATION:

回溯法，原理就不多说了。
题意就是一个排列组合的问题，将所有的可能找出来就可以。


#### SOLUTION:
```JAVA
class Solution {
    public HashSet<String> set = new HashSet<>();
    public int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        numTilePossibilitiesHelper(chars,0);
        return set.size();
    }
    
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public void numTilePossibilitiesHelper(char[] chars,int i){
        if (i >= 1) {
            set.add(String.valueOf(Arrays.copyOf(chars, i)));
            System.out.println(String.valueOf(Arrays.copyOf(chars, i)));
        }

        if (i == chars.length) return;

        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            numTilePossibilitiesHelper(chars, i + 1);
            swap(chars, i, j);
        }
    }
}
```
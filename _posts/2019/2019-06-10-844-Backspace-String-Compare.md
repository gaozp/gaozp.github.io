---
layout: post
title: 844. Backspace String Compare
categories: [leetcode]
---

#### QUESTION:
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
#### EXPLANATION:
想到回车键那么就应该想到的是：后进先出，那么就是栈了，但是，为啥我用的是queue，诶，我也不知道。
将两者都进行type之后获取到两者的string。
然后再进行比对。这样下来时间复杂度就是n+n+n = 3n 就是 O（N）的时间复杂度。
#### SOLUTION:
```
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Queue<Character> q1 = new ArrayDeque<>();
        Queue<Character> q2 = new ArrayDeque<>();
        for (char c : S.toCharArray()) {
            if (c == '#') q1.poll();
            else ((ArrayDeque<Character>) q1).push(c);
        }
        for (char c : T.toCharArray()) {
            if (c == '#') q2.poll();
            else ((ArrayDeque<Character>) q2).push(c);
        }
        if (q1.size() == q2.size()) {
            for (int i = 0; i < q1.size(); i++) {
                if (q1.poll() != q2.poll()) return false;
            }
            return true;
        }
        return false; 
    }
}
```
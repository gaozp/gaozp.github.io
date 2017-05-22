---
layout: post
title: 541. Reverse String II
---

#### QUESTION:

Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

**Example:**

```
Input: s = "abcdefg", k = 2

Output: "bacdfeg"
```

**Restrictions:**

+The string consists of lower English letters only.

+Length of the given string and k will in the range [1, 10000]

#### EXPLANATION:

1.每次前进k步，通过flag判断这个k步是否需要翻转

2.需要翻转的进行翻转操作，不需要的直接continue

3.注意判断最后的边界情况

#### SOLUTION:

```java
public class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        boolean shouldReverse = false;
        for(int i = 0;i<chars.length;i+=k){
            shouldReverse = !shouldReverse;
            if(!shouldReverse)continue;
            reverseHelper(chars,i,i+k-1);
        }
        return new String(chars);
    }
    
    public void reverseHelper(char[] chars,int a,int b){
        if(b>chars.length-1) b = chars.length-1;
        while (a<b){
            switchIndex(chars,a,b);
            a++;b--;
        }
    }
    public void switchIndex(char[] chars,int a,int b){
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }
}
```

```c#
public static string ReverseStr(string s, int k) {
			Char[] chars = s.ToCharArray();
			bool shouldReverse = false;
			for (int i = 0; i < chars.Length; i += k) {
				shouldReverse = !shouldReverse;
				if (!shouldReverse)
					continue;
				ReverseStrHelper (chars, i, i + k - 1);
			}
			return new string (chars);
		}

		public static void ReverseStrHelper(Char[] chars,int a,int b){
			if(b>chars.Length-1) b = chars.Length-1;
			while (a < b) {
				Switch (chars, a, b);
				a++;b--;
			}
		}
		public static void Switch(Char[] chars,int a,int b){
			Char tmp = chars[a];
			chars [a] = chars [b];
			chars [b] = tmp;
		}
```


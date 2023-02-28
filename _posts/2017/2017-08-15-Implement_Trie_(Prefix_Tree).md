---
layout: post
title: 208. Implement Trie (Prefix Tree)
categories: [leetcode]
---

#### QUESTION:

Implement a trie with `insert`, `search`, and `startsWith` methods.

**Note:**
You may assume that all inputs are consist of lowercase letters `a-z`.

#### EXPLANATION:

最近发现，如果把相同类型的题目放在一起做会加深记忆，而且还可以由easy到medium到hard，这样的过程，比之前的只进行easy好多了。

好的，这道题目其实就是最普通的实现一个字典树，那其实字典树很重要的一个就是最后的complete标志位。说明是不是有这样一个单词。其他的就是普通实现了了。

#### SOLUTION:

```JAVA
public class Trie {
        Trie[] child ;
        boolean complete; //字典树的话，这个complete是很重要的，需要记录是否是一个完整的单词。
        /** Initialize your data structure here. */
        public Trie() {
            child = new Trie[26];
            complete = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie root = this;
            char[] chars = word.toCharArray();
            for(int i = 0;i<chars.length;i++){
                char tmp = chars[i];
                if(root.child[tmp-'a']==null)root.child[tmp-'a'] = new Trie();
                root = root.child[tmp-'a'];
            }
            root.complete = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie root = this;
            char[] chars = word.toCharArray();
            for(int i =0;i<chars.length;i++){
                char tmp = chars[i];
                if(root.child[tmp-'a']==null) return false;
                root = root.child[tmp-'a'];
            }
            if(root.complete == false) return false;
            return true;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie root = this;
            char[] chars = prefix.toCharArray();
            for(int i =0;i<chars.length;i++){
                char tmp = chars[i];
                if(root.child[tmp-'a']==null) return false;
                root = root.child[tmp-'a'];
            }
            return true;
        }
}
```


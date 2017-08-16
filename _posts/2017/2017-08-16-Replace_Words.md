---
layout： post
title: 648. Replace Words
---

#### QUESTION:

In English, we have a concept called `root`, which can be followed by some other words to form another longer word - let's call this word `successor`. For example, the root `an`, followed by `other`, which can form another word `another`.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the `successor` in the sentence with the `root` forming it. If a `successor` has many `roots` can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

**Example 1:**

```
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

```

**Note:**

1. The input will only have lower-case letters.
2. 1 <= dict words number <= 1000
3. 1 <= sentence words number <= 1000
4. 1 <= root length <= 100
5. 1 <= sentence words length <= 1000

#### EXPLANATION:

还是字典树的一个应用吧，将所有的prefix添加到字典树中，然后再进行匹配。需要注意的是匹配完成时需要返回匹配的结果，这样就可以直接进行替换了，也就是我注释的地方。

#### SOLUTION:

```JAVA
public class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String word:dict) trie.insert(word);
        String[] strings = sentence.split(" ");
        for(int i = 0;i<strings.length;i++){
            String tmp = strings[i];
            String flag = trie.startsWith(tmp);
            if(flag!=null) strings[i] = flag;
        }
        String result = "";
        for (String str:strings) result+=str+" ";
        return result.substring(0,result.length()-1);
    }
    public class Trie {

        Trie[] child ;
        boolean complete;
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

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public String startsWith(String prefix) {
            Trie root = this;
            String result = "";
            char[] chars = prefix.toCharArray();
            for(int i =0;i<chars.length;i++){
                char tmp = chars[i];
                if(root.complete) return result;//注意此处，如果已经完成了，就可以直接返回了。
                if(root.child[tmp-'a']==null) return null;
                result+=tmp;
                root = root.child[tmp-'a'];
            }
            return null;
        }
    }
}
```


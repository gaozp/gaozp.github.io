---
layout: post
title: 211. Add and Search Word - Data structure design
---

#### QUESTION:

Design a data structure that supports the following two operations:

```
void addWord(word)
bool search(word)

```

search(word) can search a literal word or a regular expression string containing only letters `a-z` or `.`. A `.` means it can represent any one letter.

For example:

```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

```

**Note:**
You may assume that all words are consist of lowercase letters `a-z`.

#### EXPLANATION:

还是之前的字典树的操作，但是此时需要使用的是递归的操作，如果还是用之前的循环去做的话，会有问题，所以采用了递归的方式去进行操作，注意注释的两行就会没有问题了。

#### SOLUTION:

```JAVA
public class WordDictionary {
        WordDictionary[] child ;
        boolean complete;
        /** Initialize your data structure here. */
        public WordDictionary() {
            child = new WordDictionary[26];
            complete = false;
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            addWord(word,0);
        }

        public void addWord(String word,int position){
            if(position==word.length()){
                complete = true;
                return;
            }
            char tmp = word.charAt(position);
            if(tmp=='.'){
                for(int i = 0;i<child.length;i++){
                    child[i] = new WordDictionary();
                    child[i].addWord(word,position+1);
                }
                return;//此处是需要return的，因为其实此时已经走完了所有的接下去的树，并不需要再走了。
            }
            if(child[tmp-'a']==null) child[tmp-'a'] = new WordDictionary();
            child[tmp-'a'].addWord(word,position+1);

        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            WordDictionary tmp = search(word,0);
            return tmp !=null && tmp.complete;
        }
        public WordDictionary search(String word,int position){
            if(position == word.length()) return this;
            char tmp = word.charAt(position);
            if(tmp == '.'){
                for(int i = 0;i<child.length;i++){
                    WordDictionary result = null;
                    if(child[i]!=null){
                        result = child[i].search(word,position+1);
                        if(result!=null && result.complete) return result;
                    }
                }
                return null;//如addword方法注释一样。
            }
            if(child[tmp-'a']==null) return null;
            return child[tmp-'a'].search(word,position+1);
        }
}
```


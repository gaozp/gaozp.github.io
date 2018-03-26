---
layout: post
title: 804. Unique Morse Code Words
---

#### QUESTION:

International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows: `"a"`maps to `".-"`, `"b"` maps to `"-..."`, `"c"` maps to `"-.-."`, and so on.

For convenience, the full table for the 26 letters of the English alphabet is given below:

```
[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
```

Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example, "cab" can be written as "-.-.-....-", (which is the concatenation "-.-." + "-..." + ".-"). We'll call such a concatenation, the transformation of a word.

Return the number of different transformations among all words we have.

```
Example:
Input: words = ["gin", "zen", "gig", "msg"]
Output: 2
Explanation: 
The transformation of each word is:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."

There are 2 different transformations, "--...-." and "--...--.".
```

 

**Note:**

- The length of `words` will be at most `100`.
- Each `words[i]` will have length in range `[1, 12]`.
- `words[i]` will only consist of lowercase letters.

#### EXPLANATION:

这就很简单：

1.拿出每个字符串stringa

2.拿出stringa的每个字符

3.对应到摩斯码 

4.组合在一起

5.添加到hashset去重

6.返回结果的size

#### SOLUTION:

```JAVA
class Solution {
    static String[] morses = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        HashSet<String> result = new HashSet<>();
        for(int i = 0;i<words.length;i++){
            char[] tmp = words[i].toCharArray();
            String tmpResult = "";
            for(int j = 0;j<tmp.length;j++){
                char chartmp = tmp[j];
                int index = chartmp-'a';
                tmpResult+= morses[index];
            }
            result.add(tmpResult);
        }
        return result.size();
    }
}
```


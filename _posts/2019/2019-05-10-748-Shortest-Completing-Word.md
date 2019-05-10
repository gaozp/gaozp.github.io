---
layout: post
title: 748. Shortest Completing Word
---

#### QUESTION:

Find the minimum length word from a given dictionary `words`, which has all the letters from the string `licensePlate`. Such a word is said to *complete* the given string `licensePlate`

Here, for letters we ignore case. For example, `"P"` on the `licensePlate` still matches `"p"` on the word.

It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.

The license plate might have the same letter occurring multiple times. For example, given a `licensePlate` of `"PP"`, the word `"pair"` does not complete the `licensePlate`, but the word `"supper"` does.

**Example 1:**

```
Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
Output: "steps"
Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
Note that the answer is not "step", because the letter "s" must occur in the word twice.
Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
```

**Example 2:**

```
Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
Output: "pest"
Explanation: There are 3 smallest length words that contains the letters "s".
We return the one that occurred first.
```

**Note:**

1. `licensePlate` will be a string with length in range `[1, 7]`.
2. `licensePlate` will contain digits, spaces, or letters (uppercase or lowercase).
3. `words` will have a length in the range `[10, 1000]`.
4. Every `words[i]` will consist of lowercase letters, and have length in range `[1, 15]`.

#### EXPLANATION:

我的思路是首先算出标识的所有字母及个数，然后再一个一个的进行比对。

但是看了下最快的答案，其实26个字母个数最好的方式还是用26个数组进行。这样才会达到最好的效果。因为比较数组的查找比map的快很多。

#### SOLUTION:

```java
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        char[] chars = licensePlate.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<chars.length;i++){
            char tmp = chars[i];
            if(tmp>='a'&& tmp<='z')
                map.put(tmp,map.getOrDefault(tmp,0)+1);
        }
        String result = "";
        int length = Integer.MAX_VALUE;
        for(int i = 0;i<words.length;i++){
            String tmp = words[i];
            String s = tmp.toLowerCase();
            char[] tmpChars = s.toCharArray();
            HashMap<Character,Integer> tmpMap = new HashMap<>();
            for(int j=0;j<tmpChars.length;j++){
                char index = tmpChars[j];
                if(index>='a'&& index<='z')
                    tmpMap.put(index,tmpMap.getOrDefault(index,0)+1);
            }
            if(tmpMap.size()>=map.size()){
                Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
                boolean oom = false;
                while (iterator.hasNext()){
                    Map.Entry<Character, Integer> next = iterator.next();
                    if(tmpMap.getOrDefault(next.getKey(),0)<next.getValue()) {
                        oom = true;
                        break;
                    }
                }
                if(!oom && s.length()<length){
                    length = s.length();
                    result = tmp;
                }
            }
        }
        return result;
    }

class Solution {
    
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int minLen  = Integer.MAX_VALUE;
        int idx = -1;
    
        String s = normalizeLetters(licensePlate);
        
        for(int i = 0; i < words.length; i++) {
            if(words[i].length() < minLen && isGoodMatch(s,words[i])) {
                minLen = words[i].length();
                idx = i;
            }
        }
        
        if(idx == -1) {
            return "you are lying!";
        }
        
        return words[idx];
        
    }
    
  public String normalizeLetters(String s) {
    StringBuilder builder = new StringBuilder(s.length());
    for(int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if(ch >= 'A' && ch <= 'z') {
        builder.append(Character.toLowerCase(ch));
      }
    }
    
    return builder.toString();
  }
  
  // example: abc fdabcea
  public boolean isGoodMatch(String s1, String s2) {
    if(s1.length() > s2.length()) {
      return false;
    }
    
    int[] letters = new int[26];
    
    for(int i = 0; i < s2.length(); i++) {
      letters[s2.charAt(i) - 'a']++;
    }
    
    for(int j = 0; j < s1.length(); j++) {
      if(letters[s1.charAt(j) - 'a'] == 0) {
        return false;
      }
  
      letters[s1.charAt(j) - 'a']--;
    }
    
    return true;
  }
   
}

```


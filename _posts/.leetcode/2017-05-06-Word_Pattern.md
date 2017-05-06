---
layout: post
title: 290. Word Pattern
---

#### QUESTION:

Given a `pattern` and a string `str`, find if `str` follows the same pattern.

Here **follow** means a full match, such that there is a bijection between a letter in `pattern` and a **non-empty** word in `str`.

**Examples:**

1. pattern = `"abba"`, str = `"dog cat cat dog"` should return true.
2. pattern = `"abba"`, str = `"dog cat cat fish"` should return false.
3. pattern = `"aaaa"`, str = `"dog cat cat dog"` should return false.
4. pattern = `"abba"`, str = `"dog dog dog dog"` should return false.

**Notes:**
You may assume `pattern` contains only lowercase letters, and `str` contains lowercase letters separated by a single space.

#### EXPLANATION:

其实也没有什么特别的，就是一一对应的关系，注意判断是否已经被映射了。

#### SOLUTION:

```java
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] splits = str.split(" ");
        if(pattern.length()!=splits.length) return false;
        Hashtable<Character,String> reflectTable = new Hashtable<>();
        for(int i = 0;i<pattern.length();i++){
            char key  = pattern.charAt(i);
            Object value = reflectTable.get(key);
            if(value == null){
                String sValue = splits[i];
                boolean containValue = reflectTable.containsValue(sValue);
                if(containValue)//一个新的key，所以他对应的string也应该是单独的，但是如果已经有了，那么就说明已经被映射过了。此时直接return false
                    return false;
                reflectTable.put(key,sValue);
            }else{
                if(!value.equals(splits[i])){
                    return false;
                }
            }
        }
        return true;
    }
}
```


---
layout: post
title: 383. Ransom Note
categories: [leetcode]
---

####QUESTION:
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

####EXPLANATION:
首先将ransom的字符串分解成单个的，然后一个一个的去进行匹配，但是需要注意重复的需要从前一个匹配的结果处进行。  
于是就想到了用map去记录上一个字符的位置，具体的看代码吧。  

####SOLUTION:

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] chars = ransomNote.toCharArray();
        Map<Character,Integer> mapping = new HashMap<>();
        for (char alpha:chars
                ){
            if(mapping.containsKey(alpha)){
                Integer index = mapping.get(alpha);
                index = magazine.indexOf(alpha,index+1);
                if(index != -1){
                    mapping.put(alpha,index);
                }else{
                    return false;
                }
            }else{
                mapping.put(alpha,-1);
                int index = magazine.indexOf(alpha, 0);
                if(index!=-1){
                    mapping.put(alpha,index);
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    



---
layout: post
title: 1078. Occurrences After Bigram
categories: [leetcode]
---
#### QUESTION:
Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.

 

Example 1:

Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
Example 2:

Input: text = "we will we will rock you", first = "we", second = "will"
Output: ["we","rock"]
 

Note:

1 <= text.length <= 1000
text consists of space separated words, where each word consists of lowercase English letters.
1 <= first.length, second.length <= 10
first and second consist of lowercase English letters.
#### EXPLANATION:

1.首先得到所有first的位置  
2.依次检查first+1的位置是否是second  
3.以上都满足，添加上first+2的字符，注意角标越界

#### SOLUTION:
```JAVA
class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        String[] splits = text.split(" ");
        ArrayList<Integer> indexs = new ArrayList<>();
        for(int i = 0;i<splits.length;i++){
            if(first.equals(splits[i])) indexs.add(i);
        }
        ArrayList<String> resultString = new ArrayList<>();
        for(int i = 0;i<indexs.size();i++){
            if(indexs.get(i)+2<splits.length && second.equals(splits[indexs.get(i)+1]) ) resultString.add(splits[indexs.get(i)+2]);
        }
        String[] result = new String[resultString.size()];
        for(int i = 0;i<resultString.size();i++) result[i] = resultString.get(i);
        return result;
    }
}
```
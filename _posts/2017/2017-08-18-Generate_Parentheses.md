---
layout: post
title: 22. Generate Parentheses
---

#### QUESTION:

Given *n* pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given *n* = 3, a solution set is:

```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

#### EXPLANATION:

使用的是回溯算法，思路就是先算出所有可能的结果，就是n个（加上n个），最后再算出可用的结果。

然后发现了一个问题，就是格式准备好了的，只可能是（在前，所以我的这个解法其实是有重复操作的。

只要保证左括号大于右括号，那就说明是可以的。

#### SOLUTION:

```JAVA
class Solution {

    
     HashMap<Character,Integer> generateMap = new HashMap<>();
     List<String> generateList = new ArrayList<>();
    public  List<String> generateParenthesis(int n) {
        generateMap.put('(',n);
        generateMap.put(')',n);
        generateParenthesisHelper(new StringBuilder(),n,0);
        Iterator<String> iterator = generateList.iterator();
        while (iterator.hasNext()){
            String tmp = iterator.next();
            if(!isGenerateValid(tmp)) iterator.remove();
        }
        return generateList;
    }
    public  boolean isGenerateValid(String s){
        Stack<Character> stack = new Stack<>();
        for(Character c :s.toCharArray()){
            switch (c){
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if(stack.size()==0) return false;
                    stack.pop();
                    break;
            }
        }
        return stack.size()==0;
    }

    public  void generateParenthesisHelper(StringBuilder sb,int n,int count){
        if(count>2*n) return;
        if(generateMap.get('(')==0 && generateMap.get(')')==0){
            generateList.add(sb.toString());
            return;
        }
        for(int i = 0;i<2;i++){
            if(i%2==0){
                if(isValid('(')){
                    sb.append('(');
                    generateMap.put('(',generateMap.get('(')-1);
                    generateParenthesisHelper(sb,n,count+1);
                    generateMap.put('(',generateMap.get('(')+1);
                    sb.deleteCharAt(sb.length()-1);
                }
            }else {
                if(isValid(')')){
                    sb.append(')');
                    generateMap.put(')',generateMap.get(')')-1);
                    generateParenthesisHelper(sb,n,count+1);
                    generateMap.put(')',generateMap.get(')')+1);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
    public  boolean isValid(char c){
        return generateMap.get(c)>0;
    }
}

//一个更为简单的算法
public class Solution {
    List<String> result;
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        char[] s = new char[2 * n];
        helper(s, 0, 0);
        return result;
    }
    
    private void helper(char[] s, int open, int close) {
        if (open + close == s.length) {
            result.add(new String(s));
            return;
        }
        if (open < s.length >>> 1) {
            s[open + close] = '(';
            helper(s, open + 1, close);
        }
        if (open > close) {
            s[open + close] = ')';
            helper(s, open, close + 1);
        }
    }
}

```


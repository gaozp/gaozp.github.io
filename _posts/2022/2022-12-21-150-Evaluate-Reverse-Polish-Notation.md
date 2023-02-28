---
layout: post
title: 150. Evaluate Reverse Polish Notation
categories: [leetcode]
---
#### QUESTION:
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

 

__Example 1:__
```
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
```
__Example 2:__
```
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
```
__Example 3:__
```
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```
 

__Constraints:__
```
1 <= tokens.length <= 104
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
```
#### EXPLANATION:

其实stack和array一样, 在遇到运算符号的时候, 只需要pop出来两个, 作为运算参数, 将运算结果在push进去即可.

#### SOLUTION:
```swift
class Solution {

    func evalRPN(_ tokens: [String]) -> Int {
                var stack: [Int] = []
        for token in tokens {
            if let parsed = Int(token) {
                stack.append(parsed)
            } else {
                let a = stack.removeLast()
                let b = stack.removeLast()
                switch token {
                case "+":
                    stack.append(b + a)
                    break
                case "-":
                    stack.append(b - a)
                    break
                case "*":
                    stack.append(b * a)
                    break
                case "/":
                    stack.append(b / a)
                    break
                default:
                    stack.append(Int(token)!)
                }
            }
            
        }
        return stack.removeLast()
    }
}
```

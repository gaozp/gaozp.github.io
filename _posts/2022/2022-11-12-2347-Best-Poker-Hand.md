---
layout: post
title: 2347. Best Poker Hand
categories: [leetcode]
---
#### QUESTION:
You are given an integer array ranks and a character array suits. You have 5 cards where the ith card has a rank of ranks[i] and a suit of suits[i].

The following are the types of poker hands you can make from best to worst:

"Flush": Five cards of the same suit.
"Three of a Kind": Three cards of the same rank.
"Pair": Two cards of the same rank.
"High Card": Any single card.
Return a string representing the best type of poker hand you can make with the given cards.

Note that the return values are case-sensitive.

 

__Example 1:__
```
Input: ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"]
Output: "Flush"
Explanation: The hand with all the cards consists of 5 cards with the same suit, so we have a "Flush".
```
__Example 2:__
```
Input: ranks = [4,4,2,4,4], suits = ["d","a","a","b","c"]
Output: "Three of a Kind"
Explanation: The hand with the first, second, and fourth card consists of 3 cards with the same rank, so we have a "Three of a Kind".
Note that we could also make a "Pair" hand but "Three of a Kind" is a better hand.
Also note that other cards could be used to make the "Three of a Kind" hand.
```
__Example 3:__
```
Input: ranks = [10,10,2,12,9], suits = ["a","b","c","a","d"]
Output: "Pair"
Explanation: The hand with the first and second card consists of 2 cards with the same rank, so we have a "Pair".
Note that we cannot make a "Flush" or a "Three of a Kind".
```
 

__Constraints:__
```
ranks.length == suits.length == 5
1 <= ranks[i] <= 13
'a' <= suits[i] <= 'd'
No two cards have the same rank and suit.
```
#### EXPLANATION:

只要按照顺序去判断这个牌力即可, 比如第一个就是用set去判断, 这样就能判断出是不是5个同样花色. 然后就是判断单个牌的个数, 每个人使用的方法不同. 最后就只剩下高牌了. 

#### SOLUTION:
```swift
class Solution {
    func bestHand(_ ranks: [Int], _ suits: [Character]) -> String {
        if (Set(suits).count == 1) {
            return "Flush"
        }
        var countArr:[Int] = []
        ranks.forEach({ rank in
            countArr.append(ranks.filter{$0 == rank}.count)
        })
        if (countArr.max()! >= 3) {
            return "Three of a Kind"
        }
        if (countArr.max()! >= 2) {
            return "Pair"
        }
        return "High Card"
    }
}
```

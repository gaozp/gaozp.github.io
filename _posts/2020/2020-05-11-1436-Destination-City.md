---
layout: post
title: 1436. Destination City
categories: [leetcode]
---
#### QUESTION:
You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.

Example 1:
```
Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo" 
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
```
Example 2:
```
Input: paths = [["B","C"],["D","B"],["C","A"]]
Output: "A"
Explanation: All possible trips are: 
"D" -> "B" -> "C" -> "A". 
"B" -> "C" -> "A". 
"C" -> "A". 
"A". 
Clearly the destination city is "A".
```
Example 3:
```
Input: paths = [["A","Z"]]
Output: "Z"
```

Constraints:
```
1 <= paths.length <= 100
paths[i].length == 2
1 <= cityAi.length, cityBi.length <= 10
cityAi != cityBi
All strings consist of lowercase and uppercase English letters and the space character.
```
#### EXPLANATION:
题意为，给定的list表示从a-》b的路，求一个终点。既该点没有到其他地方的路，那么该问题就可以抽象为一个向量，向量的起点是path.get(i).get(0),终点是path.get(i).get(1)，那么，只有起点没有终点的就是我们需要求的。那么我们怎么建立这个向量关系呢，我们可以建立一个数组，数组index表示向量的头，数组的value表示向量的尾，那么，数组中唯一一个没有值的i就是终点了。  
1. 首先for循环建立 string 和 index的对应关系map  
2. 填充result数组  
3. 查看数组，寻找其中没有添加向量尾的点  
4. 通过该点的index反推对应的城市名字

#### SOLUTION:
```java
class Solution {
    public String destCity(List<List<String>> paths) {
        HashMap<String,Integer> maps = new HashMap<>();
        int index = -1;
        for(int i = 0;i<paths.size();i++){
            List<String> strings = paths.get(i);
            if(maps.get(strings.get(0))==null) {
                index++;
                maps.put(strings.get(0),index);
            }
            if(maps.get(strings.get(1))==null) {
                index++;
                maps.put(strings.get(1),index);
            }
        }
        int[] result = new int[index+1];
        Arrays.fill(result,-1);
        for(int i = 0;i<paths.size();i++){
            int from = maps.get(paths.get(i).get(0));
            int to = maps.get(paths.get(i).get(1));
            result[from] = to;
        }
        for(int i = 0;i<result.length;i++){
            if(result[i]==-1){
                Iterator<Map.Entry<String, Integer>> iterator = maps.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, Integer> next = iterator.next();
                    if(next.getValue()==i) return next.getKey();
                }
            }
        }
        return "";
    }
}
```

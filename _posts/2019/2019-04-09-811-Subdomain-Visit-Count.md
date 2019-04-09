---
layout: post
title: 811. Subdomain Visit Count
---

#### QUESTION:

A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

We are given a list `cpdomains` of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

```
Example 1:
Input: 
["9001 discuss.leetcode.com"]
Output: 
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
Explanation: 
We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.


Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation: 
We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
```

**Notes:**

- The length of `cpdomains` will not exceed `100`. 
- The length of each domain name will not exceed `100`.
- Each address will have either 1 or 2 "." characters.
- The input count in any count-paired domain will not exceed `10000`.
- The answer output can be returned in any order.

#### EXPLANATION:

从题目的同一个domain的访问量需要累加，我们就知道这个地方需要使用的是hashmap或者hashtable，既然是单线程，那么就肯定需要使用hashmap来提高效率了。

然后就是按照算法步骤一步一步的来就可以了。

1.先将数字和domain进行分开，用空格进行拆分。

2.然后再将domain进行分开，此处有一个点，就是每次分开的需要和后面的进行累加。比如leetcode.com，那么分开的leetcode就需要和后面的com进行合并才能算。

3.访问次数累加完成后，就直接进行添加进入list的操作。

#### SOLUTION:

```java
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        ArrayList<String> result = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        for(int i = 0;i<cpdomains.length;i++){
            String[] splits = cpdomains[i].split(" ");
            int count = Integer.parseInt(splits[0]);
            String[] domains = splits[1].split("\\.");
            String tmp = "";
            for(int j = domains.length-1;j>=0;j--){
                if(tmp=="") tmp = domains[j]+tmp;
                else tmp = domains[j]+"."+tmp;
                map.put(tmp,map.getOrDefault(tmp,0)+count);
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            result.add(next.getValue()+" "+next.getKey());
        }
        return result;
    }
}
```


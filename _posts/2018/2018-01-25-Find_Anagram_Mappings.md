---
layout: post
title: 760.Find Anagram Mappings
---

#### QUESTION:

Given two lists `A`and `B`, and `B` is an anagram of `A`. `B` is an anagram of `A` means `B` is made by randomizing the order of the elements in `A`.

We want to find an *index mapping* `P`, from `A` to `B`. A mapping `P[i] = j` means the `i`th element in `A` appears in `B` at index `j`.

These lists `A` and `B` may contain duplicates. If there are multiple answers, output any of them.

For example, given

```
A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]

```

We should return

```
[1, 4, 3, 2, 0]

```

as 

```
P[0] = 1
```

 because the 

```
0
```

th element of 

```
A
```

 appears at 

```
B[1]
```

, and 

```
P[1] = 4
```

 because the 

```
1
```

st element of 

```
A
```

 appears at 

```
B[4]
```

, and so on.

**Note:**

1. `A, B` have equal lengths in range `[1, 100]`.
2. `A[i], B[i]` are integers in range `[0, 10^5]`.

#### EXPLANATION:

思路也是很容易的

1.将b中元素和对应的位置一一取出。因为有重复的，所以用stack进行存储。

2.遍历A中的元素，获取到对应的位置，进行pop，这样就不会选取到两次一样的位置了。

#### SOLUTION:

```JAVA
import java.util.*;
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Hashtable<Integer,Stack<Integer>> table = new Hashtable<>();
        for(int i = 0;i<B.length;i++){
            Stack<Integer> tmp = table.get(B[i]);
            if(tmp==null)
                tmp = new Stack<Integer>();
            tmp.push(i);
            table.put(B[i],tmp);
        }
        int[] result = new int[A.length];
        for(int i = 0;i<A.length;i++){
            result[i] = table.get(A[i]).pop();
        }
        return result;
    }
}
```


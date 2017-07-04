---
layout: post
title: 278. First Bad Version
---

#### QUESTION:

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have `n` versions `[1, 2, ..., n]` and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API `bool isBadVersion(version)` which will return whether `version` is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

#### EXPLANATION:

其实就是一个二分法查找的思想，一直查找到最后就行。

#### SOLUTION:

```JAVA
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;

        while (low<= high){
            int middle = high - (high-low)/2;
            if(isBadVersion(middle)){
                high = middle-1;
            }else{
                low = middle+1;
            }
        }
        return low;
    }
}
```


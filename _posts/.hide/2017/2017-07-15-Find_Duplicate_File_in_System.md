---
layout: post
title: 609. Find Duplicate File in System
categories: [leetcode]
---

#### QUESTION:

Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least **two** files that have exactly the same content.

A single directory info string in the **input** list has the following format:

`"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"`

It means there are **n** files (`f1.txt`, `f2.txt` ... `fn.txt` with content `f1_content`, `f2_content` ... `fn_content`, respectively) in directory `root/d1/d2/.../dm`. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The **output** is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

`"directory_path/file_name.txt"`

**Example 1:**

```
Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:  
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

```

**Note:**

1. No order is required for the final output.
2. You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
3. The number of files given is in the range of [1,20000].
4. You may assume no files or directories share the same name in the same directory.
5. You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.

Follow-up beyond contest:

1. Imagine you are given a real file system, how will you search files? DFS or BFS?
2. If the file content is very large (GB level), how will you modify your solution?
3. If you can only read the file by 1kb each time, how will you modify your solution?
4. What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
5. How to make sure the duplicated files you find are not false positive?

#### EXPLANATION:

其实就是用hashtable将内容相同的分在一组，最后再转换成list即可。

#### SOLUTION:

```java
public class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        ArrayList<List<String>> result = new ArrayList<>();
        Hashtable<String, List<String>> table = new Hashtable<>();
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            String[] splits = path.split(" ");
            String location = splits[0];
            for (int j = 1; j < splits.length; j++) {
                String file = splits[j];
                int left = file.indexOf('(');
                int right = file.indexOf(')');
                String content = file.substring(left + 1, right);
                String filepath = file.substring(0, left);
                List<String> value = table.getOrDefault(content, new ArrayList<>());
                value.add(location + "/" + filepath);
                table.put(content, value);
            }
        }
        for (List<String> list : table.values()) {
            if (list.size() > 1) result.add(list);
        }
        return result;
    }
}
```


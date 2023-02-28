---
layout: post
title: 690. Employee Importance
categories: [leetcode]
---

#### QUESTION:

You are given a data structure of employee information, which includes the employee's **unique id**, his **importance value** and his **direct**subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is **not direct**.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

**Example 1:**

```
Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.

```

**Note:**

1. One employee has at most one **direct** leader and may have several subordinates.
2. The maximum number of employees won't exceed 2000.

#### EXPLANATION:

如代码所示，其实逻辑很简单。

#### SOLUTION:

```JAVA
class Solution {
    public int importanceResult = 0;
    public int getImportance(List<Employee> employees, int id) {
        for(int i = 0;i<employees.size();i++){
            Employee tmp = employees.get(i);
            if(tmp.id == id){
                importanceResult+=tmp.importance;
                if(tmp.subordinates.size()>=0){
                    for(int j = 0;j<tmp.subordinates.size();j++)
                        getImportance(employees,tmp.subordinates.get(j));
                }
            }
        }
        return importanceResult;
    }
}
```


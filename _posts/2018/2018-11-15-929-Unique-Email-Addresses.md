---
layout: post
title: 929. Unique Email Addresses
categories: [leetcode]
---

#### QUESTION:

Every email consists of a local name and a domain name, separated by the @ sign.

For example, in `alice@leetcode.com`, `alice` is the local name, and `leetcode.com` is the domain name.

Besides lowercase letters, these emails may contain `'.'`s or `'+'`s.

If you add periods (`'.'`) between some characters in the **local name**part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, `"alice.z@leetcode.com"` and `"alicez@leetcode.com"`forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus (`'+'`) in the **local name**, everything after the first plus sign will be **ignored**. This allows certain emails to be filtered, for example `m.y+name@email.com` will be forwarded to `my@email.com`.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of `emails`, we send one email to each address in the list.  How many different addresses actually receive mails? 

 

**Example 1:**

```
Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
```

 

**Note:**

- `1 <= emails[i].length <= 100`
- `1 <= emails.length <= 100`
- Each `emails[i]` contains exactly one `'@'` character.

#### EXPLANATION:

这个就比较简单了，就是将每一个字符拿进来进行比对.

然后我也看了最快速的10ms的答案，发现其实是错误的，钻了case的漏洞。

#### SOLUTION:

```JAVA
class Solution {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> result = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (String email : emails) {
            char[] chars = email.toCharArray();
            builder.delete(0, builder.length());
            boolean plus = false;
            for (int i = 0; i < chars.length; i++) {
                char tmp = chars[i];
                if (tmp == '.') {
                        continue;
                } else if (tmp == '+') {
                    plus = true;
                } else if (tmp == '@') {
                    plus = false;
                    builder.append(chars,i,chars.length-i);
                } else {
                    if (!plus)
                        builder.append(tmp);
                }
            }
            result.add(builder.toString());
        }
        return result.size();
    }
}
```


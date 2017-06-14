---
layout: post
title: 535. Encode and Decode TinyURL
---

#### QUESTION:

TinyURL is a URL shortening service where you enter a URL such as `https://leetcode.com/problems/design-tinyurl` and it returns a short URL such as `http://tinyurl.com/4e9iAk`.

Design the `encode` and `decode` methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

#### EXPLANATION:

其实我就是直接用base64进行编码的。。。

#### SOLUTION:

```java
import java.util.*;
public class Codec {

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        return new String(Base64.getEncoder().encode(longUrl.getBytes()));
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return new String(Base64.getDecoder().decode(shortUrl.getBytes()));
    }
}
```


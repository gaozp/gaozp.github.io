---
layout: page
title: 归档
description: 归档
---
<ul class="archive">
{% for post in site.posts %}
  {% console.log(page.title) %}
  {% capture y %}{{post.date | date:"%Y"}}{% endcapture %}
  {% console.log("year:"+year+"  y:"+y) %}
  {% if year != y %}
    {% assign year = y %}
   {% console.log("222 year:"+year+"  y:"+y) %}
    <li class="year">{{ y }}</li>
  {% endif %}
  <li class="item">
    <time datetime="{{ post.date | date:"%Y-%m-%d" }}">{{ post.date | date:"%Y-%m-%d" }}</time>
    <a href="{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a>
  </li>
{% endfor %}
</ul>

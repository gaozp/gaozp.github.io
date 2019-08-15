---
layout: page
title: 归档
description: 归档
---
<ul class="archive">
{% assign techPost = "" | split: "" %}
{% assign leetcodePost = "" | split: "" %}
{% assign lifePost = "" | split: "" %}
<!-- 按category进行分组 -->
{% for post in site.posts %}
  {% if post.categories contains 'tech' %}
    {% assign techPost = techPost | push: post %}
  {% endif %}
  {% if post.categories contains 'leetcode' %}
    {% assign leetcodePost = leetcodePost | push: post %}
  {% endif %}
  {% if post.categories contains 'life' %}
    {% assign lifePost = lifePost | push: post %}
  {% endif %}
{% endfor %}

<li class="year">Tech</li>
{% for post in techPost %}
  <li class="item">
    <time datetime="{{ post.date | date:"%Y-%m-%d" }}">{{ post.date | date:"%Y-%m-%d" }}</time>
    <a href="{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a>
  </li>
{% endfor %}

<li class="year">Life</li>
{% for post in lifePost %}
  <li class="item">
    <time datetime="{{ post.date | date:"%Y-%m-%d" }}">{{ post.date | date:"%Y-%m-%d" }}</time>
    <a href="{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a>
  </li>
{% endfor %}

<li class="year">Leetcode</li>
{% for post in leetcodePost %}
  <li class="item">
    <time datetime="{{ post.date | date:"%Y-%m-%d" }}">{{ post.date | date:"%Y-%m-%d" }}</time>
    <a href="{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a>
  </li>
{% endfor %}

<!-- {% for post in site.posts %}
  {% capture y %}{{post.date | date:"%Y"}}{% endcapture %}
  {% if year != y %}
    {% assign year = y %}
    <li class="year">{{ y }}</li>
  {% endif %}
  <li class="item">
    <time datetime="{{ post.date | date:"%Y-%m-%d" }}">{{ post.date | date:"%Y-%m-%d" }}</time>
    <a href="{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a>
  </li>
{% endfor %} -->
</ul>

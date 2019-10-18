#!/bin/bash
title=$1
categories=$2
time=$(date "+%Y-%m-%d")
touch $time.md
echo "---" >> $time.md
echo "layout: post">> $time.md
echo "title: $title" >> $time.md
echo "categories: [$categories]" >> $time.md
echo "---" >> $time.md
echo "#### QUESTION:" >> $time.md
echo "" >> $time.md 
echo "#### EXPLANATION:" >> $time.md
echo "" >> $time.md 
echo "#### SOLUTION:">> $time.md
echo "\`\`\`java" >> $time.md
echo "\`\`\`" >> $time.md

---
layout: post
title: 找出旋转有序数列的中间值
categories: [leetcode]
---

#### QUESTION:

给出一个有序数列随机旋转之后的数列，如原有序数列为：[0,1,2,4,5,6,7] ，旋转之后为[4,5,6,7,0,1,2]。
假定数列中无重复元素，且数列长度为奇数。
求出旋转数列的中间值。如数列[4,5,6,7,0,1,2]的中间值为4。

#### EXPLANATION:

需要注意的是进行过一次随机旋转的数列。

首先的想法其实就是找出旋转的位置，然后从旋转的位置往前或者往后数length/2的位置那么就是中间值了。

但是此时也是需要分为两种情况：

1.如果旋转的位置正好小鱼 length/2 那么就是向后数

2.如果旋转的位置大于length/2那么就是向前数

根据这个逻辑写出代码即可。

#### SOLUTION:

```JAVA
    private static String reversesolution(String line) {
        // 在此处理单行数据
        String[] nums = line.split(",");
        if(nums.length==0) return line;
        int res = Integer.parseInt(nums[0]);
        int index = 0;
        for(int i = 1;i<nums.length;i++){
            int tmp = Integer.parseInt(nums[i]);
            if(tmp < res){
                index = i;
                break;
            }
            res = tmp;
        }
        if(index<=nums.length/2){
            return nums[index+nums.length/2];
        }else{
            return nums[index-nums.length/2-1];
        }

    }
```


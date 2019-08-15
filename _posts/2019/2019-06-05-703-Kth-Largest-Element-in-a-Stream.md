---
layout: post
title: 703. Kth Largest Element in a Stream
categories: [leetcode]
---
#### QUESTION:
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note: 
You may assume that nums' length ≥ k-1 and k ≥ 1.
#### EXPLANATION:
有点像priorityqueue，只需要每次return的结果都是k大值就可以。
思路就是：
1.首先将值进行排序
2.然后将val添加进去再进行排序
3.找出第k大的值
那么如果这样肯定会时间不够。因为其实每次都进行了排序操作会很时间复杂度，那么就可以采用二分法进行查找位置，直接将val值插入到其中。然后再返回第k大的值，这样就避免了反复排序的操作。

#### SOLUTION:
```
class KthLargest {

    ArrayList<Integer> list  = new ArrayList<>();

        int k;

        public KthLargest(int k, int[] nums) {
            Arrays.sort(nums);
            IntStream.of(nums).forEach(a->{list.add(a);});
            this.k = k;
        }

        public int add(int val) {
            list.add(binarySearch(val),val);
            return list.get(list.size()-k);
        }

        private int binarySearch(int val){
            int start = 0;
            int end = list.size()-1;
            while (start<=end){
                int mid = (start+end)/2;
                if(list.get(mid)==val) return mid;
                else if(list.get(mid)>val) end = mid-1;
                else start = mid+1;
            }
            return start;
        }
}
```
---
layout: post
title: 1030. Matrix Cells in Distance Order
---

#### QUESTION:

We are given a matrix with `R` rows and `C` columns has cells with integer coordinates `(r, c)`, where `0 <= r < R` and `0 <= c < C`.

Additionally, we are given a cell in that matrix with coordinates `(r0, c0)`.

Return the coordinates of all cells in the matrix, sorted by their distance from `(r0, c0)` from smallest distance to largest distance.  Here, the distance between two cells `(r1, c1)` and `(r2, c2)` is the Manhattan distance, `|r1 - r2| + |c1 - c2|`.  (You may return the answer in any order that satisfies this condition.)

**Example 1:**

```
Input: R = 1, C = 2, r0 = 0, c0 = 0
Output: [[0,0],[0,1]]
Explanation: The distances from (r0, c0) to other cells are: [0,1]
```

**Example 2:**

```
Input: R = 2, C = 2, r0 = 0, c0 = 1
Output: [[0,1],[0,0],[1,1],[1,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
```

**Example 3:**

```
Input: R = 2, C = 3, r0 = 1, c0 = 2
Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
```

**Note:**

1. `1 <= R <= 100`
2. `1 <= C <= 100`
3. `0 <= r0 < R`
4. `0 <= c0 < C`

#### EXPLANATION:

其实算法也很简单，就是算出所有的dis，然后按照dis进行排序。难点就是在怎么获取到每个dis的个数。

一种方法就是不需要管，只进行集合，然后对集合进行展平。

另外一种就是需要计算出对应的位置，然后直接进行填充。

#### SOLUTION:

```JAVA
class Solution {
    private int R = 0;
    private int C = 0;
    private int r0 = 0;
    private int c0 = 0;
    private int counter = 0;
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        this.R = R;
        this.C = C;
        this.r0 = r0;
        this.c0 = c0;
        int dis = 1;
        int[][] cells = new int[R * C][];
        cells[counter++] = new int[]{r0, c0};
        while (true) {
            if (!addCells(cells, r0, c0 - dis)) {
                break;
            }
            dis++;
        }
        return cells;
    }
    
    private boolean addCells(int[][] cells, int lr, int lc) {
        boolean added = false;
        int dis = c0 - lc;
        int rr = r0;
        int rc = c0 + dis;
        if (add(cells, lr, lc)) added = true;
        if (add(cells, rr, rc)) added = true;
        for (int i = 1; i <= dis; i++) {
            if (add(cells, lr - i, lc + i)) added = true;
            if (add(cells, lr + i, lc + i)) added = true;
        }
        for (int i = 1; i < dis; i++) {
            if (add(cells, rr - i, rc - i)) added = true;
            if (add(cells, rr + i, rc - i)) added = true;
        }
        return added;
    }
    
    private boolean add(int[][] cells, int r, int c) {
        if (r >= 0 && r < R && c >= 0 && c < C) {
            cells[counter++] = new int[]{r, c};
            return true;
        }
        return false;
    }
}


class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[] counter = new int[R*C + 1];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dist = Math.abs(i-r0) + Math.abs(j-c0);
                counter[dist+1]++;
            }
        }
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i-1];
        }
        int[][] res = new int[R*C][];
        int index = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dist = Math.abs(i-r0) + Math.abs(j-c0);
                res[counter[dist]++] = new int[] {i, j};
            }
        }
       
        return res;
    }
}

    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        HashMap<Integer,ArrayList> map = new HashMap<>();
        for(int i = 0;i<R;i++){
            for(int j =0;j<C;j++){
                int[] tmp = new int[]{i,j};
                int distance = Math.abs(i-r0)+Math.abs(j-c0);
                ArrayList list = map.getOrDefault(distance,new ArrayList<>());
                list.add(tmp);
                map.put(distance,list);
            }
        }
        int[][] result = new int[R*C][];
        Iterator<Map.Entry<Integer, ArrayList>> iterator = map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()){
            Map.Entry<Integer, ArrayList> next = iterator.next();
            ArrayList value = next.getValue();
            for(int i = 0;i<value.size();i++){
                result[index] = (int[]) value.get(i);
                index++;
            }
        }
        return result;
    }
```


package GraphX;

import java.util.HashMap;

/**
 * 947. Most Stones Removed with Same Row or Column
 */
public class MostStonesRemovedwithSameRoworColumn {
    private int ans;
    private int[] parent;
    private int[] treeSize;
    private int component;

    /**
     * 并查集里的元素到底是什么?
     *
     * 1.是「横坐标」和「纵坐标」的数值,那么好像就把stone分为2份了, stone[i] = [xi, yi], xi和yi要合并到一起.也就是一个点自己合并自己?感觉很怪. 虽然通过可以通过将横坐标便宜,达到横坐标和纵坐标区分开,但是理解还是很不自然.
     *
     * 如果查并集里是i,即stone的下标,那么理解就很自然. 比如stone[2] = [1, 4], stone[3] = [1, 5], 那么就是要把2、3这俩石头union到一起
     * 所以如何通过[xi, yi]快速找到stone[i], 就很关键. 通过分别建立xi到i、yi到i的哈希表, 就解决了这个问题.
     *
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        parent = new int[n];
        treeSize = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
            treeSize[i] = 1;
        }

        HashMap<Integer, Integer> row2Component = new HashMap<>();
        HashMap<Integer, Integer> col2Component = new HashMap<>();

        component = n;
        for (int i = 0; i < n; i++){
            int row = stones[i][0];
            int col = stones[i][1];
            // 如果行坐标相同，那么跟之前相同的stone union起来
            if (row2Component.containsKey(row)){
                union(row2Component.get(row), i);
            } else {
                row2Component.put(row, i);
            }

            // 如果行坐标相同，那么跟之前相同的stone union起来
            if (col2Component.containsKey(col)){
                union(col2Component.get(col), i);
            } else {
                col2Component.put(col, i);
            }
        }

        return n - component;
    }

    // 采用加权的查并集
    private void union(int v, int w){
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot != wRoot){
            if (treeSize[vRoot] < treeSize[wRoot]){
                parent[vRoot] = wRoot;
                treeSize[wRoot] += treeSize[vRoot];
            } else {
                parent[wRoot] = vRoot;
                treeSize[vRoot] += treeSize[wRoot];
            }
            component--;
        }
    }

    private int find(int v){
        while (v != parent[v]){
            v = parent[v];
        }
        return v;
    }

    public static void main(String[] args) {
        MostStonesRemovedwithSameRoworColumn inst = new MostStonesRemovedwithSameRoworColumn();
        int[][] stones = new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        if (inst.removeStones(stones) != 5){
            throw new AssertionError();
        }
    }
}

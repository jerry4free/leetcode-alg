/**
 * 684. 冗余连接
 */
public class RedundantConnection {

    private int[] parent;
    private int[] treeSize;

    private int find(int v){
       while (parent[v] != v){
           v = parent[v];
       }
       return v;
    }

    private void union(int i, int j){
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot != jRoot) {
            if (treeSize[iRoot] < treeSize[jRoot]) {
                parent[iRoot] = jRoot;
                treeSize[jRoot] += treeSize[iRoot];
            } else {
                parent[jRoot] = iRoot;
                treeSize[iRoot] += treeSize[jRoot];
            }
        }
    }

    private boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }


    /**
     * 查并集, 加权重的查并集
     * 时间复杂度：O(n lgn)
     * 空间复杂度：O(n)
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n];
        treeSize = new int[n];

        for (int i = 0; i < n; i++){
            parent[i] = i;
            treeSize[i] = 0;
        }

        for (int[] edge: edges) {
            // 如果是一棵树，遍历完，最终是一个连通分量；但是如果又加了一条边，说明这条边的2个顶点已经连通了
            if (isConnected(edge[0], edge[1])){
                return edge;
            } else {
                union(edge[0], edge[1]);
            }
        }

        // can't go here
        return edges[0];
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        RedundantConnection inst = new RedundantConnection();
        int[] edge = inst.findRedundantConnection(edges);
        assert edge[0] == 1 && edge[1] == 4;
    }
}

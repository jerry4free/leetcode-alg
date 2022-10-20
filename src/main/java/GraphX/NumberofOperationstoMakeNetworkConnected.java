package GraphX;

/**
 * 1319. Number of Operations to Make Network Connected
 */
public class NumberofOperationstoMakeNetworkConnected {
    private int[] treeSize;
    private int[] parent;
    private int cnt;

    /**
     *  查并集方法，
     *  一个V个点的图，需要V-1个边，可以构成一个树，将所有点连接起来。少于v-1个边，无法连通
     *  如果有cnt个连通分量，那么只需要cnt-1个边即可，把这个多个分量，连为一个树
    * */

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1){
            return -1;
        }

        treeSize = new int[n];
        parent = new int[n];

        for (int i = 0; i < n; i++){
            treeSize[i] = 1;
            parent[i] = i;
        }

        int m = connections.length;
        cnt = n;
        for (int i = 0; i < m; i++){
            union(connections[i][0], connections[i][1]);
        }

        return cnt - 1;
    }

    private void union(int v, int w){
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot != wRoot){
            if (treeSize[vRoot] < treeSize[wRoot]){
                treeSize[wRoot] += treeSize[vRoot];
                parent[vRoot] = wRoot;
            } else {
                treeSize[vRoot] += treeSize[wRoot];
                parent[wRoot] = vRoot;
            }
            cnt--;
        }
    }

    private int find(int v){
        while (v != parent[v]){
            v = parent[v];
        }
        return v;
    }
}

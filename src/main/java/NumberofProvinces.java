import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 547. Number of Provinces
 */
public class NumberofProvinces {

    private int [] parrent;
    private int componentCount;
    private int [] treeSize;

    // depth of a node, lg(n)
    private int find(int p) {
        while (p != parrent[p]) {
            p = parrent[p];
        }
        return p;
    }

    // lg(n)
    private void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (qRoot != pRoot) {
            if (treeSize[qRoot] < treeSize[pRoot]) {
                parrent[qRoot] = pRoot;
            } else {
                parrent[pRoot] = qRoot;
            }
            componentCount--;
        }
    }

    /**
     * 算法：查并集
     * */
    public int findCircleNum(int[][] isConnected) {
        // init
        int n = isConnected.length;
        parrent = new int[n];
        treeSize = new int[n];
        componentCount = n;
        for (int i = 0; i < n; i++){
            parrent[i] = i;
            treeSize[i] = 1;
        }

        // union
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        return componentCount;
    }

    /**
     *
     * isConnected: 表示的就是临接矩阵
     * 省份数量：就是求图的连通分量的数量
     *
     * 算法：DFS
     * */
    public int findCircleNum2(int[][] isConnected) {
        int componetCount = 0;
        int n = isConnected.length;
        boolean [] visited = new boolean[n];

        // 遍历每一个city, 如果没有visted，则对每个city进行dfs，直到所有这个分量遍历完，则为一个省份
        // 遍历完所有的城市后，可以得到连通分量的个数，即省份的个数
        for (int i = 0; i < n; i++){
            if (!visited[i]) {
                dfs(isConnected, visited, n, i);
                componetCount++;
            }
        }
        return componetCount;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int n, int i){
        // 对于第i个city，第i行所有为1的，即代表的就是这个city的所有相邻的城市, 对这些城市进行深度优先搜索，标记访问过的
        // 直到同一个连通分量的所有城市都被访问到，即可得到一个省份
        for (int j = 0; j < n; j++){
            if (!visited[j] && isConnected[i][j] == 1){
                visited[j] = true;
                dfs(isConnected, visited, n, j);
            }
        }
    }

    /**
     * 广度优先搜索
     */
    public int findCircleNum3(int[][] isConnected) {
        int componetCount = 0;
        int n = isConnected.length;
        boolean [] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();


        for (int i = 0; i < n; i++){
            if (!visited[i]) {
                q.add(i);   // 添加第一个某个连通分量的第一个点，即某个省份的第一个城市
                bfs(isConnected, visited, q, n); // 广度优先搜索，遍历完这个通量，即：查找这个省份的所有城市
                componetCount++;
            }
        }
        return componetCount;
    }

    private void bfs(int[][] isConnected, boolean[] visited, Queue<Integer> q, int n){
        while (!q.isEmpty()) {
            // 标记访问过的
            int i = q.remove();
            visited[i] = true;
            // 遍历所有没有访问过的相邻城市, 加入队列
            for (int j = 0; j < n; j++){
                if (!visited[j] && isConnected[i][j] == 1) {
                    q.add(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberofProvinces inst = new NumberofProvinces();
        int [][] isConnected = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        int [][] isConnected2 = new int[][]{{1,0,0},{0,1,0},{0,0,1}};
        assert inst.findCircleNum(isConnected) == 2;
        assert inst.findCircleNum(isConnected2) == 3;

        assert inst.findCircleNum2(isConnected) == 2;
        assert inst.findCircleNum2(isConnected2) == 3;

        assert inst.findCircleNum3(isConnected) == 2;
        assert inst.findCircleNum3(isConnected2) == 3;
    }
}

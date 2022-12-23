package GraphX;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 542. 01 矩阵
 */
public class UpdateMatrix {
    Queue<int[]> q;
    static final int[][] offset = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * 题目是求一个每个元素到0的最短距离的矩阵
     *
     * 1。很直接的一个想法，是枚举每个是1的元素，然后进行广度优先遍历直到遇到0。这种办法能行得通吗？为什么？
     *    对于一个1，可以采用这种BFS的方式找到最近的0，然后更新这个1的距离。但是矩阵里有多个1，这种方法不是最优，而是求所有1到所有0的这个集合的最短路
     *    所以属于一个多源最短路径问题
     * 2。为什么要从0开始，广度优先遍历直到遇到1呢？
     *    从每个源点0开始，从各个0开始一圈圈的同时向外扩散（一步步的向外广播），那么每一步都可以找到到所有0的距离最短，而且能找到许多1到最近0的距离
     *    思考：其实可以认为图中有一个超级源点，这个超级源点跟所有的0相连，那么这个多源广度优先搜索，可以转化为单源广度优先搜索。多源广度优先搜索就是
     *    单源广度优先搜索的第2步
     */
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];  // 由于是无向图，每条边存储2边，需要是否访问过的标记，否则会死循环（有向图不需要）
        q = new ArrayDeque<>();

        // 就是从所有的0开始，进行广度优先遍历
        // 加入所有的0到队列中，所有的源都加入到队列中
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (mat[i][j] == 0){
                    visited[i][j] = true;
                    q.add(new int[]{i,j});
                }
            }
        }

        bfs1(mat, dist, visited);

        return dist;
    }

    private void bfs1(int[][] mat, int[][] dist, boolean[][] visited){
        int n = mat.length;
        int m = mat[0].length;

        while (!q.isEmpty()) {
            int[] v = q.poll();
            for (int d = 0; d < 4; d++){
                int i = v[0] + offset[d][0];
                int j = v[1] + offset[d][1];
                if (i >= 0 && i <= (n - 1) && j >= 0 && j <= (m - 1) && !visited[i][j]){  // 没访问过且没越界
                    visited[i][j] = true;  // 入队之后就标记已访问
                    dist[i][j] = dist[v[0]][v[1]] + 1;
                    q.add(new int[]{i, j});
                }
            }
        }
    }

    private void bfs(int[][] mat, int[][] dist, boolean[][] visited){
        int n = mat.length;
        int m = mat[0].length;

        while (!q.isEmpty()){
            int[] v = q.poll();
            int i = v[0];
            int j = v[1];

            if (i < (n - 1) && !visited[i+1][j]){
                dist[i+1][j] = dist[i][j] + 1;
                visited[i+1][j] = true;
                q.add(new int[]{i+1, j});
            }
            if (j < (m - 1) && !visited[i][j+1]){
                dist[i][j+1] = dist[i][j] + 1;
                visited[i][j+1] = true;
                q.add(new int[]{i, j+1});
            }
            if (i > 0 && !visited[i-1][j]){
                dist[i-1][j] = dist[i][j] + 1;
                visited[i-1][j] = true;
                q.add(new int[]{i-1, j});
            }
            if (j > 0 && !visited[i][j-1]){
                dist[i][j-1] = dist[i][j] + 1;
                visited[i][j-1] = true;
                q.add(new int[]{i, j-1});
            }
        }
    }
}

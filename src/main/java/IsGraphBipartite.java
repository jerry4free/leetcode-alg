import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {
    private boolean ret = true;
    boolean[] visited ;
    boolean[] color ;

    public boolean isBipartite(int[][] graphs){
        int n = graphs.length;
        visited = new boolean[n];
        color = new boolean[n];
        // color 存储每个点的颜色

        // 遍历所有顶点，对每个顶点进行DFS（因为可能不是连通图，存在多个连通分量）
        for(int i = 0; i < n; i++){
            if (!visited[i]){
                dfs(graphs, i);
            }
        }

        return ret;
    }

    private void dfs(int[][] graphs, int v){
        // 遍历v点的邻接边
        for (int w: graphs[v]) {
            // 如果访问过，且跟当前v颜色一致，则不是
            if (visited[w]) {
                if (color[w] == color[v]){
                    ret = false;
                }
            } else {
                // 如果没访问过，颜色改为v的相反色，标记访问过
                color[w] = !color[v];
                visited[w] = true;
                dfs(graphs, w);
            }
        }
    }

    public boolean isBipartite2(int[][] graphs) {
        int n = graphs.length;
        visited = new boolean[n];
        // color 存储每个点的颜色
        color = new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        // 遍历所有顶点，对每个顶点进行BFS（因为可能不是连通图，存在多个连通分量）
        for(int i = 0; i < n; i++){
            if (!visited[i]){
                // 没访问，加入队列进行BFS
                q.add(i);
                bfs(graphs, q);
            }
        }

        return ret;
    }

    private void bfs(int[][] graphs, Queue<Integer> q){
        while (!q.isEmpty()){
            int v = q.remove();
            // 取出最近的点，遍历它的邻接点
            for (int w: graphs[v]){
                // 访问过且跟现在的点颜色一样，那么不是二分图
                if (visited[w]){
                    if (color[w] == color[v]){
                        ret = false;
                    }
                } else {
                    // 没访问过，标相反色，加入到队列
                    color[w] = !color[v];
                    visited[w] = true;
                    q.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        IsGraphBipartite inst = new IsGraphBipartite();
        int[][] graphs = new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] graphs1 = new int[][]{{1,3},{0,2},{1,3},{0,2}};
//        for(int[] vertices: graphs){
//            for (int vertice: vertices) {
//                System.out.print("-" + vertice);
//            }
//            System.out.println("\n");
//        }
//        assert !inst.isBipartite(graphs);
//        assert inst.isBipartite(graphs1);

        assert !inst.isBipartite2(graphs);
        assert inst.isBipartite2(graphs1);
    }
}

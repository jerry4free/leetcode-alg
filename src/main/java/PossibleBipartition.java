import java.util.ArrayList;
import java.util.List;

public class PossibleBipartition {
    /**
     * 886. Possible Bipartition
     */
    private boolean isBipartition = true;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 初始化邻接表
        List<Integer>[] graphs = new List[n+1];
        for (int i = 0; i < n+1; i++){
            graphs[i] = new ArrayList<>();
        }
        for (int[] dislike: dislikes){
            graphs[dislike[0]].add(dislike[1]);
            graphs[dislike[1]].add(dislike[0]);
        }

        boolean[] color = new boolean[n+1];
        boolean[] visited = new boolean[n+1];
        // DFS遍历每个节点（因为可能存在多个连通分量）
        for (int i = 1; i < n+1; i++){

            // 如果没访问过，说明是一个连通分量的开始，根据这个节点DFS遍历整个连通分量
            if (!visited[i]){
                visited[i] = true;
                dfs(graphs, i, visited, color);
            }
        }
        return isBipartition;
    }

    private void dfs(List<Integer>[] graphs, int v, boolean[] visited, boolean[] color){
        for (int w: graphs[v]){
            if (!visited[w]){
                color[w] = !color[v];
                visited[w] = true;
                dfs(graphs, w, visited, color);
            } else {
                if (color[w] == color[v]){
                    isBipartition = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = new int[][]{{1,2},{1,3},{1, 4}};
        PossibleBipartition inst = new PossibleBipartition();
        assert inst.possibleBipartition(n, dislikes);
        assert !inst.possibleBipartition(3, new int[][]{{1,2},{1,3},{2,3}});
        assert !inst.possibleBipartition(5, new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}});
    }

}

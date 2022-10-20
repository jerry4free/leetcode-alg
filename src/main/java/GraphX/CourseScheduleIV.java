package GraphX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1462. Course Schedule IV
 * 任意2点的可达性
 *
 */
public class CourseScheduleIV {
    /**
     * 空间复杂度：O(V^2)
     * 时间复杂度：O(V(V+E))
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // 初始化邻接表O(V+E)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        for (int[] edge: prerequisites){
            graph.get(edge[0]).add(edge[1]);
        }

        // 用一个邻接矩阵（V^2）表示, 任意两个顶点的是否可达
        boolean[][] marked = new boolean[numCourses][numCourses];
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++){
            // 对每一个顶点i，进行DFS。mark[i][j]表示节点i是否可达节点j
            dfs(graph, visited, marked[i], i);
            Arrays.fill(visited, false);
        }

        // 计算结果，时间复杂度等于queries的线性级别
        List<Boolean> ret = new ArrayList<>();
        for (int[] query: queries){
            ret.add(marked[query[0]][query[1]]);
        }
        return ret;
    }

    public void dfs(List<List<Integer>> graph, boolean[] visited, boolean[] marked, int v){
        marked[v] = true;
        visited[v] = true;
        for (int u: graph.get(v)){
            if (!visited[u]){
                dfs(graph, visited, marked, u);
            }
        }
    }

    public static void main(String[] args) {

    }
}

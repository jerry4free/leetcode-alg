package GraphX;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 399. Evaluate Division
 */
public class EvaluateDivision {
    private HashMap<String, Integer> indices;
    private boolean[] visited;
    double[][] graph;

    /**
     *  无向加权图
     *  本题是求解多源路径问题，而不是多源连通性问题
     *  因为最终任意2点的值，是这2点的路径的权重相乘的结果。
     *  采用DFS，时间复杂度(worse case)：O(Q * N^2),
     *  空间复杂度: O(N^2 + NL), L为字符串的平均长度。NL为哈希表大小
     *
     * */

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ret = new double[queries.size()];
        indices = new HashMap<>();

        // 构建哈希索引
        int i = 0;
        for (List<String> edge: equations){
            String v = edge.get(0);
            String w = edge.get(1);
            if (!indices.containsKey(v)){
                indices.put(v, i++);
            }
            if (!indices.containsKey(w)){
                indices.put(w, i++);
            }
        }
        int n = i;

        // 构建图：邻接矩阵
        graph = new double[n][n];
        visited = new boolean[n];
        i = 0;
        for (List<String> edge: equations){
            int v = indices.get(edge.get(0));
            int w = indices.get(edge.get(1));

            graph[v][v] = 1.0;
            graph[w][w] = 1.0;
            graph[v][w] = values[i];
            graph[w][v] = 1 / values[i];
            i++;
        }

        // 对于每一次查询，进行DFS
        i = 0;
        double[] ratio = new double[n];
        for (List<String> q: queries){
            if (!indices.containsKey(q.get(0)) || !indices.containsKey(q.get(1))){
                ret[i] = -1.0;
            } else if (indices.get(q.get(0)).equals(indices.get(q.get(1)))){
                ret[i] = 1.0;
            } else {
                int v = indices.get(q.get(0));
                int w = indices.get(q.get(1));
                Arrays.fill(ratio, -1.0);
                Arrays.fill(visited, false);
                ratio[v] = 1.0;
                // 从v开始，进行dfs遍历，直到找到w
                dfs(ratio, v, w, n);
                ret[i] = ratio[w];
            }
            i++;
        }
        return ret;
    }

    private void dfs(double[] ratio, int v, int w, int n){
        // base case, found w
        if (v == w){
            return;
        }

        visited[v] = true;
        for (int i = 0; i < n; i++){
            // 遍历v的所有邻接边
            if(graph[v][i] != 0.0 && !visited[i]){
                // a/b=2, b/c=1, 那么 a/c = (a/b)*(b/c)
                ratio[i] = ratio[v] * graph[v][i];
                dfs(ratio, i, w, n);
            }
        }
    }

    public class Pair{
        int index;
        double weight;

        Pair(int index, double weight){
            this.index = index;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
//        testCase1();
        testCase2();
    }

    public static void testCase1(){
        EvaluateDivision inst = new EvaluateDivision();
        List<String> equation1 = Arrays.asList("a", "b");
        List<String> equation2 = Arrays.asList("b", "c");
        List<String> equation3 = Arrays.asList("bc", "cd");
        List<List<String>> equations = Arrays.asList(equation1, equation2, equation3);
        double[] values = new double[]{1.5, 2.5, 5.0};
        List<String> query1 = Arrays.asList("a", "c");
        List<String> query2 = Arrays.asList("c", "b");
        List<String> query3 = Arrays.asList("bc", "cd");
        List<String> query4 = Arrays.asList("cd", "bc");
        List<List<String>> queries = Arrays.asList(query1, query2, query3, query4);

        double[] ret = inst.calcEquation(equations, values, queries);
        for(double i: ret){
            System.out.println(i);
        }
    }

    public static void testCase2(){
        EvaluateDivision inst = new EvaluateDivision();
        List<String> equation1 = Arrays.asList("x1", "x2");
        List<String> equation2 = Arrays.asList("x2", "x3");
        List<String> equation3 = Arrays.asList("x3", "x4");
        List<String> equation4 = Arrays.asList("x4", "x5");
        List<List<String>> equations = Arrays.asList(equation1, equation2, equation3, equation4);
        double[] values = new double[]{3.0, 4.0, 5.0, 6.0};
        List<String> query1 = Arrays.asList("x1", "x5");
        List<String> query2 = Arrays.asList("x5", "x2");
        List<String> query3 = Arrays.asList("x2", "x4");
        List<String> query4 = Arrays.asList("x2", "x2");
        List<String> query5 = Arrays.asList("x2", "x9");
        List<String> query6 = Arrays.asList("x9", "x9");
        List<List<String>> queries = Arrays.asList(query1, query2, query3, query4, query5, query6);

        double[] ret = inst.calcEquation(equations, values, queries);
        for(double i: ret){
            System.out.println(i);
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllPathsFromSourcetoTarget {
    /**
     * 797. All Paths From Source to Target
     * */

    // 由于是DAG，有向无环图，而且是探索从0到n-1的所有路径。所以只需要从0开始DFS就行，没必要且不能从每个节点开始DFS
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> q = new ArrayList<>();

        dfs(graph, ret, q, 0);
        return ret;
    }

    private void dfs(int[][] graph, List<List<Integer>> ret, List<Integer> stack, int v){
        // 由于栈中保存的就是从0到n-1的路径，所以如果找到n-1，复制当前栈即可
        stack.add(v);
        if (v == graph.length - 1){
            // 找到了，复制当前栈到ret中
            List<Integer> path = new ArrayList<>(stack);
            ret.add(path);
        }
        for (int w: graph[v]){
            dfs(graph, ret, stack, w);
        }
        stack.remove(stack.size()-1);
    }

    public static void main(String[] args) {
        AllPathsFromSourcetoTarget inst = new AllPathsFromSourcetoTarget();
        List<List<Integer>> ret = inst.allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}});

        for (List<Integer> path: ret){
            for (int w: path){
                System.out.print("--" + w);
            }
            System.out.println("");
        }
    }
}

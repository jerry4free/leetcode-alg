import java.util.*;

/**
 * 802. Find Eventual Safe States
 */
public class FindEventualSafeStates {

    /**
     * 方法1：拓扑排序法
     * */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 由题目可知：没有出度的顶点是终点，那么自环的点不是终点，所以子环的点不是安全点。
        // 由于有向图是有方向的。对于v->w，假如w是终端节点，不可能从w找到v
        // 所以有向图如果反向之后，原图的终端节点就变成了起始节点，即v->w变为v<-w，那么就可以从w找到v了。
        // 1。反向图，就可以进行拓扑排序（原图的终端节点现在是起始节点，入度为0，刚好可以作为队列方法的第一批入队的节点）
        // 2。反向图中，如果存在环，说明原图也存在环，环上的节点是无论是否反向都不是安全节点，并不受影响
        // 所以本质上就是反向后能排出拓扑顺序的节点，就是安全节点。
        // get a reverse graph
        List<List<Integer>> rGraph = new ArrayList<>();
        for (int i = 0; i < n; i++){
            rGraph.add(new ArrayList<>());
        }
        int [] inDe = new int[n];
        for (int i = 0; i < n; i++){
            for (int w: graph[i]){
                inDe[i]++;  // 注意采用i，原图的出度，即反向图的入度
                rGraph.get(w).add(i);
            }
        }

        // 所有入度为0的节点入队，其实不需要排序，只需要区分出这些可以排序的节点即可
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++){
            if (inDe[i] == 0){
                q.add(i);
            }
        }
        while (!q.isEmpty()){
            int v = q.remove();
            for (int u :rGraph.get(v)){
                // v的所有邻边入度减1
                inDe[u]--;
                if (inDe[u] == 0){
                    q.add(u);
                }
            }
        }

        // 顺序遍历n个节点，入度为0的代表可以进行拓扑排序的，即不在环上的，那么就是结果
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (inDe[i] == 0){
                ans.add(i);
            }
        }

        return ans;
    }

    /**
     * 方法2：等价于：所有在环上的节点和导向环的点，都是不安全的。其他都是安全的
     */
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int n = graph.length;
        boolean[] onStack = new boolean[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++){
            if (!visited[i]){
                dfs(graph, onStack, visited, i);
            }
        }

        for (int i = 0; i < n; i++){
            if (!onStack[i]){
                ans.add(i);
            }
        }
        return ans;
    }

    public void dfs(int[][] graph, boolean[] onStack, boolean[] visited, int v){
        visited[v] = true;
        onStack[v] = true;
        for (int w: graph[v]){
            if (!visited[w]){
                dfs(graph, onStack, visited, w);
            }
            if (onStack[w]){
                // got a cycle
                // 注意：这个return很重要
                // 找到环后，退出，但是栈中的节点仍保持，因为这一做法可以将「找到了环」这一信息传递到栈中的所有节点上
                return;
            }
        }

        onStack[v] = false;
    }

    private static void show(List<Integer> ans){
        for (int i: ans){
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        FindEventualSafeStates inst = new FindEventualSafeStates();
        int[][] graph = new int[][]{{0},{2,3,4},{3,4},{0,4},{}};
        show(inst.eventualSafeNodes2(graph));
    }

}

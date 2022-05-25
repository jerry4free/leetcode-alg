import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 743. Network Delay Time
 */
public class NetworkDelayTime {

    /**
     * 给定有向加权图，求SPT（最短路径树）到所有点的最长路径。如果不连通，返回-1
     * 方法：因为没有负权重的边，采用优先队列的Dijkstra算法
     * 时间复杂度：O(E logE)，空间复杂度：O(E + V)
     */
    private List<Vertex>[] graph;
    PriorityQueue<Vertex> queue;
    private int[] disTo;

    public int networkDelayTime(int[][] times, int n, int k) {
        // representation
        graph = new List[n];
        disTo = new int[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
            disTo[i] = Integer.MAX_VALUE;
        }
        for (int[] edge: times){
            int v = edge[0] - 1;  // 注意下标-1
            int w = edge[1] - 1;
            graph[v].add(new Vertex(w, edge[2]));
        }

        // priority queue：存放的是边，可能多条边的尾部都是同一个点
        //（由于标准Java库没有IndexPriorityQueue队列，无法针对某个点更新距离，无法更新权重。如果有，时间复杂度就是O(E lgV)）
        queue = new PriorityQueue<>();
        // 将源点k-1加入队列，SPT从源点开始增长
        disTo[k-1] = 0;
        queue.add(new Vertex(k-1,0));

        // 每次取出一个非树上的距离s最近的点，放松该点
        while (!queue.isEmpty()){
            Vertex edge = queue.remove();
            relax(edge);
        }

        int minWeight = 0;
        for(int i = 0; i < n; i++){
            if (disTo[i] == Integer.MAX_VALUE){
                return -1;
            }
            minWeight = Math.max(minWeight, disTo[i]);
        }
        return minWeight;
    }

    /**
     * relax v点：对所有v的邻接点w（边）进行relax，
     * 遍历每个w点，如果s->v的距离，v->w的距离，这2者之和小于s->w的距离，那么更新s->w的距离, 并将w点加入优先队列
     * @param v
     */
    private void relax(Vertex v){
        // v->w
        for (Vertex w: graph[v.vertex]){
            if (disTo[w.vertex] > disTo[v.vertex] + w.weight) { // 发现一条到w的近路（即从v到w，比以前的某种到w的距离更近）
                disTo[w.vertex] = disTo[v.vertex] + w.weight;  // 更新到w距离（更新到w的目前已知最近距离）
                queue.add(w);  // 将这个顶点（其实是边，因为某个点可能加入了多次）加入队列，以供下次查找
            }
        }
    }

    public class Vertex implements Comparable<Vertex>{
        private int vertex = 0;
        private int weight = 0;
        Vertex(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex that) {
            return this.weight - that.weight;
        }
    }

    /**
     * 朴素的Dijkstra方法
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                graph[i][j] = -1;
            }
        }
        // 采用邻接矩阵构图
        for (int[] edge: times){
            int v = edge[0] - 1;
            int w = edge[1] - 1;
            graph[v][w] = edge[2];
        }

        boolean[] onTree = new boolean[n];
        // 初始化distTo
        int[] distTo = new int[n];
        for (int i = 0; i < n; i++){
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[k-1] = 0;  // 源点距离为0
        onTree[k-1] = true;

        // 遍历每个节点
        for (int i = 0; i < n; i++){
            // 放松节点i, i->j, 对于i的邻接点更新距离
            if (!onTree[i]){
                for (int j = 0; j < n; j++){
                    if (graph[i][j] != -1 && (distTo[i] + graph[i][j] < disTo[j])){
                        distTo[j] = distTo[i] + graph[i][j];
                    }
                }
                onTree[i] = true;
            }
        }
        int ret = 0;

        // TODO:

        return ret;
    }

    public static void main(String[] args) {

        NetworkDelayTime inst = new NetworkDelayTime();
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        int ret = inst.networkDelayTime(times, n, k);
        System.out.println(ret);
    }
}

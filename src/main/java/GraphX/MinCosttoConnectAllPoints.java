package GraphX;

import java.util.PriorityQueue;

/**
 * 1584. Min Cost to Connect All Points
 */
public class MinCosttoConnectAllPoints {
    private int[][] graph;
    private boolean[] visited;
    PriorityQueue<Edge> pq;

    // 边的类，实现Comparable接口，提供比较方法，以便在优先队列中进行比较排序
    public class Edge implements Comparable<Edge>{
        private int v;
        private int w;
        private int distance;

        public Edge(int v, int w){
            this.v = v;
            this.w = w;
            this.distance = getDistance(graph[v], graph[w]);
        }

        private int getDistance(int[] v, int[] w){
            return Math.abs(v[0]-w[0]) + Math.abs(v[1]-w[1]);
        }

        @Override
        public int compareTo(Edge that) {
            if (this.distance < that.distance){
                return -1;
            } else if (this.distance > that.distance){
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * MST
     * 采用Prim的最小生成树算法(lazy version，即优先队列中的边很多，没有及时删除）
     * 时间复杂度：O(E logE)= O(N^2 lgN), N是point的长度。
     * 空间复杂度：O(E) = O(N^2), 优先队列中是所有的顶点个数
     */
    public int minCostConnectPoints(int[][] points) {
        graph = points;
        int n = points.length;
        visited = new boolean[n];
        int minWeight = 0;

        // 访问第一个点
        pq = new PriorityQueue<>();
        visit(n, 0);

        while (!pq.isEmpty()){
            // 每次取出树上的点和树外的点的连接边中，最小权重的边
            Edge edge = pq.remove();
            // 如果这个边的2个点都已经加到树上，则忽略
            if (visited[edge.v] && visited[edge.w]){
                continue;
            }
            // 否则，这条最小权重边在MST上
            minWeight += edge.distance;
            if (!visited[edge.v]){
                visit(n, edge.v);
            }
            if (!visited[edge.w]){
                visit(n, edge.w);
            }
        }

        return minWeight;
    }

    private void visit(int n, int v){
        visited[v] = true;

        // 遍历所有邻接边，加入优先队列
        for (int i = 0; i < n; i++){
            if (!visited[i] && i != v){
                pq.add(new Edge(i, v));
            }
        }
    }

    public static void main(String[] args) {
        MinCosttoConnectAllPoints inst = new MinCosttoConnectAllPoints();
        int[][] points = new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}};
        int ret = inst.minCostConnectPoints(points);
        if (ret != 20){
            throw new AssertionError();
        }

    }
}

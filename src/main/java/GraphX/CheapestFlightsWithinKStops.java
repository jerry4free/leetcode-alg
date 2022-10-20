package GraphX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 787. Cheapest Flights Within K Stops
 */
public class CheapestFlightsWithinKStops {
    class Edge {
        int i;
        int j;
        int w;
        public Edge(int i, int j, int w){
            this.i = i;
            this.j = j;
            this.w = w;
        }
    }
    /**
     * 前提：
     * 1。有向加权图，没有自环和平行边。没有负的权重
     * 2。但是没说是不是有环，所以假设有环
     * 3。0 <= flights.length <= (n * (n - 1) / 2)，可知是稀疏图
     * 4。没说所有城市都连通，所以可能存在多个连通分量
     *
     * 求：src到dst的价格最便宜，有向加权图的最短路径问题
     * 但是有个限制条件最多经过k站中转，也就是路径的边数之和是k+1
     */
    List<Edge>[] graph;
    final int INF = 10000 * 101 + 1;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        graph = new List[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        // 邻接表表示有向图，元素是Edge
        for (int[] f: flights){
            graph[f[0]].add(new Edge(f[0], f[1], f[2]));
        }

        int [][] f = bf(k, n, src);

        // 所有到dst点的每一轮中的最小值，即为不超过k点的最短路径
        int ans = INF;
        for (int t = 1; t <= k+1; t++){
            ans = Math.min(ans, f[t][dst]);
        }
        return ans == INF ? -1 : ans;
    }


    /**
     * bellman-ford 算法, 基于动态规划
     *
     * 状态转移方程：f[k][j] = min( f[k][j], f[k-1][i] + weight(i,j) )
     * 经过k轮到j点的最短路径，是第k-1轮的每个到i的距离加上其所有i->j的边中的权重的最小值
     * 代表从src到i点，经过最多k条边的最短路径。
     */
    private int[][] bf(int k, int n, int src){
        int[][] f = new int[k+2][n];  // 注意此处是k+2，所以f[0]代表所有点的初始状态, 一共经过k+1轮遍历
        for (int t = 0; t <= k+1; t++){
            Arrays.fill(f[t], INF);
        }

        f[0][src] = 0;
        for(int t = 1; t <= k+1; t++){  // 经过k+1轮循环
            // i -> j, 放松i点，遍历i所有的邻接边j，
            for (int i = 0; i < n; i++){
                for(Edge e: graph[i]){
                    // 注意如果设置为Integer.MAX_VALUE，加上e.w可能越界，成为负值
                    f[t][e.j] = Math.min(f[t][e.j], f[t-1][e.i] + e.w);  //更新所有j点的距离
                }
            }
        }
        return f;
    }

    private void show(int[][] f){
        for(int[] n: f){
            for(int i: n){
                System.out.print(i + "\t");
            }
            System.out.println("\n");
        }
    }

    // bellman-ford算法，基于边的数组
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int t = 0; t < k + 1; t++){  // k+1条边，进行k+1次循环
            int[] clone = dist.clone();  // 基于上一次的状态值，放松所有点
            for (int[] flight: flights){
                int i = flight[0];
                int j = flight[1];
                int w = flight[2];
                dist[j] = Math.min(dist[j], clone[i] + w);
            }
        }
        return dist[dst] == INF ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops inst = new CheapestFlightsWithinKStops();

        int[][] flights = new int[][]{{0,1,100},{1,2,100},{0,2,500}};
        int ret = inst.findCheapestPrice(3,flights,0,2,1);
        System.out.println(ret);

        int ret2 = inst.findCheapestPrice(3,flights,0,2,0);
        System.out.println(ret2);

        int[][] flights3 = new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        int ret3 = inst.findCheapestPrice(4,flights3,0,3,1);
        System.out.println(ret3);
    }
}

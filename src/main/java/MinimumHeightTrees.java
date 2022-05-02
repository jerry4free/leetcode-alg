import java.util.*;

/**
 * 310. Minimum Height Trees
 */
public class MinimumHeightTrees {

    /**
     * 暴力搜索：对每个节点为根构成的树，求树的深度，O(n^2)
     * 有没有时间复杂度更优的算法呢？
     * 先求出一个树的直径，那么直径的中间的点，就是树的最小高度树的根节点
     * 所以有2步走：1，求出树的最长路径；2，如果直径长度（度）是偶数，有1个跟节点；如果是奇数，那么有2个根节点
     * */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ret = new ArrayList<>();
        HashMap<Integer, List<Integer>> heightToRoots = new HashMap<>();
        int[] parent = new int[n];

        // 邻接表表示，初始化邻接表
        List<Integer>[] graphs = new List[n];
        for (int i = 0; i < n; i++){
            parent[i] = -1;
            graphs[i] = new ArrayList<>();
        }
        for (int[] edge: edges){
            addEdge(graphs, edge[0], edge[1]);
        }

        // 任意找一节点比如0，找到距离其最远的点v，再找到距离v最远的点w，那么树的最大深度就是v到w的长度
        // 为查找路径，parent 表示的是v为root的父连接的树
        int v = getLongestNode(graphs, parent, 0);
        int w = getLongestNode(graphs, parent, v);
        System.out.println(v + ":" + w);

        // path，就是最长路径
        List<Integer> path = new ArrayList<>();
        parent[v] = -1;  // 将v的parent设置为-1（代表根，第一次getLongestNode可能改掉了）
        while (w != -1){
            path.add(w);
            w = parent[w];
        }

        // path的长度减去1，就是树的最大深度，即：直径
        int d = path.size();

        // 偶数个节点，即深度是奇数，存在2个节点
        if (d % 2 == 0){
            ret.add(path.get(d / 2 - 1));
        }
        ret.add(path.get(d / 2));

        return ret;
    }

    // BFS, 求出以s为起点，距离最远的节点
    private int getLongestNode(List<Integer>[] graphs, int[] parent, int s){
        int n = graphs.length;
        int w = s;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.add(s);
        visited[s] = true;
        while (!q.isEmpty()) {
            w = q.remove();
            for (int j: graphs[w]) {
                if (!visited[j]){
                    q.add(j);
                    parent[j] = w;
                    visited[j] = true;
                }
            }
        }

        return w;
    }

    private void addEdge(List<Integer>[] graphs,int v, int w){
        graphs[w].add(v);
        graphs[v].add(w);
    }

    /**
     * BFS遍历，height是数组，height[i]：是v为root到i节点的长度
     * */
    private Integer getHeight(List<Integer>[] graphs, int v){
        int n = graphs.length;
        int[] distance = new int[n];
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(v);
        distance[v] = 0;
        visited[v] = true;
        while (!q.isEmpty()) {
            int w = q.remove();
            for (int j: graphs[w]) {
                if (!visited[j]){
                    q.add(j);
                    distance[j] = distance[w] + 1;
                    visited[j] = true;
                }
            }
        }

        int h = 0;
        for(int d: distance){
            if (d > h){
                h = d;
            }
        }

        return h;
    }

    public static void main(String[] args) {
        MinimumHeightTrees inst = new MinimumHeightTrees();
        List<Integer> ret;
        int [][] edges = new int[][]{{1,0}, {1,2}, {1,3}};
        int n = 4;
        ret = inst.findMinHeightTrees(n, edges);
        for(int v: ret){
            System.out.println(v);
        }
        System.out.println("====");

        int [][] edges2 = new int[][]{{3,0}, {3,1}, {3,2}, {3,4}, {5,4}};
        int n2 = 6;
        ret = inst.findMinHeightTrees(n2, edges2);
        for(int v: ret){
            System.out.println(v);
        }
    }
}

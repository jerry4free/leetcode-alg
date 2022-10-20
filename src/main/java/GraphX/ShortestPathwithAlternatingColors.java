package GraphX;

import java.util.*;

/**
 * 1129. Shortest Path with Alternating Colors
 */
public class ShortestPathwithAlternatingColors {
    private List<Integer>[] rGraph;
    private List<Integer>[] bGraph;
    private int[] dist;
    private Boolean[] redVisited;
    private Boolean[] blueVisited;

    // 可能存在环, 所以需要visited来确保不死循环
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 邻接表表示, 将红边和蓝边分别存储到红图和蓝图中
        rGraph = new List[n];
        bGraph = new List[n];
        dist = new int[n];
        for (int i = 0; i < n; i++){
            rGraph[i] = new ArrayList<>();
            bGraph[i] = new ArrayList<>();
            dist[i] = -1;
        }

        for (int[] r: redEdges){
            rGraph[r[0]].add(r[1]);
        }
        for (int[] b: blueEdges){
            bGraph[b[0]].add(b[1]);
        }

        // BFS，队列里存储的是点。
        // 题目要求红边和蓝边交替出现，那么从0点进行BFS时，存在2种走法，第一步是蓝，或者第一步是红
        Queue<Integer[]> q = new LinkedList<>();
        dist[0] = 0;

        // 假设：题目说存在平行边，但是存在同一颜色的平行边没有意义，因为是重复的。
        // 但是存在不同颜色的平行边就有意义，因为可以切换颜色
        redVisited = new Boolean[n];
        blueVisited = new Boolean[n];
        Arrays.fill(redVisited, false);
        Arrays.fill(blueVisited, false);
        redVisited[0] = true;
        blueVisited[0] = true;


        //case 1:
        // red: 1->2->3->4
        // blue: 1->2->3

        // v, 上一步的color, dist
        q.add(new Integer[]{0, 1, 0}); // red
        q.add(new Integer[]{0, 0, 0}); // blue
        while (!q.isEmpty()){
            Integer[] vertice = q.remove();
            int v = vertice[0];
            int color = vertice[1];
            int prevDist = vertice[2];
            if (color == 1) { // 上一步是红
                // 那么下一步是蓝，对蓝图的v的邻接边遍历，
                for(int w: bGraph[v]){
                    if (!blueVisited[w]){ // 如果蓝图中w未访问, 则标记访问
                        blueVisited[w] = true;
                        if (dist[w] == -1){
                            dist[w] = prevDist+1;
                        }
                        q.add(new Integer[]{w, 0,prevDist+1});  // 节点、下个颜色、当前距离，入队
                    }
                }
            } else {
                // 那么下一步是红，对红图的v的邻接边遍历，
                for (int w: rGraph[v]){
                    if (!redVisited[w]){
                        redVisited[w] = true;
                        if (dist[w] == -1){
                            dist[w] = prevDist+1;
                        }
                        q.add(new Integer[]{w, 1, prevDist+1});  // 节点、下个颜色、当前距离，入队
                    }
                }
            }
        }

        return dist;
    }

    private int getMin(int i, int j){
        if (i == -1){
            return j;
        }
        if (i < j){
            return i;
        } else {
            return j;
        }
    }


    public static void main(String[] args) {
        ShortestPathwithAlternatingColors inst = new ShortestPathwithAlternatingColors();
        int[] ret = inst.shortestAlternatingPaths(3, new int[][]{{0,1},{1,2}}, new int[][]{});
        show(ret);
        show(inst.shortestAlternatingPaths(3, new int[][]{{0,1}}, new int[][]{{2, 1}}));
        show(inst.shortestAlternatingPaths(3, new int[][]{{1,0}}, new int[][]{{2, 1}}));
        show(inst.shortestAlternatingPaths(3, new int[][]{{0,1}}, new int[][]{{1, 2}}));
        show(inst.shortestAlternatingPaths(3, new int[][]{{0,1}, {0, 2}}, new int[][]{{1, 0}}));

        // case：存在环
        show(inst.shortestAlternatingPaths(5,
                new int[][]{{0,1}, {1, 2}, {2, 3}, {3, 4}},
                new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }

    private static void show(int[] ret){
        for(int i: ret){
            System.out.print(i + "\t");
        }
        System.out.println("");
    }
}

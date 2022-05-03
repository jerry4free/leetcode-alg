import java.util.*;

/**
 * 851. Loud and Rich
 *
 * 返回一个整数数组 answer 作为答案,对于 answer[x] = y, 意思是在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人
 */
public class LoudandRich {
    private List<Integer>[] graph;

    /**
     * 拓扑排序方法
     */
    // 人是节点，方向是有钱指向没钱的方向，组成DAG
    // 采用队列进行DFS
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        graph = new List[n];
        int[] inDe = new int[n]; // 每个顶点的入度

        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for (int[] edge: richer){
            graph[edge[0]].add(edge[1]);
            inDe[edge[1]]++;
        }

        // 初始化结果ans，对于每个person x，设置自己x为最安静的人
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }
        // 现将所有入度为0的点，入队
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if (inDe[i] == 0){
                q.add(i);
            }
        }
        // 进入和离开队列的顺序都是入度为0的顺序
        while (!q.isEmpty()){
            int v = q.remove();
            // v->w
            for (int w: graph[v]){
                // 如果ans[v]这个最安静的人，比ans[w]这个人还安静，则更新ans[w]
                // 更新v的下游的答案
                if (quiet[ans[v]] < quiet[ans[w]]){
                    ans[w] = ans[v];
                }
                // 所有临边入度减1，如果邻边的入度为0，则加入队列
                if (--inDe[w] == 0){
                    q.add(w);
                }
            }
        }

        return ans;
    }

    private static void show(int[] order){
        for(int i: order){
            System.out.print("--" + i);
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {
        LoudandRich inst = new LoudandRich();
        int[][] richer = new int[][]{{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = new int[]{3,2,5,4,6,1,7,0};
        int[] answer = inst.loudAndRich(richer, quiet);
        show(answer);

    }
}

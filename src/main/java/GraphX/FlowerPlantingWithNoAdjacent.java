package GraphX;

import java.util.*;

/**
 * 1042. Flower Planting With No Adjacent
 */
public class FlowerPlantingWithNoAdjacent {

    // 1到n个花园，看作点。每个点最多有3条边。无向图
    private List<Integer>[] graph;
    private int[] color;
    public int[] gardenNoAdj(int n, int[][] paths) {
        graph = new List[n];
        color = new int[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
            color[i] = 0;
        }

        // 邻接表表示无向图
        for (int[] p: paths){
            int v = p[0] - 1;
            int w = p[1] - 1;
            graph[v].add(w);
            graph[w].add(v);
        }

        // 由于图存在多个联通分量，所以需要遍历
        for (int i = 0; i < n; i++){
            if (color[i] == 0){
                dfs(i);
            }
        }

        return color;
    }

    private void dfs(int v){
        if (color[v] == 0){
            int[] colored = new int[5];
            // 遍历所有邻接点，找出没有设置的颜色
            for (int w: graph[v]){
                if (color[w] != 0){
                    colored[color[w]] = 1;
                }
            }

            // 设置v点的颜色
            for (int i = 1; i < 5; i++){
                if (colored[i] == 0){
                    color[v] = i;
                }
            }

            // 对v的邻接点中未标记的点标记颜色
            for (int w: graph[v]){
                dfs(w);
            }
        }
    }


    public static void main(String[] args) {
        FlowerPlantingWithNoAdjacent inst = new FlowerPlantingWithNoAdjacent();
        int[] ret = inst.gardenNoAdj(3, new int[][]{{1,2},{2,3},{3,1}});
        show(ret);
        int[] ret2 = inst.gardenNoAdj(4, new int[][]{{1,2},{3,4}});
        show(ret2);
        int[] ret3 = inst.gardenNoAdj(4, new int[][]{{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}});
        show(ret3);
        int[] ret4 = inst.gardenNoAdj(4, new int[][]{{1,2},{3,4},{3,2},{4,2},{1,4}});
        show(ret4);
    }

    private static void show(int[] ret){
        for(int v: ret){
            System.out.print(v + "\t");
        }
        System.out.println("");
    }
}

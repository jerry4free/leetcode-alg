package GraphX;

import java.util.*;

/**
 * 210. 课程表 II
 */
public class CourseScheduleII {
    private List<Integer>[] graph;
    boolean[] onStack;
    boolean[] visited;
    Stack<Integer> stack;
    boolean hasCycle;


    // 可以第一次DFS判断是否有环，第二次DFS存储逆后序。也可以一起做
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // init
        hasCycle = false;
        onStack = new boolean[numCourses];
        visited = new boolean[numCourses];
        graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge: prerequisites){
            graph[edge[1]].add(edge[0]);
        }

        // detect Cycle
        stack = new Stack<>();
        for (int i = 0; i < numCourses; i++){
            if (!visited[i]){
                dfs(i);
            }
        }

        if (hasCycle){
            return new int[0];
        }

        // if has no cycle, then topological sort
        int[] ret = new int[numCourses];
        int i = 0;
        // 克隆这个栈，就是拓扑排序
        while(!stack.isEmpty()){
            ret[i++] = stack.pop();
        }
        return ret;
    }


    // 如果当前节点已经访问过，且当前的栈中已经有该节点，说明有环
    private void dfs(int v){
        onStack[v] = true;
        visited[v] = true;
        for (int w: graph[v]){
            if (!visited[w]){
                dfs(w);
            } else {
                if (onStack[w]){
                    hasCycle = true;
                }
            }
        }
        // 逆后序就是拓扑排序, 逆向采用栈来存储
        stack.push(v);
        onStack[v] = false;
    }

    public static void main(String[] args) {
        CourseScheduleII inst = new CourseScheduleII();
        show(inst.findOrder(2, new int[][]{{1,0}}));
        show(inst.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));

    }

    private static void show(int[] ret){
        for(int i: ret){
            System.out.print("--" + i);
        }
        System.out.println("");
    }
}

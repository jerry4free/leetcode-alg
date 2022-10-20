package GraphX;

import java.util.ArrayList;
import java.util.List;

public class KeysandRooms {
    /**
     * 841. Keys and Rooms
     */

    private boolean[] visited;

    /**
     * 把房间看作顶点，能进入某个房间看作边，组成一个图
     * 问题能否进入所有房间，就是求解：是否图只有一个连通分量
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // 顶点树
        int n = rooms.size();
        // 每个房间是否访问过
        visited = new boolean[n];

        // 注意：有可能有的房间没有钥匙，即：rooms并不是完整意义的邻接表, 边可能只存储了一次。
        // 1. 通过房间0进行DFS，如果都能访问完所有房间。
        // 2. 或者DFS遍历所有顶点，如果有1个连通分量，则可以访问所有房间
        dfs(rooms, 0);

        for (int i = 0; i < n; i++){
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }

    public void dfs(List<List<Integer>> rooms, int v){
        visited[v] = true;
        List<Integer> keys = rooms.get(v);
        for (Integer w: keys){
            if (!visited[w]) {
                dfs(rooms, w);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> room1 = new ArrayList<>();
        room1.add(1);
        List<Integer> room2 = new ArrayList<>();
        room2.add(2);
        List<Integer> room3 = new ArrayList<>();
        room3.add(3);
        List<Integer> room4 = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        KeysandRooms inst = new KeysandRooms();
        boolean ret = inst.canVisitAllRooms(rooms);
        if (!ret) throw new AssertionError();
    }
}

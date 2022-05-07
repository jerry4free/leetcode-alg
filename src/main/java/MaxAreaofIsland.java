/**
 * 695. Max Area of Island
 */
public class MaxAreaofIsland {


    private int[] parent;
    private int[] treeSize;
    private int maxTreeSize;
    private int cnt;

    /**
     * 查并集
     * */
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int cnt = m * n;
        maxTreeSize = 0;

        parent = new int[cnt];
        treeSize = new int[cnt];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = i*m+j;  // 注意下标是i*m+j, 而不是i*n+j
                parent[x] = x;
                treeSize[x] = 1;
                // 注意如果n=m=1，grid[0][0]=1, 则只有一个方格，需要将maxTreeSize设置为1，因为不会引起union
                if (grid[i][j] > maxTreeSize ){
                    maxTreeSize = 1;
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                // union up
                if (i > 0){
                    if (grid[i][j] == 1 && grid[i-1][j] == 1){
                        union(i*m + j, (i-1)*m+j);
                    }
                }
                // union left
                if (j > 0){
                    if (grid[i][j] == 1 && grid[i][j-1] == 1){
                        union(i*m + j, i*m + j-1);
                    }
                }
            }
        }
        return maxTreeSize;
    }

    private int getCnt(){
        return cnt;
    }

    private void union(int v, int w){
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot != wRoot){
            if (treeSize[vRoot] < treeSize[wRoot]){
                parent[vRoot] = wRoot;
                treeSize[wRoot] += treeSize[vRoot];
                if (maxTreeSize < treeSize[wRoot]){
                    maxTreeSize = treeSize[wRoot];
                }
            } else {
                parent[wRoot] = vRoot;
                treeSize[vRoot] += treeSize[wRoot];
                if (maxTreeSize < treeSize[vRoot]){
                    maxTreeSize = treeSize[vRoot];
                }
            }
            cnt--;
        }
    }

    private int find(int v){
        while (v != parent[v]){
            v = parent[v];
        }
        return v;
    }

    /**
     *
     * DFS
     */
    public int maxAreaOfIsland2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int maxArea = 0;
        for (int i = 0;i < n; i++){
            for (int j = 0; j < m; j++){
                if (!visited[i][j] && grid[i][j] == 1){
                    int ret = dfs(grid, visited, i, j);
                    if (ret > maxArea){
                        maxArea = ret;
                    }
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, boolean[][] visited, int i, int j){
        int n = grid.length;
        int m = grid[0].length;
        visited[i][j] = true;
        int ret = 1;
        // up
        if (i > 0 && !visited[i-1][j] && grid[i-1][j] == 1){
            ret += dfs(grid, visited, i-1, j);
        }
        // down
        if (i < (n - 1) && !visited[i+1][j] && grid[i+1][j] == 1){
            ret += dfs(grid, visited, i+1, j);
        }
        // left
        if (j > 0 && !visited[i][j-1] && grid[i][j-1] == 1){
            ret += dfs(grid, visited, i, j-1);
        }
        // right
        if (j < (m - 1) && !visited[i][j+1] && grid[i][j+1] == 1){
            ret += dfs(grid, visited, i, j+1);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaxAreaofIsland inst = new MaxAreaofIsland();
        int [][] grid = 
                {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};

        int ret = inst.maxAreaOfIsland(grid);
        int ret2 = inst.maxAreaOfIsland2(grid);
        System.out.println(ret);
        System.out.println(ret2);
    }
}

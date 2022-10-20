package GraphX;

/**
 * 1254. Number of Closed Islands
 */
public class NumberofClosedIslands {

    private boolean[][] visited;
    public int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        visited = new boolean[n][m];

        int cnt = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                // 如果是0，且没访问过，从其开始DFS遍历
                if (grid[i][j] == 0 && (!visited[i][j])){
                    if (dfs(grid, n, m, i, j)){
                        System.out.println(i + ":" + j);
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    // 对grid[i][j]进行DFS遍历，如果是一个封闭岛屿，返回true
    private boolean dfs(int[][] grid, int n, int m, int i, int j){
        visited[i][j] = true;
        // 不论到没到边界，是1（海洋）则返回true
        if (grid[i][j] == 1){
            return true;
        }

        boolean ret = true;
        // 如果到边界，是0（陆地）,则将现在的点标为false，直到所有其他两连的陆地都遍历完，即这个连通分量遍历完。
        if (i == 0 || j == 0 || i == (n-1) || j == (m-1)){
            if (grid[i][j] == 0){
                ret = false;  // 注意不能提前返回，因为可能还要往右遍历
            }
            // return grid[i][j] != 0;  // 错误，假如到上边界，还可以想右遍历的
        }


        // 没到边界，是0
        // up
        if (i > 0 && (!visited[i-1][j])){
            ret &= dfs(grid, n, m, i-1, j);
        }
        // down
        if (i < (n-1) && (!visited[i+1][j])){
            ret &= dfs(grid, n, m, i+1, j);
        }
        // left
        if (j > 0 && (!visited[i][j-1])){
            ret &= dfs(grid, n, m, i, j-1);
        }
        // right
        if (j < (m-1) && (!visited[i][j+1])){
            ret &= dfs(grid, n, m, i, j+1);
        }

        return ret;
    }

    public static void main(String[] args) {
        NumberofClosedIslands inst = new NumberofClosedIslands();
        int[][] grid = new int[][]
                {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};

        int ret = inst.closedIsland(grid);
        if (ret != 2){
            throw new AssertionError("assert error: "+ ret);
        }


        int[][] grid2 = new int[][]{
          {1,1,0,1,1,1,1,1,1,1},
          {0,0,1,0,0,1,0,1,1,1},
          {1,0,1,0,0,0,1,0,1,0},
          {1,1,1,1,1,0,0,1,0,0},
          {1,0,1,0,1,1,1,1,1,0},
          {0,0,0,0,1,1,0,0,0,0},
          {1,0,1,0,0,0,0,1,1,0},
          {1,1,0,0,1,1,0,0,0,0},
          {0,0,0,1,1,0,1,1,1,0},
          {1,1,0,1,0,1,0,0,1,0}
          };
        int ret2 = inst.closedIsland(grid2);
        if (ret2 != 4){
            throw new AssertionError("assert error: "+ ret2);
        }


    }
}

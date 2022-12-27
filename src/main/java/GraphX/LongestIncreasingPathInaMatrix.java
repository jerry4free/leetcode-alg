package GraphX;

import Util.Utils;

/**
 * 329. 矩阵中的最长递增路径
 */
public class LongestIncreasingPathInaMatrix {

    final static int[][] offset = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] cache;

    /**
     * 假设矩阵行宽是M，N
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    public int longestIncreasingPath(int[][] matrix) {
        int ret = 1;
        int n = matrix.length;
        int m = matrix[0].length;
        cache = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                ret = Math.max(ret, dfs(matrix, i, j));
            }
        }

        return ret;
    }

    private int dfs(int[][] matrix, int i, int j){
        int n = matrix.length;
        int m = matrix[0].length;
        int ret = 1;

        for (int d = 0; d < 4; d++){
            int iNext = i+offset[d][0];
            int jNext = j+offset[d][1];
            if (iNext >= 0 && jNext >= 0 && iNext <= (n - 1) && jNext <= (m - 1) && matrix[iNext][jNext] > matrix[i][j]){
                // 通过记忆化之前已经DFS过的顶点，直接读取cache，避免重复搜索
                // 已经探索过，get cache
                if (cache[iNext][jNext] != 0){
                    ret = Math.max(ret, cache[iNext][jNext] + 1);
                } else {
                    ret = Math.max(ret, dfs(matrix, iNext, jNext) + 1);  // 递归式的深度优先搜索，返回时进行加1
                }
            }
        }
        // 4个反向探索完之后，update cache
        cache[i][j] = ret;

        return ret;
    }


    public static void main(String[] args) {
        LongestIncreasingPathInaMatrix inst = new LongestIncreasingPathInaMatrix();
        //[[3,4,5],[3,2,6],[2,2,1]]
//        System.out.println("expect 4, real: " + inst.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
        System.out.println("expect 4, real: " + inst.longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}}));
    }
}

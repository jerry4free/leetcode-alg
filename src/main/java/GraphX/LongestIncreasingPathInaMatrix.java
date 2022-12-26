package GraphX;

import Util.Utils;

/**
 * 329. 矩阵中的最长递增路径
 */
public class LongestIncreasingPathInaMatrix {

    int ret;
    final static int[][] offset = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] cache;
    public int longestIncreasingPath(int[][] matrix) {
        ret = 1;
        int n = matrix.length;
        int m = matrix[0].length;
        cache = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                ret = Math.max(ret, dfs(matrix, i, j, 1));
            }
        }

        Utils.show2D(cache);

        return ret;
    }

    private int dfs(int[][] matrix, int i, int j, int pathCnt){
        int n = matrix.length;
        int m = matrix[0].length;
//        if (i < 0 || j < 0 || i >= n || j >= m){
//            return;
//        }
        int ret = pathCnt;

        for (int d = 0; d < 4; d++){
            int iNext = i+offset[d][0];
            int jNext = j+offset[d][1];
            if (iNext >= 0 && jNext >= 0 && iNext <= (n - 1) && jNext <= (m - 1) && matrix[iNext][jNext] > matrix[i][j]){
                // get cache
                if (cache[iNext][jNext] != 0){
                    ret = Math.max(ret, cache[iNext][jNext] + 1);
                } else {
                    ret = Math.max(ret, dfs(matrix, iNext, jNext, pathCnt+1));
                }
            }
        }
        // update cache
        cache[i][j] = ret;

        return ret;
    }


    public static void main(String[] args) {
        LongestIncreasingPathInaMatrix inst = new LongestIncreasingPathInaMatrix();
        System.out.println("expect 4, real: " + inst.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
    }
}

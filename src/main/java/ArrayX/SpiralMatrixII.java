package ArrayX;

/**
 * 59 Spiral Matrix II
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {

        int[][] ret = new int[n][n];

        int[][] offset = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int i = 0;
        int j = 0;
        int d = 0;
        int len = n * n;
        for (int k = 1; k <= len; k++){
            ret[i][j] = k;

            // 如果下一个越界，或遇到0，则换方向
            int iNext = i + offset[d][0];
            int jNext = j + offset[d][1];
            if (iNext > (n - 1) || iNext < 0 || jNext > (n - 1) || jNext < 0 || ret[iNext][jNext] != 0) {
                d = (d + 1) % 4;
            }
            // 否则按照之前方向继续偏移
            i += offset[d][0];
            j += offset[d][1];
        }
        return ret;

    }
}

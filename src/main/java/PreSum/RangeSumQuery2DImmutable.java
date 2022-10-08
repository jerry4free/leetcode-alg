package PreSum;

import Util.Utils;

/**
 * 304. Range Sum Query 2D - Immutable
 */
public class RangeSumQuery2DImmutable {
    /**
     * 二维前缀和
     */
    static class NumMatrix {
        int[][] preSum;

        public NumMatrix(int[][] matrix) {

            int m = matrix.length;
            int n = matrix[0].length; // 列数
            // 技巧：行列加1，为方便统一边界case, 不需要对第1行或第1列进行特殊处理
            // 那么preSum[i][j]表示到matrix[i-1][j-1]的前缀和
            preSum = new int[m+1][n+1];

            for (int i = 1; i <= m; i++){
                for (int j = 1; j <= n; j++){
                    // 注意加上当前的matrix[i][j]
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i-1][j-1] - preSum[i-1][j-1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2+1][col2+1] - preSum[row1][col2+1] - preSum[row2+1][col1] + preSum[row1][col1];
        }
    }

    /**
     * not AC
     */
    static class NumMatrix2 {
        int[][] preSum;

        public NumMatrix2(int[][] matrix) {

            int m = matrix.length;
            int n = matrix[0].length; // 列数
            preSum = new int[m][n];
            preSum[0][0] = matrix[0][0];

            // 第一列
            for (int i = 1; i < m; i++){
                preSum[i][0] = preSum[i-1][0] + matrix[i][0];
            }

            // 第一行
            for (int j = 1; j < n; j++){
                preSum[0][j] = preSum[0][j-1] + matrix[0][j];
            }

            for (int i = 1; i < m; i++){
                for (int j = 1; j < n; j++){
                    // 注意加上当前的matrix[i][j]
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i][j] - preSum[i-1][j-1];
                }
            }
            Utils.show2D(preSum);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 > 0 && col1 > 0){
                return preSum[row2][col2] - preSum[row1-1][col2] - preSum[row2][col1-1] + preSum[row1-1][col1-1];
            } else if (row1 == 0 && col1 == 0){
//                System.out.println("row2,col2:" + row2 + "," + col2);
                return preSum[row2][col2];
            } else if (row1 == 0){
                return preSum[row2][col2] - preSum[row2][0];
            } else {
                return preSum[row2][col2] - preSum[0][col2];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix inst = new NumMatrix(matrix);
        System.out.println(inst.sumRegion(2,1,4,3));
        System.out.println(inst.sumRegion(1,1,2,2));
        System.out.println(inst.sumRegion(1,2,2,4));

        int[][] matrix2 = new int[][]{{-4,-5}};
        NumMatrix inst2 = new NumMatrix(matrix2);
        System.out.println(inst2.sumRegion(0,0,0,0));
        System.out.println(inst2.sumRegion(0,0,0,1));
        System.out.println(inst2.sumRegion(0,1,0,1));
    }
}

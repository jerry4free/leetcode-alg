package DynamicPrograming;

/**
 * 221. Maximal Square
 */
public class MaximalSquare {

    /**
     * 二维矩阵求最优值。可以考虑用动态规划的方式
     * 二维矩阵的状态有2个参数：(i,j)。怎么状态转移到(i,j-1),(i-1,j),(i-1,j-1)呢？
     *
     * 状态确定：假设f(i,j)表示以matrix[i][j]为右下角的正方形的边长。我自己初次想的是面积，而不是边长，所以没推导出关系。想问题可以再次简化
     *
     * 状态转移方程确定：
     * 那么如果当前matrix[i][j]是1的话，f(i,j)的边长取决于距离它上方、左方、左上角3个点的边长，3个点边长的最小值+1就是能组成的最大正方形的边长
     * 那么f(i,j) = min(f(i,j-1), f(i-1,j), f(i-1,j-1)) + 1, if (matrix[i][j] = 1)
     * // TODO: 可优化空间复杂度为O(1), 即2*2的int数组
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m+1][n+1];

        int ret = 0;

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (matrix[i][j] == '1'){
                    f[i-1][j-1] = Math.min(Math.min(f[i-1][j], f[i][j-1]), f[i-1][j-1]) + 1;
                    ret = Math.max(ret, f[i-1][j-1]);
                }
            }
        }

        return ret * ret;
    }

    public static void main(String[] args) {

    }
}

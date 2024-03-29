package DynamicPrograming;

/**
 * Offer47
 */
public class MaxValue{

    public int maxValue2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i > 0 && j > 0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                } else if (i == 0 && j > 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (j == 0 && i > 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args){

    }
}

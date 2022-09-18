package DynamicPrograming;

import java.util.Arrays;

/**
 * 62. Unique Paths
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int [] dp = new int[n];

        // base case
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[j] += dp[j-1];
            }
        }

        return dp[n-1];
    }

    // f(i,j) = f(i-1,j) + f(i,j-1)
    public int uniquePaths2(int m, int n) {
        int [][] dp = new int[2][n];

        // base case
        // 第一行都是1
        Arrays.fill(dp[0], 1);
        // 第一列也都是1
        dp[1][0] = 1;


        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i%2][j] = dp[(i-1)%2][j] + dp[i%2][j-1];
            }
        }

        return dp[(m-1)%2][n-1];
    }

    public static void main(String[] args) {
        UniquePaths inst = new UniquePaths();
        inst.uniquePaths(3, 7);
    }
}

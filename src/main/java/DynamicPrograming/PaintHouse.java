package DynamicPrograming;


/**
 * 256, 粉刷房子
 */

public class PaintHouse{

    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[2][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < n; i++){
            dp[i%2][0] = costs[i][0] + Math.min(dp[(i-1)%2][1], dp[(i-1)%2][2]);
            dp[i%2][1] = costs[i][1] + Math.min(dp[(i-1)%2][0], dp[(i-1)%2][2]);
            dp[i%2][2] = costs[i][2] + Math.min(dp[(i-1)%2][0], dp[(i-1)%2][1]);
        }

        return Math.min(Math.min(dp[(n-1)%2][0], dp[(n-1)%2][1]), dp[(n-1)%2][2]);
    }

    public int minCost2(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < n; i++){
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        return Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
    }
}

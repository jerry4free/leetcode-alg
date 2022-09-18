package DynamicPrograming;


import java.util.List;

/**
 * 120. Triangle
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[2][n];

        // 左边那个线，dp[i][j] = dp[i-1][j] + triangle[i][j]
        // 右边那个线，
        // 中间节点：dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j]
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                if (i == 0 && j == 0){ // 第一行第一列
                    dp[i%2][j] = triangle.get(i).get(j);
                } else if (i > 0 && j == 0) { // 左边那个线
                    dp[i % 2][j] = dp[(i - 1) % 2][j] + triangle.get(i).get(j);
                } else if (i > 0 && j == i) { // 右边那个线
                    dp[i % 2][j] = dp[(i - 1) % 2][j-1] + triangle.get(i).get(j);
                } else {
                    dp[i%2][j] = Math.min(dp[(i-1)%2][j], dp[(i-1)%2][j-1]) + triangle.get(i).get(j);
                }
            }
        }

        // 最后一行的最小值就是最小路径和
        int ans = dp[(n-1)%2][0];
        for(int i = 1; i < n; i++){
            ans = Math.min(ans, dp[(n-1)%2][i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Triangle inst = new Triangle();
        inst.minimumTotal();
    }
}

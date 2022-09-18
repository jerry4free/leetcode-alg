package DynamicPrograming;


/**
 * 1143. 最长公共子序列
 */
public class LongestCommonSubsequence {
    /**
     * 时间复杂度和空间复杂度都是O(mn)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int dp[][] = new int[n+1][m+1];

        // dp[i+1][j+1] 代表这text1[0:i]和text2[0:j]的最长公共子序列长度
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[n][m];
    }


    /**
     * 用2行状态
     * 空间复杂度是O(min(m,n))
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        if (n < m){
            return longestCommonSubsequence2(text2, text1);
        }
        int[][] dp = new int[2][m+1];

        // dp[i+1][j+1] 代表这text1[0:i]和text2[0:j]的最长公共子序列长度
        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[(i+1)%2][j+1] = dp[i%2][j] + 1;
                } else {
                    dp[(i+1)%2][j+1] = Math.max(dp[(i+1)%2][j], dp[i%2][j+1]);
                }
            }
        }

        return dp[n%2][m];
    }

    public static void main(String[] args) {

    }
}

package DynamicPrograming;

import java.util.Arrays;

/**
 * 322. Coin Change
 * 剑指 Offer II 103. 最少的硬币数目
 */
public class CoinChange {

    /**
     * 完全背包问题
     *
     * 每种物品的数量都是无限的，本题是个无限背包问题
     * 假设f(i,j)表示前i个物品中选择若干硬币能组成金额j的最少硬币数目，那么f(n,amount)就起题目的解
     * 对于第i-1个硬币，数量无限，假设最多取k个
     * f(i,j) = min(f(i-1, j - k * coins[i-1]) + k), 其中0 <= k, k * coins[i-1] <= j
     *
     * f(0,0) = 0
     * f(0,j) = -1, 从前0个物品取若干硬币是j的最少数量，其中j >= 1
     * f(i,0) = 0, i >= 0
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if (amount == 0){
            return 0;
        }

        int[][] dp = new int[n+1][amount+1];
        // f(0,j) = -1, 这种情况无法做到
        Arrays.fill(dp[0], amount+1);
        // f(0,0) = 0
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= amount; j++){
                dp[i][j] = dp[i-1][j];
                for (int k = 1; k * coins[i-1] <= j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j - k*coins[i-1]] + k);
                }
            }
        }
        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }

    /**
     * 动态规划，优化空间复杂度为O(amount)
     */
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        if (amount == 0){
            return 0;
        }

        int[] dp = new int[amount+1];
        // f(0,j) = -1, 这种情况无法做到, 填为特殊值
        Arrays.fill(dp, amount+1);
        // f(0,0) = 0
        dp[0] = 0;

        for (int c: coins){
            for (int j = amount; j >= 0; j--){
                for (int k = 1; k * c <= j; k++){  // 注意k从1开始，因为默认就是k=0，即不包含当前
                    dp[j] = Math.min(dp[j], dp[j - k*c] + k);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 假设f(i)表示组成金额i的最少的硬币数量，那么f(amount)就是解
     * 如果f(0),...f(i-1)已经知道，
     * 如果对于每一个硬币coins[j]，选择时，那么f(i) = f(i-coin) + 1, 其中 i > coin
     * 那么状态转移方程就是：
     * f(i) = min( f(i-coin) + 1 for c in coins )
     * base case: f(0) = 0;
     *
     * 跟之前方案相比，二维状态表行列互换了。这是遍历amount轮，每轮计算枚举所有面额的硬币来转移状态。
     * 时间复杂度还是O(n*amount)
     * 空间复杂度是O(amount)
     */
    public int coinChange3(int[] coins, int amount) {
        int n = coins.length;
        if (amount == 0){
            return 0;
        }

        int[] dp = new int[amount+1];
        // f(0) = 0
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++){
            for (int coin: coins){
                if (i >= coin){
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }

        return dp[amount] == max ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange inst = new CoinChange();
        System.out.println(inst.coinChange(new int[]{2}, 3));  // -1
        System.out.println(inst.coinChange(new int[]{1,2,5}, 11)); //
        System.out.println(inst.coinChange(new int[]{2}, 0));

    }
}

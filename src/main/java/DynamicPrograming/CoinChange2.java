package DynamicPrograming;

/**
 * 518. Coin Change 2
 */
public class CoinChange2 {

    /**
     * 看题目：题目是一个完全背包问题。
     * 状态确定：假设f(i,j)表示前i个物品凑成金额j的组合总数
     * 那么f(0,0) = 1，
     * f(0,j) = 0 if j > 0, 组成金额不为0，前0个物品是无法做到的
     * f(i,0) = 1, 前i个物品凑成金额0的组合都是1
     * 状态转移方程推导：对于第i个物品，有2个选择
     * 1. 选择coins[i-1]，f(i,j) = f(i-1, j - k * coins[i-1]) for k in range(1, j / coins[i-1])
     * 2. 不选择coins[i-1]: f(i,j) = f(i-1,j)
     */
    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] f = new int[n+1][amount+1];

        /**
         * 空间复杂度:O(n * amount)
         * 时间复杂度:O(n * amount ^ 2)
         */
        f[0][0] = 1;
        for (int i = 1; i <= n; i++){
            f[i][0] = 1;
            for (int j = 1; j <= amount; j++){
                // 选择第i-1个硬币，由于是完全背包问题，可以选择无限次，所以遍历所有的次数
                // 如果是0次，那就是不选择第i-1个硬币。那就是前i-1个硬币组成j的数量
                // 如果选择k次，那就是前i-1个硬币组成j-k*coins[i-1], 加上当前硬币就可以
                for (int k = 0; k <= j / coins[i-1]; k++){
                    f[i][j] += f[i-1][j - k * coins[i-1]];
                }
            }
        }
        return f[n][amount];
    }

    /**
     * 假设f(i)表示组成金额i的硬币组合总数
     * 空间复杂度优化到一维
     */
    public int change(int amount, int[] coins){
        int[] f = new int[amount+1];

        f[0] = 1;
        for (int c: coins){
            for (int i = amount; i >= 1; i--){
                for (int k = 1; k <= i/c; k++){  // 加上选择当前硬币的组合数，当前硬币可以选择k次，每次都要加上
                    f[i] += f[i - k * c];
                }
            }
        }
        return f[amount];
    }


    public static void main(String[] args) {
        CoinChange2 inst = new CoinChange2();
        System.out.println(inst.change(5, new int[]{1,2,5}));
        System.out.println(inst.change(3, new int[]{2}));
        System.out.println(inst.change(10, new int[]{10}));
    }

}

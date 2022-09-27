package DynamicPrograming;

import Util.Utils;

import java.util.Arrays;

/**
 * 279. Perfect Squares
 */
public class PerfectSquares {

    /**
     * 对于任意n，它的完全平方数的最少数量不会大于n的平方根
     * 可以规约为在1～n的平方根的数组nums里，任选若干个数字，求其平方的和能组成n的最少数量
     * 这是一个完全背包问题，
     * 假设f(i,j)表示前i个数，能组成j的最少数量
     * 那么f(i,j) = min(f(i-1, j - k * nums[i-1] * nums[i-1]) + k), 0 <= k <= j / (nums[i-1] * nums[i-1])
     * base case
     * f(0,j) = max_value, j > 0, 前0个数字组成大于1的数字的最少数量, 不可能办到的状态，非法状态
     * f(0,0) = 0,
     * f(i,0) = 0, 前i个物品组成0的最少数量就是0
     */
    public int numSquares2(int n) {
        Integer m = (int)Math.sqrt(n);

        int[][] f = new int[m+1][n+1];
        // 非法状态：不可能办到
        Arrays.fill(f[0], n+1);
        f[0][0] = 0;

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                f[i][j] = f[i-1][j];
                for (int k = 1; k * i * i <= j; k++){
                    f[i][j] = Math.min(f[i][j], f[i-1][j - k * i * i] + k);
                }
            }
        }

        Utils.show2D(f);

        return f[m][n];
    }

    // 动态转移方程是：f(i,j) = min(f(i-1,j), f(i,j-nums[i]) + 1), 0 <= nums[i] < j
    // 分别对应不选，或者选一个, 完全背包问题转化为了01背包问题
    public int numSquares(int n) {
        int m = (int)Math.sqrt(n);
        int[][] f = new int[m+1][n+1];
        Arrays.fill(f[0], n+1);
        f[0][0] = 0;

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (j > i*i){
                    f[i][j] = Math.min(f[i-1][j], f[i][j-i*i] + 1);
                } else {
                    f[i][j] = f[i-1][j];
                }
            }
        }

        return f[m][n];
    }


    public static void main(String[] args) {
        PerfectSquares inst = new PerfectSquares();
        System.out.println(inst.numSquares(1));
        System.out.println(inst.numSquares(13));
        System.out.println(inst.numSquares(12));
    }
}

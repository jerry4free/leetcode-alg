package DynamicPrograming;

import Util.Utils;

import java.util.Arrays;

/**
 * 494. Target Sum
 * 剑指 Offer II 102. 加减的目标值
 */
public class TargetSum {
    /**
     * 由题意可知道，加减将数组分为2部分，加的部分：p1，减的部分：p2
     * p1 - p2 = target
     * p1 + p2 = sum
     * 可以算出来,p1 = (target + sum) / 2，由于都是非负整数，所以p1 >= 0，所以target+sum 也要>= 0
     * 那么就是从数组中求目标和等于p1的个数，
     * 这跟416一样，也是一个经典的0-1背包问题，用动态规划来解决，只不过状态存储的解决方案的个数，即组合的数目，而不是是否能解决
     *
     * 状态转移方程分析：
     * 假设：f(i,j)表示从前i个元素选取若干个元素，总和为j，这样子集的个数。那么f(n,p1)就是题目的解
     * 那么如何分析状态转移呢？经典的0-1背包问题，对于每个元素都有选或不选2个选择，那么可以从这里入手
     * 对于nums[i]
     * 1。选择时，要求前i-1个里和等于j-nums[i-1], 即：f(i,j) = f(i-1,j-nums[i-1])，前提是j >= nums[i-1]
     * 2。不选时，即：f(i,j) = f(i-1,j)
     *
     * 所以对于f(i,j)就是这2种方案的和
     * 分析base case：
     * f(0,0) = 1。 从前0个元素选择若干元素总和为0
     * f(0,j) = 0, j >= 1。从前0个元素选择若干元素，总和不小于1
     * f(i,0) = 1? 从前i个元素选择若干元素，总和为0。这个是等于1吗？由题目知道元素是可能等于0的，所以这个不成立
     *
     */
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        for (int i: nums){
            s += i;
        }
        // 如果超过所有元素之和，或者target+s是奇数，无法满足
        int p1 = (target + s) / 2;
        if (p1 < 0 || target > s || (target + s) % 2 == 1){
            return 0;
        }
        int[][] dp = new int[n+1][p1+1];

        dp[0][0] = 1;
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= p1; j++){ // j需要从0开始，因为这不是base case。可能有多个0的情况
                dp[i][j] = dp[i-1][j];
                if (j >= nums[i-1]){
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[n][p1];
    }

    public int findTargetSumWays2(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        for (int i: nums){
            s += i;
        }
        // 如果超过所有元素之和，或者target+s是奇数，无法满足
        int p1 = (target + s) / 2;
        if (p1 < 0 || target > s || (target + s) % 2 == 1){
            return 0;
        }
        int[] dp = new int[p1+1];

        dp[0] = 1;
        for (int i = 1; i <= n; i++){
            for (int j = p1; j >= 0; j--){
                dp[j] = dp[j];
                if (j >= nums[i-1]){
                    dp[j] += dp[j-nums[i-1]];
                }
            }
        }

        return dp[p1];
    }

    public static void main(String[] args) {
        TargetSum inst = new TargetSum();
        System.out.println(inst.findTargetSumWays(new int[]{1,1,1,1,1}, 3));
        System.out.println(inst.findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));
        System.out.println(inst.findTargetSumWays(new int[]{100}, -200));
    }
}

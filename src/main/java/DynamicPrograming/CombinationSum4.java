package DynamicPrograming;

import Util.Utils;

/**
 * 377：组合总和V
 * Offer104
 */
public class CombinationSum4 {

    /**
     * 完全背包问题
     *
     * 1.确定状态：f(i)表示和为i的排列的数量
     * 2.确定状态转移方程：
     * 对于num[j], f(i) = f(i-nums[j]) 前提是i >= nums[j]。
     * 那么枚举nums，对于和等于i-num的每种排列，都相当于追加在排列后面追加一个num, 所以f(i)是计算所有i-num的排列数的和
     * 那么f(i) = sum( f(i-num) for num in nums )
     *
     * 3.确定base case:
     * f(0) = 1，组成0只有1种选择，就是空
     *
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++){
            for (int n: nums){
                if (n <= i){
                    dp[i] += dp[i-n];
                }
            }
        }

        Utils.show(dp);

        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSum4 inst = new CombinationSum4();
        System.out.println(inst.combinationSum4(new int[]{1,2,3}, 4));
        System.out.println(inst.combinationSum4(new int[]{9}, 3));

    }
}

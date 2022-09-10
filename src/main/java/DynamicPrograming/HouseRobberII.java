package DynamicPrograming;

/**
 * 213. House Robber II
 * 剑指 Offer II 090. 环形房屋偷盗
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2){
            return nums[0];
        }

        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 去掉尾部
        for (int i = 2; i < len-1; i++){
            dp[i%2] = Math.max(dp[(i-2)%2] + nums[i], dp[(i-1)%2]);
        }
        int ret = dp[(len-2)%2];

        // 去掉头部
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < len; i++){
            dp[i%2] = Math.max(dp[(i-2)%2] + nums[i], dp[(i-1)%2]);
        }
        return Math.max(ret, dp[(len-1)%2]);
    }
}

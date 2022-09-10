package DynamicPrograming;

/**
 * 198. House Robber(打家劫舍)
 * 剑指 Offer II 089. 房屋偷盗
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[2];
        dp[0] = nums[0];
        if (len >= 2){
            dp[1] = Math.max(nums[0], nums[1]);
        }

        // dp是单调递增的，只需判断前2个
        for (int i = 2; i < len; i++){
            dp[i%2] = Math.max(dp[(i-2)%2] + nums[i], dp[(i-1)%2]);
        }
        return dp[(len-1)%2];
    }
}

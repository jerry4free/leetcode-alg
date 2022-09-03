package DynamicPrograming;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 类似题目：53
 */
public class MaxSubArray {
    /**
     * 求最大值问题，可以尝试使用动态规划来解决
     * 假设dp[i]代表是以nums[i]为结尾的连续子数组的最大和，因为要算连续子数组，所以必须要以nums[i]结尾
     * 所有0到i的状态已经知道，那么如何求根据子问题dp[i-1]来算dp[i]呢？怎么推导状态转移方程呢？
     * 对于i来说，只有2中情况，
     * 1 是nums[i]大，那么就是nums[i]重新开始
     * 2 是dp[i-1]+ nums[i]大，那么就是在之前的连续子数组上再加上nums[i]
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int preMax = nums[0];
        int ret = preMax;
        for (int i = 1; i < len; i++){
            preMax = Math.max(nums[i], preMax + nums[i]);
            ret = Math.max(ret, preMax);
        }
        return ret;
    }

    static void show(int[] n){
        for (int i : n){
            System.out.print(i + ", ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        MaxSubArray inst = new MaxSubArray();
        inst.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }
}

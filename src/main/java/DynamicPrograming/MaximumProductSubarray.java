package DynamicPrograming;

/**
 * 152. Maximum Product Subarray
 */
public class MaximumProductSubarray {

    // 状态确定：假设f(i)表示以i-1结尾的子数组的乘积的最大值
    // 状态转移方程：那么f(i) = max(nums[i-1] * f(i-1), nums[i-1])
    // 那么最大乘积就是，所有状态的最大值。
    // 但是这是不对的？[-2, 3, -4]这种情况不满足，因为负负得正，会更大
    // 所以这个状态转移方程并不满足最优子结构，即子问题的最优质不能由更小规模的最优质推导出来
    public int maxProduct1(int[] nums) {
        int[] dp = new int[2];
        dp[0] = 1;
        int len = nums.length;
        int ret = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++){
            dp[i%2] = Math.max(nums[i-1] * dp[(i-1)%2], nums[i-1]);
            ret = Math.max(dp[i%2], ret);
        }

        return ret;
    }

    /**
     * 所以负数该怎么考虑呢？
     * 负数怎么传递呢？负数怎么转移呢？其实叫状态转移方程，是说从状态从大规模转移到小规模。这是top-down的思考方式
     * 反过来bottom-up，即负数怎么传输给后面呢？ state transition equation，英文中的转移就是transition，也有传输的意思。
     * 负的越多，乘以负数会越大，即求负数最小值。
     * 假设f(i)表示以i-1结尾的子数组的乘积的正数最大值，f'(i)表示以i-1结尾的子数组的乘积的负数的最小值
     * f[i] = max(nums[i-1] * f(i-1), nums[i-1], nums[i-1]* f'(i-1))
     * f'[i] = min(nums[i-1], nums[i-1]* f'(i-1), nums[i-1] * f(i-1)) 同理负数的最小值也要考虑之前乘积是正数，nums[i-1]为负数的情况
     *
     */
    public int maxProduct(int[] nums) {
        int[] dp = new int[2];
        int[] dp1 = new int[2];
        dp[0] = 1;
        dp1[0] = 1;
        int len = nums.length;
        int ret = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++){
            dp[i%2] = Math.max(Math.max(nums[i-1] * dp[(i-1)%2], nums[i-1]), nums[i-1] * dp1[(i-1)%2]);
            dp1[i%2] = Math.min(Math.min(nums[i-1] * dp1[(i-1)%2], nums[i-1]), nums[i-1] * dp[(i-1)%2]);
            ret = Math.max(dp[i%2], ret);
        }

        return ret;
    }


    public static void main(String[] args) {
        MaximumProductSubarray inst = new MaximumProductSubarray();
        System.out.println(inst.maxProduct(new int[]{-2, 0, -3}));
        System.out.println(inst.maxProduct(new int[]{2,3,-2,4}));
        System.out.println(inst.maxProduct(new int[]{-2,3,-4}));
        System.out.println(inst.maxProduct(new int[]{1,2,-1,-2,2,1,-2,1,4,-5,4}));
    }
}

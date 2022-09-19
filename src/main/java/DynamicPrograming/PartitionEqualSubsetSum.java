package DynamicPrograming;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 * 剑指 Offer II 101. 分割等和子集
 */
public class PartitionEqualSubsetSum {
    /**
     * 假设f(i,j)表示从前i个物品种选择若干物品装满容量为j的背包，那么f(n, s/2)就是题目的解。s为总和
     * 那么针对第i-1个物品，存在2种选择，选择或不选
     * 1。若选择将第i-1个物品放入到背包，那么如果前i-1个物品能装满j-nums[i-1]，算上第i-1个物品就可以装满j。即f(i,j) = f(i-1, j - nums[i-1)，前提条件是j>nums[i-1]
     * 2。若不选择将第i-1个物品放入到背包，那么如果前i-1个物品已经能装满j，那也成立。这种情况f(i,j) = f(i-1,j)
     */
    public boolean canPartition(int[] nums) {
        int s = 0;
        int maxValue = 0;
        for (int i: nums){
            s += i;
            maxValue = Math.max(maxValue, i);
        }
        if ((s % 2 == 1) || (maxValue > s / 2)){
            return false;
        }

        int n = nums.length;
        int t = s/2;
        // base case
        /**
         * 1。 当j=0时，就是背包的容量为0。不论有多少个背包，只要不选择，就可以让背包为0。所以f(i,0)都是true
         * 2。 当i=0，j>=1时，就是有0个物品，装满不少于1的背包，这种不可能。所以f(0,j)都是false，j>=1
         * 由于要处理2种base case，所以二维状态要多一列和一行
         */
        boolean[][] dp = new boolean[n+1][t+1];

        // 第一行
        Arrays.fill(dp[0], false);
        // 第一列
        for (int i = 1; i <= n; i++){
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= t; j++){
                dp[i][j] = dp[i-1][j] || (nums[i-1] <= j && dp[i-1][j - nums[i-1]]);
            }
        }

//        show2D(dp);

        return dp[n][t];
    }

    /**
     * 优化
     * 时间复杂度为O(n*t)
     * 空间复杂度为O(t)
     */
    public boolean canPartition2(int[] nums) {
        int s = 0;
        int maxValue = 0;
        for (int i: nums){
            s += i;
            maxValue = Math.max(maxValue, i);
        }
        int t = s/2;
        if ((s % 2 == 1) || (maxValue > t)){  // 奇数或者最大值已经大于t
            return false;
        }
        if (maxValue == t){   // 等于t直接返回
            return true;
        }

        int n = nums.length;
        /**
         * 1。 当j=0时，就是背包的容量为0。不论有多少个背包，只要不选择，就可以让背包为0。所以f(i,0)都是true
         * 2。 当i=0，j>=1时，就是有0个物品，装满不少于1的背包，这种不可能。所以f(0,j)都是false，j>=1
         * 由于要处理2种base case，所以二维状态要多一列和一行
         */
        boolean[] dp = new boolean[t+1];

        // base case
        dp[0] = true;
        for (int i = 1; i <= n; i++){
            // 根据状态转移方程知道，每一轮状态依赖它上一轮的左边的状态，用一维数组存放状态时，覆盖为本轮状态后右边的元素再使用就会有问题。
            // 从左到右计算状态时，f(i,j)可能还被右边的值用到，所以不能覆盖f(i-1,j)。
            // 但如果从右往左填状态，这样f(i,j)的状态被替换掉不会有问题，因为不会被用到了
            for (int j = t; j >= 0 && nums[i-1] <= j; j--){
                dp[j] = dp[j] || dp[j - nums[i-1]];
            }
            if (dp[t]){  // 能填满，提前直接返回, 后面物品可以不用遍历了
                return true;
            }
        }

        return dp[t];
    }

    public static void show2D(boolean[][] n){
        for (boolean[] a : n){
            for (boolean i: a){
                System.out.print(i + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum inst = new PartitionEqualSubsetSum();
        System.out.println(inst.canPartition2(new int[]{1,5,11,5}));  // true
        System.out.println(inst.canPartition2(new int[]{1,2,3,5}));  // false
        System.out.println(inst.canPartition2(new int[]{2,2,3,5}));  // false
        System.out.println(inst.canPartition2(new int[]{3,3,3,4,5})); // true
    }
}

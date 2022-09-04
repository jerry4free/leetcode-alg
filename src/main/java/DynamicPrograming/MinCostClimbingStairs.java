package DynamicPrograming;

/**
 * 主站：746
 * 剑指 Offer II 088. 爬楼梯的最少成本
 */
public class MinCostClimbingStairs {

    /**
     * dp[i] = min(dp[i-2], dp[i-1]) + cost[i]
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int dp[] = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        // 从2开始
        for (int i = 2; i < n; i++){
            dp[i] = Math.min(dp[i-2], dp[i-1]) + cost[i];
        }

        //注意：由于每次只能爬1个或2个台阶，而且到楼梯顶部，那么只能是n-2再爬2步，或者n-1再爬1步
        // 这个题意得明确
        return Math.min(dp[n-2], dp[n-1]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs inst = new MinCostClimbingStairs();
        System.out.println(inst.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(inst.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}

package DynamicPrograming;

/**
 * 121
 * offer: 63
 */
public class MaxProfit {

    /**
     * 遍历一次
     * dp[i] = Math.max(dp[i-1], prices[i] - leftMin)
     */
    public int maxProfit(int[] prices) {
        int ret = 0;
        int n = prices.length;
        if (n == 0) {
            return ret;
        }
        int m = prices[0];  // 表示从头到现在的最低值，不包括i
        for (int i = 1; i < n; i++){
            ret = Math.max(ret, prices[i] - m);
            m = Math.min(m, prices[i]);
        }

        return ret;
    }

    /**
     * 遍历3次
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;

        //7, 1, 5, 3, 6, 4
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        // leftMin是0到i的最小值，包含i
        for (int i = 0; i < n; i++){
            if (i == 0){
                leftMin[i] = prices[i];
            } else {
                leftMin[i] = Math.min(leftMin[i-1], prices[i]);
            }
        }

        // rightMax是i到n-1的最大值，包含i
        for (int i = n-1; i >= 0; i--){
            if (i == n - 1){
                rightMax[i] = prices[i];
            } else {
                rightMax[i] = Math.max(rightMax[i+1], prices[i]);
            }
        }

        int ret = 0;
        for(int i = 0; i < n - 1; i++){  //注意是到n-1，只有1个价格，就是0
            ret = Math.max(ret, rightMax[i+1] - leftMin[i]);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaxProfit inst = new MaxProfit();
        System.out.println(inst.maxProfit(new int[]{7,1,5,3,6,4}));
    }
}


package DynamicPrograming;


/**
 * 343
 */

public class IntegerBreak{

    public int integerBreak(int n) {

        /**
         * 重要的是怎么写出状态转移方程呢？
         *
         * 动态方程一般都需要检查所有之前记忆的状态
         * 设dp[n]为表示将正整数 n 拆分成至少两个正整数的和之后，这些正整数的最大乘积
         * f(n) = max( k * f(n-k), k * (n-k) )
         * 1. 一个n可以拆分为k、n-k，n-k可以继续拆分或者不拆分。
         * 2. 不拆分就是k * (n-k),
         * 3. 拆分就是k * dp[n-k]
         * 那么就是求k从1到n-1时，所有轮中的拆分或不拆分的最大值。这是一种自上而下的方法，那么可以倒着来，自下而上
         *
         *
         */
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            int currMax = 0;
            for (int j = 1; j < i; j++){
                currMax = Math.max(currMax, j * dp[i-j]);
                currMax = Math.max(currMax, j * (i-j));
            }
            dp[i] = currMax;
        }
        return dp[n];
    }


    public static void main(String[] args){
        IntegerBreak inst = new IntegerBreak();
        System.out.println(inst.integerBreak(10));
        System.out.println(inst.integerBreak(2));
    }
}


package DynamicPrograming;


/**
 * 343
 */

public class IntegerBreak{

    public int integerBreak(int n) {

        /**
         * 重要的是怎么写出动态方程呢？
         *
         * 动态方程一般都需要检查所有之前记忆的状态
         * 设dp[k]为表示将正整数 k 拆分成至少两个正整数的和之后，这些正整数的最大乘积
         * 一个n可以拆分为k、n-k，n-k可以继续拆分或者不拆分。不拆分就是k*(n-k), 拆分就是dp[n-k]
         * 那么就是求k从1到n-1时，所有轮中的拆分或不拆分的最大值。
         *
         *
         */
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++){
            for (int j = 1; j < i; j++){
                dp[i] = Math.max(j * dp[i-j], dp[i]);
                dp[i] = Math.max(j * (i-j), dp[i]);
            }
        }
        return dp[n];
    }


    public static void main(String[] args){
        IntegerBreak inst = new IntegerBreak();
        System.out.println(inst.integerBreak(10));
        System.out.println(inst.integerBreak(2));
    }
}

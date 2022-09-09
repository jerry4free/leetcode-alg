package DynamicPrograming;

import Util.Utils;

/**
 * offer 46
 */
public class TranslateNum {
    // 技巧：注意点：如果知道重点考察的算法，其他不重要的可以快速简单实现，或调用系统API。
    public int translateNum(int num) {
        String s = String.valueOf(num); // 转化成string，简化数值从高到低的遍历

        int len = s.length();
        int[] dp = new int[2];

        dp[0] = 1;

        /**
         *
         * 状态转移方程：
         * 1. dp[i] = dp[i-1] + dp[i-2], if 10 <= num[i-1]*10+num[i] <= 25
         * 2. dp[i] = dp[i-1], if num[i-1]*10+num[i] > 25
         * 注意边界case：00,01,...09这种不能组成2种翻译
          */
        for (int i = 1; i < len; i++){
            int n = (s.charAt(i-1) - '0') * 10 + s.charAt(i) - '0';
            if (i == 1){
                if (n < 26 && n >= 10){
                    dp[1] = 2;
                } else {
                    dp[1] = dp[0];
                }
            } else {
                if (n < 26 && n >= 10){
                    dp[i%2] = dp[0] + dp[1];
                } else {
                    dp[i%2] = dp[(i-1)%2];
                }
            }
        }
//        Utils.show(dp);

        return dp[(len-1)%2];
    }

    public static void main(String[] args) {
        TranslateNum inst = new TranslateNum();
        System.out.println(inst.translateNum(0));
        System.out.println(inst.translateNum(3));
        System.out.println(inst.translateNum(25));
        System.out.println(inst.translateNum(12258));

    }
}

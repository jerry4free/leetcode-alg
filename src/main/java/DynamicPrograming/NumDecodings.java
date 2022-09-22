package DynamicPrograming;

import Util.Utils;

/**
 * 91. Decode Ways
 */
public class NumDecodings {

   public int numDecodings(String s) {
        /**
         * 11106
         * 1。确定状态：假设f(i)表示到前i个字符能够解码的数量，那么f(n)就是题目的解
         * 2。确定状态转移，保证情况不重不漏。先从大的2种情况来。注意条件
         * 0）如果s[i-1]是0。除非是10，20，才能有效被解码:f(i)=f(i-2)，其他均无法被解码
         * 1）如果s[i-1]单独有效，且跟前面的s[i-2]也能被解码，
         * 那么f(i) = f(i-1) + f(i-2), if s[i-2:i-1] > 10 and <= 26,
         * 2）如果s[i-1]单独被解码，跟前面的s[i-2]不能被解码
         * 那么f(i) = f(i-1),
         *
         *
         */
        if (s.charAt(0) == '0'){
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len+1];
        // 注意base case，由于状态转移涉及i-1,i-2，所以注意这2种情况的边界case
        dp[0] = 1;

        for (int i = 1; i <= len; i++){
            int p2 = s.charAt(i-1) - '0';
            // 确定状态转移，保证情况不重不漏。先从大的2种情况来, 分别是单独解码和2个字符解码
            if (p2 != 0) {  // 1个字符解码：只有不为0，才能单独解码；0这种解码是无效的。
                dp[i] = dp[i-1];
            }
            if (i > 1){  // 2个字符解码：只有处于10～26才行; 00~09这种跟之前字符的解码，是无效的
                int p1 = s.charAt(i-2) - '0';
                int x = p1 * 10 + p2;
                if (x >= 10 && x <= 26){  // 10~26
                    dp[i] += dp[i-2];
                }
            }
        }

//        Utils.show(dp);

        return dp[len];
    }

    public static void main(String[] args) {

        NumDecodings inst = new NumDecodings();
        System.out.println(inst.numDecodings("00"));
        assert inst.numDecodings("0") == 0;  // 0
        assert inst.numDecodings("21") == 2; // 2
        System.out.println(inst.numDecodings("2101"));  // 1
        assert inst.numDecodings("1201234") == 3;  // 3
        assert inst.numDecodings("1123") == 5;  // 5
        assert inst.numDecodings("226") == 3;
        assert inst.numDecodings("11106") == 2;
    }
}

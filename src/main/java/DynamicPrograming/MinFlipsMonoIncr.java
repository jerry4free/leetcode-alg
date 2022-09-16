package DynamicPrograming;

import Util.Utils;

/**
 * 926
 */
public class MinFlipsMonoIncr {

    /**
     *  假设前i位是递增的，那么可以通过第i+1位和第i位的判断来知道是否递增，这种有重叠子问题可以通过动态规划的方式来解决
     * 每一位既可以翻0，也可以翻1，假设dp0是第i位翻0的次数，dp1是第i位翻1的次数，那么他俩的最小值就是第i位的最小翻转次数
     *
     * 其中：
     * 1. 第i位是0时，要求第i-1位必须是0；在第i-1位翻0的次数上，看是否要加1
     * 2. 第i位是1时，第i-1位可是0或1。翻1的最小次数就是在第i-1位的最小次数上，看是否要加1
     *
     * 那么动态转移方程就是
     * 1. dp0[i] = dp0[i-1] + s.charAt(i) == '0' ? 0 : 1);
     * 2. dp1[i] = Math.min(dp0[i-1] , dp1[i-1] ) + (s.charAt(i) == '1' ? 0 : 1)
     */
    public int minFlipsMonoIncr(String s) {
        int len = s.length();
        int dp0 = 0;
        int dp1 = 0;
        int dp0New = 0;
        int dp1New = 0;

        // dp0代表第i位翻为0时，使得0到i都是递增序列的最小翻转次数
        // dp1代表第i位翻为1时，使得0到i都是递增序列的最小翻转次数
        for (int i = 1; i < len; i++){
            dp0New = dp0 + (s.charAt(i) == '0' ? 0 : 1);
            dp1New = Math.min(dp0, dp1) + (s.charAt(i) == '1' ? 0 : 1);
            dp0 = dp0New;
            dp1 = dp1New;
        }

        return Math.min(dp0, dp1);
    }

    public int minFlipsMonoIncr2(String s) {
        int len = s.length();
        int[] dp0 = new int[len];
        int[] dp1 = new int[len];

        // dp0代表第i位翻为0时，使得0到i都是递增序列的最小翻转次数
        // dp1代表第i位翻为1时，使得0到i都是递增序列的最小翻转次数
        dp0[0] = s.charAt(0) == '0' ? 0 : 1;
        dp1[0] = s.charAt(0) == '1' ? 0 : 1;
        for (int i = 1; i < len; i++){
//            char c = s.charAt(i);
//            if (c == '0'){
//                dp0[i] = dp0[i-1];
//                dp1[i] = Math.min(dp0[i-1], dp1[i-1]) + 1;
//            } else {
//                dp0[i] = dp0[i-1] + 1;
//                dp1[i] = Math.min(dp0[i-1], dp1[i-1]);
//            }
            dp0[i] = dp0[i-1] + (s.charAt(i) == '0' ? 0 : 1);
            dp1[i] = Math.min(dp0[i-1], dp1[i-1]) + (s.charAt(i) == '1' ? 0 : 1);
        }

        Utils.show(dp0);
        Utils.show(dp1);

        return Math.min(dp0[len-1], dp1[len-1]);
    }

    public static void main(String[] args) {
        MinFlipsMonoIncr inst = new MinFlipsMonoIncr();
        System.out.println(inst.minFlipsMonoIncr("00110"));
//        System.out.println(inst.minFlipsMonoIncr("010110"));
        System.out.println(inst.minFlipsMonoIncr("00011000"));

    }
}

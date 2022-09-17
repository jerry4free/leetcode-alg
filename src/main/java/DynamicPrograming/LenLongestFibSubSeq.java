package DynamicPrograming;

import Util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 873
 * 剑指 Offer II 093. 最长斐波那契数列
 */
public class LenLongestFibSubSeq {
    /**
     * 这道题采用动态规划时，动态转移方程分析的难点在于动态方程有2个参数，这点比较难想到
     * 1。假设0<=k<j<i, 满足arr[k] + arr[j] == arr[i]，那么最容易想到的就是：dp[i] = dp[j] + 1
     * 2。但是6=4+2，也可以6=5+1，所以不同的j则有不同的k，不同的子序列长度也不相同。
     *    所以j也是变化的参数，动态方程需要考虑2个参数,即：f[i][j] = f[j][k] + 1, if arr[k] + arr[j] == arr[i]
     * 3。如果动态转移方程没有想到2个参数，用一维数组存储状态，就很容易想到将第i位存储最长长度，但是全局看它可能就不是最优解。
     *    因为后面可能存在其他数字跟前面的比较短的序列组成更长的序列
     * 4。当i确定时，要找到所有的arr[k] + arr[j] == arr[i]，也可以通过双指针k，j，k++，j--这种方式来找，因为是递增序列
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */

    public int lenLongestFibSubseq2(int[] arr) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        int ret = 0;
        // 存储索引，以便在O(1)的时间找到arr[k]是否存在
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++){
            index.put(arr[i], i);
        }

        for(int i = 2; i < n; i++){
            for (int j = i-1; j >= 0; j--){
                int k = index.getOrDefault(arr[i]-arr[j], -1);
                // 如果满足斐波那契数列，则更新状态
                if (k != -1 && k < j){
                    dp[i][j] = Math.max(dp[j][k] + 1, 3);
                    ret = Math.max(ret, dp[i][j]);
//                    if (i == 8 && j == 6){
                    System.out.println(k + ":" + arr[k] + ", " + j + ":" + arr[j] + ", " + i + ":" + arr[i]);
                        System.out.println(dp[i][j] + "," + dp[j][k]);
//                    }
                }
            }
        }
        Utils.show2D(dp);

        return (n > 2) ? ret : 0;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        int ret = 0;

        for(int i = 2; i < n; i++){
            int k = 0;
            int j = i - 1;
            while (k < j){
                if (arr[k] + arr[j] == arr[i]){
//                    System.out.println(arr[k] + ", " + arr[j] + ", " + arr[i]);
                    if (dp[j][k] == 0){
                        dp[i][j] = 3;
                    } else {
                        dp[i][j] = dp[j][k] + 1;
                    }
                    ret = Math.max(ret, dp[i][j]);
                    k++;
                    j--;
                } else if (arr[k] + arr[j] > arr[i]){
                    j--;
                } else {
                    k++;
                }
            }
        }
//        Utils.show2D(dp);


        return (n > 2) ? ret : 0;
    }

    public static void main(String[] args) {
        LenLongestFibSubSeq inst = new LenLongestFibSubSeq();
//        System.out.println(inst.lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8}));
//        System.out.println(inst.lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18}));
        System.out.println(inst.lenLongestFibSubseq(new int[]{2,4,7,8}));
        System.out.println(inst.lenLongestFibSubseq(new int[]{4,10,14,18}));
//        System.out.println(inst.lenLongestFibSubseq(new int[]{2,4,7,8,9,10,14,15,18,23,32,50}));
//        System.out.println(inst.lenLongestFibSubseq2(new int[]{2,4,7,8,9,10,14,15,18,23,32,50}));
    }
}

package DynamicPrograming;

import java.util.Arrays;

/**
 * 312, 戳气球
 */
public class BurstBalloons {
    int[] arr;
    int[][] f;
    public int maxCoins(int[] nums) {

        // 区间DP
        // 假设f[l][r]表示为在(l,r)范围内气球戳掉(不包括l和r边界)，能取到的最大价值
        // 那么对于每个k，k属于(l, r)
        // f[l][r] = ma(f[l][k] + f[k][r] + nums[l] * nums[k] * nums[r])
        // f[0][n-1]就是答案
        // base case: 0, if (l >= r - 1)， 可以理解为2个相邻的元素间没有元素可以戳

        // 技巧1：为了减少边界情况，对nums进行扩充，头尾加个1
        int len = nums.length;
        int n = len + 2;
        arr = new int[n + 2];
        arr[0] = 1;
        arr[n - 1] = 1;
        for (int i = 0; i < len; i++) {
            arr[i + 1] = nums[i];
        }

        f = new int[n][n];
        for (int i = 0; i < n; i++){
            Arrays.fill(f[i], -1);
        }

        // 上面的状态转移方程，是自上而下的递归，top-down
        return run(0, n - 1);
    }

    private int run(int left, int right){
        // base case
        if (left >= right - 1){
            return 0;
        }

        // get from cache
        if (f[left][right] != -1){
            return f[left][right];
        }

        for (int k = left + 1; k < right; k++){
            int sum = arr[left] * arr[k] * arr[right];
            sum += run(left, k) + run(k, right);
            f[left][right] = Math.max(f[left][right], sum);
        }
        return f[left][right];
    }
}

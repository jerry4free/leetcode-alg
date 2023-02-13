package PriorityQueueX;

import Util.Utils;

import java.util.PriorityQueue;

/**
 * 313. 超级丑数
 */
public class nthSuperUglyNumber {

    /**
     *  本题跟264类似，只不过264的时间复杂度是O(MNlog(MN)),不通过
     *
     *  假设dp[i]表示第i个丑数，那么dp[n-1]就是本题的解
     *  每个丑数都是由之前的丑数产生，即由dp[i] * primes[j] 产生
     *  对于primes是[2,7,13,19]来说，
     *  序列1：2*1, 2*2, 2*4, 2*7, 2*8, 2*13, 2*16, 2*19
     *  序列2：7*1, 7*2, 7*4, 7*7, 7*8, 7*13, 7*16, 7*19
     *  序列3：13*1, 13*2, 13*4, 13*7, 13*8, 13*13, 13*16, 13*19
     *  序列4：19*1, 19*2, 19*4, 19*7, 19*8, 19*13, 19*16, 19*19
     *  4个序列都是有序的（虽然有重复），就是将4个序列合并输出，找出第n个。那么可以采用多路归并的方法，借助于堆
     *
     *  所以dp[k] = dp[i] * primes[j]，这个当前丑数跟之前丑数的递推关系很重要，这其实也就是状态转移关系
     *
     *  经过动态规划优化后，时间复杂度是O(max(M,N) * logM)
     *
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;

        int[] dp = new int[Math.max(n, m)];
        dp[0] = 1;

        // 里面元素是i, j, current_prime
        // i就是之前的丑数下标
        // j就是第几个序列，第几路，即primes的下标
        // current_prime就是迭代到当前的丑数
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        // 堆中加入M路的第1个元素
        for (int j = 0; j < m; j++){
            q.add(new int[]{0, j, dp[0] * primes[j]});
        }

        // 迭代直到n为0
        for(int k = 1; k < n;) {
            int[] pair = q.remove();
            int i = pair[0]; // 之前的丑数下标
            int j = pair[1]; // 第j路

//            System.out.println("i:" + i + ",n:" + n + ",k:" + k);
            if (dp[k-1] != pair[2]){  // 如果当前的丑数跟之前的丑数不等，才更新
                // 更新当前状态
                dp[k++] = pair[2];
            }
            int next = dp[i+1] * primes[j];
            // 第j路往后移动1位，即依赖的dp[i]的下标i往后移动1位, 同时这一路的下一个丑数加入堆中
            q.add(new int[]{i+1, j, next});
        }
//        Utils.show(dp);

        return dp[n-1];
    }

    public static void main(String[] args) {
        nthSuperUglyNumber inst = new nthSuperUglyNumber();
        System.out.println("expected: 12, real:" + inst.nthSuperUglyNumber(12, new int[]{2,3,5}));
        int[] primes = new int[]{7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,127,131,137,139,157,167,179,181,199,211,229,233,239,241,251};
        System.out.println("expected: 12, real:" + inst.nthSuperUglyNumber(100000, primes));
        System.out.println("expected: 32, real:" + inst.nthSuperUglyNumber(12, new int[]{2,7,13,19}));

    }
}

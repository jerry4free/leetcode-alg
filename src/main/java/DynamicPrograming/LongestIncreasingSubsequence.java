package DynamicPrograming;

/**
 * 300. Longest Increasing Subsequence
 */
public class LongestIncreasingSubsequence {
    /**
     * 时间复杂度:O(n^2)
     * 空间复杂度:O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int n = nums.length;
        if (n == 0){
            return 0;
        }

        // 对于每个下标i，f(i)为以i结尾的最长序列长度，f(i-1)是以i-1结尾的最长序列长度，愿问题和子问题之间关系如何呢？
        // 那么f(i)=1到i-1的所有子序列中加上nums[i]时还是递增关系时最长的长度，再加1
        // 状态转移方程,f(i) = max(f(j)), 所有的j满足：0<=j<i, nums[j] < nums[i]，
        // 前提是nums[i]是当前以i结尾时的最长子序列中被选取的最大值，即nums[i]已经被选择。否则还需要单独存储每轮最长子序列的末尾值
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++){
            // 计算第i个状态，即dp[i],初始化为1
            int currMaxLen = 1;
            // 遍历之前的所有轮状态，
            // 如果nums[j] < nums[i]，满足递增关系，那么记录下来，找到所有轮状态中的最大值。
            // 如果nums[j] > nums[i]，不满足递增，意味着不被考虑，意味着这些轮加上该nums[i]不符合递增
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){  // nums
                    currMaxLen = Math.max(currMaxLen, dp[j] + 1);
                }
            }
            dp[i] = currMaxLen;
            // 更新全局最长长度
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    // 贪心法, 使用二分查找提高每轮状态的时间复杂度: O(NlgN), 比较难
    // 核心参考：patient sorting
    // 1. 这个游戏会玩，但是为什么这么排序后的堆数就是最长子序列的个数，无法直觉的想明白，或许得证明
    public int lengthOfLIS1(int[] nums) {
        int piles = 0;
        int n = nums.length;
        // 存储每个堆的堆顶元素
        int[] top = new int[n];

        for (int num: nums) {
            int left = 0;
            int right = piles;
            // 任意时刻，堆顶元素都是自增的，所以可以通过二分查找对应的堆
            while (left < right) {
                int mid = (right + left) / 2;
                if (num < top[mid]) {
                    right = mid;
                } else if (num > top[mid]) {
                    left = mid + 1;
                } else {
//                    left = mid;
                    right = mid;
                }
            }
            // left为最后找到的堆下标，
            // 如果超过原来堆数，即left == piles，则新建一个堆
            if (left == piles) piles++;
            // 更新堆顶元素
            top[left] = num;
        }
        return piles;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence inst = new LongestIncreasingSubsequence();
        System.out.println(inst.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(inst.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(inst.lengthOfLIS(new int[]{7,7,7,7,7,7}));
    }
}

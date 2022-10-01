package TwoPointer;

import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K
 */
public class SubarraySumEqualsK {

    // O(n^2)
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        // r代表子数组最右端，l代表最左端
        int ret = 0;
        for (int r = 0; r < len; r++){  // 枚举每一个最右端下标
            int s = 0;
            for (int l = r; l >= 0; l--){ // 固定r，枚举每一个最左端下标，O(n)找到以r为结束的子数组中和为k的数量
                s += nums[l];
                if (s == k){
                    ret++;
                }
            }
        }

        return ret;
    }

    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        // 哈希表存储前缀和的次数，即：key是前缀和的值，value是前缀和的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        // 下标0之前没有元素，那么前缀和就是0，次数是1。case：[1]找1
        preSum.put(0, 1);

        int ret = 0;
        int s = 0;
        for (int i = 0; i < len; i++){
            s += nums[i];
            // O(1)的时间找到以i为结束的子数组中和为k的数量
            // 当前和为s，那么所有前缀和为s-k的都可以满足要求
//            System.out.print("add:" + preSum.getOrDefault(s - k, 0));
            ret += preSum.getOrDefault(s - k, 0);
//            System.out.println(", ret:" + ret);
            preSum.put(s, preSum.getOrDefault(s, 0) + 1);  // 最后更新前缀和的数量
        }
        return ret;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK inst = new SubarraySumEqualsK();
        System.out.println(inst.subarraySum(new int[]{1,1,1}, 2));
        System.out.println(inst.subarraySum(new int[]{1,2,3}, 3));
        System.out.println(inst.subarraySum(new int[]{1,-1,0}, 0)); // 3

    }
}

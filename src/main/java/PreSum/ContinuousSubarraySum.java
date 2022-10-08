package PreSum;

import java.util.HashSet;
import java.util.Set;

/**
 * 523. Continuous Subarray Sum
 */
public class ContinuousSubarraySum {

    /**
     * 前缀和
     * 时间复杂度:O(n)
     * 空间复杂度：O(min(n,k)), 哈希表存储的是所有元素模k的余数，当n < k时，哈希表的大小就是n
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len < 2){
            return false;
        }
        // 哈希表里存储的元素数的前缀和，跟累积的元素当前的和，元素数始终差2，这样就可以保证子数组至少有2个元素
        Set<Integer> set = new HashSet<>();
        // base case
        set.add(0);

        // preSum 和 sum的元素下标差2
        int preSum = 0;
        int sum = nums[0];
        for (int i = 1; i < len; i++){
            sum += nums[i];
            // sum - preSum == n * k
            // preSum = sum - n * k
            // 第一次做，想的是遍历所有的倍数
            /*
            for (int j = 0; j < sum / k; j++){
                if (set.contains(sum - j * k)) {
                    return true;
                }
            }
            */

            /**
             * sum - preSum == n * k
             * 当sum和preSum是k的倍数时，sum和preSum除以k的余数相同，这个怎么能想到呢？
             * 根据上面的等式，知道sum求解preSum是否存在，如何消除未知数n？
             * 等式两边都模k，那么sum % k - preSum % k == 0, 即 sum % k == preSum % k
             */
            if (set.contains(sum % k)){
                return true;
            }
            preSum += nums[i-1];
            set.add(preSum % k);
        }

        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum inst = new ContinuousSubarraySum();
        System.out.println(inst.checkSubarraySum(new int[]{23,2,4,6,7}, 6));   // true
        System.out.println(inst.checkSubarraySum(new int[]{23,2,4,6,7}, 13));  // true
        System.out.println(inst.checkSubarraySum(new int[]{23,2,6,4,7}, 13));  // false
        System.out.println(inst.checkSubarraySum(new int[]{4,2}, 6));

    }
}

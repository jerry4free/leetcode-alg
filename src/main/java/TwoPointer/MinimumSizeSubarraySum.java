package TwoPointer;

/**
 * 209. Minimum Size Subarray Sum
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int inf = 100001;
        int ret = inf;
        int len = nums.length;
        int s = 0;
        int i = 0;

        // 滑动窗口，i表示开始，j表示结束
        // 如果滑动窗口的和大于target，则减小窗口，i++，直到不再大于target或者i大于j
        // 如果和小于target，则扩大窗口。
        for (int j = 0; j < len; j++){  // 扩大窗口，每一次扩大窗口后, 去搜索解
            s += nums[j];
            while (i <= j && s >= target){  // 缩小窗口, 去遍历窗口内所有有效解，直到窗口为0或者无解
                ret = Math.min(ret, j - i + 1);
                s -= nums[i++];
            }
        }
        return (ret == inf) ? 0 : ret;
    }


    /*
    // 如下解法不对，第一次写，想在一层循环里实现滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int ret = 100001;
        int len = nums.length;
        int i = 0;
        int j = 0;
        int s = nums[i];
        while (i < len && j < len){
            System.out.println("i:" + i + ",j:" + j + ",sum:" + s);
            if (s >= target){
                ret = Math.min(ret, j - i + 1);
                s -= nums[i++];
            } else {
                s += nums[++j];
            }
        }
        return (ret > 10000) ? 0 : ret;
    }
     */

    public static void main(String[] args) {
        MinimumSizeSubarraySum inst = new MinimumSizeSubarraySum();
        System.out.println(inst.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(inst.minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(inst.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));

    }
}

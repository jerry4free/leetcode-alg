package SlidingWindow;

/**
 * 713. Subarray Product Less Than K
 */
public class SubarrayProductLessThanK {

    /**
     * 一般连续子数组等可以思考能否用滑动窗口的思想。
     * 滑动窗口：有2个指针，左指针指向窗口最左端，右指针指向窗口最右端。右指针用来扩大窗口，左指针用来缩小窗口
     * 外层循环每次扩大窗口，然后找到以新的右指针为窗口结束的所有可能解。这时通过右移左指针来缩小窗口
     * 时间复杂度：O(n)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        int ret = 0;
        int i = 0;
        int p = 1;
        for (int j = 0; j < len; j++){  // 扩大窗口
            p *= nums[j];
            // 找到以j为结尾的所有可能连续子数组的数量,
            // 如果窗口不为0且窗口乘积一直大于等于k，那么一直缩小窗口，直到乘积p小于k或窗口为0（i和j交错）。
            while (i <= j && p >= k) {
                p /= nums[i++];
            }
//            System.out.println("j:" + j + ",i: " + i + ", cnt:" + (j - i + 1));
            // 然后算出当前i到j的所有子数组的数量
            ret += (j - i + 1);
        }

        return ret;
    }

    public static void main(String[] args) {
        SubarrayProductLessThanK inst = new SubarrayProductLessThanK();
        System.out.println(inst.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
        System.out.println(inst.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 0));
    }

}

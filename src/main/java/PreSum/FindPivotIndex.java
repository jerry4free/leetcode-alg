package PreSum;

/**
 * 724. Find Pivot Index
 * 1991. 找到数组的中间位置
 */
public class FindPivotIndex {

    // 空间复杂度:O(1)
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++){
            sum += nums[i];
        }

        int preSum = 0;
        for (int i = 0; i < len; i++){
            if (sum - nums[i] == (2 * preSum)){
                return i;
            }
            preSum += nums[i];
        }
        return -1;
    }
    /**
     * 前缀和
     * 空间复杂度：O(n)
     */
    public int pivotIndex2(int[] nums) {
        int len = nums.length;
        // preSum[i]表示，第i个下标之前的前缀和，即preSum[i]表示的是nums[0,i-1]的前缀和
        // 之所以用len+1的长度，是为了统一边界case
        // preSum[0] = 0;
        int[] preSum = new int[len+1];
        for (int i = 1; i <= len; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        for (int i = 1; i <= len; i++){
            if (preSum[len] - preSum[i] == preSum[i-1]){
                return i-1;  // 注意要转化为原nums的下标
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindPivotIndex inst = new FindPivotIndex();
        System.out.println(inst.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(inst.pivotIndex(new int[]{1,2,3}));
        System.out.println(inst.pivotIndex(new int[]{2,1,-1}));
    }
}

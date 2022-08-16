package BinarySearch;

/**
 * 153. Find Minimum in Rotated Sorted Array
 */
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums){
        int l = 0;
        int r = nums.length - 1;

        /**
         * 相错终止
         * 画图看性质
         * 旋转数组的性质：如果后半有序，即 num[m] < nums[r], 那么最小值一定不在 [m+1, r]里，但可能是num[r]
         * 如果后半无序，即num[m] > nums[r], 那么一定在后半部分, （不包含num[m])
         */
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] < nums[r]){ // 注意
                r = m;
            } else if (nums[m] == nums[r]){ // 这种case，即只有一个，可以拿一个元素特殊处理，快速判断
                l = m + 1;
            } else {
                l = m + 1;
            }
        }
        return nums[r];
    }

    public int findMin3(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[l] < nums[m]){  // 如果左边有序，那么一定不在左边（不包括nums[l])
                l = m;
            } else if (nums[l] == nums[m]){ // [3, 1, 2, 3, 3, 3, 3], 这种情况无法判断，只能去掉一个，重新定位中间值
                l += 1;
            } else {  // 如果右边无序，那么最小值一定在右边，排除左边
                r = m - 1;
            }
        }
        return nums[r];
    }

    // 但是跟官方解法相比，这是一种比较粗糙的二分法，虽然时间复杂度是O(lgN)，每次多了一次判断和赋值。
    public int findMin2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int ret = nums[0];
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[l] < nums[m]){  // 如果左边有序，那么将左边最小值比较并存储，去找右边
                if (nums[l] < ret){
                    ret = nums[l];
                }
                l = m + 1;
            } else if (nums[l] == nums[m]){ // 如果相等，意味着l == m, 意味着只有2个元素了
                if (nums[l] < ret){
                    ret = nums[l];
                }
                l = m + 1;
            } else {  // 如果右边有序，将右边最小值即nums[m]，
                if (nums[m] < ret){
                    ret = nums[m];
                }
                r = m - 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArray inst = new FindMinimuminRotatedSortedArray();
        System.out.println(inst.findMin3(new int[]{3,4,5,1,2}));
        System.out.println(inst.findMin3(new int[]{4,5,6,7,0,1,2}));
        System.out.println(inst.findMin3(new int[]{11,13,15,17}));
        System.out.println(inst.findMin3(new int[]{7,1}));
        System.out.println(inst.findMin3(new int[]{7}));
        System.out.println(inst.findMin3(new int[]{4,5,6,7,0,1,2}));
    }
}

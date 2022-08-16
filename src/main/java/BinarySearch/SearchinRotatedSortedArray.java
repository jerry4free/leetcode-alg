package BinarySearch;

/**
 * 33. Search in Rotated Sorted Array
 */
public class SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length -1;

        /**
         * 思路：数组经过旋转过，且0<=k<n,那么数组可能也没有被旋转，因为k=0时
         * 一个性质必须知道：
         * 0。 即从中间一分为二，一半是确定有序的，另一半不确定有序(可能是有序也可能是无序)
         * 1。 如果target在有序中，那么查找有序这一半，就是有序数组的二分查找了。
         * 2。 否则查找另一半，再一分为二，一半有序，一半可能有序也可能无序。就这样循环，这样还是二分的结果。
         *
         * 精髓，在于优先每次先判断有序的那一半，排除有序一半后，继续拆分另一半无序的。
         *
         * 技巧：可恶的等号如何处理呢？特殊处理比较好理解
         */
        while (low <= high){
            // 注意每次在循环里求mid
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) return mid;
            if (nums[low] < nums[mid]){  // 如果左半边有序，
                if (nums[low] <= target && target < nums[mid]){ // 处于左边有序之中
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[low] == nums[mid]){  // 因为题目说不会重复, nums[mid]和nums[low]相等时，即low=mid, 且还判断过了，直接跳过mid。
                low = mid + 1;
            } else {  // 反之，右半部分有序
                if (nums[mid] < target && target <= nums[high]){  // 处于右边有序之中
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        SearchinRotatedSortedArray inst = new SearchinRotatedSortedArray();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(inst.search(nums, 0));
        System.out.println(inst.search(nums, 2));
        System.out.println(inst.search(nums, 4));
        System.out.println(inst.search(nums, 8));
        System.out.println(inst.search(new int[]{1}, 0));
        System.out.println(inst.search(new int[]{1, 3}, 0));

        // 注意这个case
        System.out.println(inst.search(new int[]{3, 1}, 1));
    }
}

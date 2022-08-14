package search;

/**
 * 81. Search in Rotated Sorted Array II
 */
public class SearchinRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;


        /**
         * 数组旋转后的一个性质是：一半有序，另一半可能有序。有序的可以进行二分查找，
         * 另一半可以继续二分，分为一半有序另一半可能有序
         * 所以这是一个二分性：即每次一半符合一定条件，另一半不符合，且每次可以排除一半
         */

        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] == target){
                return true;
            }

            // 有重复值的一个case是：[3, 1, 2, 3, 3, 3, 3], 找2
            // 针对这种case特殊处理，即掐头去尾，缩小搜索范围，
            // [3, 3, 3, 3, 3, 3, 3]，找4，所以这种case时间复杂度最坏是O(N)
            if (nums[l] == nums[m] && nums[m] == nums[r]){
                l += 1;
                r -= 1;
            } else if (nums[l] <= nums[m]){// 如果左边有序
                if (nums[l] <= target && target < nums[m]) { // 且在左边，那么可以继续二分查找
                    r = m - 1;
                } else {  // 否则去找另一半
                    l = m + 1;
                }
            } else { // 如果右边有序
                if (nums[m] < target && target < nums[r]) {  // 且在右边，那么可以继续二分查找
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SearchinRotatedSortedArrayII inst = new SearchinRotatedSortedArrayII();
        int[] nums = new int[]{2,5,6,0,0,1,2};
        System.out.println(inst.search(nums, 0));
        System.out.println(inst.search(nums, 3));
    }
}

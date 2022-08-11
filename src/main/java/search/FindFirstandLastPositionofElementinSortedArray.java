package search;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 */
public class FindFirstandLastPositionofElementinSortedArray {

    // 参考：
    // 1. https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solution/acm-xuan-shou-tu-jie-leetcode-zai-pai-xu-yz1m/
    // 2. https://leetcode.cn/circle/discuss/ooxfo8/#%E8%B0%A8%E4%BB%A5%E6%AD%A4%E6%96%87%E7%8C%AE%E7%BB%99%E8%BF%B7%E5%A4%B1%E5%9C%A8%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE%E4%B8%AD%E7%9A%84%E9%9D%92%E6%98%A5%E5%B2%81%E6%9C%88
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{-1, -1};
        }
        int l = 0;
        int r = nums.length - 1;
        int start = -1;
        int end = -1;

        /**
         * 循环条件是：l <= r, 相错终止，那么结束循环后，一定是l > r, 且l = r + 1，即r,l
         *
         */
        // 查找target的左边界,
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] == target){  // 相等，找左侧
                r = m - 1;
            } else if (nums[m] < target){ // 小于，找右侧
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // 由于相等时优先找左侧，所以r左移动了一位，所以l是第一个等于的，即左边界
        // 如果没越界，且nums[l] == target，那么就是左边界
        if (l != nums.length && nums[l] == target){
            start = l;
        }

        l = 0;
        r = nums.length - 1;
        // 查找target的右边界,
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] == target){ // 找右侧
                l = m + 1;
            } else if (nums[m] < target){ // 小于，找右侧
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // 由于相等时优先找右侧，所以l已经右移了一位，r就是最后一个等于的。即右边界
        // 如果没越界，且nums[r] == target，那么就是右边界
        if (r != -1 && nums[r] == target){
            end = r;
        }

        return new int[]{start, end};
    }

    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{-1, -1};
        }
        int l = 0;
        int r = nums.length - 1;
        int start = -1;
        int end = -1;

        /**
         * 循环条件是：l <= r, 相错终止，那么结束循环后，一定是l > r, 且l = r + 1，即r,l
         *
         */
        // 查找target的左边界,那么就是一定要<r, 要小于l
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] < target){
                l = m + 1;  // l左侧一定小于target
            } else {
                r = m - 1;  // r右侧一定大于等于target
            }
        }
        // 最后，l是r右侧的第一个，即大于等于target的第一个元素。
        // 如果没越界，且nums[l] == target，那么就是左边界
        if (l != nums.length && nums[l] == target){
            start = l;
        }

        l = 0;
        r = nums.length - 1;
        // 查找target的右边界,那么就是一定要<=r,
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] <= target){
                l = m + 1;  // l左侧一定小于等于target
            } else {
                r = m - 1;  // r右侧一定大于target
            }
        }
        // 最后，r是l左侧的第一个，即小于等于target的第一个元素
        // 如果没越界，且nums[r] == target，那么就是右边界
        if (r != -1 && nums[r] == target){
            end = r;
        }

        return new int[]{start, end};
    }

    private static void show(int[] nums){
        for (int n: nums){
            System.out.print(n + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        FindFirstandLastPositionofElementinSortedArray inst = new FindFirstandLastPositionofElementinSortedArray();
        show(inst.searchRange(new int[]{0, 0}, 0));
        show(inst.searchRange(new int[]{5,7,7,8,8,10}, 8));
        show(inst.searchRange(new int[]{5,7,7,8,8,10}, 6));
        show(inst.searchRange(new int[]{5,7,7,7,8,10}, 7));

    }
}

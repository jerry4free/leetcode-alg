package search;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 */
public class FindMinimuminRotatedSortedArrayII {

    /**
     * 画图，看性质，看如何达成二分性
     * TODO: 为什么比较中间和尾部,而不是比较头部和中间？
     * 或者说为什么头部和中间的比较可以达到二分性，每次排除一半？因为每次只能排除有序的那一半
     */
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] < nums[r]){  // 如果右边有序，那么一定不在右边（不包括nums[l])
                r = m;
            } else if (nums[m] == nums[r]){ // [3, 1, 2, 3, 3, 3, 3], 这种情况无法判断，只能去掉一个，重新定位中间值
                r -= 1;
            } else {  // 如果右边无序，那么最小值一定在右边，排除左边
                l = m + 1;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArrayII inst = new FindMinimuminRotatedSortedArrayII();
        System.out.println(inst.findMin(new int[]{1,3,5}));
        System.out.println(inst.findMin(new int[]{1}));
        System.out.println(inst.findMin(new int[]{2,2,2,0,1}));
        System.out.println(inst.findMin(new int[]{3, 1, 2, 3, 3, 3, 3}));

    }
}

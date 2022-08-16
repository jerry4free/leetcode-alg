package BinarySearch;

/**
 * 35. Search Insert Position
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        // 左闭右闭，相错终止。
        while (l <= r){
            int m = l + (r - l) / 2;
            if (nums[m] == target){
                return m;
            } else if (nums[m] < target){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // 循环结束后，r在l左边，而且有个性质：l左边都比target小，r右边都比target大
        // 而且targer没找到，那么要插入的位置一定是l
        return l;
    }

    public static void main(String[] args) {
        SearchInsertPosition inst = new SearchInsertPosition();
        System.out.println(inst.searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(inst.searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(inst.searchInsert(new int[]{1,3,5,6}, 7));
        System.out.println(inst.searchInsert(new int[]{1,3,5,6}, 0));
    }
}

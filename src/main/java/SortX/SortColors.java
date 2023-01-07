package SortX;

/**
 * 75. Sort Colors
 */
public class SortColors {

    public void sortColors(int[] nums) {
        // 循环不变量的定义
        // [0, lt] == 0，gt是被看到过的
        // (lt, i) == 1
        // [gt, len-1] == 2，gt是被看到过的
        int lt = -1;
        int i = 0;
        int gt = nums.length;
        while (i < gt){  // 由于gt是被看到过的，所以i严格小于gt即可
            if (nums[i] == 1){
                i++;
            } else if (nums[i] == 0){
                swap(nums, i, ++lt);
                i++;  // 为什么此时i要自增? 因为交换回来的元素已经看过了,所以此时i要自增
            } else {
                swap(nums, i, --gt);
                // 交换后的那个元素还不知道是什么,所以此时i不增自增
            }
        }
    }


    public void sortColors2(int[] nums) {
        // 循环不变量的定义，注意这三个变量的边界：
        // [0, lt) == 0，lt是没看过的
        // [lt, i) == 1，
        // (gt, len-1] == 2，gt是没看过的
        int lt = 0;
        int i = 0;
        int gt = nums.length - 1;
        while (i <= gt){ //注意等于i==gt时，gt是没被看过的
            if (nums[i] == 1){
                i++;
            } else if (nums[i] == 0){
                swap(nums, i, lt);
                lt++;
                i++;  // 为什么此时i要自增? 因为交换回来的元素已经看过了,所以此时i要自增
            } else {
                swap(nums, i, gt);
                gt--;
                // 交换后的那个元素还不知道是什么,所以此时i不增自增
            }
        }
    }

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void show(int[] nums){
        for (int i: nums){
            System.out.print(i + ",");
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        SortColors inst = new SortColors();
        inst.sortColors(new int[]{2,0,2,1,1,0});
//        inst.sortColors(new int[]{2,0,1});
//        inst.sortColors(new int[]{1,1,2,0,1,1});
//        inst.sortColors(new int[]{1});
//        inst.sortColors(new int[]{0});
//        inst.sortColors(new int[]{2});
//        inst.sortColors(new int[]{2,1});
//        inst.sortColors(new int[]{1,1});
//        inst.sortColors(new int[]{0,0,0});
    }
}

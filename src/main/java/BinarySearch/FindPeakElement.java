package BinarySearch;

/**
 * 162. Find Peak Element
 */
public class FindPeakElement {
    // 每次比较m和m+1
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int max = r;
        // 直到左右交错
        while (l <= r){
            int m = l + (r - l) / 2;
            if (m == max){ // 走到最右侧，说明m是峰值，此时
                return r;
            } else {
                if (nums[m] < nums[m + 1]){ // 上坡, 往右走
                    l = m + 1;
                } else { // 下坡，往左走
                    r = m - 1;
                }
            }
        }
        return l;
    }


    // 自己写出来的第一版，比较m和左右2个
    public int findPeakElement2(int[] nums) {
        if (nums.length == 1){
            return 0;
        }
        int l = 0;
        int r = nums.length - 1;
        int max = nums.length - 1;
        /**
         * 画图看性质，
         * 由于每个数跟左右都不想等，且num[-1], nums[n]是负无穷，那么
         * 看m跟左右相比是处于哪种状态，分别有：
         * 1。升升，上坡：那么峰值一定在右侧
         * 2。降降，下坡：峰值一定在左侧
         * 3。升降，山峰：（峰值）、
         * 4。降升，山谷：左右都可以
         */
        while (l <= r){
            int m = l + (r - l) / 2;

            if (m == 0){ // 如果m到最左侧
                if (nums[m] > nums[m + 1]){
                    return m;
                } else {
                    l = m + 1;
                }
            } else if (m == max){  // 如果m到最右侧
                if (nums[m] > nums[m - 1]){
                    return m;
                } else {
                    r = m - 1;
                }
            } else { // m左右都有值
                if (nums[m - 1] < nums[m] && nums[m] < nums[m + 1]){
                    l = m + 1;
                } else if (nums[m - 1] > nums[m] && nums[m] > nums[m + 1]) {
                    r = m - 1;
                } else if (nums[m - 1] > nums[m] && nums[m] < nums[m + 1]) {
                    l = m + 1;
                } else {
                    return m;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        FindPeakElement inst = new FindPeakElement();
        System.out.println(inst.findPeakElement(new int[]{1,2,3,1}));
        System.out.println(inst.findPeakElement(new int[]{1}));
        System.out.println(inst.findPeakElement(new int[]{1,2,1,2,1}));
        System.out.println(inst.findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }
}

package BinarySearch;

/**
 * 540
 */
public class SingleNonDuplicate {

    /**
     *  由于一定有一个数字只有一个，那么数组长度是奇数个
     *  通过画图可以知道, 偶数下标和之后的下标相等时，说明该下标及以前是不包含这个数字，每次可以通过中点下标来排除一半元素。
     *  所以可以通过二分查找的方法
     *  又每次都是偶数跟其之后下标比较，所以可以只算偶数下标，那么所有下标/2，对于每一个i，它对应的要比较的下标是2i,2i+1
     *
     *  总结技巧：
     *  1 一定要在纸上画一画，列出下标和中间状态，帮助梳理逻辑关系
     *  2 列举不同case，分别验证下
     *
     *  */
    public int singleNonDuplicate2(int[] nums) {
        int l = 0;
        int len = nums.length;

        int r = (len - 1) / 2;
        while (l <= r){
            int m = l + (r - l) / 2;

            // 如果要比较的mid标的下一个已经越界，那么就是当前mid*2就是
            if (2 * m + 1 > len - 1){
                return nums[2 * m];
            }

            if (nums[2 * m] == nums[2 * m + 1]){  // 左侧正常
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return nums[l * 2];
    }

    /**
     * 如下参考官方的做法
     * 对于偶数下标i，是跟i+1判断；对于奇数下标i，是跟i-1判断。这2种case如何整合到一起呢？或者说如何将第2种整合到第一种呢？
     *
     * 对于奇数i，i & 1 = 1; 对于偶数i, i & 1 = 0, 所以奇数-1=偶数，偶数=0还是本身
     *
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        int r = h;
        while (l <= r){
            int m = l + (r - l) / 2;
            m -= m & 1; // 对齐到偶数下标
            if (m == h){  // 由于采用的是l <= r的循环条件，所以mid可能等于r，mid+1可能越界
                return nums[m];
            } else if (nums[m] == nums[m+1]){
                l = m + 2;
            } else {
                r = m - 1;
            }
        }
        return nums[l];
    }



    public static void main(String[] args) {
        SingleNonDuplicate inst = new SingleNonDuplicate();
        System.out.println(inst.singleNonDuplicate(new int[]{11, 11, 10}) + ", expect: 10");
        System.out.println(inst.singleNonDuplicate(new int[]{11, 10, 10}) + ", expect: 11");
        System.out.println(inst.singleNonDuplicate(new int[]{11}) + ", expect: 11");

        System.out.println(inst.singleNonDuplicate(new int[]{3,3,7,7,10,11,11}) + ", expect: 10");
        System.out.println(inst.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}) + ", expect: 2");
    }
}

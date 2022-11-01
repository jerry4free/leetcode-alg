package BinarySearch;

/**
 * 540
 */
public class SingleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int len = nums.length;

        int r = (len - 1) / 2;
        while (l <= r){
            int m = l + (r - l) / 2;

            // 如果中点的下一个已经越界，那么就是当前节点
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

    public static void main(String[] args) {
        SingleNonDuplicate inst = new SingleNonDuplicate();
        System.out.println(inst.singleNonDuplicate(new int[]{11, 11, 10}) + ", expect: 10");
        System.out.println(inst.singleNonDuplicate(new int[]{11, 10, 10}) + ", expect: 11");
        System.out.println(inst.singleNonDuplicate(new int[]{11}) + ", expect: 11");

        System.out.println(inst.singleNonDuplicate(new int[]{3,3,7,7,10,11,11}) + ", expect: 10");
        System.out.println(inst.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}) + ", expect: 2");
    }
}

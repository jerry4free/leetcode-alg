package BinarySearch;

public class BinarySearch {

    /**
     * 左闭右开: l = 0; r = len;
     * 想等终止：while (l < r)
     */
    public int search2(int[] nums, int k){
        // loop invariant: 在循环的过程中保持不变的性质
        // 循环不变量的定义
        int l = 0;  // 初始化时是左闭
        int r = nums.length; // 初始化时右开，但不妨问r
        while (l < r) {  // 假如只有1个元素，必须要访问1次，所以是小于
            int m = l + (r - l) / 2; // 向下取整

            if (nums[m] == k){
                return m;
            } else if (nums[m] < k){
                l = m + 1;  // 左闭
            } else {
//                r = m - 1;
                r = m; // TODO:此处必须是m，循环执行过程中也必须是右开
            }
        }

        return -1;
    }

    public int search(int[] nums, int k){
        // 循环不变量的定义
        int l = 0;  // 左闭
        int r = nums.length - 1; // 右闭

        while (l <= r){ //假如只有1个元素，则必须要访问1次，必须是<=
            int m = l + (r - l) / 2;
            if (nums[m] == k){
                return m;
            } else if (nums[m] > k){
                r = m - 1; // 左闭
            } else {
                l = m + 1; // 左闭
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BinarySearch inst = new BinarySearch();
        System.out.println("result:" + inst.search(new int[]{1,2}, 2) + ", expected: 1");
        System.out.println("result:" + inst.search(new int[]{1,2}, 3) + ", expected: -1");
        System.out.println("result:" + inst.search(new int[]{1,2,3}, 1) + ", expected: 0");
    }
}

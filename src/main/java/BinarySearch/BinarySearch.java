package BinarySearch;

public class BinarySearch {

    /**
     * 左闭右开: l = 0; r = len;
     * 想等终止：while (l < r)
     */
    public int search(int[] nums, int k){
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == k){
                return m;
            } else if (nums[m] < k){
                l = m + 1;
            } else {
//                r = m - 1;
                r = m; // TODO:此处必须是m，为什么？
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

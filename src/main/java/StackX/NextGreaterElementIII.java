package StackX;


/**
 * 556. 下一个更大元素 III
 */
public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        // 比如要找2467531
        char[] nums = String.valueOf(n).toCharArray();
        int len = nums.length;
        int i, j;
        // 倒序遍历，找到第一个小于后面的数字（4）的下标i
        for (i = len - 2; i >= 0 && nums[i] >= nums[i+1]; i--){
        }
        // 如果n从高位到低位是递减的,则找不到
        if (i < 0){
            return -1;
        }

        // 倒序遍历，找到第一个大于4的数字（5）的下标j
        for (j = len - 1; j > i && nums[j] <= nums[i]; j--){
        }

        // 交换nums[i]和nums[j], 即：4和5
        swap(nums, i, j);

        // 在对i+1到len-1的这段进行倒序,因为这段是76421
        reverse(nums,i+1, len-1);

        // 注意下一个数字转换成int可能越界，所以用long存储
        long ans = Long.parseLong(new String(nums));
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    private void reverse(char[] nums, int l, int r){
        for (int i = l, j = r; i < j; i++,j--){
            swap(nums, i, j);
        }
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    void show(char[] nums){
        for (char c: nums){
            System.out.print(c + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NextGreaterElementIII inst = new NextGreaterElementIII();
//        System.out.println(inst.nextGreaterElement(2147483476));
        System.out.println(inst.nextGreaterElement(12443322));
    }
}

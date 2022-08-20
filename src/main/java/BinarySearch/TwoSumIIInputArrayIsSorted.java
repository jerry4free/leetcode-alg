package BinarySearch;


/**
 * 167. Two Sum II - Input Array Is Sorted
 */
public class TwoSumIIInputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        // 比较3个值，nums[l] + nums[m], nums[l] + nums[r], nums[m] + nums[r]
        // 一共7个区间
        while (l <= r){
            int m = l + (r - l) / 2;
            if (target > numbers[m] + numbers[r]) {  // 排除右侧
                l = m + 1;
            } else if (target < numbers[l] + numbers[m]){  // 排除左侧
                r = m - 1;
            } else if (target < numbers[l] + numbers[r]) {
                r--;
            } else if (target > numbers[l] + numbers[r]) {
                l++;
            } else if (target == numbers[m] + numbers[r]) {
                return new int[]{m+1, r+1};
            } else if (target == numbers[m] + numbers[l]) {
                return new int[]{l+1, m+1};
            } else {
                return new int[]{l+1, r+1};
            }
        }
        return new int[]{-1, -1};
    }


    // 下面是我第一次写的，二分查找和双指针结合，但是结合的方式不是很好。
    // 能排除一半时，可以二分；不能排除时就是双指针，顺序遍历了
    public int[] twoSum2(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (target == numbers[m] + numbers[r]){
                return new int[]{m+1, r+1};
            } else if (target > numbers[m] + numbers[r]) {  // 排除右侧
                l = m + 1;
            } else if (target == numbers[l] + numbers[m]){
                return new int[]{l+1, m+1};
            } else if (target < numbers[l] + numbers[m]){  // 排除左侧
                r = m - 1;
            } else {
                return find(numbers, target, l, r);
            }
        }
        return new int[]{-1, -1};
    }

    // 双指针
    public int[] find(int[] nums, int t, int start, int end){
        int i = start;
        int j = end;
        while (i < j){
            if (nums[i] + nums[j] == t){
                return new int[]{i+1, j+1};
            } else if (nums[i] + nums[j] < t){
                i++;
            } else {
                j--;
            }
        }
        return new int[]{-1, -1};
    }

    static void show(int[] nums){
        for (int n : nums){
            System.out.print(n + ",");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        TwoSumIIInputArrayIsSorted inst = new TwoSumIIInputArrayIsSorted();
        show(inst.twoSum(new int[]{2,7,11,15}, 9));
        show(inst.twoSum(new int[]{2,7,11,15}, 18));
        show(inst.twoSum(new int[]{2,7,11,15}, 17));
        show(inst.twoSum(new int[]{-1,0}, -1));
        // not exists
        show(inst.twoSum(new int[]{-1,0}, -2));
    }
}

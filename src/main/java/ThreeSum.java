import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            // skip duplicated
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int target = -nums[i];
            int j = i + 1;
            int k = length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    List<Integer> found = new ArrayList<>();
                    found.add(nums[i]);
                    found.add(nums[j]);
                    found.add(nums[k]);
                    ret.add(found);
                    // important! got it , then keep finding
                    j++;
                    k--;
                    // if duplicated, then keep skipping
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (nums[j] + nums[k] < target){
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                } else {
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args){
        ThreeSum inst = new ThreeSum();
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        System.out.println(inst.threeSum(nums));
        int[] nums1 = new int[]{-2,0,0,2,2};
        System.out.println(inst.threeSum(nums1));
        int[] nums2 = new int[]{-2,0,0,0,0,2,2,2,2};
        System.out.println(inst.threeSum(nums2));
        int[] nums3 = new int[]{0,0,0};
        System.out.println(inst.threeSum(nums3));
    }
}

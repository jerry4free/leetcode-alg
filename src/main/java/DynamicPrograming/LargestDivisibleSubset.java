package DynamicPrograming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 368 最大整除子集
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
//        Arrays.sort(nums);
//        HashMap<Integer, List<Integer>> dp = new HashMap<>();
//        int[] dp = new int[n+1];

        // 假设f(i)表示前i个正数的最大子集
        // 那么对于f(i) = f(i-1) + nums[i-1], nums[i-1] % nums[i-2] == 0
        for (int i = 1; i < n; i++){

        }


    }

}

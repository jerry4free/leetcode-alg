package DynamicPrograming;

import Util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 368 最大整除子集
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // 假设f(i)表示前i个正数的最大子集的个数, f1(i)表示
        // 那么对于nums[i]，
        // f(i) = max{ f(j) + 1, if nums[i-1] % nums[j] == 0},  1 <= j < i
        int[] f = new int[n];
        Arrays.fill(f,1);

        int[] f1 = new int[n];
        for (int i = 0; i < n; i++){
            f1[i] = nums[i];
        }

        int maxLength = 1; // 最长子集的长度
        int maxValue = nums[0];  // 最长子集的最大值
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] % nums[j] == 0 ){
                    f[i] = Math.max(f[i], f[j] + 1);
                    if (f[i] > maxLength){
                        maxLength = f[i];
                        maxValue = nums[i];
                    }
                }
            }
        }
//        Utils.show(f);
//        Utils.show(f1);

        List<Integer> ret = new ArrayList<>();
        // maxValue就是最长子集中的最大倍数
        for (int i = n-1; i >= 0; i--){
            if (maxValue % nums[i] == 0 && f[i] == maxLength){ // 注意还需要判断i对应的状态：即子集长度
                ret.add(nums[i]);
                maxValue = nums[i];  // 随着子集减小，更新子集的最大值
                maxLength--;
            }
        }

        return ret;
    }

    private static void show(List<Integer> l){
        for(Integer i: l){
            System.out.print(i + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        LargestDivisibleSubset inst = new LargestDivisibleSubset();
        show(inst.largestDivisibleSubset(new int[]{1,2,3}));
        show(inst.largestDivisibleSubset(new int[]{1,2,4,8}));
        show(inst.largestDivisibleSubset(new int[]{3,4,16,8}));
        show(inst.largestDivisibleSubset(new int[]{4,8,10,240}));
        show(inst.largestDivisibleSubset(new int[]{1}));
        show(inst.largestDivisibleSubset(new int[]{5,9,18,54,108,540,90,180,360,720}));
    }

}

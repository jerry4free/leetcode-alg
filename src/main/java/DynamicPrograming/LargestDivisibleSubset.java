package DynamicPrograming;

import Util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368 最大整除子集
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // 由于除法具有传递性，所以将nums升序排列后，可以找到某子集结尾数，就是该子集的最大整数, 不用再往前找了，这样就可以状态转移
        // 假设f(i)表示前i个正数，且以nums[i]为最大整数为结尾的整除子集的长度
        // 那么对于nums[i]，
        // f(i) = max{ f(j) + 1, if nums[i-1] % nums[j] == 0},  1 <= j < i
        int[] f = new int[n];
        Arrays.fill(f,1);

        int[] f1 = new int[n];
        for (int i = 0; i < n; i++){
            f1[i] = nums[i];
        }

        int maxLength = 1; // 最长子集的长度
        int maxValue = nums[0];  // 最长子集的最大值，用来倒推取子集需要
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){  // 可能存在多个子集，所以需要遍历所有i之前的状态
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
        // 倒推取最长子集
        for (int i = n-1; i >= 0; i--){
            if (maxValue % nums[i] == 0 && f[i] == maxLength){ // 注意还需要判断i对应的状态：即子集长度，否则可能找到其他不是最长子集的元素上
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

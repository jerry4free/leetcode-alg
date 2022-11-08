package BackTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

/**
 * 47, 含有重复元素的全排列
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        HashMap<Integer, Boolean> onStack = new HashMap<>();
        List<Integer> path = new ArrayList<>();

        // 由于nums有重复，所以用下标作为索引
        for (int i = 0; i < nums.length; i++){
            onStack.put(i, false);
        }

        Arrays.sort(nums);

        dfs(onStack, ret, path, nums);

        return ret;
    }

    private void dfs(HashMap<Integer, Boolean> onStack,
                     List<List<Integer>> ret,
                     List<Integer> path,
                     int[] nums
    ) {
        if (path.size() == nums.length){
            ret.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (!onStack.get(i)){
                // 如果不在栈上的同一层，有与当前元素相等的下标，那么忽略，避免重复
                if (i > 0 && nums[i] == nums[i-1] && !onStack.get(i-1)) {
                    continue;
                }
                onStack.put(i, true);
                path.add(nums[i]);

                dfs(onStack, ret, path, nums);

                path.remove(path.size()-1);
                onStack.put(i, false);
            }
        }
    }

    public static void show(List<Integer> path){
        System.out.print("path:");
        for(Integer n: path){
            System.out.print(n + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PermutationsII inst = new PermutationsII();
        List<List<Integer>> ret = inst.permuteUnique(new int[]{1,1,2});
        for (List<Integer> l: ret){
            show(l);
        }
    }
}

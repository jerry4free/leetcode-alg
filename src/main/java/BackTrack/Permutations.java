package BackTrack;

import java.util.*;

/**
 * 46. 全排列
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        HashMap<Integer, Boolean> onStack = new HashMap<>();
        for (int n: nums){
            onStack.put(n, false);
        }
        dfs(nums, onStack, ans, path);

        return ans;
    }

    private void dfs(int[] nums, HashMap<Integer, Boolean> onStack, List<List<Integer>> ans, List<Integer> path){
        int l = nums.length;

        if (path.size() == l){
            ans.add(new ArrayList<>(path));
            return;
        }

        // 由于是全排列，所以元素顺序不一样都算。当第一次选3，下一次可能选择3前面的2。所以每次循环里都需要从0开始算。
        for (int i = 0; i < l; i++){
            if (!onStack.get(nums[i])){
                path.add(nums[i]);
                onStack.put(nums[i], true);

                dfs(nums, onStack, ans, path);

                onStack.put(nums[i], false);
                path.remove(path.size()-1);
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
        Permutations inst = new Permutations();
        List<List<Integer>> ret = inst.permute(new int[]{1,2,3});
        for(List<Integer> l : ret){
            show(l);
        }
    }
}

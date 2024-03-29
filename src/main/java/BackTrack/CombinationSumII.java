package BackTrack;

import Util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * 40. 组合总和 II
 */
public class CombinationSumII {

    // 多叉数
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);

        dfs(0, target, candidates, path, ans);
        return ans;
    }

    public static void show(List<Integer> path){
        System.out.print("path:");
        for(Integer n: path){
            System.out.print(n + ",");
        }
        System.out.println();
    }

    private void dfs(int start, int target, int[] candidates, List<Integer> path, List<List<Integer>> ans){
        if (target == 0){
            ans.add(new ArrayList<>(path));
            return;
        } else if (start >= candidates.length || target < 0){
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝：避免重复，同一层时的相同元素只取第1个。因为后续的相同元素已经在第一个中考虑过了
            if (i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            // 剪枝：如果后续候选元素超过target，直接就跳出循环
            if (target < candidates[i]){
                break;
            }
            path.add(candidates[i]);
            // 不能重复使用，所以是每次下标得+1
            dfs(i + 1, target - candidates[i], candidates, path, ans);
            path.remove(path.size() - 1);
        }
    }


    // 二叉树
    /*
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);

        _dfs(0, target, candidates, path, ans);
        return ans;
    }

    private void _dfs(int start, int target, int[] candidates, List<Integer> path, List<List<Integer>> ans) {
        if (target == 0){
            ans.add(new ArrayList<>(path));
            return;
        } else if (start >= candidates.length || target < 0){
            return;
        }

        // 不加
        dfs(start + 1, target, candidates, path, ans);

        // 加
        path.add(candidates[start]);
        // 不能重复使用，所以是每次下标得+1
        dfs(start + 1, target - candidates[start], candidates, path, ans);
        path.remove(path.size() - 1);
    }
     */

    public static void main(String[] args) {
        CombinationSumII inst = new CombinationSumII();
        List<List<Integer>> ret = inst.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        for(List<Integer> l : ret){
            show(l);
        }
    }
}

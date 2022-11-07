package BackTrack;

import Util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * 40. 组合总和 II
 */
public class CombinationSumII {

    // TODO: finish
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        Utils.show(candidates);

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
        show(path);
        System.out.println("---start:" + start + ",target:" + target);
        if (target == 0){
            System.out.print("got one, ");
            show(path);
            ans.add(new ArrayList<>(path));
            return;
        } else if (start >= candidates.length || target < 0){
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            if (target < candidates[i]){
                break;
            }
//            if (path.size() > 0 && candidates[i] < path.get(path.size()-1)){
//                System.out.println(candidates[i] + "," + path.get(path.size()-1));
//            }
            path.add(candidates[i]);
//            System.out.println("push stack:" + candidates[i]);
            // 不能重复使用，所以是每次下标得+1
            dfs(start + 1, target - candidates[i], candidates, path, ans);
//            System.out.println("pop stack:" + candidates[i]);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumII inst = new CombinationSumII();
        List<List<Integer>> ret = inst.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        for(List<Integer> l : ret){
            show(l);
        }
    }
}

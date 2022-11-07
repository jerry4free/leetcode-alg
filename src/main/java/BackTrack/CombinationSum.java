package BackTrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 39 组合总和
 */
public class CombinationSum {
    Deque<Integer> path;
    List<List<Integer>> ans;

    // 多叉树的DFS
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        path = new ArrayDeque<>();
        // 为了更好的避免结果组合的重复, 将其排序
        Arrays.sort(candidates);

        dfs(0, 0, candidates, target);

        return ans;
    }


    private void dfs(int start, int sum, int[] candidates, int target){
        // 如果当前路径的和大于target,则忽略
        if (sum > target){
            return;
        } else if (sum == target){
            ans.add(new ArrayList<>(path));
        }

        for (int i = start; i < candidates.length; i++){
            // 剪枝: 如果下一层的元素,大于栈顶的元素,
            // 针对重复的组合：[2,3,3], [3,2,2]，
            // 在进行3的子树遍历时，它的子节点不应该再考虑2。因为2作为第1个子节点时，已经将考虑过包含2的组合了
            if (!path.isEmpty() && candidates[i] < path.peek()){
                break;
            }
            // 将当前节点压栈
            path.push(candidates[i]);
            // 将当前节点加到总和, 调用下一层
            dfs(start, sum + candidates[i], candidates, target);
            // 出栈
            path.pop();
        }
    }

    // 二叉树的DFS
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        path = new ArrayDeque<>();

        _dfs(target, candidates, 0);

        return ans;
    }

    // 每一步都有2个选择，使用当前节点，或者不使用跳过去，搜索所有解的过程类似于一个二叉树
    private void _dfs(int target, int[] candidates, int idx){
        if (target == 0){ // 符合条件
            ans.add(new ArrayList<>(path));
            return;
        } else if (idx > candidates.length || target < 0){  // 超过数组长度，到达叶子节点
            return;
        }

        // 加: 加上当前idx，将当前下标元素加到组合中
        path.push(candidates[idx]);
        _dfs(target - candidates[idx], candidates, idx);
        path.pop();

        // 不加，跳过当前idx
        _dfs(target, candidates, idx + 1);
    }
}

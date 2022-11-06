package BackTrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 77. Combinations
 */
public class Combinations {

    /**
     * 时间复杂度是O(C(n,k))，空间复杂度是O(k)，因为递归的隐式的栈深和显式的path栈深都是k，
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(k, n, new ArrayDeque<>(), ans);

        return ans;
    }

    private void dfs(int k, int level, Deque<Integer> path, List<List<Integer>> ans){
        // 当前栈的节点加上剩余节点,不足k时,直接返回
        if (path.size() + level < k){
            return;
        } else if (path.size() == k){
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = level; i > 0; i--){
            // 调用前,将当前节点入栈
            path.push(i);
            // 因为题目要求算的组合，所以path这个栈里的数字i都是被选择过不能再用，所以下一层从i-1开始
            dfs(k, i-1, path, ans);
            // 调用完后,再出栈
            path.pop();
        }
    }


    /**
     *  DFS（二叉树）
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs2(k, n, new ArrayDeque<>(), ans);

        return ans;
    }

    private void dfs2(int k, int curr, Deque<Integer> path, List<List<Integer>> ans){
        if (path.size() + curr < k){
            return;
        } else if (path.size() == k){
            ans.add(new ArrayList<>(path));
            return;
        }

        // 加
        path.push(curr);
        dfs2(k, curr - 1, path, ans);
        path.pop();

        // 不加
        dfs2(k, curr - 1, path, ans);
    }

}

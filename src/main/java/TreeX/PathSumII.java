package TreeX;

import BinarySearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II
 */
public class PathSumII {


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new ArrayList<>();

        dfs(root, targetSum, new ArrayList<>(), ret);

        return ret;
    }

    private void dfs(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> ret) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) { // 叶子节点
            if (root.val == targetSum) {
                path.add(root.val);
                ret.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }

        // 非叶子节点
        path.add(root.val);
        dfs(root.left, targetSum - root.val, path, ret);
        dfs(root.right, targetSum - root.val, path, ret);
        path.remove(path.size() - 1);

    }
}

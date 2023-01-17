package TreeX;

import BinarySearch.TreeNode;

/**
 * 112 Path Sum
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }

        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int target){
        // base case注意点：
        // 1。target和节点值都可能是负数
        // 2。左右节点不同时为null的情况
        if (root.left == null &&
                root.right == null){  // 叶子节点
            return root.val == target;
        }

        int t = target - root.val;

        boolean ret = false;
        if (root.left != null){ //进入前需要判空
            ret |= dfs(root.left, t);
        }
        if (root.right != null){
            ret |= dfs(root.right, t);
        }

        return ret;
    }
}

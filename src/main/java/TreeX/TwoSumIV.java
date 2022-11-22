package TreeX;

import BinarySearch.TreeNode;
import java.util.HashSet;

/**
 * 653. Two Sum IV - Input is a BST
 */
public class TwoSumIV {
    HashSet<Integer> set;
    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        return dfs(root, k);
    }

    private boolean dfs(TreeNode root, int k){
        if (root == null){
            return false;
        }
        boolean leftExist = dfs(root.left, k);

        if (set.contains(k - root.val)){
            return true;
        }
        set.add(root.val);

        boolean rightExist = dfs(root.right, k);
        return leftExist || rightExist;
    }
}

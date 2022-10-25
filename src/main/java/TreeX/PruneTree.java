package TreeX;

import BinarySearch.TreeNode;

/**
 * 814. 二叉树剪枝
 */
public class PruneTree {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null){
            return null;
        }
        // 由于需要从下至上的去减枝,所以采用后序遍历
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0){
            return null;
        } else {
            return root;
        }
    }
}

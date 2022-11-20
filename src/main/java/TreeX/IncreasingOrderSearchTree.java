package TreeX;

import BinarySearch.TreeNode;

/**
 * 897. 递增顺序搜索树
 */
public class IncreasingOrderSearchTree {
    // 用一个类变量，在递归地进行中序遍历过程中，可以方便的将前一个节点保存下来
    TreeNode prev;

    /**
     * 中序遍历二叉树
     */
    public TreeNode increasingBST(TreeNode root) {
        // 哨兵节点，转成一维的链表时，链表头可能变动，方便统一边界case
        TreeNode dummy = new TreeNode(-1);
        prev = dummy;
        dfs(root);
        return dummy.right;
    }

    private void dfs(TreeNode root){
        if (root == null){
            return;
        }

        dfs(root.left);

        prev.right = root;
        root.left = null;  // 注意将当前节点的left置null
        prev = root;

        dfs(root.right);
    }
}

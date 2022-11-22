package TreeX;

import BinarySearch.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 * 285
 */
public class InorderSuccessor {

    /**
     * 迭代版本的中序遍历
     * 时间复杂度是O(N)，空间复杂度是O(H)
     */
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode prev = null;

        // 如果栈中的节点不为空
        // 由于每次先入栈的左子树，然后入栈的是右子树。所以弹出时也是先左子树,最后是右子树
        while (!stack.isEmpty() || curr != null){

            // 1. 每次循环时, 一直将当前节点的左子树入栈.
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }

            // 2. 弹出栈顶
            curr = stack.pop();
            // 如果之前节点是，则返回
            if (prev != null && prev.val == p.val){
                return curr;
            }

            // 3. curr指向前元素的右子树的根节点
            prev = curr;
            curr = curr.right;
        }

        return null;
    }

    /**
     * 利用二叉搜索树的性质，
     *
     * 对于每个节点来说，左子树小于当前节点值，右子树大于当前节点值
     *
     * 所以从根节点开始，
     * 1。当前节点值小于等于目标值p，则p的下一个值一定在当前节点的右子树，
     * 2。当前节点值大于目标值p，则p的下一个值可能是当前节点值，也可能是在当前节点的左子树里
     *
     * 由于只会遍历树的深度，所以时间复杂度是：O(h)，空间复杂度是O(1)
     *
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode curr = root;
        TreeNode found = null;
        while (curr != null){
            if (curr.val <= p.val){  // 小于等于目标值，则下一个值一定在右子树
                curr = curr.right;
            } else {  // 大于目标值，则可能是当前节点值，记录下来；同时在遍历左子树，因为左子树可能存在比当前值还小但是仍大于p的节点
                found = curr;
                curr = curr.left;
            }
        }

        return found;
    }


    /**
     * 中序遍历的递归版本
     */
    private TreeNode next;
    private boolean visited;
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        next = null;
        visited = false;
        dfs(root, p);

        return next;
    }

    private void dfs(TreeNode root, TreeNode p){
        if (root == null){
            return;
        }

        dfs(root.left, p);

        if (visited){
            next = root;
            visited = false;
        }
        if (root.val == p.val){
            visited = true;
        }

        if (next == null){
            dfs(root.right, p);
        }
    }
}

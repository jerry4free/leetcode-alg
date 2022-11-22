package TreeX;
import BinarySearch.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 173. Binary Search Tree Iterator
 */
public class BSTIterator {

    /**
     * 用栈来实现二叉树的迭代版本的中序遍历
     * 时间复杂度比较难分析，因为hasNext明显是O(1), next()每次会弹出一次栈，但是会压入多次栈，均摊下来是不是O(1)呢？
     * 虽然迭代的过程中对于某个节点的next()只会调用出栈（因为没有右节点），有的节点会出栈一次入栈多次，
     * 但是整个树迭代完，对于每个节点都会入栈出栈各1次，所以均摊下来是O(1)
     */
    private Deque<TreeNode> stack ;

    public BSTIterator(TreeNode root) {
        // 初始化将所有root的左子树加入到队列中
        stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null){
            stack.push(curr);
            curr = curr.left;
        }
    }

    public int next() {
        // 栈顶
        TreeNode node = stack.pop();
        // 遍历当前节点的右子树
        TreeNode curr = node.right;
        // 所有右子树（及其右子树的左节点）加入到栈中
        while (curr != null){
            stack.push(curr);
            curr = curr.left;
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
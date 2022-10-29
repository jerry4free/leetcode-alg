package QueueX;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 919
 */
public class CBTInserter {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    List<TreeNode> l;

    // TODO: List和Queue可以只保留一个，本质上都是用队列来实现
    public CBTInserter(TreeNode root) {
        l = new ArrayList<>();
        l.add(null);
        if (root != null){
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.remove();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
                l.add(node);
            }
        }
    }

    public int insert(int v) {
        TreeNode node = new TreeNode(v);
        l.add(node);
        // 注意下标0没用，所以获取root时，先去掉1，那么对于k来说，父节点是k/2
        // 左右子节点分别是2k，2k+1
        int n = l.size() - 1;
        TreeNode root = l.get(n / 2);
        if (n % 2 == 1){  // 去除第0个，如果是奇数则是右节点
            root.right = node;
        } else {
            root.left = node;
        }
        return root.val;
    }

    public TreeNode get_root() {
        return l.get(1);
    }
}

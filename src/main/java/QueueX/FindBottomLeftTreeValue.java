package QueueX;

import BinarySearch.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. 找树左下角的值
 */
public class FindBottomLeftTreeValue {

    int currHeight;
    int ret;
    // DFS
    public int findBottomLeftValue(TreeNode root) {
        ret = 0;
        currHeight = -1;
        dfs(root, 0);
        return ret;
    }

    private void dfs(TreeNode node, int height){
        if (node == null){
            return;
        }
        if (height > currHeight){
            ret = node.val;
            currHeight = height;
        }
        dfs(node.left, height + 1);
        dfs(node.right, height + 1);
    }

    // BFS
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int ret = 0;
        while (!q.isEmpty()){
            int n = q.size();
            for (int i = 0; i < n; i++){
                TreeNode node = q.remove();
                if (i == 0){
                    ret = node.val;
                }
                if (node.left != null){
                    q.add(node.left);
                }
                if (node.right != null){
                    q.add(node.right);
                }
            }
        }
        return ret;
    }
}

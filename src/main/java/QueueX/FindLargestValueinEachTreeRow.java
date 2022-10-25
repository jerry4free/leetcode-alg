package QueueX;

import BinarySearch.TreeNode;

import java.util.*;

/**
 * 515. Find Largest Value in Each Tree Row
 */
public class FindLargestValueinEachTreeRow {

    /**
     * DFS:深度优选搜索
     * 先序遍历
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, ret, 0);
        return ret;
    }
    private void dfs(TreeNode node, List<Integer> ret, int height){
        if (node == null){
            return;
        }
        // 采用先序遍历, 先当前节点，再子节点,
        // 因为结果是由上到下的层级结果，所以采用先顺遍历，即：当前节点优先。采用后序或中序回导致ret中并不是从上到下的顺序
        if ((ret.size() - 1) < height){
            ret.add(node.val);
        } else if (node.val > ret.get(height)){
            ret.set(height, node.val);
        }
        dfs(node.left, ret, height + 1);
        dfs(node.right, ret, height + 1);
    }

    /**
     * BFS:使用队列对二叉树进行层级遍历。
     * 每次遍历这一层时，先保存队列中的节点个数，就是这一层的节点个数，同时遍历完这一层。
     * 遍历完，队列中的节点就是下一层的节点
     */
    public List<Integer> largestValues2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();
        if (root == null){
            return ret;
        }
        q.add(root);
        while (!q.isEmpty()){
            // 遍历之前记录这一层的节点个数, 就是队列中的个数,
            // 然后个数递减直到0,就是遍历完这一层了.
            // 同时将下一层的节点加入到队列尾部
            Integer currMax = Integer.MIN_VALUE;
            for (int len = q.size(); len > 0; len--){
                TreeNode node = q.remove();
                currMax = Math.max(node.val, currMax);
                if (node.left != null){
                    q.add(node.left);
                }
                if (node.right != null){
                    q.add(node.right);
                }
            }
            // 遍历完，队列中的节点就是下一层
            ret.add(currMax);
        }
        return ret;
    }

}

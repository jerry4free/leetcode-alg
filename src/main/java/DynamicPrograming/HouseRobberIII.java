package DynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 *
 */
public class HouseRobberIII {

    /**
     *
     * 每个节点有2种状态：f表示被选中，g表示不被选中
     * 1. 当节点n被选中时，能偷到的最大值是, f(n) = g(l) + g(r)
     * 2. 当节点n不被选中时，能偷到的最大值时：g(n) = max(g(l), f(l)) + max(g(r), f(r))。
     * 即：爷爷不被偷时，儿子可以不被偷，或者被偷，取决于哪个最大
     *
     * 可以看到状态转移方程调用了多次子函数，即存在重叠子问题，所以可以通过记忆化来避免递归调用
     * 可以自顶向下的方法，先遍历2个子节点再处理当前节点，采用后序的方式遍历
     */
    Map<TreeNode, Integer> f = new HashMap<>();
    Map<TreeNode, Integer> g = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);

        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node) {
        if (node == null){
            return;
        }

        // 先递归处理子节点
        dfs(node.left);
        dfs(node.right);

        // 然后处理当前节点
        // node被选中，更新缓存
        int status1 = g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0) + node.val;
        f.put(node, status1);

        // node不被选择
        int status2 = Math.max(g.getOrDefault(node.left, 0), f.getOrDefault(node.left, 0))
                + Math.max(g.getOrDefault(node.right, 0), f.getOrDefault(node.right, 0));
        g.put(node, status2);
    }

    public static void main(String[] args) {

    }
}

package TreeX;

import BinarySearch.TreeNode;

/**
 * 124.二叉树中的最大路径和
 */
public class BinaryTreeMaximumPathSum {
    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }


    // 计算node节点的贡献值：即在该子树中，node节点到所有叶子节点的路径和的最大值
    private int maxGain(TreeNode node){
        if (node == null){
            return 0;
        }

        // 递归地计算左右子树（包含左右子节点）的最大贡献值
        // 如果左右子树都是负的，那么可以不用算左右子树的贡献值, 则舍弃
        int leftSum = Math.max(maxGain(node.left), 0);
        int rightSum = Math.max(maxGain(node.right), 0);

        // 1。递归的在每个子树里，更新状态。在node该子树里，计算最大路径和，然后更新全局状态，
        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);

        // 2。递归的返回每个子树（含子树根节点）的最大路径和。包含该节点路径，就是左右的最大值加上当前节点值
        return Math.max(leftSum, rightSum) + node.val;
    }

    public static void main(String[] args) {

        BinaryTreeMaximumPathSum inst = new BinaryTreeMaximumPathSum();
        TreeNode root = new TreeNode(-3);
        System.out.println("expected: -3, real:" + inst.maxPathSum(root));
        System.out.println("expected: 2, real:" + inst.maxPathSum(new TreeNode(2, new TreeNode(-1), null)));
    }
}

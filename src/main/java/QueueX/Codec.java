package QueueX;

import BinarySearch.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return "";
        }
        queue.add(root);
        // 表示下一层有不为null的节点
        boolean hasNext = true;

        // 返回完全二叉树
        while (!queue.isEmpty() && hasNext){
            int n = queue.size();
            hasNext = false;
            for (int i = n; i > 0; i--){
                TreeNode node = queue.poll();
                if (node != null){
                    queue.add(node.left);
                    queue.add(node.right);
                    if (node.left != null || node.right != null){
                        hasNext = true;
                    }
                    nodes.add(String.valueOf(node.val));
                } else {
                    queue.add(null);
                    queue.add(null);
                    nodes.add("null");
                }
            }
        }

        return String.join(",", nodes);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")){
            return null;
        }

        String[] strs = data.split(",", -1);
        int n = strs.length + 1;
        TreeNode[] nodes = new TreeNode[n];

        // 0节点不存储
        for (int i = n - 1; i > 0; i--){
            nodes[i] = strs[i-1].equals("null") ? null : new TreeNode(Integer.parseInt(strs[i-1]));
            // 对于null节点或者最后一层节点，不挂子节点
            if (i < n/2 && nodes[i] != null){
                // 对于节点k，它的左右子节点分别是：2k，2k+1
                nodes[i].left = nodes[2 * i];
                nodes[i].right = nodes[2 * i + 1];
            }
        }

        return nodes[1];
    }
}
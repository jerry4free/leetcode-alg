package LinkedListX;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. Populating Next Right Pointers in Each Node
 */
public class PopulatingNextRightPointersinEachNode {
    public Node connect(Node root) {
        if (root == null){
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        // 层级遍历
        while (!q.isEmpty()){
            // 遍历该层节点
            int size = q.size();
            Node prev = null;
            for (int i = 0; i < size; i++){
                Node node = q.remove();
                node.next = prev;
                prev = node;
                if (node.left != null){
                    q.add(node.right);
                    q.add(node.left);
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, new Node(6), new Node(7), null);
        Node root = new Node(1, left, right, null);
        PopulatingNextRightPointersinEachNode inst = new PopulatingNextRightPointersinEachNode();
        inst.connect(root);
    }
}

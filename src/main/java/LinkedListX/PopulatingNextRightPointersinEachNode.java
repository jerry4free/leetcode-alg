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

    public Node connect2(Node root) {
        if (root == null){
            return null;
        }
        Node leftmost = root;

        // 层级遍历，T层是已经构建完的链表，
        while (leftmost.left != null){ // 注意判断是否下一层也不为空，即：T+1层存在时

            // 遍历leftmost这一层，为下一层构建连接
            Node head = leftmost;
            while (head != null){

                // 同父节点
                head.left.next = head.right;

                // 不同父节点, 注意第1层没有这种情况
                if (head.next != null){
                    head.right.next = head.next.left;
                }

                head = head.next;
            }

            leftmost = leftmost.left;
        }
        return root;
    }


    public static void main(String[] args) {
        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, new Node(6), new Node(7), null);
        Node root = new Node(1, left, right, null);
        PopulatingNextRightPointersinEachNode inst = new PopulatingNextRightPointersinEachNode();
        inst.connect2(root).showByLevel();
    }
}

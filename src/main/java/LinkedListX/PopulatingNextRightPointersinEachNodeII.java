package LinkedListX;

/**
 * 117. Populating Next Right Pointers in Each Node II
 */
public class PopulatingNextRightPointersinEachNodeII {

    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        Node leftmost = root;

        // 层级遍历，如果有下一层
        while (leftmost != null){

            // 遍历leftmost这一层，为下一层构建连接
            Node currHead = leftmost;
            // 下一层的哑节点
            Node dummy = new Node(0);
            // 精髓：用prev串联起来下一层
            Node prev = dummy;
            while (currHead != null){

                if (currHead.left != null){
                    prev.next = currHead.left;
                    prev = prev.next;
                }

                if (currHead.right != null){
                    prev.next = currHead.right;
                    prev = prev.next;
                }

                currHead = currHead.next;
            }

            leftmost = dummy.next;
        }
        return root;
    }

    public static void main(String[] args) {
        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, null, new Node(7), null);
        Node root = new Node(1, left, right, null);
        PopulatingNextRightPointersinEachNodeII inst = new PopulatingNextRightPointersinEachNodeII();
        inst.connect(root).showByLevel();
    }
}

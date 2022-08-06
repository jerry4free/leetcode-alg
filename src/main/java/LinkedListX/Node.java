package LinkedListX;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    // 下划线打头的变量作为参数，可以减少变量起名成本，不错
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public void showByLevel(){
        Node curr = this;
        while (curr != null){
            // 找到下一层的头
            Node next = null;
            while (curr != null){
                System.out.print(curr.val + ",");
                // next如果为null，有左子树
                if (curr.left != null && next == null){
                    next = curr.left;
                }
                // next如果为null，有右子树
                if (curr.right != null && next == null){
                    next = curr.right;
                }
                curr = curr.next;
            }
            curr = next;
            System.out.print("null,");
        }
        System.out.print("\n");
    }
}

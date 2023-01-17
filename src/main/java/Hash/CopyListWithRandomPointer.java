package Hash;


import java.util.HashMap;


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * 138 Copy List with Random Pointer
 */
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        // 原始链表，节点到random的映射
        HashMap<Node, Node> src2Random = new HashMap<Node, Node>();
        // 原始节点对copy节点的一一映射
        HashMap<Node, Node> src2Dest = new HashMap<Node, Node>();

        Node dummy = new Node(0);
        Node prev = dummy;
        Node src = head;
        while (src != null) {
            Node node = new Node(src.val);
            // 设置原始链表，src node到src random node，src node到dest node的映射
            src2Random.put(src, src.random);
            src2Dest.put(src, node);

            prev.next = node;
            src = src.next;
            prev = prev.next;
        }

        Node curr = dummy.next;
        src = head;
        while (curr != null) {
            curr.random = src2Dest.get(src2Random.get(src));
            curr = curr.next;
            src = src.next;
        }

        return dummy.next;
    }

    // TODO: 递归式的创建，一个哈希表即可
}

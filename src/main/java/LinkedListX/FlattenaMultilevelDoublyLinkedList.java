package LinkedListX;

/**
 * 430  Flatten a Multilevel Doubly Linked List
 */
public class FlattenaMultilevelDoublyLinkedList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
        myFlatten(head);
        return head;
    }

    private Node myFlatten(Node head){
        Node curr = head;
        Node prev = null;
        while (curr != null){
            if (curr.child != null){
                // 保留下一个节点
                Node next = curr.next;

                Node child = curr.child;
                // flatten下一层
                Node childTail = myFlatten(curr.child);
                // child挂到curr后面
                curr.next = child;
                child.prev = curr;
                // 子链表后面挂next
                if (next != null){  // ！！！注意next可能是NULL
                    childTail.next = next;
                    next.prev = childTail;
                }
                // child置空
                curr.child = null;

                // ！！！prev就是下一层的尾节点
                prev = childTail;
                curr = next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return prev;
    }

    public static void main(String[] args) {

    }
}

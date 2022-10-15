package LinkedListX;

/**
 * 剑指 Offer II 029. 排序的循环链表
 * 708
 */
public class InsertIntoaSortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null){  // 0个节点
            node.next = node;
            return node;
        } else if (head.next == head){ // 1个节点
            head.next = node;
            node.next = head;
            return head;
        }

        Node curr = head;
        Node next = head.next;
        // 枚举1遍
        while (next != head){
            // insertVal位于2个节点之前
            if(curr.val <= insertVal && insertVal <= next.val){
                break;
            }
            // !!!此时curr是最大值，next是最小值
            if (curr.val > next.val){
                // insertVal 大于最大值，或者小于最小值，这时insertVal都处于2者之间
                if (insertVal >= curr.val || insertVal <= next.val){
                    break;
                }
            }
            curr = curr.next;
            next = next.next;
        }
        // 插入node节点，包括所有都相等的情况
        curr.next = node;
        node.next = next;

        return head;
    }

    public static void main(String[] args) {

    }
}

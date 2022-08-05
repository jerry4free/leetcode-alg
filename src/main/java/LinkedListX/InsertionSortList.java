package LinkedListX;

/**
 * 147. 对链表进行插入排序
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(-5001);
        ListNode curr = head; // 4
        ListNode next;
        ListNode node;
        ListNode tail = dummy;

        while (curr != null){
            next = curr.next;

            // insert curr to the right position
            curr.next = null;
            if (curr.val > tail.val) {  // 如果大于有序的最后一个节点, 直接挂到tail后面，tail后移, 这一步可以节省比较次数
                tail.next = curr;
                tail = tail.next;
            } else {  //  否则，插入到中间，tail不用变
                /**
                 * 小技巧：判断node.next是不是null，而不是node，能节省一个变量。
                 * 即node是前驱节点，node.next是后驱节点
                 */
                node = dummy;
                // 遍历链表，直到node.next比curr大，则把curr挂到node后面，即node.next之前
                while (node.next != null && node.next.val < curr.val){
                    node = node.next;
                }

                /**
                 * 链表插入中间注意要点：第1步：将后驱节点挂到curr；第2步：将curr挂到前驱
                 */
                curr.next = node.next;  // 将node.next挂到当前节点
                node.next = curr; // 将curr挂到node上
            }

            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

        InsertionSortList inst = new InsertionSortList();
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        inst.insertionSortList(head).show();
        inst.insertionSortList(new ListNode(4)).show();
    }
}

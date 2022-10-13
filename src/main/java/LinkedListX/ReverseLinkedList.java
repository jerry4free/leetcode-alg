package LinkedListX;

/**
 * 206. Reverse Linked List
 */
public class ReverseLinkedList {

    /**
     * 太容易出错了！！
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode tmp;
        ListNode prev = head;
        ListNode curr = head.next;
        // 需要保存下一个节点tmp，否则就中断了，陷入死循环，下个节点丢失
        // 需要保存前一个节点，否则也中断了
        while (curr != null){
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        head.next = null;  // 头指向null

        return prev;
    }

    public static void main(String[] args) {

    }
}

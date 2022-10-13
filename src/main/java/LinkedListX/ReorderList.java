package LinkedListX;

/**
 * 143
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        int n = 0;
        while (curr != null){
            curr = curr.next;
            n++;
        }

        ListNode second = head;
        ListNode prev = null;
        int i = n / 2;
        while (i > 0){
            prev = second;
            second = second.next;
            i--;
        }
        prev.next = null;

        // reverse second

    }

    public static void main(String[] args) {

    }
}

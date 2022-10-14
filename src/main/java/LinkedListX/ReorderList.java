package LinkedListX;

/**
 * 143. Reorder List
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
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
        curr = second;
        prev = null;
        ListNode next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        second = prev;

        // merge two linked list
        ListNode curr1 = head;
        ListNode next1;
        ListNode curr2 = second;
        ListNode next2;
        while (curr1 != null && curr2 != null){
            next1 = curr1.next;
            next2 = curr2.next;

            curr1.next = curr2;

            if (next1 != null){
                curr2.next = next1;
            }

            curr2 = next2;
            curr1 = next1;
        }
    }

    public static void main(String[] args) {

    }
}

package LinkedListX;

/**
 * 83. Remove Duplicates from Sorted List
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode curr = dummy.next;
        ListNode prev = dummy;

        // 同时判断2个节点
        while (curr != null && curr.next != null){
            // 如果第1个和第2个不等
            if (curr.val != curr.next.val){
                prev.next = curr;
                prev = prev.next;
            }
            curr = curr.next;
        }
        prev.next = curr;  // 边界case：最后2个节点相等。

        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList inst = new RemoveDuplicatesFromSortedList();
        inst.deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(2)))).show();

        inst.deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))))).show();

    }
}

package LinkedListX;

public class ReverseLinkedListII {
    private ListNode middleFirst = null;

    private ListNode reverse(ListNode curr){
        if (curr.next == null){
            middleFirst = curr;
            return curr;
        }
        ListNode next = reverse(curr.next);
        next.next = curr;
        return curr;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode firstTail = dummy;  //默认值很重要，如果left是第1个
        ListNode lastHead = null;
        ListNode curr = dummy.next;
        ListNode start = dummy.next;
        ListNode end = dummy.next;
        int i = 0;
        while (curr != null){
            i++;
            if (i == left - 1){
                firstTail = curr;
            }
            if (i == left){
                start = curr;
            }
            if (i == right + 1){
                lastHead = curr;
            }
            if (i == right){
                end = curr;
            }
            curr = curr.next;
        }

        // 将中间截断
        end.next = null;
        // 反转中间，取到尾部节点，指向后半部分的头
        reverse(start).next = lastHead;
        // 前半部分的尾部，指向中间的头
        firstTail.next = middleFirst;

        return dummy.next;
    }

    public static void main(String[] args) {

        ReverseLinkedListII inst = new ReverseLinkedListII();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        inst.reverseBetween(head, 2, 4).show();

        ListNode head1 = new ListNode(5);
//        inst.reverseBetween(head1, 1, 1).show();

        ListNode head2 = new ListNode(3, new ListNode(5));
        inst.reverseBetween(head2, 1, 2).show();
    }
}

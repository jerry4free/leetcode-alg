package LinkedListX;

/**
 * 82. Remove Duplicates from Sorted List II
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        // 设置哑节点
        ListNode dummyHead = new ListNode(-101, head);
        ListNode curr = dummyHead.next;
        ListNode prev = dummyHead;
        int same;

        // 如果第1个和第2个都存在
        while (curr != null && curr.next != null){
            // 如果接下来的前2个相等
            if (curr.val == curr.next.val){
                // 记录下来
                same = curr.val;

                // 移到到第3个，如果跟前面记录的一直相同，一直往后移动，直到遍历完重复的。
                curr = curr.next.next;
                while (curr != null && curr.val == same){
                    curr = curr.next;
                }
            } else {
                // 如果前2个不相等
                // ！！！prev指到当前，这一步很重要，意味着中间可能重复的都被skip掉了。然后往后移动一步
                prev.next = curr;
                prev = prev.next;
                // curr往后移动一步
                curr = curr.next;
            }
        }
        prev.next = curr;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListII inst = new RemoveDuplicatesFromSortedListII();
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
        inst.deleteDuplicates(head).show();

        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        inst.deleteDuplicates(head1).show();
    }
}

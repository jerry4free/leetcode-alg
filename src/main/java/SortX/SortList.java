package SortX;

import LinkedListX.ListNode;

/**
 * 148. Sort List
 */
public class SortList {

    // prev是之前节点，返回是合并后的头节点
    private ListNode merge(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode i = head1;
        ListNode j = head2;

        while (i != null || j != null){
            if (i == null) {  // 左半部分已经遍历完
                prev.next = j;
                j = j.next;
            } else if (j == null){  // 右半部分遍历完
                prev.next = i;
                i = i.next;
            }else if (i.val < j.val){
                prev.next = i;
                i = i.next;
            } else {
                prev.next = j;
                j = j.next;
            }
            prev = prev.next;
        }
        return dummy.next;
    }

    /**
     * bottom-up
     */
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null){
            len++;
            curr = curr.next;
        }

        ListNode fast;
        ListNode slow;
        ListNode prev;
        for (int sz = 1; sz < len; sz *= 2){
            fast = head;
            slow = head;
            while (prev != null && prev.next != null){
                prev = merge(dummy, sz);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}

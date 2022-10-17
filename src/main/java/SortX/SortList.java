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

    private ListNode split(ListNode head){
        ListNode dummy = new ListNode(0, head);
        // 加上dummy是为了避免只有2个节点:2,1时，分成了2,1和null
        // 除了加dummy外还有一种办法：fast = head.next;
        // 循环完，slow.next是后半段的开始
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;

        return head2;
    }

    /**
     * top-down的归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }

        ListNode head2 = split(head);

        ListNode left = sortList(head);
        ListNode right = sortList(head2);

        return merge(left, right);
    }

    /**
     * bottom-up
     * TODO: not finish
     */
    public ListNode sortList2(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null){
            len++;
            curr = curr.next;
        }

//        ListNode dummy = new ListNode(0, head);
        ListNode next;
        ListNode head1;
        ListNode head2;
        for (int sz = 1; sz < len; sz *= 2){
            curr = head;
            while (curr != null){
                head1 = curr;

                // 获取head2，
                for (int i = 1; i < sz && curr.next != null; i++){
                    curr = curr.next;
                }
                head2 = curr.next;
                curr.next = null;

                if (head2 == null){ // 长度不足sz，没有head2
                    break;
                }

                // 获取下一次的next，并截断
                for (int i = 1; i < sz && curr != null; i++){
                    curr = curr.next;
                }
                if (curr != null && curr.next != null){
                    next = curr.next;
                    curr.next = null;
                    curr = next;
                }

                // if head1 and head2, then merge
                merge(head1, head2);
            }


        }

        return head;
    }

    public static void main(String[] args) {

    }
}

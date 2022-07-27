package LinkedListX;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * 86. Partition List
 *
 * */
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode lowPrev = null;
        ListNode highPrev = null;
        ListNode highHead = null;
        ListNode lowHead = null;
        ListNode curr = head;

        // 如果链表是空
        if (head == null){
            return null;
        }

        while (curr != null) {
            if (curr.val < x){
                if (lowPrev == null){
                    lowPrev = curr;
                    lowHead = curr;
                } else {
                    lowPrev.next = curr;
                    lowPrev = curr;
                }
            } else {
                if (highPrev == null){
                    highPrev = curr;
                    highHead = curr;
                } else {
                    highPrev.next = curr;
                    highPrev = curr;
                }
            }
            curr = curr.next;
        }

        // 如果只有前半分区
        if (highPrev == null){
            return lowHead;
        } else {
            highPrev.next = null;
        }
        // 如果只有后半分区
        if (lowPrev == null){
            return highHead;
        } else {
            lowPrev.next = highHead;
        }

        return lowHead;
    }


    public ListNode partition1(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode largeHead = new ListNode(0);
        ListNode smallPrev = smallHead;  // smallPrev为哑节点，
        ListNode largePrev = largeHead;
        while (head != null){
            if (head.val < x){
                smallPrev.next = head;  // 不用判断smallPrev为null的情况
                smallPrev = smallPrev.next;
            } else {
                largePrev.next = head; // 不用判断largePrev为null的情况
                largePrev = largePrev.next;
            }
            head = head.next;
        }
        largePrev.next = null;   // 此处也不用判断largePrev或smallPrev为null的情况
        smallPrev.next = largeHead.next;

        return smallHead.next;
    }


    public static void show(ListNode head){
        while (head != null){
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4, new ListNode(3,
                new ListNode(2,
                        new ListNode(5,
                                new ListNode(2, null)))));
        show(head);
        PartitionList inst = new PartitionList();
        show(inst.partition1(head, 3));

        show(inst.partition1(new ListNode(2, new ListNode(1)), 2));
        show(inst.partition1(new ListNode(2), 2));
        show(inst.partition1(new ListNode(2), 1));
    }
}

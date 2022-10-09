package LinkedListX;

/**
 * 19. Remove Nth Node From End of List
 */
public class RemoveNthNodeFromEndofList {

    /**
     *
     * 维护一个间隔为n的前后指针（或快慢指针），当快指针到尾部倒数第1个时，此时慢指针位于倒数N+1个，即n的前一个
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哨兵节点
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode f = dummy;  // fast
        ListNode s = dummy;  // slow

        // fast和slow间隔为n
        while (n > 0){
            f = f.next;
            n--;
        }
        // 直到fast是倒数第1个
        while (f.next != null){
            f = f.next;
            s = s.next;
        }
        s.next = s.next.next;

        return dummy.next;
    }


    /**
     * 想要一次循环删除倒数第n个节点，必须要倒着来遍历，
     * 可以采用栈结构，先进后出，这样就可以倒序计数
     * 也可以采用递归的思想，递归执行就是不断压栈，返回是就是从栈返回
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        remove(dummy, n);
        return dummy.next;
    }

    private int remove(ListNode node, int n){
        // base case
        if (node == null){
            return 0;
        }
        // 不断压栈
        int last = remove(node.next, n);
        // 返回时取得上一个节点的编号，如果等于n，就是倒数第n个节点
        if (last == n){
            node.next = node.next.next;
        }
        return last + 1;
    }

    public static void main(String[] args) {

    }
}

package LinkedListX;

/**
 * 142. Linked List Cycle II
 */
public class LinkedListCycleII {


    /**
     *
     *
     *
     * 假设链表头部到环入口处有a个节点，环有b个节点
     * 如果环的周长b已经知道，那么可以采用双指针的方法
     * 1。p1先从链表头部向前走b步
     * 2。p2从链表头开始走，p2从b步处走，同速度走。那么当p2走了a步第一次到达入口处时，此时p1走了b+a步，也到达入口处。即此时p1==p2
     *
     * 这种同速度的双指针的方式可以
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }

        // 如果结束了，说明无环
        if (fast == null || fast.next == null){
            return null;
        }

        // 求出环的周长, 即节点数
        ListNode curr = slow;
        int loopCnt = 1;
        while (slow.next != curr){
            slow = slow.next;
            loopCnt++;
        }

        // 双指针法就是利用前后指针
        // p1先走一个周长
        ListNode p1 = head;
        while (loopCnt > 0){
            p1 = p1.next;
            loopCnt--;
        }

        // p2、p1同时再走，相遇处时即为入口处
        ListNode p2 = head;
        while (p1 != p2){
            p2 = p2.next;
            p1 = p1.next;
        }

        return p1;
    }

    /**
     * 假设链表头部到环入口处有a个节点，环有b个节点
     * 当fast和slow在环中第一次相遇时：
     * 1. fast的路程是slow的2倍，即：f = 2s
     * 2. fast比slow多走了n圈，即：f = s + nb
     * 以上相减：s = nb，即慢指针刚好走了环的n圈
     *
     * 对于指针走k步能到入环出，一定有k=a+nb。而现在slow已经走了nb步，那么再走a步就到入环处。
     * 但是我们不知道a是多少步，那么让另一个指针从链表头开始，2个指针相同速度走，当另一个指针到入口处时，慢指针走了s+a步，刚好到达入口处。
     * 即：2个指针相遇的点就是环的入口处
     *
     * 参考：
     * https://leetcode.cn/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     *
     * 另外：环的长度是可以知道的，只能知道第一次相遇后，slow再次出发，一边移动一边计数，再次回到现在这个节点的步数，就是环的长度
     */
    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }

        // 如果结束了，说明无环
        if (fast == null || fast.next == null){
            return null;
        }

        // 没结束
        ListNode curr = head;
        while (curr != slow){
            curr = curr.next;
            slow = slow.next;
        }

        return curr;

    }


    public static void main(String[] args) {

    }
}

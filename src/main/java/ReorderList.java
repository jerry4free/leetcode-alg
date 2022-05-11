import java.util.List;
import java.util.Stack;

/**
 * 143. Reorder List
 */
public class ReorderList {

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * 从题意可以看出，一个要正序，一个要倒序。
     * 倒序就可以使用栈来保存下列表，然后分别遍历链表和栈，注意结尾置成null
     */
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        ListNode next;
        ListNode second = null;
        int cnt = 0;
        while (curr != null){
            stack.push(curr);
            curr = curr.next;
            cnt++;
        }
        if (cnt <= 2){
            return;
        }

        curr = head;
        for (int i = cnt / 2; i > 0; i--){
            next = curr.next;  // 保存next
            second = stack.pop(); // 取出stack中的节点，并赋值next
            second.next = next;
            curr.next = second;
            curr = next;  // next赋值下一个
        }

        if (cnt % 2 == 0){
            second.next = null;
        } else {
            curr.next = null;
        }
    }

    public static void main(String[] args) {
        ReorderList inst = new ReorderList();
        ListNode head = inst.new ListNode(1);
        ListNode two = inst.new ListNode(2);
        ListNode three = inst.new ListNode(3);
        ListNode four = inst.new ListNode(4);
        ListNode five = inst.new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        inst.reorderList(head);

        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}

package LinkedListX;

import java.util.Stack;

/**
 *
 */
public class AddTwoNumbersII {
    int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null){
            return null;
        }
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }

        int c = 0;
        ListNode next = null;
        ListNode curr = null;
        while ((!s1.isEmpty()) || (!s2.isEmpty())){
            if (!s1.isEmpty()){
                c += s1.pop();
            }
            if (!s2.isEmpty()){
                c += s2.pop();
            }
            curr = new ListNode(c % 10);
            c /= 10;

            curr.next = next;
            next = curr;
        }

        // 注意：最后的进位
        if (c > 0){
            curr = new ListNode(c);
            curr.next = next;
        }

        return curr;
    }

    // WRONG
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return  addTwo(l1, l2);
    }

    private ListNode addTwo(ListNode node1, ListNode node2){
        if (node1 == null && node2 == null){
            return null;
        } else if (node1 == null && node2 != null){
            System.out.println(", node2:" + node2.val);
            return addTwo(node1, node2.next);
        } else if (node2 == null && node1 != null){
            System.out.println("==node1:"+ node1.val);
            return addTwo(node1.next, node2);
        }

        ListNode prev = addTwo(node1.next, node2.next);

        carry += (node1.val + node2.val);
        System.out.println("node1:"+ node1.val + ", node2:" + node2.val);
        ListNode node = new ListNode(carry % 10);
        node.next = prev;

        carry /= 10;

        return node;
    }

    private static void show(ListNode head){
        while (head != null){
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //输入：l1 = [7,2,4,3], l2 = [5,6,4]
        //输出：[7,8,0,7]
        ListNode l1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        AddTwoNumbersII inst = new AddTwoNumbersII();
        show(inst.addTwoNumbers(l1, l2));
    }
}

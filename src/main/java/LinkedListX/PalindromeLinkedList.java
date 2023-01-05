package LinkedListX;

/**
 * 234, Palindrome Linked List
 */
public class PalindromeLinkedList {
    /**
     * TODO：如果是O(1)的复杂度，则需要修改链表，反转后半部分的链表，验证完再反转回来
     */

    /**
     * 下面采用递归的方式，去判断前后2部分链表，所以也用了n/2的栈。空间复杂度还是O(n)
     */
    ListNode second;
    boolean isValid;
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }

        ListNode curr = head;
        int n = 0;
        while (curr != null){
            curr = curr.next;
            n++;
        }

        int j = n/2;
        second = head;
        while (j > 0){
            second = second.next;
            j--;
        }
        if (n % 2 == 1){
            second = second.next;
        }

        isValid = true;
        check(head, n / 2);
        return isValid;
    }

    private void check(ListNode first, int idx){
        if (idx == 0){
            return;
        }

        check(first.next, idx-1);

        if (first.val != second.val){
            isValid = false;
        }
        second = second.next;
    }

    private ListNode secondHead;
    public boolean isPalindrome(ListNode head) {

        // 0或1个节点
        if (head == null || head.next == null){
            return true;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 通过快慢指针，找到中间节点
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }


        ListNode second = slow.next;
        slow.next = null;
        // 对第2部分链表进行倒序
        ListNode secondTail = reverse(second);
        secondTail.next = null;  // 对翻转后的链表截断
        ListNode curr2 = secondHead;
        // 对第一部分结尾截断
        ListNode curr1 = head;

        show(curr1);
        show(curr2);

        // 双指针进行遍历判断
        while (curr2 != null){
            if (curr2.val != curr1.val){
                return false;
            }
            curr2 = curr2.next;
            curr1 = curr1.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head){
        if (head.next == null){
            secondHead = head;
            return secondHead;
        }

        // 递归式的翻转后面的链表
        ListNode next = reverse(head.next);
        next.next = head;

        return head;
    }

    private void show(ListNode head){

        while (head != null){
            System.out.print("->" + head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}

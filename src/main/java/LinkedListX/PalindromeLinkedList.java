package LinkedListX;

/**
 * 234, Palindrome Linked List
 */
public class PalindromeLinkedList {
    ListNode second;
    boolean isValid;
    public boolean isPalindrome(ListNode head) {
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

    public static void main(String[] args) {

    }
}

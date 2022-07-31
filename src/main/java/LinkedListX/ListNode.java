package LinkedListX;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public void show(){
        ListNode curr = this;
        while (curr != null){
            System.out.print(curr.val + ",");
            curr = curr.next;
        }
        System.out.println("");
    }
}
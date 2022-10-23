package SortX;

import LinkedListX.ListNode;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 */
public class MergekSortedLists {

    class ListNodeComparable implements Comparable<ListNodeComparable>{
        ListNode node;
        ListNodeComparable(ListNode x){
            node = x;
        }

        @Override
        public int compareTo(ListNodeComparable o) {
            return this.node.val - o.node.val;
        }
    }

    /**
     * k个链表中找最小的，可以通过一个大小为K的小根堆。
     * 每次可以O(1)从堆里取出最小的节点，但是堆里插入一个新节点需要O(lgK)
     * 但是ListNode没有继承Comparable接口，无法进行比较，所以可以对ListNode再封装一层
     *
     * 一共k个链表，每个大小是N，那么时间复杂度是O(lgK * KN)，空间复杂度是O(lgK)
     *
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNodeComparable> pq = new PriorityQueue<>();
        int k = lists.length;
        // k个链表, 取出每个头节点，初始化pq
        for (int i = 0; i < k; i++){
            if (lists[i] != null){
                pq.add(new ListNodeComparable(lists[i]));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode curr;
        while (!pq.isEmpty()){
            // 每次取出最小的节点，
            curr = pq.poll().node;

            prev.next = curr;
            // 如果它还有下一个节点，将它的下一个节点加入到pq中
            if (curr.next != null){
                pq.add(new ListNodeComparable(curr.next));
            }
            // prev指向下一个
            prev = prev.next;
        }

        return dummy.next;
    }

    // TODO: 另外还能通过归并排序的方法来实现，top-down 或者bottom-up的2种方法

    public static void main(String[] args) {

    }
}

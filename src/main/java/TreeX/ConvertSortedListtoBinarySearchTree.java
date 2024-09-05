package TreeX;

import BinarySearch.TreeNode;
import LinkedListX.ListNode;

/**
 * 109. 有序链表转换二叉搜索树
 */
public class ConvertSortedListtoBinarySearchTree {

    private ListNode getMiddle(ListNode head){
        int cnt = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            cnt++;
        }

        curr = head;
        ListNode prev = head;
        for (int i = 0; i < cnt / 2; i++){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null; // 截断

        return curr;
    }

    // 方法1：前序遍历来赋值
    // 构造生成BST，时间复杂度O(NlgN)，空间复杂度O(N)
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode middle = getMiddle(head); // O(N)
        TreeNode curr = new TreeNode(middle.val);

        curr.left = sortedListToBST(head);

        curr.right = sortedListToBST(middle.next);

        return curr;
    }

    // 方法2：用中序遍历来赋值
    // 时间复杂度：O(N)
    // 空间复杂度：O(logN),栈深，即树的高度
    public TreeNode sortedListToBST2(ListNode head) {
        int len = getLength(head);

        gCurr = head;

        return buildTree(0, len - 1);
    }

    // 链表的全局遍历偏移量
    private ListNode gCurr;

    // 用中序遍历的方式来构造BST
    // 左闭右闭，分别代表链表的[left, right]段
    private TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        // 往下遍历时,先建立root节点（即进入函数时）
        TreeNode root = new TreeNode();

        // 构造左子树
        root.left = buildTree(left, mid - 1);

        // 从左子树返回时，才赋值root节点的值
        root.val = gCurr.val;
        gCurr = gCurr.next;

        // 构造右子树
        root.right = buildTree(mid + 1, right);
        return root;
    }

    private int getLength(ListNode head){
        int cnt = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            cnt++;
        }
        return cnt;
    }
}

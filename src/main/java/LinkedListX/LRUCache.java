package LinkedListX;

import java.util.HashMap;

/**
 * 146. LRU Cache
 */
public class LRUCache {
    class DlinkedNode {
        int key;
        int value;
        DlinkedNode prev;
        DlinkedNode next;
        public DlinkedNode(){}
        public DlinkedNode(int _key, int _value){
            key = _key;
            value = _value;
        }

    }

    private DlinkedNode head, tail;
    private HashMap<Integer, DlinkedNode> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        // 使用哑节点
        // 注意：且组成链表
        head = new DlinkedNode();
        tail = new DlinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public void showLinkedList(){
        DlinkedNode curr = head.next;
        System.out.print("The linked is:");
        while (curr != tail){
            System.out.print(curr.key + ",");
            curr = curr.next;
        }
        System.out.print("\n");
    }

    // 本身哈希表可以的读写可以打到O(1)，如何在O(1)的时间插入最新访问和删除最老访问呢？
    // 按照时间顺序访问，本身就是一种有序，FIFO的规律，使用队列可以满足。
    // 想要插入第一个，删除最后一个，可以使用双向队列
    private void moveToFirst(DlinkedNode node){
//        System.out.printf("moveToFirst, %d:%d\n", node.key, node.value);
        removeNode(node);
        addFirst(node);
    }

    private void removeNode(DlinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addFirst(DlinkedNode node){
//        System.out.printf("addFirst, %d:%d\n", node.key, node.value);
        // back
        node.next = head.next;
        head.next.prev = node;
        // forward
        head.next = node;
        node.prev = head;
    }

    public int get(int key) {
        DlinkedNode node = cache.get(key);

        if (node != null){
            moveToFirst(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        //更新队列
        DlinkedNode node;
        node = cache.get(key);
        if (node != null){
            // 若存在，update，移动到第一个
            node.value = value; // 注意，update
            moveToFirst(node);
        } else {
            // 不存在，插入到head
            node = new DlinkedNode(key, value);
            addFirst(node);
            cache.put(key, node);
            // 若超过容量，移除最后一个
            if (cache.size() > capacity) {
                // 注意：需要先删除cache，再删链表。否则tail.prev删完已经改变
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.showLinkedList();
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.showLinkedList();
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4

    }
}

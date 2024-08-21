package Hash;

import java.util.LinkedList;

/**
 * 706. 设计哈希映射
 *
 */
class MyHashMap {

    private static final int BASE = 769;

    private LinkedList<int[]>[] bucket;

    private int hash(int key) {
        return key % BASE;
    }

    public MyHashMap() {
        bucket = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            bucket[i] = new LinkedList<>();
        }
    }

    public void put(int key, int value) {
        int h = hash(key);
        for (int[] pair: bucket[h]) {
            if (pair[0] == key) {
                pair[1] = value;
                return;
            }
        }
        bucket[h].add(new int[]{key, value});
    }

    public int get(int key) {
        int h = hash(key);
        for (int[] pair: bucket[h]) {
            if (pair[0] == key) {
                return pair[1];
            }
        }
        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        for (int[] pair: bucket[h]) {
            if (pair[0] == key) {
                bucket[h].remove(pair);
                return;
            }
        }
    }
}
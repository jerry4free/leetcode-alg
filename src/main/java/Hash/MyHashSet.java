package Hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *705. 设计哈希集合
 */
class MyHashSet {
    private static final int BASE = 769;
    private List<Integer>[] data;

    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<>();
        }
    }

    public void add(int key) {
        int bucket = key % BASE;
        for (Integer d: data[bucket]) {
            if (d == key){
                return;
            }
        }
        data[bucket].add(key);
    }

    public void remove(int key) {
        int bucket = key % BASE;
        for (int i = 0; i < data[bucket].size(); i++) {
            if (data[bucket].get(i) == key) {
                data[bucket].remove(i);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int bucket = key % BASE;
        for (Integer d: data[bucket]) {
            if (d == key){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.remove(3);
    }
}

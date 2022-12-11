package TrieX;

import java.util.HashMap;

/**
 * 208. 实现 TreeX.Trie (前缀树)
 */
public class Trie {

    public class Node{
        public HashMap<Character, Node> children;  // 哈希表方式
//        public Node[] children;  // 数组形式
        public boolean isLeaf;

        public Node() {
            children = new HashMap<>();
            isLeaf = false;
        }
    }

    private Node root;

    /**
     * R是字符集个数，N是字典里词的个数，w是词（key）的平均长度
     * 空间复杂度是O(RNw)。
     * 实际中，短词是O(RN)，长词是O(RNw)
     */
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (char c: word.toCharArray()){
            Node child;
            if (curr.children.containsKey(c)){
                child = curr.children.get(c);
            } else {
                child = new Node();
                curr.children.put(c, child);
            }
            curr = child;
        }
        curr.isLeaf = true;
    }

    /**
     * 词的字符长度是L，R是字符集个数，N是字典里词的个数
     * 查找命中和插入的时间复杂度是O(L)
     * 查找未命中的平均时间复杂度是以R为底N的对数
     */
    public boolean search(String word) {
        Node curr = root;
        for (char c: word.toCharArray()){
            if (!curr.children.containsKey(c)){
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isLeaf;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (char c: prefix.toCharArray()){
            if (!curr.children.containsKey(c)){
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie inst = new Trie();
        inst.insert("apple");
        System.out.println("true:" + inst.search("apple"));
        System.out.println("false:" + inst.search("app"));
        System.out.println("true:" + inst.startsWith("app"));
        inst.insert("app");
        System.out.println("true:" + inst.search("app"));
    }
}

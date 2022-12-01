package TreeX;

import java.util.HashMap;

/**
 * 208. 实现 Trie (前缀树)
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

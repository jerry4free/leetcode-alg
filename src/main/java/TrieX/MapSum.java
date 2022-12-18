package TrieX;

/**
 * 677. 键值映射
 */
class MapSum {

    static public class Trie{
        public Trie[] next;
        public int val;

        public Trie(){
            next = new Trie[26];
        }
    }

    private Trie root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Trie();
    }

    public void insert(String key, int val) {
        Trie curr = root;
        for (int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if (curr.next[c - 'a'] == null){
                curr.next[c - 'a'] = new Trie();
            }
            curr = curr.next[c - 'a'];
        }
        curr.val = val;
    }

    public int sum(String prefix) {
        Trie curr = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (curr.next[c - 'a'] != null){
                curr = curr.next[c - 'a'];
            } else {
                return 0;  // 未找到提前返回
            }
        }
        return sum(curr);
    }

    private int sum(Trie node){
        if (node == null){
            return 0;
        }
        int ret = node.val;
        for (Trie child: node.next){
            ret += sum(child);
        }
        return ret;
    }
}

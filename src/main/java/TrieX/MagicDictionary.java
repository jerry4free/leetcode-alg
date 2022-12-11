package TrieX;

/**
 * 676. 神奇的字典
 */
public class MagicDictionary {
    static public class TrieNode{
        public TrieNode[] children;
        boolean isWord;

        public TrieNode(){
            children = new TrieNode[26];
            isWord = false;
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }


    /**
     * dictionary的个数是N，每个dictionary的字符长度是w，字符集大小是R，
     * 那么trie数的整体空间复杂度是O(RNw)
     */
    public void buildDict(String[] dictionary) {
        for (String d: dictionary){
            TrieNode curr = root;
            for (char c: d.toCharArray()){
                if (curr.children[c - 'a'] == null){
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr = curr.children[c - 'a'];
            }
            curr.isWord = true;
        }
    }

    public boolean search(String searchWord) {
        boolean[] found = new boolean[1];
        dfs(searchWord, root, found, 1);
        return found[0];
    }

    private void dfs(String searchWord, TrieNode node, boolean[] found, int cnt){
        if (found[0]){
            return;
        }

        // 多于1个字符
        if (cnt < 0){
            return;
        }

        // 有1个字符不同，且searchWord长度跟trie树里的一个单词长度相等
        if (cnt == 0 && searchWord.equals("") && node != null && node.isWord){
            found[0] = true;
            return;
        }
        if (searchWord.equals("") || node == null){
            return;
        }

        char c = searchWord.charAt(0);
        int len = searchWord.length();

        for (int i = 0; i < node.children.length; i++){
            if (node.children[i] == null){
                continue;
            }
            if (i == c - 'a'){
                dfs(searchWord.substring(1, len), node.children[i], found, cnt);
            } else {
                dfs(searchWord.substring(1, len), node.children[i], found, cnt - 1);
            }
        }
    }
}

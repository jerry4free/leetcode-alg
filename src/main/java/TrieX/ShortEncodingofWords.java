package TrieX;

/**
 * 820. Short Encoding of Words
 */
public class ShortEncodingofWords {

    static public class Trie{
        Trie[] children;
        boolean isWord;
        int depth;

        public Trie(){
            children = new Trie[26];
            isWord = false;
            depth = 0;
        }
    }

    /**
     * 由题目可知，如果一个word1是另一个word2的后缀，就可以word1去掉，用word2来进行编码。
     * 所以就是求所有word数组中，对有相同后缀的词进行去重，只保留最长的那个词。
     * 反过来就是所有前缀相同的词只保留最长的那一个，可以想到通过前缀树的方式进行去重
     */
    public int minimumLengthEncoding(String[] words) {
        // init trie tree
        Trie root = new Trie();
        Trie curr;
        for (String word: words){
            curr = root;
            for (int i = word.length() - 1; i >=0; i--){
                char c = word.charAt(i);
                if (curr.children[c - 'a'] == null){
                    curr.children[c - 'a'] = new Trie();
                }
                // 将之前路径上的节点depth置为0，即有相同前缀的词的depth置为0，只留最长的词。
                curr.depth = 0;
                curr = curr.children[c - 'a'];
            }

            // 如果到达叶子节点，则记录深度
            boolean isLeaf = true;
            for (Trie next: curr.children){
                if (next != null){
                    isLeaf = false;
                    break;
                }
            }
            if (isLeaf){
                curr.depth = words.length;
            }
        }

        return dfs(root);
    }


    private int dfs(Trie node){
        // 空节点说明不是word
        if (node == null) {
            return 0;
        }

        int sumDepth = 0;
        for (Trie child: node.children){
            sumDepth += dfs(child);
        }
        // 如果节点深度不是0，则说明是去重后的词，算上#
        if (node.depth != 0){
            return sumDepth + node.depth + 1;
        } else {
            return sumDepth;
        }
    }
}

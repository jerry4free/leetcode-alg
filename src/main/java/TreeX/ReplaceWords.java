package TreeX;

import java.util.ArrayList;
import java.util.List;

/**
 * 648: 单词替换
 */
public class ReplaceWords {
    // 定义嵌套型的数据类
    private class TrieNode{
        TrieNode[] children;  // 下一层级的所有子节点，用数组表示
        boolean isEnd;

        TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private void insert(TrieNode root, String d){
        // add every dict to trie
        TrieNode curr = root;
        for (char c: d.toCharArray()){
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    private String search(TrieNode root, String d){
        // char数组或list用StringBuilder转化成String
        StringBuilder sb = new StringBuilder();
        TrieNode curr = root;
        for (char c: d.toCharArray()){
            if (curr.children[c - 'a'] != null) {
                sb.append(c);
                curr = curr.children[c - 'a'];
                if (curr.isEnd){
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }

    public String replaceWords(List<String> dictionary, String sentence) {

        // init trie tree
        TrieNode root = new TrieNode();
        // iterate dictionary
        for (String d: dictionary){
            insert(root, d);
        }

        // search and replace
        List<String> ans = new ArrayList<>();
        for (String w: sentence.split(" ", -1)){
            ans.add(search(root, w));
        }

        return String.join(" ", ans);
    }

    public static void main(String[] args) {

    }
}

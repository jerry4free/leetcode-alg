package GraphX;

import java.util.HashMap;

/**
 * 1061. Lexicographically Smallest Equivalent String
 */
public class LexicographicallySmallestEquivalentString {

    /**
     * 并查集
     * 本题是个连通性问题，每个联通分量是一组相等字符集，每个字符集对应一个最小字符
     * time: O(min(N, M)), M为baseStr的长度
     * space: O(R) R是s1和s2一起的字符集大小
     */
    HashMap<Character, Character> tree;

    private void union(Character v, Character w){
        Character vRoot = find(v);
        Character wRoot = find(w);
        if (vRoot > wRoot){
            tree.put(vRoot, wRoot);  // vRoot -> wRoot
        } else {
            tree.put(wRoot, vRoot);
        }
    }

    /*
    private Character find(Character v){
        while (tree.get(v) != v){
            v = tree.get(v);
        }
        return v;
    }
     */

    private Character find(Character v){
        if (v == tree.get(v)) {
            return v;
        }
        Character vRoot = find(tree.get(v));
        tree.put(v, vRoot);
        return vRoot;
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        // union find
        tree = new HashMap<>();
        // init tree
        for (int i = 0; i < n; i++){
            tree.put(s1.charAt(i), s1.charAt(i));
            tree.put(s2.charAt(i), s2.charAt(i));
        }


        for (int i = 0; i < n; i++){
            union(s1.charAt(i), s2.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (char c: baseStr.toCharArray()){
            if (tree.containsKey(c)){
                sb.append(find(c));
            } else {
                sb.append(c);
            }
        }

        return new String(sb);
    }

}

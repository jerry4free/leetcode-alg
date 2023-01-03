package GraphX;

import java.util.*;

/**
 * 839. Similar String Groups
 */
public class SimilarStringGroups {
    Set<String> set;
    Map<String, Boolean> visited;

    // 假设每个string是一个顶点，相似关系就是边，整体构成一个无向图。本题就是求所有连通通量的数量。可以用DFS或者并查集解决
    public int numSimilarGroups(String[] strs) {
        set = new HashSet<>();
        visited = new HashMap<>();

        for (String str: strs){
            visited.put(str, false);
            set.add(str);
        }

        int ret = 0;
        for (String str: strs){
            if (!visited.get(str)){
                dfs(str);
                ret++;
            }
        }

        return ret;
    }

    private List<String> getAdj(String str){
        List<String> ret = new ArrayList<>();
        for (String other: set){
            if (isAnagram(str, other)){
                ret.add(other);
            }
        }
        for (String other: ret){
            set.remove(other);
        }
        // 如下对于稀疏图来说，成本太高。str的长度是N，那么找每个顶点的所有邻接边，需要O(N^2)，对于长字符串，时间超时
        /*
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                swap(chars, i, j);
                String newStr = new String(chars);
                if (set.contains(newStr)){
                    ret.add(newStr);
                    set.remove(newStr);
                }
                swap(chars, i, j);
            }
        }
        */
        return ret;
    }

    private Boolean isAnagram(String v, String w){
        if (v == w){
            return true;
        }

        int i = -1;
        int j = -1;
        int cnt = 0;
        for (int k = 0; k < v.length(); k++){
            if (v.charAt(k) != w.charAt(k)){
                cnt++;
                if (cnt == 1) {
                    i = k;
                }
                if (cnt == 2){
                    j = k;
                }
            }
            if (cnt > 2){
                return false;
            }
        }

        return j != -1 && i != -1 && v.charAt(i) == w.charAt(j) && v.charAt(j) == w.charAt(i);
    }

    private void swap(char[] chars, int i, int j){
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    private void dfs(String str){
        visited.put(str, true);
        for (String v: getAdj(str)){
            if (!visited.get(v)){
                dfs(v);
            }
        }
    }
}

package GraphX;

import java.util.*;

/**
 * 126. 单词接龙 II
 */
public class WordLadderII {
    HashSet<String> dict;
    HashSet<String> visited;

    /**
     * 时间复杂度分析：
     * BFS的构建图需要遍历所有单词，时间复杂度是O(V*R*L)，V是单词个数，R是字符集大小，L是单词长度，E是边数
     * DFS遍历图，需要遍历整个图，时间复杂度是O(V+E)
     *
     * 空间复杂度是O(V+E)
     * TODO: Not AC
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ret = new ArrayList<>();
        dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)){
            return ret;
        }

        // 队列里存储的不是上一层的顶点, 而是上一层的顶点所对应的路径
        Queue<List<String>> q = new LinkedList<>();
        q.add(new ArrayList<>(Arrays.asList(beginWord)));
        visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;
        while (!q.isEmpty() && !found){
            // 一层层的遍历队列
            int len = q.size();
            // 这一层的访问集合。
            // 如果这一层遇到endWord，可能存在多条路径到endWord，所以不能直接将endWord加入到visited中，否则只找到了一条路径，
            // 所以需要将这一层访问过的保存到这一层的访问集合中
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < len; i++){
                List<String> path = q.poll(); // 取出队列中的上一层顶点的所对应路径
                String curr = path.get(path.size()-1); // 取出上一层的顶点
                List<String> adj = getAdj(curr); // 对于每个词，枚举所有邻接单词
                for (String next: adj){
                    // 更新path，并加入队列
                    List<String> nextPath = new ArrayList<>(path);  // 注意这里要更新path，所以克隆一个path，再加入节点
                    nextPath.add(next);
                    q.add(nextPath);
                    // 更新这一层的访问集合
                    subVisited.add(next);
                    if (next.equals(endWord)){
                        ret.add(nextPath);
                        found = true;
                    }
                }
            }
            // 更新这一层已访问的单词到全局访问
            visited.addAll(subVisited);
        }

        return ret;
    }

    private void show(List<String> list){
        System.out.print("show: ");
        for (String v: list) {
            System.out.print("," + v);
        }
        System.out.println();
    }


    private List<String> getAdj(String word){
        char[] wordChars = word.toCharArray();
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < wordChars.length; i++){
            char old = wordChars[i];
            for (char c = 'a'; c <= 'z'; c++){
                if (c == wordChars[i]){
                    continue;
                }
                wordChars[i] = c;
                String newWord = new String(wordChars);
                // 通过哈希表dict来快速判断是否合法，visited来判断是否已经访问过
                if (dict.contains(newWord) && !visited.contains(newWord)){
                    ret.add(newWord);
                }
            }
            wordChars[i] = old;
        }
        return ret;
    }

    public static void main(String[] args) {
        WordLadderII inst = new WordLadderII();
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<List<String>> ret = inst.findLadders("hit", "cog", wordList);
        for (List<String> path: ret){
            for(String word: path){
                System.out.print( word + ",");
            }
            System.out.println();
        }
    }
}

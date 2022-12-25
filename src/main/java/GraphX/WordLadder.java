package GraphX;

import Util.Utils;

import java.util.*;

/**
 * 127. 单词接龙
 */
public class WordLadder {
    HashSet<String> set;

    /**
     * 每个单词是一个顶点，每个单词跟其所有邻接单词之间构成一条边, 所以本题目就是求解一个单源最短路径问题：存在路径时求路径长度，否则返回0
     *
     * 空间复杂度是O(N)，N是单词个数
     * 时间复杂度是O(N
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 通过哈希表来避免同一个单词重复加入到路径中
        set = new HashSet<>(wordList);
        if (!set.contains(endWord)){
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int ret = 1;
        while (!q.isEmpty()){
            // 遍历队列中所有单词
            ret++;
            int len = q.size();
            for (int i = 0; i < len; i++){
                String w = q.poll();
                for (String v: getAdj(w)){  // 对于每个词，枚举所有邻接单词
                    if (v.equals(endWord)){
                        return ret;
                    }
                    q.add(v);
                }
            }
        }

        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 通过哈希表来避免同一个单词重复加入到路径中
        set = new HashSet<>(wordList);
        if (!set.contains(endWord)){
            return 0;
        }

        Set<String> s1 = new HashSet<>();
        s1.add(beginWord);
        Set<String> s2 = new HashSet<>();
        s2.add(endWord);
        set.remove(endWord);

        int ret = 2;
        while (!s1.isEmpty() && !s2.isEmpty()){
            // 确保s1是较小的集合
            if (s1.size() > s2.size()){
                Set<String> t = s1;
                s1 = s2;
                s2 = t;
            }

            // 遍历队列中所有单词
            Set<String> s3 = new HashSet<>();
            for (String w: s1) {
                for (String v: getAdj(w)){  // 对于每个词，枚举所有邻接单词
                    if (s2.contains(v)){
                        return ret;
                    } else {
                        s3.add(v);
                    }
                }
            }

            Utils.show(s3);
            s1 = s3;
            ret++;
        }

        return 0;
    }

    /**
     * 获取word的所有有效的邻接单词, 时间复杂度是O(R*L), R是字符集大小26，L是单词长度。
     * @param word
     * @return
     */
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
                if (set.contains(newWord)){
                    ret.add(newWord);
                    set.remove(newWord);
                }
            }
            wordChars[i] = old;
        }
        return ret;
    }

    public static void main(String[] args) {
        WordLadder inst = new WordLadder();
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("expect 5, real: " + inst.ladderLength("hit", "cog", wordList));
    }
}

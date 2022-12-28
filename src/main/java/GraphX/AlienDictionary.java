package GraphX;

import Util.Utils;

import java.util.*;

/**
 * 剑指 Offer II 114. 外星文字典
 * 269
 */
public class AlienDictionary {
    final static int R = 26;

    /**
     * 单词数组是N，平均长度是M
     * 构图时间复杂度是O(N*M)
     */
    public String alienOrder(String[] words) {
        List<Integer>[] graph = new ArrayList[R];
        int[] in = new int[R];
        Arrays.fill(in, -1);
        // init graph
        for (int i = 0; i < R; i++){
            graph[i] = new ArrayList<>();
        }

        // 首先初始化入度数组，和顶点大小cnt，避免下面构建图时同时做这个事情容易出错
        int cnt = 0;
        for (String w: words){
            for (char c : w.toCharArray()){
                int v = c - 'a';
                if (in[v] == -1){
                    in[v] = 0;
                    cnt++;
                }
            }
        }

        // build graph
        for (int i = 0; i < words.length - 1; i++){
            String w1 = words[i];
            String w2 = words[i+1];
            if (w1.startsWith(w2) && !w1.equals(w2)){  // abc, ab
                return "";
            }
            int j;
            for (j = 0; j < w1.length() && j < w2.length(); j++){
                int v = w1.charAt(j) - 'a';
                int w = w2.charAt(j) - 'a';
                if (v != w){
                    graph[v].add(w);
                    in[w]++;
                    break;
                }
            }
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < R; i++){
            if (in[i] == 0){
                q.add(i);
            }
        }
        String ret = "";

        while (!q.isEmpty()){
            int v = q.poll();
            ret += (char) ('a' + v);
            cnt--;
            for (int w: graph[v]){
                in[w]--;
                if (in[w] == 0){
                    q.add(w);
                }
            }
        }

        // 入度数组里仍有顶点，说明存在环
        if (cnt > 0){
            return "";
        }

        return ret;
    }

    public static void main(String[] args) {
        AlienDictionary inst = new AlienDictionary();
        String[] words = new String[]{"ab", "adc"};
        System.out.println("expected: abcd, real :" + inst.alienOrder(words));
        System.out.println("expected: jkrtw, real :" + inst.alienOrder(new String[]{"wrt","wrtkj"}));
        System.out.println("expected: , real :" + inst.alienOrder(new String[]{"abc","ab"}));
        System.out.println("expected: wertf, real :" + inst.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println("expected: bdgilpqsvcwx, real :" + inst.alienOrder(new String[]{"vlxpwiqbsg","cpwqwqcd"}));
    }
}

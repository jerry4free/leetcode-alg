package DynamicPrograming;

import java.util.*;

/**
 * 139. Word Break
 */
public class WordBreak {

    /**
     * 假设f(i)表示前i个的字符串能否被拆分, 那么f(len)就是解
     * f(i) =
     *   (f(j) for j in range(0,i-1) if f(j) ):
     *      f[i] = true if s[j:i-i] in wordDict
     *
     * base case : f(0) = true
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] f = new boolean[len+1];

        HashMap<String, Boolean> map = new HashMap<>();
        for (String w: wordDict){
            map.put(w, true);
        }
        // 一般都可以用dp[len+1]个状态，因为状态要转移到规模较小的状态上，所以dp[i]一定是涉及dp[i-1]等较小的状态
        // 这时加上dp[0] base case，比较好处理边界case
        f[0] = true;

        // substring, 左开右闭
        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (f[j] && map.getOrDefault(s.substring(j, i), false)){   //s[j:i]
                    f[i] = true;
                }
            }
        }

        return f[len];
    }

    // 采用set
    public boolean wordBreak2(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] f = new boolean[len+1];

        Set<String> set = new HashSet<>(wordDict);
        f[0] = true;

        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (f[j] && set.contains(s.substring(j, i))){   //s[j:i]
                    f[i] = true;
                }
            }
        }

        return f[len];
    }

    public static void main(String[] args) {
        WordBreak inst = new WordBreak();
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        System.out.println(inst.wordBreak("applepenapple", wordDict));
        // s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    }
}

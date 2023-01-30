package StringX;

import java.util.Arrays;

/**
 * 28. 找出字符串中第一个匹配项的下标
 */
public class FindtheIndexoftheFirstOccurrenceinaString {

    /**
     * brute force
     * time: O(MN)
     * space: O(1)
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m > n){
            return -1;
        }

        for (int p = 0; p <= n - m + 1; p++){
            int i = p, j = 0;
            for (; j < m && haystack.charAt(i) == needle.charAt(j); i++,j++){
            }
            if (j == m){
                return p;
            }
        }

        return -1;
    }

    /**
     * KMP
     * 构造DFA的时间复杂度是O(RM), R是字符集大小。如果构造跟查询分开，那么查询的时间复杂度是O(M+N)
     * 空间复杂度是O(RM),
     *
     * 优势：text不用回退，适合无界数据流
     *
     * */
    final public static int R = 256; // 字符集大小，可以优化为26
    // dfa是状态转移表，列代表当前的状态，行代表遇到的字符，值代表转移的状态
    public int[][] dfa;

    // 构造确定性有限状态自动机
    private void constructDFA(String pat){
        int m = pat.length();
        dfa = new int[R][m];

        // 状态0是base case，其他状态都是由此推导而来
        // 0是开始状态，m是终止状态. 状态1一定是由状态0而来
        dfa[pat.charAt(0)][0] = 1;

        // X变量是restart state, 或者可以称为可以拷贝的上一次状态。即迭代到当前第j个状态时，可以从之前的X状态重启。
        for (int X = 0, j = 1; j < m; j++){
            // 1. copy mismatch transition from X
            // 复制第X个状态，即拷贝第X列的状态到第i列
            for (int c = 0; c < R; c++){
                dfa[c][j] = dfa[c][X];
            }
            // 2. update match transition
            // 意思是当前状态是i时，如果遇到pat.charAt(j)，匹配上了，所以进行下一次匹配，状态转移到j+1
            dfa[pat.charAt(j)][j] = j+1;
            // 3. update restart state
            // 比如对于ABABAA，假设前5个字符的状态转移已经知道，此时第6个字符A与ABABAC中的C不匹配。如何通过前面的5个状态推导出来呢？
            // 可以借助暴力法来理解，如果出现不匹配时，需要向右移动i指针1位，即不考虑开头的A，最后一位A需要待计算，需要从BABA开始匹配,
            // 就是将BABA放入状态机算出BABA所处的状态
            // 因为j是从1开始的，X是从0开始的，就是说X的状态模拟，是从状态0开始，但是从第1个字符（去掉pattern的第0个字符）模拟。
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int strStr2(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        constructDFA(needle);

        // text的指针i不回退，pattern的指针j根据dfa来指导是否回退
        for (int i = 0, j = 0; i < n; i++){
            j = dfa[haystack.charAt(i)][j];
            if (j == m){ // 到达终止状态
                return i - j + 1;
            }
        }

        return -1;
    }


    /**
     * BM
     * 时间复杂度平均是O(N/M)，最坏是O(MN)
     * 空间复杂度是O(R)
     *
     * 思想是：pattern从右向左匹配，当text的字符c出现不匹配时，
     * 1。如果c没出现在pattern中，pattern跳到j右边1位
     * 2。如果c出现在pattern中，pattern向右移动直到其最右侧的c与text中的c对齐。来保证最大交集
     * 3。如果经过1和2之后，反而没有移动1位，则至少移动1位
     */
    public int strStr3(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        //  事先计算pattern的每个字符在pattern中的最右侧的位置
        int[] right = new int[R];
        Arrays.fill(right, -1);
        for (int j = 0; j < m; j++){
            right[pattern.charAt(j)] = j;
        }

        // text的指针i还是从左到右
        for (int i = 0, skip = 0; i < n - m + 1; i += skip){
            // pattern的j指针是从右向左
            int j;
            for (j = m - 1; j >= 0; j--){
//                System.out.println("i:" + i + ",j:" + j);
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    // mismatch的3种情况
                    // 1. 如果text的字符不在pattern中，pattern移动到i+j的右边，即：i += j + 1
                    // 2. 如果text的字符在pattern中, i移动到i += j - right[]
                    skip = j - right[text.charAt(i+j)];
                    // 3. 如果没有产生移动，至少移动1位
                    skip = Math.max(skip, 1);
                    break;
                }
            }
            if (j < 0){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        FindtheIndexoftheFirstOccurrenceinaString inst = new FindtheIndexoftheFirstOccurrenceinaString();
        System.out.println("expected: 0, real: " + inst.strStr3("sadbutsad", "sad"));
        System.out.println("expected: 0, real: " + inst.strStr3("happybutsad", "sad"));
        System.out.println("expected: -1, real: " + inst.strStr3("leetcode", "leeto"));
        System.out.println("expected: -1, real: " + inst.strStr3("aaaaa", "bba"));
    }
}

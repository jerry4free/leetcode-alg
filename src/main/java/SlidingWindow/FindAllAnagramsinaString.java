package SlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 */
public class FindAllAnagramsinaString {

    /**
     * 经典的变长滑动窗口，1层循环
     */
    public List<Integer> findAnagrams(String s, String p) {
        int m = p.length();
        int n = s.length();
        // cnt是消耗品, 用滑动窗口遍历s时需要
        int [] cnt = new int[128];
        for (char c: p.toCharArray()){
            cnt[c]++;
        }

        int l = 0;
        int r = 0;
        List<Integer> ret = new ArrayList<>();
        while (r < n){
            if (cnt[s.charAt(r)] > 0){  // 如果还有消耗品, 就右移右边界
                cnt[s.charAt(r++)]--;
                if (r - l == m){  //l是在滑动窗口内的，r是滑动窗口外的，左闭右开，所以r-l就是长度
                    ret.add(l);
                }
            } else { // 如果没有消耗品，就右移左边界来释放消耗品（说明找到了一个不包含的字符，或者字符的数量消耗完了）
                cnt[s.charAt(l++)]++;
            }
        }

        return ret;
    }
    /**
     * 变长滑动窗口
     * 2层循环
     */
    public List<Integer> findAnagrams2(String s, String p) {
        int m = p.length();
        int n = s.length();
        List<Integer> ret = new ArrayList<>();

        // 维护一个滑动窗口和字符串p的每个字母数量的差
        // 开始时就是p里面每个字符对应的次数。
        int[] freq = new int[128];
        for (int i = 0; i < m; i++){
            freq[p.charAt(i)]++;
        }

        // 外层循环右移右边界
        for (int l = 0, r = 0; r < n; r++){
            freq[s.charAt(r)]--;  // 中和频次表
            // 如果找到不在freq里的字符，或者在freq的字符但是次数多了，都会导致当前freq[x]变为负数，那么就缩小窗口，将其排除掉窗口之外
            // 内层循环右移左边界
            while (freq[s.charAt(r)] < 0){
                freq[s.charAt(l++)]++;  // 归位频次表
            }
            if (r - l + 1 == m){
//                System.out.println("got it:" + l + "," + r);
                ret.add(l);
            }
        }
        return ret;
    }

    static void show(List<Integer> l){
        for (Integer i: l){
            System.out.print(i + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindAllAnagramsinaString inst = new FindAllAnagramsinaString();
        show(inst.findAnagrams("cbaebabacd", "abc"));
        show(inst.findAnagrams("abab", "ab"));
    }
}

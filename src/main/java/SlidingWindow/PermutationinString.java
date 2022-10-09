package SlidingWindow;

import java.util.Arrays;

/**
 *567. Permutation in String
 */
public class PermutationinString {

    /**
     * "消耗品"：要找的频次表，
     * 扩大窗口：在s2上不断扩大窗口，需要消耗"消耗品"，
     * 缩小窗口：如果没有消耗品，就缩小窗口，释放消耗品
     * 变长窗口版本：
     */
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[] pattern = new int[26]; // 要找的s1的频次表, 为正即为有消耗品
        for (int i = 0; i < m; i++){
            pattern[s1.charAt(i) - 'a']++;
        }

        int l = 0;
        // 然后遍历s2
        // 右移右边界，不断扩大窗口，中和频次表，直到长度也为m
        for (int r = 0; r < n; r++){
            int x = s2.charAt(r) - 'a';
            pattern[x]--;  // 消耗消耗品
            // 如果窗口内有不符合条件的字符，就是负的，那么右移左边界，缩小窗口，直到当前右边界囊括的字符符合条件
            // 滑动窗口的收缩条件：pattern[x] < 0
            while (pattern[x] < 0){
                pattern[s2.charAt(l++) - 'a']++;  // 右移左边界，缩小窗口，
            }
            if (r - l + 1 == m){
                return true;
            }
        }
        return false;
    }

    /**
     * 固定长度窗口版本：
     * 维护一个固定长度的窗口，每次左右边界同时右移1位，然后判断窗口内的频次表是否跟要找的频次表相同。
     */
    public boolean checkInclusion2(String s1, String s2) {
        int[] cnt1 = new int[26];  // s1的频次表
        int[] cnt2 = new int[26];  // s2的频次表
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len > s2Len){
            return false;
        }

        for (int i = 0; i < s1Len; i++){
            cnt1[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s1Len; i++){
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2)){
            return true;
        }

        // 遍历s2维护一个固定成长度为m的滑动窗口，每次右移1位，判断2个频次表是否相同
        for (int i = s1Len; i < s2Len; i++){
            cnt2[s2.charAt(i) - 'a']++;  // 右边界右移1位
            cnt2[s2.charAt(i-s1Len) - 'a']--;  // 左边界右移1位
            if (Arrays.equals(cnt1, cnt2)){  // 每次比较需要O(m)次
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PermutationinString inst = new PermutationinString();
        System.out.println(inst.checkInclusion("ab", "eidbaooo"));
        System.out.println(inst.checkInclusion("ab", "eidboaoo"));

    }
}

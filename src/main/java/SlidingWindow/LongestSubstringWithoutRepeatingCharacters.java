package SlidingWindow;

/**
 * 3. 无重复字符的最长子串
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] win = new int[256];
        int l = 0;
        int r = 0;
        int len = 0;
        while (r < n){
            // 扩大窗口
            int x = s.charAt(r++);
            win[x]++;

            // 如果窗口内有重复字符
            while (win[x] > 1){
                // 缩小窗口
                win[s.charAt(l++)]--;
            }
            len = Math.max(len, r - l);
        }
        return len;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters inst = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(inst.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(inst.lengthOfLongestSubstring("bbbbb"));
        System.out.println(inst.lengthOfLongestSubstring("pwwkew"));

    }
}

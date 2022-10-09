package TwoPointer;

/**
 * 647. 回文子串
 */
public class PalindromicSubstrings {
    // 中心拓展法
    // 枚举每一个可能的回文中心，O(N)，然后check每一个回文是否是回文:O(N)
    // 时间复杂度：O(N^2)，空间复杂度：O(1)
    public int countSubstrings(String s) {
        int l = 0;
        int r = 0;
        int n = s.length();

        int ret = 0;
        // l,r 代表字符串的每一个回文的中心，
        // l==r，代表中心就1个字符，奇数个
        // l+1==r, 代表中心是2个字符，偶数个
        // 枚举中心，类似滑动窗口的移动，窗口长度是1或2
        while (l < n || r < n){
            int i = l;
            int j = r;
            // check 以l和r为中心的字符串，扩展
            while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)){
                ret++;
                i--;
                j++;
            }

            if (l == r){ //一个中心点时，先滑动右边界
                r++;
            } else { // 2个中心点时，先滑动左边界
                l++;
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        PalindromicSubstrings inst = new PalindromicSubstrings();
        System.out.println(inst.countSubstrings("abc"));
        System.out.println(inst.countSubstrings("aaa"));
    }
}

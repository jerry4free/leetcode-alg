package TwoPointer;

/**
 * 680. Valid Palindrome II
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        // 这里是整体分别尝试减左，减右测的2种方式，最坏遍历2边s
        return MyvalidPalindrome(s, true) || MyvalidPalindrome(s, false);
    }

    private boolean MyvalidPalindrome(String s, boolean isLeft ){
        int l = 0;
        int r = s.length()-1;
        int cnt = 1;
        while (l < r){
            if (s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            } else {
                if (cnt > 0){
                    // 还有1种办法是：在遇到不相等的字符之后，再分别尝试2种办法，可以减少遍历次数
                    if (isLeft){
                        l++;
                    } else {
                        r--;
                    }
                    cnt--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}

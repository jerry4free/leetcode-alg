package StackX;

import java.util.Arrays;
import java.util.Stack;

/**
 * 32. Longest Valid Parentheses
 */
public class LongestValidParentheses {
    public int longestValidParentheses2(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c: s.toCharArray()){

        }

        return s.length();
    }

    public static void show(int [] nums){
        for (int n: nums){
            System.out.print(n + ",");
        }
        System.out.println("");
    }

    // TODO: not finish
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1){
            return 0;
        }
        // dp表示以i结尾的最长子序列
        int []dp = new int[n];
        Arrays.fill(dp, 0);
        int ans = 0;
        // 从下标1开始
        for (int i = 1; i < n; i++){
            if (s.charAt(i) == ')') {
                if (s.charAt(i-1) == '(') {  //()
                    if (i >= 2){
                        dp[i] = dp[i-2] + 2;  // 越界
                    } else {
                        dp[i] = 2;
                    }
                } else { // ))
                    int middleLen = dp[i-1]; // 中间闭合这部分的长度
                    System.out.println("---->" + (i-middleLen-1));
                    if (i-middleLen-1 >= 0 && dp[i-middleLen-1] == '('){  //如果满足(())，注意判断是否越界
                        int start = i - middleLen - 2;  // start可能越界
                        System.out.println("==>" + start);
                        if (start < 0){  // case: (())，这种情况
                            dp[i] = 2 + dp[i-1];
                        } else { // case:)(())
                            dp[i] = dp[start] + 2 + dp[i-1];
                        }
                    }
                }
            }
            if (dp[i] > ans){
                ans = dp[i];
            }
        }

        show(dp);

        return ans;
    }

    public static void main(String[] args) {

        LongestValidParentheses inst = new LongestValidParentheses();
        System.out.println(inst.longestValidParentheses("(()"));
        System.out.println(inst.longestValidParentheses(")()())"));
        System.out.println(inst.longestValidParentheses("()(())"));
    }
}

package BackTrack;

import Util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 */
public class PalindromePartitioning {

    /**
     * 题目要找分割回文串的所有方案，这种搜索所有分割方案的过程，即需要分割很多步骤，每一步又有很多选择。
     * 在递归地进行深度搜索的过程中，可以采用回溯的方法，
     */

    public String[][] partition(String s) {
        List<List<String>> ret = new ArrayList<>();

        backTrack(s, 0, ret, new ArrayList<>());

        // 每行长度不等的二维数组，初始化时，可以只指定行的空间大小，列不用指定，因为二维数组本质上还是一维数组的数组。
        String[][] ans = new String[ret.size()][];
        int i = 0;
        for (List<String> path: ret) {
            String[] tmp = new String[path.size()];
            path.toArray(tmp);  // List有toArray的方法，可将list转化成array
            ans[i++] = tmp;
        }

        return ans;
    }

    // DFS
    private void backTrack(String s, int start, List<List<String>> ret, List<String> path) {
        if (start == s.length()){ // 下标等于s的长度时，说明已经越界
            ret.add(new ArrayList<>(path));  // copy path加入结果集中
            return;
        }

        for (int i = start; i < s.length(); i++){
            // 判断每一个子串，如果是回文，加入栈path里
            String sub = s.substring(start, i+1); // 左闭右开
            if (isPalindrome(sub)){
                path.add(sub);
                backTrack(s, i+1, ret, path);  // start是子串后下个字符的开始
                path.remove(path.size()-1);
            }
        }
    }

    // TODO: 此处判断是否回文可以优化，采用一个二维数组来
    private boolean isPalindrome(String s){
        for (int i = 0, j = s.length() - 1; i < j; i++,j--){
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    static private void show(String[][] ans){
        for(String[] s: ans){
            for (String i: s){
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        PalindromePartitioning inst = new PalindromePartitioning();
        String[][] ret = inst.partition("google");
        show(ret);
    }
}

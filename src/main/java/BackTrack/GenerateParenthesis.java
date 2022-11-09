package BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 */
public class GenerateParenthesis {

    // reduction: 本题目跟求1到n个数字的全排列有点像，但是细想，是加了限制条件的，因为要考虑左右括号的闭合的有效性
    // 可以认为一共有2n个步骤，每一步可以选择"("或者")"
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();


        backTrack(ret, 0, 2 * n, "");

        return ret;
    }

    public void backTrack(List<String> ret, int idx, int n, String path){
        if (path.length() == n){
            ret.add(path);
            return ;
        }

        int leftCnt = 0;
        int rightCnt = 0;
        // 统计()的个数
        for (int j = 0; j < path.length(); j++){
            if (path.charAt(j) == '('){
                leftCnt++;
            } else {
                rightCnt++;
            }
        }

        for (int i = idx; i < n; i++){
            // 左括号不足n/2个，可以追加
            if (leftCnt < (n / 2)){
                backTrack(ret, i + 1, n, path + "(");
            }
            // 右括号不足n/2个，且少于已经存在左括号的个数时，则可以追加
            if (rightCnt < leftCnt && rightCnt < (n / 2)){
                backTrack(ret, i + 1, n, path + ")");
            }
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis inst = new GenerateParenthesis();
        for (String s : inst.generateParenthesis(3)){
            System.out.println(s);
        }
    }
}

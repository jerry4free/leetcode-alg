package StackX;

import Util.Utils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 85. Maximal Rectangle
 */
public class MaximalRectangle {
    /**
     * 单调栈
     * 在第84.柱状图中最大的矩形，的基础上做这个题目
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // sum[i][j]存储每一行的到i,j的累积最长面积
        int[][] sum = new int[m+1][n+1];
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (matrix[i-1][j-1] == '1'){
                    sum[i][j] = sum[i][j-1] + 1;
                }
            }
        }

        Utils.show2D(sum);

        int ans = 0;
        // 把矩形顺时针旋转90度，针对每一行计算这一行形成的直方图的最大面积，枚举每一列即可完成整个矩形的最大面积
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int j = 1; j <= n; j++){  // 枚举每一列
            for (int i = 1; i <= m; i++){
                // 利用单调栈算每一列作为底的直方图的最大面积, 栈中存储的是每一行的下标：i
                // 如果遇到小于栈顶高度的高度，那么可以计算栈中累积的单调递增的那些高度
                while (stack.peek() != 0 && sum[stack.peek()][j] > sum[i][j]){
                    int h = sum[stack.pop()][j];
                    int w = i - stack.peek() - 1;
                    ans = Math.max(ans, h * w);
                }
                stack.push(i);
            }

            while (stack.peek() != 0){
                int h = sum[stack.pop()][j];
                int w = m + 1 - stack.peek() - 1;
                ans = Math.max(ans, h * w);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}};
        MaximalRectangle inst = new MaximalRectangle();
        System.out.println(inst.maximalRectangle(matrix));

    }
}

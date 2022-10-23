package StackX;

import java.util.Stack;

/**
 * 84.柱状图中最大的矩形
 */
public class LargestRectangleArea {
    /**
     * 单调栈中存储下标，方便计算宽度
     * 时间和空间复杂度都是O(N)
     */
    public int largestRectangleArea(int[] heights) {
        // 栈中存储的是单调递增的高度，代表尚未确定下一次更低的高度
        // TODO：采用Deque替换掉Stack
        Stack<Integer> stack = new Stack<>();
        // base case：存储-1下标, 代表柱子最左侧的边界（不包含）
        stack.push(-1);

        int ret = 0;
        int len = heights.length;
        for (int i = 0; i < len; i++){
            // 第i个柱子小，即遇到了更低的高度，则可以算之前的累积的高柱子了。
            // 直到枚举完所有栈中内容，最左侧除外
            while(stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;  // i是柱子的右边界，stack.peek()是柱子的左边界，左开右开
                int area = height * width;
                ret = Math.max(ret, area);
            }
            stack.push(i);
        }

        // 最后栈中累积的递增柱子
        while(stack.peek() != -1){
            int height = heights[stack.pop()];
            int width = len - stack.peek() - 1;
            int area = height * width;
            ret = Math.max(ret, area);
        }

        return ret;
    }

}

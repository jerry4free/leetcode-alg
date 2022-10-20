package StackX;

import java.util.Stack;

/**
 * 739. 每日温度
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        // stack存储的是气温递减的温度下标，这样取出下标可以O(1)去设置ret，跟后面下标取差就是相隔天数
        // 在单调栈里，则表示尚未找到下一次温度更高的下标。
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int len = temperatures.length;
        int[] ret = new int[len];
        int i;
        for (i = 1; i < len; i++){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int top = stack.pop();
                ret[top] = i - top;
            }
            stack.push(i);
        }
        // 如下代码也可去掉，因为最后stack中的就是天数就是温度递降的，以后都不会存在温度比它高，填0。数组默认就是0
        while (!stack.isEmpty()){
            int top = stack.pop();
            ret[top] = 0;
        }

        return ret;
    }
}

package StackX;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 739. Daily Temperatures
 */

/**
 * 方法1：
 * 对于每个元素i，往后找直到找到大于当前的。然后再解决i+1。
 * 很明显这个是2层循环，时间复杂度最坏是：O(N2)，space：无
 *
 * 方法2：
 * 如何降低复杂时间复杂度呢？如何避免重复遍历呢？
 * 对于某个元素i，一直往后遍历直到找到大于i的温度时，这段时间积攒的温度，如何遍历一次搞定呢？
 *
 * 如果温度一直比之前低，则把这些低温存储起来（存储下标），因为温度越来越低（单调减），这些天一直未找到高温度。
 * 然后进来一个高温，则可以解决之前的积攒低温，但是能解决到多少不知道，所以倒着解决（后进先出，那么就是使用栈），直到解决不了为止。
 * 注意：如果此处把这些积攒的温度存到队列中，无法做到遍历时不重复
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
public class Temperature {

    public static int[] solution(int[] temperatures){
        Stack<Integer> stack = new Stack<>();
        int [] ret = new int[temperatures.length];

        int size = temperatures.length;
        for(int i = 0; i < size; i++){
            // 如果比之前温度低，则没找到，继续压栈（先积攒未解决的这些天）
            if (!stack.isEmpty()) {
                // 如果比栈顶的温度高，则找到了（可以解决之前积攒的天）
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    int j = stack.pop();
                    ret[j] = i - j; //则可知道栈顶温度与当前温度的距离
                }
            }
            stack.push(i);
        }

        // 最后栈里留的都是未找到最高温度的积攒的这些天
        while (!stack.isEmpty()) {
            ret[stack.pop()] = 0;
        }
        return ret;
    }


    public static void main(String[] args){

        int[] temperatures = new int[]{73,74,75,71,69,72,76,73};
        int[] expected = new int[]{1,1,4,2,1,1,0,0};
        int[] ret = solution(temperatures);
        for (int i = 0; i < expected.length; i++) {
            assert ret[i] == expected[i];
        }
    }
}

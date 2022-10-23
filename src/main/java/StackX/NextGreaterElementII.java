package StackX;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 503. Next Greater Element II
 */
public class NextGreaterElementII {

    // 单调栈
    // TODO：循环可以使用：i % n，如下可以简化为遍历0到2n-1
    public int[] nextGreaterElements(int[] nums) {
        // 栈中的数据是尚未找到更大元素的元素的下标, 所以是递减的
        Deque<Integer> stack = new ArrayDeque<>();
        int len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);

        // 遍历第1遍
        for (int i = 0; i < len; i++){
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                ans[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        // 此时栈里的就是遍历第1遍时,剩余递减的元素下标
        // 循环第2遍,直到栈为空,或者
        int i = 0;
        while (!stack.isEmpty() && i < stack.peek()){
            if (nums[i] <= nums[stack.peek()]){
                i++;
            } else {
                ans[stack.pop()] = nums[i];
            }
        }

        return ans;
    }
}

package StackX;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 581. Shortest Unsorted Continuous Subarray
 */
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 1){
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int minStartIndex = nums.length - 1;
        // 单调栈,由于数组中元素可能重复, 也不能通过哈希比<nums[i], i>来实现, 所以栈中存储元素下标
        // 单调递增
        for (int i = 0; i < nums.length; i++){
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]){
                int startIndex = stack.pop();
                minStartIndex = Math.min(startIndex, minStartIndex);
            }
            stack.push(i);
        }

        if (minStartIndex == nums.length - 1){
            return 0;
        }

        while (!stack.isEmpty()){
            stack.pop();
        }

        int minEndIndex = 0;
        // 单调递减
        for (int i = nums.length - 1; i >= 0; i--){
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                int endIndex = stack.pop();
                minEndIndex = Math.max(endIndex, minEndIndex);
            }
            stack.push(i);
        }

        return minEndIndex - minStartIndex + 1;
    }
}

package StackX;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * 496. Next Greater Element I
 */
public class NextGreaterElementI {

    // 单调栈
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> nextGreat = new HashMap<>();
        for (int n: nums2){
            while (!stack.isEmpty() && n > stack.peek()){
                int top = stack.pop();
                nextGreat.put(top, n);
            }
            stack.push(n);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++){
            ans[i] = nextGreat.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
    public static void main(String[] args) {

    }
}

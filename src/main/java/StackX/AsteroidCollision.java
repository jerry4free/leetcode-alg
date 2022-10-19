package StackX;

import java.util.Stack;

/**
 * 735. 小行星碰撞
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++){
            if (asteroids[i] > 0 || stack.isEmpty() || stack.peek() < 0){
                stack.push(asteroids[i]);
            } else {
                boolean toAdd = true;
                while (!stack.isEmpty()) {
                    if (stack.peek() < 0){
                        stack.push(asteroids[i]);
                        toAdd = false;
                        break;
                    } else if ( stack.peek() < Math.abs(asteroids[i])) {
                        stack.pop();
                    } else if (stack.peek() == Math.abs(asteroids[i])){
                        stack.pop();
                        toAdd = false;
                        break;
                    } else {
                        toAdd = false;
                        break;
                    }
                }
                if (toAdd){
                    stack.push(asteroids[i]);
                }
            }
        }

        int[] ret = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--){
            ret[i] = stack.pop();
        }
        return ret;
    }
}

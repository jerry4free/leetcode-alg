package StackX;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 */
public class ValidPathese {

    private boolean isPair(Character before, Character after) {
        if (before == '{' && after == '}') {
            return true;
        } else if (before == '[' && after == ']') {
            return true;
        } else if (before == '(' && after == ')') {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (Character c: s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()){  // case ]
                    return false;
                } else if(!isPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {  // case (
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args){
        ValidPathese v = new ValidPathese();
        assert v.isValid("()[]{}");
        assert ! v.isValid("()[({}");
        assert ! v.isValid("[");
        assert ! v.isValid("]");
        assert ! v.isValid("]]]]");
        assert v.isValid("");
    }
}

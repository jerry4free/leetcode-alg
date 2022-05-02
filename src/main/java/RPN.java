import java.util.Objects;
import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 */
public class RPN {

        private int operator(String left, String right, String op){
            Integer l = Integer.parseInt(left);
            Integer r = Integer.parseInt(right);
            if (Objects.equals(op, "+")){
                return l + r;
            } else if (Objects.equals(op, "-")) {
                return l - r;
            } else if (Objects.equals(op, "*")) {
                return l * r;
            } else {
                return l / r;
            }
        }
        public int evalRPN(String[] tokens) {
            Stack<String> stack = new Stack<>();
            String left;
            String right;
            for (String c: tokens) {
                if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(String.valueOf(operator(left, right, c)));
                } else {
                    stack.push(c);
                }
            }
            return Integer.parseInt(stack.pop());
        }

        public static void main(String[] args){
            String[] rpn = new String[]{"2","1","+","3","*"};
            RPN inst = new RPN();
            assert inst.evalRPN(rpn) == 9;
        }
}

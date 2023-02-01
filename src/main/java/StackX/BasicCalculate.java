package StackX;

import Util.Utils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 224. 基本计算器
 */
public class BasicCalculate {
    Deque<Character> ops;
    Deque<Integer> vals;

    public int calculate(String s) {
        // 使用2个栈，一个存储算子，一个存储数字
        ops = new ArrayDeque<>();
        vals = new ArrayDeque<>();
        // 负数这种边界条件的处理：
        // 开头是负数时，避免缺少第一个操作数，加上0
        vals.push(0);
        // 括号里的计算值是负数时，相同处理，加上0
        s = s.replace(" ", "").replace("(-", "(0-");
        int n = s.length();

        // 枚举所有s的字符
        for (int i = 0; i < n; ) {
            char c = s.charAt(i++);

            if (c == '(') {
                ops.push(c);
            } else if (c == '+' || c == '-'){
                // 注意：由于包含减法，所以计算必须是从左到右，从右向左计算是不正确的。
                // 比如2-1+2=3，从右到左计算是-1，是错误的。所以在遇到+之后，需要将之前积累的有效表达式2-1计算完
                // 所以当满足2-1这种有1个运算符时，就需要进行计算. 那么什么时候触发计算呢，是在读到-还是读到1时呢？如果被加数很长呢？比如2-11111
                // 所以可以在遇到遇到运算符时，将之前积累的合法表达式计算。比如遇到+时，计算2-1
                while (!ops.isEmpty() && ops.peek() != '(') {
                    cal();
                }
                ops.push(c);
            } else if (c == ')') {
                // 遇到）开始计算，直到遇到（
                while (!ops.isEmpty() && ops.peek() != '(') {
                    cal();
                }
                if (!ops.isEmpty() && ops.peek() == '(') {
                    ops.pop();
                }
            } else {
                // push digit to vals
                int val = c - '0';
                while (i < n && isDigit(s.charAt(i))) {
                    val *= 10;
                    val += s.charAt(i++) - '0';
                }
                vals.push(val);
            }
        }

        System.out.println("begin to read stack");
        Utils.show(ops);
        Utils.show(vals);
        while (!ops.isEmpty()) {
            cal();
        }

        return vals.pop();
    }

    private void cal(){
        int r = vals.pop();
        int l = vals.pop();
        char op = ops.pop();
        int ret;
        if (op == '-') {
            ret = l - r;
        } else {
            ret = l + r;
        }
        vals.push(ret);
        System.out.println(r + "+" + l + "=" + ret);
    }

    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        BasicCalculate inst = new BasicCalculate();
//        System.out.println("expected 3, real: " + inst.calculate("1+2"));
//        System.out.println("expected 1, real: " + inst.calculate("-1+2"));
//        System.out.println("expected 3, real: " + inst.calculate("2-1 + 2"));
//        System.out.println("expected 3, real: " + inst.calculate("(2-1 + 2)"));
//        System.out.println("expected -1, real: " + inst.calculate("(2-(1 + 2))"));
        System.out.println("expected -1, real: " + inst.calculate("(2-(-1 + 2))"));
//        System.out.println("expected 17, real: " + inst.calculate("(20-(1 + 2))"));
    }
}

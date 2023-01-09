package StackX;

import java.util.Stack;

/**
 * 394: 字符串编码
 */
public class EncodeString {
    public String decodeString(String s) {
        // 2个栈，栈1存储数字，栈2存储字符
        Stack<Integer> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        int num = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z'){
                stack2.push(c);
            } else if (c >= '0' && c <= '9') {
                num *= 10;
                num += (c - '0');
            } else if (c == '['){
                // 整数结束，压入栈1
                stack1.push(num);
                stack2.push(c);
                num = 0;
            } else if (c == ']'){
                // 只有遇到]，才能将到上一个[到现在的字符进行处理
                String curr = "";
                while (!stack2.isEmpty() && stack2.peek() != '['){
                    curr = stack2.pop() + curr;
                }
                stack2.pop(); // pop [
                int cnt = stack1.pop(); // pop number
                String currItem = get(cnt, curr);
                for (char c1: currItem.toCharArray()){
                    stack2.push(c1);
                }
            }
        }


        String ret = "";
        while (!stack2.isEmpty()){
            ret = stack2.pop() + ret;
        }

        return ret;
//        return decodeString(1, s);

    }

    private String get(int cnt, String s){
        String ret = "";
        for (int i = 0; i < cnt; i++){
            ret += s;
        }
        return ret;
    }
}

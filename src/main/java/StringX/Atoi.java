package StringX;

/**
 *8. String to Integer (atoi)
 */
public class Atoi {
    // 官方题解竟然是动态机
    public int myAtoi(String s) {
        int flag = 1;
        long ret = 0;
        // ignore any leading whitespace
        String s1 = s.trim();
        if (s1.equals("")){
            return 0;
        }

        // check the next character is - or +
        int i = 0;
        if (s1.charAt(0) == '-'){
            i++;
            flag = -1;
        } else if (s1.charAt(0) == '+'){
            i++;
        }

        // Read in next the characters until the next non-digit character or the end of the input is reached
        for (; i < s1.length() && s1.charAt(i) >= '0' && s1.charAt(i) <= '9'; i++){
            char c = s1.charAt(i);
            ret *= 10;
            ret += c - '0';
            if (ret * flag > Integer.MAX_VALUE ){
                return Integer.MAX_VALUE;
            } else if (ret * flag < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
//        System.out.println(ret);

        ret *= flag;
        return (int)ret;
    }

    public static void main(String[] args) {
        Atoi inst = new Atoi();
        System.out.println("expected: 0, real: " + inst.myAtoi(""));
        System.out.println("expected: -42, real: " + inst.myAtoi("-42"));
        System.out.println("expected: 789, real: " + inst.myAtoi("hello word 789"));
        System.out.println("expected: 2147483647, real: " + inst.myAtoi("9223372036854775808"));
        System.out.println("expected: 12345678, real: " + inst.myAtoi("  0000000000012345678"));
        System.out.println("expected: -2147483648, real: " + inst.myAtoi("-91283472332"));
    }
}

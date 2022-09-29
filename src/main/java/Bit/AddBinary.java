package Bit;

/**
 * 67: 二进制求和
 */
public class AddBinary {

    // 自己写的第一版
    public String addBinary2(String a, String b) {

        int m = a.length() - 1;
        int n = b.length() - 1;
        int l = 0;
        StringBuilder s = new StringBuilder();
        while (m >= 0 && n >= 0) {
            int i = a.charAt(m--) - '0';
            int j = b.charAt(n--) - '0';
//            System.out.printf("i:%d, j:%d, l:%d%n", i, j, l);
            s.append(i ^ j ^ l);
            if ((i + j + l) >= 2){
                l = 1;
            } else {
                l = 0;
            }
        }

        if (m >= 0){
            while (m >= 0){
                int i = a.charAt(m--) - '0';
                s.append(i ^ l);
                l = i & l;
            }
            if (l == 1){
                s.append(l);
            }
        } else if (n >= 0){
            while (n >= 0){
                int j = b.charAt(n--) - '0';
                s.append(j ^ l);
                l = j & l;
            }
            if (l == 1){
                s.append(l);
            }
        } else if (l == 1){
            s.append(l);
        }

        return s.reverse().toString();
    }

    // 看完官方解答，这代码真是简洁明了，都是当成一般情况去处理
    // 自己写的反而很多if else。多了很多case去处理
    public String addBinary(String a, String b) {
        int m = a.length() - 1;
        int n = b.length() - 1;
        StringBuilder ret = new StringBuilder();
        int carry = 0;
        while (m >= 0 || n >= 0) {
            if (m >= 0){
                carry += a.charAt(m--) - '0';
            }
            if (n >= 0){
                carry += b.charAt(n--) - '0';
            }
            ret.append(carry % 2);  //二进制加法，当前位就是模2
            carry /= 2;  // 进位就是/2
        }
        if (carry == 1){
            ret.append("1");
        }
        return ret.reverse().toString();
    }


    public static void main(String[] args) {
        AddBinary inst = new AddBinary();
        System.out.println(inst.addBinary("11", "10"));
        System.out.println(inst.addBinary("1011", "1011"));
        System.out.println(inst.addBinary("111", "111"));
        System.out.println(inst.addBinary("1", "111"));

    }
}
package Bit;

public class Divide {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int tag = 2;
        if (dividend > 0) {
            dividend = -dividend;
            tag--;
        }
        if (divisor > 0) {
            divisor = -divisor;
            tag--;
        }
//        Integer.MIN_VALUE;

        int ret = 0;
        while (dividend <= divisor){
            int kd = divisor;
            int k = 1;
            while (kd > dividend - kd){  // 如果kd + kd > dividend, 采用加法，两个大数相加可能越界，采用减法不会
                kd <<= 1;
                k <<= 1;
            }

            dividend -= kd;
            ret += k;
        }

        if (tag == 1){
            return -ret;
        } else {
            return ret;
        }
    }

    public static void main(String[] args) {
        Divide inst = new Divide();
        System.out.println(inst.divide(15, 2));
        System.out.println(inst.divide(2147483647, 3)); //715827882
    }
}

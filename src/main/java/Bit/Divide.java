package Bit;

/**
 * 29 两数相除
 */
public class Divide {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 因为int的范围是-2^31, 2^31-1, 所以-2^31转成正数会越界。所以反过来不会越界
        int tag = 2;
        if (dividend > 0) {
            dividend = -dividend;
            tag--;
        }
        if (divisor > 0) {
            divisor = -divisor;
            tag--;
        }

        int ret = 0;
        while (dividend <= divisor){
            // 成倍的去减，借鉴倍增的思想，用来加快减法
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

    // 用加法实现，快速乘
    static long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
//            System.out.println("ans:" + ans + ",k:" + k);
            k >>= 1;
            a += a;
        }
        return ans;
    }


    public static void main(String[] args) {
        Divide inst = new Divide();
        System.out.println(inst.divide(15, 2));
        System.out.println(inst.divide(2147483647, 3)); //715827882

        System.out.println(mul(101L, 5L));
    }
}

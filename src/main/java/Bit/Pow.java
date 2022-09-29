package Bit;

/**
 * 50
 */
public class Pow {
    public double myPow(double x, int n) {
        if (n == 0){
            return 1D;
        }
        int flag = (n < 0) ? -1: 1;
        long N = n;
        if (N < 0){
            N = -N;
        }

        // 采用相乘实现幂，倍增的思想来加快相乘
        double ret = 1.0;
        while (N > 0){
            if ((N & 1) == 1) ret *= x;
            N >>= 1;
            x *= x;
        }
        return (flag < 0) ? (1/ret) : ret;
    }

    public static void main(String[] args) {

        Pow inst = new Pow();
        System.out.println(inst.myPow(2.0, 10));
        System.out.println(inst.myPow(2.1, 3));
        System.out.println(inst.myPow(2.0, -2));
        System.out.println(inst.myPow(2.0, -200000));
    }
}

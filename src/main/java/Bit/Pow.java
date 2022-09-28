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
        // -2147483648取反时，要注意避免越界
        // Integer.MIN_VALUE取反，还是自身。调用abs也还是自身，本身避免了越界
        n = Math.abs(n);

        // 采用相乘实现幂，倍增的思想来加快相乘
        double ret = 1.0;
        while (n > 0){
            if ((n & 1) == 1) ret *= x;
            n >>= 1;
            x *= x;
        }

//        System.out.println(flag);
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

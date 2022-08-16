package BinarySearch;

/**
 * 69. Sqrt(x)
 */
public class mySqrt {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int l = 1;
        int r = x;
        while (l <= r){
            int m = l + (r - l) / 2;
            // 1. 注意此处很容易写为：m * m == x，大数相乘会越界
            // 所以int的乘法和加法需要注意越界，改为减法或除法
            // 2. 改为除法后，需要注意m可能为负数，所以l必须从1开始
            if (m == x / m){
                return m;
            } else if (m < x / m){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // 循环结束后，r在l左边，而且有个性质：l左边都比target小，r右边都比target大
        return r;
    }

    /**
     * 二分查找：代码最简洁。越界就改为long，避免越界
     */
    public int mySqrt2(int x) {
        int l = 0;
        int r = x;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (((long) m * m) == x){
                return m;
            } else if (((long) m * m) < x){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        mySqrt inst = new mySqrt();
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(inst.mySqrt(0));
        System.out.println(inst.mySqrt(1));
        System.out.println(inst.mySqrt(4));
        System.out.println(inst.mySqrt(8));
        System.out.println(inst.mySqrt(28));
        System.out.println(inst.mySqrt(Integer.MAX_VALUE));
    }
}

package DynamicPrograming;

/**
 * 338. 比特位计数
 */
public class CountBits {
    public int[] countBits(int n) {
        // 1111的最高有效位是1000，
        // 101的最高有效位是100，
        // i的最高有效位:即i的二进制数字，最高位是1其余位是0。
        int[] bits = new int[n+1];
        int highBit = 0;
        for (int i = 1; i <= n; i++){
            // 如果一个数字i，i & (i-1) = 0，那么i一定是最高位是1，其余位都是0。即i是2的整数幂，那么i此时就是一部分数字的最高有效位
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }

        return bits;
    }

    public int[] countBits2(int num) {
        int[] ret = new int[num+1];
        for (int i = 1;i < num+1; i++){
            // 动态规划，分2部分，第1位是否是1，其余位就是右移一位
            ret[i] = ret[i>>1] + (i&1);
        }
        return ret;
    }

    public static void main(String[] args) {

        CountBits inst = new CountBits();
        inst.countBits(2);
        inst.countBits(7);

    }
}

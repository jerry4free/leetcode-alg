package Bit;

/**
 * 137. Single Number II
 */
public class SingleNumberII {

    // 将int值的每一比特位拆分看，
    // 如果这一位能被3整除，说明这位是1,即那出现一次的那个数在这一位是0
    // 如果不能被3整除，说明这位是1，即那出现一次的那个数在这一位是1
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++){
            int s = 0;
            for (int n: nums){
                s += (n >> i & 1); //第i位求和
            }
            if (s % 3 == 1){  // 第i位置1
                ret |= (1 << i);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        SingleNumberII inst = new SingleNumberII();
        System.out.println(inst.singleNumber(new int[]{2,2,3,2}));
        System.out.println(inst.singleNumber(new int[]{0,1,0,1,0,1,99}));
    }
}

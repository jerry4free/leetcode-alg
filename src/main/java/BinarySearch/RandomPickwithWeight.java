package BinarySearch;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 */
public class RandomPickwithWeight {

    private int[] preSum;
    private int total;

    /**
     * 假设数组是3，1，2，4，那么返回下标0，1，2，4的概率分别是30%，10%，20%，40%。
     * 一种笨办法是：重建一个数组，分别存储3个3，1个1，2个2，4个4，这样random(10)取出的就是对应的数字，然后基于数字：下标的索引去找到索引
     * 但是权重可能是10000，所以这种空间复杂度会很大。
     *
     * 如何将random(10)随机出来的0～9分别映射到3，1，2，4的下标0,1,2,3呢？
     * 或者说怎么将0～9映射到[0,2]，[3,3], [4,5], [6,9]这4个区间呢，或者怎么到区间的左边界或者右边界呢：怎么到0，3，4，6？或者2，3，5，9或者3，4，6，10
     *
     * 有序数组，可以通过二分查找快速找到随机数的区间，从而定位到区间的下标
     */
    public RandomPickwithWeight(int[] w){
        int len = w.length;
        preSum = new int[len];
        // preSum[i]代表i之前的数字之和
        // 下面的方法是找区间的左边界,所以是左闭右开
        // 3,1,2,4的前缀和就是0,3,4,6，
        for (int i = 1; i < len; i++){
            preSum[i] = preSum[i-1] + w[i-1];
        }
        total = preSum[len-1] + w[len-1];
    }

    // 可以O(lgN)找到下标
    public int pickIndex() {
        int k = new Random().nextInt(total);

        // 通过二分查找找到要插入的k的左侧下标
        return floor(k);
    }

    // 查找小于等于k的最大数字
    public int floor(int k) {
        int l = 0;
        int r = preSum.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (preSum[m] == k){
                return m;
            } else if (preSum[m] > k){
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        RandomPickwithWeight inst = new RandomPickwithWeight(new int[]{1,2,3,4});

        System.out.println(inst.floor(7) + ", expected: 3");
        System.out.println(inst.floor(8) + ", expected: 3");
        System.out.println(inst.floor(9) + ", expected: 3");
        System.out.println(inst.floor(0) + ", expected: 0");

        System.out.println("====1,2,3,4====");
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
        System.out.println(inst.pickIndex());
    }
}

package PriorityQueueX;

/**
 * 719. Find K-th Smallest Pair Distance
 */
import Util.Utils;

import java.util.*;

public class FindKthSmallestPairDistance {

    /**
     * 本方法not AC，当k很大时时间超限，有个测试用例的k是25000000！！！
     * 时间复杂度：O(max(N,k) * lgN)
     * 空间复杂度：O(N)
     */
    public int smallestDistancePair(int[] nums, int k) {
        // 多路合并的思想.
        // reduction: 由于每一路都有序,可以通过最小堆来进行合并
        int n = nums.length;
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                // 2个数对差的差
                return (nums[ints[1]] - nums[ints[0]]) - (nums[t1[1]] - nums[t1[0]]);
            }
        });

        // 先进行排序, 那么相邻的数一定是数对距离最小的
        Arrays.sort(nums);

        // 不满k个,且最小数对的数还有就加入到优先队列中
        for (int i = 0; i < n - 1; i++){
            q.offer(new int[]{i, i+1});
        }

        Utils.show(nums);

        int ret = 0;
        int[] minIndex;
        while (k > 0 && !q.isEmpty()){
            minIndex = q.poll();
            int i = minIndex[0];
            int j = minIndex[1];
//            System.out.println("i:" + i + ",j:" + j);
            if (k == 1){
                ret = nums[j] - nums[i];
            }
            k--;
            if (j < n - 1){
                q.offer(new int[]{i, j+1});
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        FindKthSmallestPairDistance inst = new FindKthSmallestPairDistance();
//        System.out.println(inst.smallestDistancePair(new int[]{1,3,1}, 1) + ", expected: 0");
//        System.out.println(inst.smallestDistancePair(new int[]{1,6,1}, 3) + ", expected: 5");
        System.out.println(inst.smallestDistancePair(new int[]{62,100,4}, 2) + ", expected: 58");
    }
}

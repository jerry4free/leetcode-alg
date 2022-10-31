package PriorityQueueX;

/**
 * 719. Find K-th Smallest Pair Distance
 */
import java.util.*;

public class FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        // 多路合并的思想.
        // reduction: 由于每一路都有序,可以通过最小堆来进行合并
        int n = nums.length;
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return (nums[ints[0]] + nums[ints[1]]) - (nums[t1[0]] + nums[t1[1]]);
            }
        });

        // 先进行排序, 那么相邻的数一定是数对距离最小的
        Arrays.sort(nums);

        // 不满k个,且最小数对的数还有就加入到优先队列中
        for (int i = 0, j = 0; i < n - 1 && j < k; i++, j++){
            q.offer(new int[]{i, i+1});
        }

        int ret = 0;
        int[] minIndex;
        while (k > 0 && !q.isEmpty()){
            minIndex = q.poll();
            int i = minIndex[0];
            int j = minIndex[1];
            if (k == 1){
                ret = nums[i] + nums[j];
            }
            k--;
            if (j + 1 < n - 1){
                q.offer(new int[]{i, j+1});
            }
        }

        return ret;
    }
}

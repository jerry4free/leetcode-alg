package PriorityQueueX;

import java.util.*;

/**
 * 373. Find K Pairs with Smallest Sums
 */
public class FindKPairswithSmallestSums {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return (nums1[ints[0]] + nums2[ints[1]]) - (nums1[t1[0]] + nums2[t1[1]]);
            }
        });

        // 最小的是nums1[0], nums2[0], 最大的是nums1[m-1], nums2[n-1],
        // 那么第2大的一定是nums1[1], nums2[0]和nums1[0], nums2[1]中的一个, 假如nums1[1], nums2[0]是第2大
        // 那么,第3大的是nums1[0], nums2[1], nums1[1], nums2[2]和nums1[2], nums2[0]中的一个

        // 假设nums1[0], nums[0]可以表示成(0,0), 那么nums1和nums2的所有下标可以组成的如下图：
        // (0,0), (0,1), (0,2), (0,3), (0,4)
        // (1,0), (1,1), (1,2), (1,3), (1,4)
        // (2,0), (2,1), (2,2), (2,3), (2,4)
        // (3,0), (3,1), (3,2), (3,3), (3,4)
        // 由于nums1和nums2都是有序递增的，那么左上角的(0,0)是最小值，右下角的(3,4)是最大值
        // 将上图顺时针旋转45度，那么上图就类似一个最小堆, 但是每一层是无法判断大小的，只知道每个节点比它的右侧和下侧是小的。

        // 多个节点无法判断大小，多路合并，可以想到用最小堆来算

        // 由于每取出一个最小值的下标:(i,j)，那么将比它稍微大的2个就是：(i+1,j),(i,j+1)，将其加入优先队列中进行比较
        // 对于(0,1)和(1,0)，都会将(1,1)重复加入到队列中。
        // 所以为了避免重复加入, 现将第一列k个都加入到队列中，即:(0,0),(1,0),(2,0)...(k,0)
        // 这样每次对于(i,j)，都只需要将(i,j+1)加入到队列。即它的同行后一列加入队列，进行比较
        int[] minIndex;
        for (int i = 0; i < Math.min(k, m); i++){
            q.offer(new int[]{i, 0});
        }

        // 如果不满k，且队列不为空
        while (k > 0 && !q.isEmpty()){
            minIndex = q.poll();
            int i = minIndex[0];
            int j = minIndex[1];
            // 弹出最小的，并将其加入结果中
            List<Integer> pair = new ArrayList<>();
            pair.add(nums1[i]);
            pair.add(nums2[j]);
            ret.add(pair);
            k--;
            if (j < n - 1){ // j别越界
                q.offer(new int[]{i, j+1});
            }
        }

        return ret;
    }

    static void show(List<List<Integer>> ret){
        for (List<Integer> l: ret){
            System.out.print(l.get(0) + "," + l.get(1) + ";");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindKPairswithSmallestSums inst = new FindKPairswithSmallestSums();
        List<List<Integer>> ret = inst.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3);
        show(ret);
    }
}

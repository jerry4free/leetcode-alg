package PriorityQueueX;

import Util.Utils;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 347. Top K Frequent Elements
 */
public class TopKFrequentElements {

    static class Frequent implements Comparable<Frequent> {
        int n;
        int cnt;

        Frequent(int n, int cnt){
            this.n = n;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Frequent that){
            return this.cnt - that.cnt;
        }

        @Override
        public String toString() {
            return "Frequent{" +
                    "n=" + n +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // 统计每个元素和其频率
        HashMap<Integer, Frequent> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(nums[i])){
                map.get(nums[i]).cnt++;
            } else {
                map.put(nums[i], new Frequent(nums[i], 1));
            }
        }

        // 直接遍历map
        // 采用小根堆保存,对频率进行排序
        PriorityQueue<Frequent> q = new PriorityQueue<>();
        int i = 0;
        for (Frequent f: map.values()){
            if (i < k){
                q.offer(f);
            } else if (f.cnt > q.peek().cnt) {
                q.poll();
                q.offer(f);
            }
            i++;
        }

        int[] ret = new int[k];
        for (int j = 0; j < k; j++){
            ret[j] = q.poll().n;
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,1,-1,2,-1,2,3};
        TopKFrequentElements inst = new TopKFrequentElements();
        Utils.show(inst.topKFrequent(nums, 2));
    }
}

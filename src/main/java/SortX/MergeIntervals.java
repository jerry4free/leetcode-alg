package SortX;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 56. Merge Intervals
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // 如何对元素是数组的数组，进行排序呢？采用标准的Comparator
        // 先对区间按照开始位置进行排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));

        // 注意：数组初始化需要设置长度；
        int[][] ret = new int[intervals.length][2];
        int idx = -1;
        for(int[] v: intervals){
            if (idx == -1 || v[0] > ret[idx][1]){  // 初次直接添加, 或者这次开始位置比上次的末尾位置还要大，那么就不存在重合，因为区间已经排序过了，后面的开始位置比当前还要大
                ret[++idx] = v;
            } else {  // 如果这次开始值比上次的末尾值要小，那么存在重合区间，而且只需要更新末尾值。因为当前区间的开始位置大于等于之前区间
                ret[idx][1] = Math.max(v[1], ret[idx][1]);
            }
        }

        return Arrays.copyOf(ret, idx+1);
    }

    public static void show(int[][] intervals){
        for(int[] interval: intervals){
            System.out.print(interval[0] + ":" + interval[1] + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        MergeIntervals inst = new MergeIntervals();
        show(inst.merge(intervals));
    }
}

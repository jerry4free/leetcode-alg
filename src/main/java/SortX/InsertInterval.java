package SortX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 57. Insert Interval
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        List<int[]> ret = new ArrayList<>();

        // 区间一共可以分为3部分:处于新区间左侧不重合的部分,跟新区间有重合的部分, 处于新区间右侧不重合的部分

        // 处于新区间左侧不重合的部分, 即：新区间开始位置大于每个区间的结束位置
        int i = 0;
        while (i < len && newInterval[0] > intervals[i][1]){
            ret.add(intervals[i]);
            i++;
        }

        // 跟新区间有重合的部分，新区间结束位置 >= 某个开始位置（TODO:注意）
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < len && newInterval[1] >= intervals[i][0]){
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        
        ret.add(new int[]{start, end});  // 包括新区间处于最右侧完全没交集的情况

        // 剩下就是处于新区间右侧不重合的部分，
        while (i < len){
            ret.add(intervals[i]);
            i++;
        }

        int[][] ans = new int[ret.size()][2];
        for (i = 0; i < ret.size(); i++){
            ans[i] = ret.get(i);
        }

        return ans;
    }

    /*
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int s = newInterval[0];
        int e = newInterval[1];
        int i = Arrays.binarySearch(intervals, s, Comparator.comparingInt(v -> v[0]));
        int j = Arrays.binarySearch(intervals, e, Comparator.comparingInt(v -> v[1]));

        boolean isStartOverLap = false;
        int startInsert = -1;
        if (i < 0){ // 没找到，就是开始位置与所有区间开始位置不一样
            startInsert = -(i+1); // 开始位置要插入的点
            if (s < intervals[startInsert][1]) { // 开始存在重合
                isStartOverLap = true;
            }
        }

        boolean isEndOverLap = false;
        int endInsert = -1;
        if (j < 0){
            endInsert = -(i+1);
            if (e >= intervals[endInsert][0]) { // 结尾存在重合
                isEndOverLap = true;
            }
        }


        List<int[]> ret = new ArrayList<>();
        i = 0;
        while (i < intervals.length) {
            if (i == startInsert){
                if (isStartOverLap){
                    if (isEndOverLap){
                        ret.add(new int[]{intervals[startInsert][0], intervals[endInsert][1]});
                        i = endInsert;
                    } else { // 只跟后半部分重合
                        int end = Math.max(newInterval[1], intervals[i][1]);
                        ret.add(new int[]{intervals[i][0], end});
                    }
                } else {
                    if (isEndOverLap){ // 只跟前半部分重合
                        int start = Math.min(newInterval[0], intervals[i][0]);
                        ret.add(new int[]{start, intervals[i][1]});
                    } else {  // 没有不重合
                        ret.add(newInterval);
                    }
                }
            }
            ret.add(intervals[i]);
            i++;
        }

        int[][] ans = new int[ret.size()][2];
        for (i = 0; i < ret.size(); i++){
            ans[i] = ret.get(i);
        }

        return ans;
    }
     */

    public static void show(int[][] intervals){
        for(int[] interval: intervals){
            System.out.print(interval[0] + ":" + interval[1] + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        InsertInterval inst = new InsertInterval();
        show(inst.insert(new int[][]{{1, 3}, {6,9}}, new int[]{2,5}));
        show(inst.insert(new int[][]{}, new int[]{2,5}));
        show(inst.insert(new int[][]{{1,5}}, new int[]{2,3}));
        show(inst.insert(new int[][]{{1,2}}, new int[]{3,5}));
    }
}

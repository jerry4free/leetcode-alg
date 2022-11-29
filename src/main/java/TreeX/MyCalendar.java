package TreeX;

import java.util.Map;
import java.util.TreeMap;

/**
 * 729. 我的日程安排表 I
 */
public class MyCalendar {
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }

    /**
     * 假设已经有一些合法的时间区间存在某种数据结构中。
     * 那么对于一个[m,n)，只需要
     * 1。找到小于等于m的最大开始起点，找到其终点，看其是否大于m
     * 2。找到大于等于m的最小开始起点，看其是否小于n
     * 如果满足其一，就是跟[m,n)有交集
     *
     * 所以如何更高效的查找呢？红黑树可以O(lgK)支持floor和ceiling
     */
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entry = map.floorEntry(start);
        if (entry != null && entry.getValue() > start){
            return false;
        }

        Integer ceilingKey = map.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end){
            return false;
        }

        map.put(start, end);

        return true;
    }
}

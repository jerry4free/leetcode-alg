package QueueX;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 041. 滑动窗口的平均值
 * 346
 */
public class MovingAverage {
    Queue<Integer> q;
    int capacity;
    int total;
    int count;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q = new ArrayDeque<>();
        capacity = size;
        total = 0;
        count = 0;
    }

    public double next(int val) {
        if (this.count < capacity){
            this.count++;
        } else {
            total -= q.remove();
        }
        q.add(val);
        total += val;
        return (double)total / count;
    }
}

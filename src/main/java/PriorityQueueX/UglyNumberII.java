package PriorityQueueX;

import java.util.*;

/**
 * 264. Ugly Number II
 */
public class UglyNumberII {

    // 时间复杂度O(NlgN),空间复杂度O(N)
    final public static int[] primes = new int[]{2,3,5};
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        HashSet<Long> s = new HashSet<>();
        q.add(1L);
        s.add(1L);

        long ret = 0;
        for (int i = 0; i < n; i++){
            ret = q.remove();  // O(1)
            for (int j = 0; j < 3; j++){
                long next = primes[j] * ret;
                if (!s.contains(next)){
                    q.add(next); // O(lgN)
                    s.add(next);
                }
            }
        }

        return (int)ret;
    }

    public static void main(String[] args) {
        UglyNumberII inst = new UglyNumberII();
        System.out.println("expected: 12, real:" + inst.nthUglyNumber(10));
        System.out.println("expected: 536870912, real:" + inst.nthUglyNumber(1407));
    }
}

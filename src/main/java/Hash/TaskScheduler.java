package Hash;

import java.util.HashMap;

/**
 * 621. Task Scheduler
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> freq = new HashMap<>();

        // 计算每种任务的数量
        int maxRunCnt = 0; // 所有任务的最大执行次数
        for (char t: tasks){
            int runCnt = freq.getOrDefault(t, 0) + 1;
            freq.put(t, runCnt);
            maxRunCnt = Math.max(runCnt, maxRunCnt);
        }

        // 执行次数最多的任务的数量
        int maxTaskCnt = 0;
        for (Character c: freq.keySet()){
            if (freq.get(c) == maxRunCnt){
                maxTaskCnt++;
            }
        }

        // 由题意可知,必须保证n+1个必须是不重复的, 执行次数最多是maxRun, 则考虑一个(n+1) * maxRun的矩阵
        // 1. 那么可以优先安排次数最多的任务,放到第一列, 假设其他任务都能放到这个矩阵里, 此时有冷却时间也可能没有
        // 那么此时所有任务的最短时间就是:
        int a = (maxRunCnt - 1) * (n + 1) + maxTaskCnt;
        // 2. 如果假设1不成立, 其他任务的数量放到矩阵后,仍然剩余多个, 此时所有时间是大于a的. 没有冷却时间
        int b = tasks.length;
        // 所以总的最短时间,
        return Math.max(a, b);
    }
}

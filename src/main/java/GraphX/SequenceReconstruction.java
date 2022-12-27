package GraphX;

import java.util.*;

/**
 * 444:
 * 剑指 Offer II 115. 重建序列
 */
public class SequenceReconstruction {

    /**
     * 时间空间都是O(V+E), V是nums的长度，E是sequences中所有序列的长度之和，即边的数量
     */
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        // 下标0不算
        int len = nums.length + 1;
        int[] inDegree = new int[len];
        List<Integer>[] graph = new ArrayList[len];
        for (int i = 0; i < len; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] seq: sequences){
            for (int i = 1; i < seq.length; i++){
                graph[seq[i-1]].add(seq[i]);
                inDegree[seq[i]]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < len; i++){
            if (inDegree[i] == 0){
                q.add(i);
            }
        }
        int cnt = 0;
        while (!q.isEmpty()){
            // 1. 判断是否可以拓扑排序, 且仅有一种拓扑排序
            if (q.size() > 1){
                return false;
            }
            int v = q.remove();
            // 2. 判断nums,是否等于这一种拓扑排序, 没必要单独存储拓扑排序
            if (nums[cnt++] != v){
                return false;
            }
            for (int w: graph[v]){
                inDegree[w]--;
                if (inDegree[w] == 0){
                    q.add(w);
                }
            }
        }

        // 最后如果cnt 小于nums.length, 说明存在环,不存在超序列。等于nums.length，说明存在一个拓扑排序

        return cnt == nums.length;
    }
}

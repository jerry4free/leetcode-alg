package Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128. 最长连续序列
 */
public class LongestConsecutiveSequence {

    // worse case：2N
    // time:O(n)
    public int longestConsecutive2(int[] nums) {
        int maxLength = 0;
        HashMap<Integer, Integer> indices = new HashMap<>();


        // 因为连续，所以可以用hash表快速查找，O(1)
        for(int i = 0; i < nums.length; i++){
            indices.put(nums[i], i);
        }

        // x,x+1,x+2,..., x+y. 要从x开始遍历，而不是x+1
        for (int i = 0; i < nums.length; i++) {
            int currentLength = 1;

            // 这道题的预置假设：连续且步长为1(因为步长为1，所以没必要存储路径。)
            // 如果有前驱（即：不是连续序列中最小的数），就不开始遍历。因为遍历也是浪费的
            // 如果没有前驱，开始逐个遍历后驱，直到遍历结束
            if (indices.get(nums[i] - 1) == null) {
                int next = nums[i] + 1;
                while (indices.containsKey(next)) {
                    next += 1;
                    currentLength += 1;
                }
            }

            // 更新
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }

        return maxLength;
    }

    Set<Integer> visited;
    HashMap<Integer, Integer> parent;
    HashMap<Integer, Integer> sz;  // 每个连通通量的大小

    /**
     * 1。要找出最长的连续序列的长度，如果把图中的每个整数当作顶点，而前后相差1的数字是连续的，就是一条边，
     * 那么这个题就可以规约为求无向图中的最大连通通量的大小。但是数组中的元素是无序的，对于一个元素nums[i]，nums[i]+1和nums[i]-1在数组中可能是不连续的，
     * 如果遍历一遍数组来确定nums[i]的边，时间复杂度是O(n^2)，不可接受。如何优化时间复杂度呢？可以借助于一个哈希集合，O(1)去判断前后顶点是否存在
     *
     * 2。那么这是一个图的连通性问题。可以用图的遍历算法（BFS或者DFS），也可以用并查集来解决。
     * 图的那2种遍历方法都是O(V+E)，数组元素大小为N，由于顶点和边大小都不超过N，所以时间复杂度是O(N)
     *
     * 3。跟一般的图不一样的是元素大小可能是负的。所以不能用邻接表数组或者父链接数组，得用哈希表
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0){
            return 0;
        }

        parent = new HashMap<>();
        sz = new HashMap<>();
        visited = new HashSet<>();

        for (int n: nums){
            parent.put(n, n);
            sz.put(n, 1);
            visited.add(n);
        }
        int ret = 1;

        for (int v: nums){
            if (visited.contains(v+1)){
                union(v, v+1);
            }

            if (visited.contains(v-1)){
                union(v, v-1);
            }
        }

        for (Map.Entry<Integer, Integer> p: sz.entrySet()){
            System.out.println(p.getKey() + ":" + p.getValue());
            ret = Math.max(ret, p.getValue());
        }

        return ret;
    }


    private int find(int v){
        if (v != parent.get(v)){
            parent.put(v, find(parent.get(v)));  // 路径压缩
        }
        return parent.get(v);
    }

    private void union(int v, int w){
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot != wRoot){
            if (sz.get(vRoot) < sz.get(wRoot)){
                parent.put(vRoot, wRoot);
                sz.put(wRoot, sz.get(wRoot) + sz.get(vRoot));
            } else {
                parent.put(wRoot, vRoot);
                sz.put(vRoot, sz.get(vRoot) + sz.get(wRoot));
            }
        }
    }


    public static void main(String[] args) {
        int [] nums = new int[]{100,4,200,1,3,2};
        int [] nums2 = new int[]{0,3,7,2,5,8,4,6,0,1};
        LongestConsecutiveSequence inst = new LongestConsecutiveSequence();
        System.out.println("expected 4, real: " + inst.longestConsecutive(nums));
        System.out.println("expected 9, real: " + inst.longestConsecutive(nums2));
    }
}

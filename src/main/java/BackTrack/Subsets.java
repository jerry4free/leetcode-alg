package BackTrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 78. 子集
 */
public class Subsets {

    /**
     * 这种方法类似于树型结构的BFS。
     * 思路：求解问题的每一步可看作树中的一个节点，一共有n个元素，对于每个元素，都有选和不选2个步骤（类似二叉树的左右节点）
     * 算法分析：一共有n步，每步都是2个选择，一共n个2相乘，所以有2^n个节点。时间复杂度：O(2^n)，空间复杂度：O(n)
     */
    public List<List<Integer>> subsets2(int[] nums) {

        List<List<Integer>> prev = new ArrayList<>();
        // 先加一个空的List
        prev.add(new ArrayList<>());

        // 外层循环就是枚举nums
        for (int n : nums){
            List<List<Integer>> curr = new ArrayList<>();
            // 内层循环，遍历上一层的所有子集，在它基础上加上当前数
            for (List<Integer> set: prev){
                // 不加，左节点
                List<Integer> l1 = new ArrayList<>(set);
                curr.add(l1);

                // 加，右节点
                List<Integer> l2 = new ArrayList<>(set);
                l2.add(n);
                curr.add(l2);
            }
            prev = curr;
        }

        return prev;
    }

    /**
     * DFS
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(nums, 0, new ArrayDeque<>(), ans);

        return ans;
    }

    /**
     * 进行深度优先遍历
     * @param nums：输入数组
     * @param level: 当前层级，从0开始
     * @param path：深度遍历时当前的栈（路径）
     * @param ans：输出结果列表
     */
    private void dfs(int[] nums, int level, Deque<Integer> path, List<List<Integer>> ans){
        // base case，如果数据已经遍历完，到达了最终的状态（到叶子节点），则加入结果列表
        if (level == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }

        // 不加，左节点
        dfs(nums, level + 1, path, ans);

        // 加，右节点
        path.push(nums[level]);  // 将当前的层级节点入栈
        dfs(nums, level + 1, path, ans);
        path.pop();  // 出栈
    }


    public static void main(String[] args) {
        Subsets inst = new Subsets();
        List<List<Integer>> ret = inst.subsets(new int[]{1,2,3});
        for (List<Integer> set: ret){
            for (Integer n: set){
                System.out.print(n + ",");
            }
            System.out.println();
        }
    }
}

package TreeX;

import BinarySearch.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 437. 路径总和 III
 */
public class PathSumIII {
    private int ret;

    /**
     *  自己第一遍写的前缀和，用List来表示
     *  首先需要遍历一遍整个树，一共有N个节点。对于每个节点，判断是否符合条件需要遍历2遍所有列表中的值即：2lgN
     *  所以时间复杂度是：O(NlgN)， 空间复杂度是O(lgN)
     * */
    public int pathSum2(TreeNode root, int targetSum) {
        ret = 0;
        // 维护一个从路径上每个节点开始的累加值的列表
        List<Long> sumList = new ArrayList<>();
        dfs(root, targetSum, sumList);
        return ret;
    }

    private void dfs(TreeNode root, int targetSum, List<Long> sumList){
        if (root == null){
            return;
        }

        // 进入递归之前, 枚举所有前缀和, 增加当前节点值
        for (int i = 0; i < sumList.size(); i++){
            sumList.set(i, sumList.get(i) + root.val);
            if (sumList.get(i) == targetSum){
                ret++;
            }
        }

        sumList.add((long)root.val);  // 增加当前节点值, 从当前节点计算
        if (root.val == targetSum){
            ret++;
        }

        dfs(root.left, targetSum, sumList);
        dfs(root.right, targetSum, sumList);

        // 递归函数返回时, 恢复栈: 删除当前节点, 栈中前缀和减去当前节点值
        sumList.remove(sumList.size()-1);
        for (int i = 0; i < sumList.size(); i++){
            sumList.set(i, sumList.get(i) - root.val);
        }
    }


    /**
     * 前缀和思想：
     * 1.定义： 一个节点的前缀和就是该节点到根之间的路径和
     * 2.作用： 两节点间的路径和 = 两节点的前缀和之差
     *
     * 遍历一遍树的N个所有节点，每次判断用哈希表可以O(1)的时间判断当前节点距离所有祖先节点的路径之和等于targetSum的数量，
     * 所以时间复杂度：O(N), 优于方法1
     */
    public int pathSum(TreeNode root, int targetSum) {
        // 从根节点到当前节点的所有节点的前缀和存储到哈希表里， key是前缀和，value是该前缀和的次数，
        // 由于从跟节点到叶子节点整条路径上的部分节点值之和可能是0，所以可能存在多个节点的前缀和相同，
        // 所以哈希表里存储的是遍历过程中，某个节点的所有祖先节点的前缀和及其数量，那么空间复杂度不超过树的深度：O(lgN)
        HashMap<Long, Integer> preSumToCnt = new HashMap<>();

        preSumToCnt.put(0L, 1);

        return dfs1(root, targetSum, preSumToCnt, 0L);
    }

    private int dfs1(TreeNode node, int targetSum, HashMap<Long, Integer> preSumToCnt, Long currSum){
        if (node == null){
            return 0;
        }
        currSum += node.val;

        // 先进行判断, 再将包含当前节点的前缀和加入到哈希表里, 否则会算出0个节点的数量(算targetSum=0时就会存在问题)
        // 当前前缀和减去targetSum，在哈希表中的次数，就是该节点到根节点中所有节点的路径之和的数量
        int cnt = preSumToCnt.getOrDefault(currSum - targetSum, 0);

        // 再更新哈希表的状态
        // 如果不存在，设置该前缀和次数为1，否则加1
        preSumToCnt.put(currSum, preSumToCnt.getOrDefault(currSum, 0) + 1);

        cnt += dfs1(node.left, targetSum, preSumToCnt, currSum);
        cnt += dfs1(node.right, targetSum, preSumToCnt, currSum);

        // 清理哈希表里的状态（避免从该节点返回时仍然保留该节点的值）, 该前缀和次数减1，
        preSumToCnt.put(currSum, preSumToCnt.get(currSum) - 1);
        // 同时累计和减去当前节点值, 由于currSum是按值传递，上层函数调用方不受影响
        // currSum -= node.val;
        return cnt;
    }

    public static void main(String[] args) {
        PathSumIII inst = new PathSumIII();
    }
}

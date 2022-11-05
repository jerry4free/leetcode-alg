package TraceBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 */
public class Subsets {

    /**
     * 一共有n个元素，对于每个元素，都有选和不选2个步骤，即：一共有n步，每步都是2个选择，一共n个2相乘，所以有2^n个
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> prev = new ArrayList<>();
        // 先加一个空的List
        prev.add(new ArrayList<>());

        // 外层循环就是枚举nums
        for (int n : nums){
            List<List<Integer>> curr = new ArrayList<>();
            // 内层循环，遍历上一层的所有子集，在
            for (List<Integer> set: prev){
                // 不加
                List<Integer> l1 = new ArrayList<>(set);
                curr.add(l1);

                // 加
                List<Integer> l2 = new ArrayList<>(set);
                l2.add(n);
                curr.add(l2);
            }
            prev = curr;
        }

        return prev;
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

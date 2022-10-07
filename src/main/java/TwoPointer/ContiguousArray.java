package TwoPointer;


import java.util.HashMap;

/**
 * 525. Contiguous Array
 */
public class ContiguousArray {
    /**
     * 前缀和思想：
     * 记录0和1相差的个数
     */
    public int findMaxLength(int[] nums) {
        // diffCnt当前下标时的1和0的个数差
        int diffCnt = 0;
        int len = nums.length;
        // 1和0个数差的最小下标
        HashMap<Integer, Integer> map = new HashMap<>();
        // base case: 空的数组中，0和1都没有，个数差是1，下标记做-1
        // 举例：[0,1], 如果只有0和1，那么此时应该是1-(-1)=2
        map.put(0, -1);

        int maxLength = 0;

        for (int i = 0; i < len; i++){
            // 是1加一，是0减一
            if (nums[i] == 1){
                diffCnt++;
            } else {
                diffCnt--;
            }
            if (map.containsKey(diffCnt)){  // 如果当前个数差之前出现过，那么取之前的最小下标，跟当前下标的差，就是最长
                System.out.println("i:" + i + ",diff:" + diffCnt + ", idx:" + map.get(diffCnt));
                maxLength = Math.max(maxLength, i - map.get(diffCnt));
            } else {
                map.put(diffCnt, i);  // 只记录每个diffCnt第一次的下标
            }
        }
        return maxLength;
    }

    /**
     * TODO: not accepted
     * 求连续子数组的最长长度，可以采用动态规划的方式
     * 假设f(i)表示前i个元素里，以i为结尾的连续子数组的包含01相同的长度
     * 那么f(1)...f(n-1)里的最大值，就是最长连续子数组
     */
    public int findMaxLength2(int[] nums) {
        int len = nums.length;
        if (len < 2){
            return 0;
        }

        int[] f = new int[len];
        // base case
        f[1] = nums[0] == nums[1] ? 0 : 2;
        int ret = f[1];
        int pre;
        for (int i = 2; i < len; i++){
            if (nums[i] != nums[i-1]){  // nums[i]跟nums[i-1]组合成01
                f[i] = Math.max(f[i], f[i-2]+2);
            }
            // 如果之前存在配对的01，且配对01前还存在元素，那么可以之前存在元素进行比较配对
            pre = i - f[i-1] - 1;
            if (f[i-1] > 0 && pre >= 0 && nums[i] != nums[pre]){  //即：
                int preMax = pre > 0 ? f[pre-1] : 0;
                f[i] = Math.max(f[i], preMax+f[i-1]+2);
            }
            ret = Math.max(ret, f[i]);
        }
//        Utils.show(f);
        return ret;
    }


    public static void main(String[] args) {
        ContiguousArray inst = new ContiguousArray();
        System.out.println(inst.findMaxLength(new int[]{0,1}));
//        System.out.println(inst.findMaxLength(new int[]{0,1,0}));
//        System.out.println(inst.findMaxLength(new int[]{0,1,1})); // 2
//        System.out.println(inst.findMaxLength(new int[]{0,1,0,0,0,1}));
//        System.out.println(inst.findMaxLength(new int[]{0,1,0,0,1,1}));
        System.out.println(inst.findMaxLength(new int[]{0,0,1,0,0,0,1,1}));
    }
}

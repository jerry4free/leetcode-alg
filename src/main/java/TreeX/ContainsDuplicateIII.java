package TreeX;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 */
public class ContainsDuplicateIII {

    /**
     *
     * 枚举每个nums[i]，判断其之前的前k个数字跟其之差是否在valueDiff之内, 这种解法时间复杂度是O(N*k)
     * 如何优化时间复杂度呢？每次判断能否在O(lgK)呢？
     *
     * 如何判断滑动窗口内的k个数字至少有1个满足在[nums[i]-valueDiff, nums[i]+valueDiff]的闭合区间呢？
     * 需要将滑动窗口内的K个数字保持有序，可以lgK查找，同时要求插入和删除也是lgK。
     * 比如维护一个K大小的二分查找平衡树，这样可以O(lgK)查找大于nums[i]-valueDiff的最小值是否小于nums[i]+valueDiff。
     * 所以可以用Java里的TreeSet类型，底层实现是红黑树, 通过ceiling，floor等API可以O(lgK)的时间找到小于等于或大于等于某个值的最近值
     *
     * 这种时间复杂度是O(NlgK)，空间复杂度是O(K)
     * 其他注意：为了避免int值在计算时越界，转化成long。
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();

        // 维护一个TreeSet，保持indexDiff个元素
        for (int i = 0; i < nums.length; i++) {
            // unboxing may introduce nullpointer exception, use Long not long
            Long ceiling = set.ceiling((long)nums[i] - valueDiff);  // O(lgK)

            if (ceiling != null && ceiling <= ((long)nums[i] + valueDiff)) {
                return true;
            }

            // 由于indexDiff可能是0，即set中一直有0个元素，
            set.add((long)nums[i]);

            // 从第indexDiff开始，每次删除最早之前的元素，保持indexDiff数量
            if (i >= indexDiff){
                set.remove((long)nums[i - indexDiff]);
            }
        }

        return false;
    }

    /**
     * 某天老师让全班同学各自说出自己的出生日期，然后统计一下出生日期相差小于等于30天的同学。
     * 我们很容易想到，
     * 1.出生在同一个月的同学，一定满足上面的条件。
     * 2.出生在相邻月的同学，也有可能满足那个条件，这就需要计算一下来确定了。
     * 3.但如果月份之间相差了两个月，那就不可能满足这个条件了。
     *
     *
     * 桶排序，基本思想是对滑动窗口内的k个数，维护一个大小为t+1的桶（具体实现是哈希表），保证一个桶内同一时刻只会有一个元素
     *
     * 时间复杂度是O(N)
     * 空间复杂度是O(K)
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            int id = getId(nums[i], valueDiff);

            // 如果当前桶内有，则说明在一个valueDiff之内，（即生日在同一个月内）
            if (map.containsKey(id)) {
                return true;
            }

            // 不在当前桶，看相邻桶的数字是否在valueDiff之内，（即属于相邻月，需要再查出来进行判断一次）
            if (map.containsKey(id+1) && map.get(id+1) - nums[i] <= valueDiff){
                return true;
            }

            if (map.containsKey(id-1) && nums[i] - map.get(id-1) <= valueDiff){
                return true;
            }

            // 更新状态：先加入最右侧数据, 再删除滑动窗口最左侧数据，
            map.put(id, nums[i]);

            if (i >= indexDiff) {
                map.remove(getId(nums[i - indexDiff], valueDiff));
            }
        }

        return false;
    }

    private int getId(int n, int valueDiff){
        if (n >= 0){
            // 由于差距为valueDiff的2个整数，连续起来一共有valueDiff+1个。桶的长度就是valueDiff+1
            return n / (valueDiff + 1);
        } else {
            // 如果是负数，则需要处理,
            // 比如valueDiff是4，0，1，2，4映射到0，那么如何将-4，-3，-2，-1映射到id为-1呢
            // 画图：数轴上现将负数转化成正数再减1，这样就跟正数对称了, 然后除后再减1
            return - (-n - 1) / (valueDiff + 1) - 1;
        }
    }


    public static void main(String[] args) {
        ContainsDuplicateIII inst = new ContainsDuplicateIII();
        System.out.println("expect: true, real:" + inst.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
        System.out.println("expect: false, real:" + inst.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
        System.out.println("expect: true, real:" + inst.containsNearbyAlmostDuplicate(new int[]{1,2,2,3,4,5}, 3, 0));
        System.out.println("expect: true, real:" + inst.containsNearbyAlmostDuplicate(new int[]{4,1,6,3}, 4, 1));
        System.out.println("expect: true, real:" + inst.containsNearbyAlmostDuplicate(new int[]{2147483640,2147483641}, 1, 100));
        System.out.println("expect: false, real:" + inst.containsNearbyAlmostDuplicate(new int[]{1,2}, 0, 1));
        System.out.println("expect: false, real:" + inst.containsNearbyAlmostDuplicate(new int[]{-3,3}, 2, 4));

    }
}

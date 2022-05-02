import java.util.HashMap;

/**
 * 128. 最长连续序列
 */
public class LongestConsecutiveSequence {

    // worse case：2N
    // time:O(n)
    public int longestConsecutive(int[] nums) {
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

    public static void main(String[] args) {
        int [] nums = new int[]{100,4,200,1,3,2};
        int [] nums2 = new int[]{0,3,7,2,5,8,4,6,0,1};
        LongestConsecutiveSequence inst = new LongestConsecutiveSequence();
        assert inst.longestConsecutive(nums) == 4;
        assert inst.longestConsecutive(nums2) == 10;
    }
}

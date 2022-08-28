package BinarySearch;

/**
 * 42. Trapping Rain Water
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int lMax = 0; // 左侧最高值
        int rMax = 0; // 右侧最高值
        int ans = 0;
        while (l < r){
            if (lMax < rMax){ // 如果左侧最高值小，那么优先计算左侧下标l，更新lMax，然后往右移.直到左侧最高值不再小
                ans += Math.max(lMax - height[l], 0);  // 如果
                lMax = Math.max(lMax, height[l]); // 更新当前遇到的最高值，即lMax
                l++;  // 移动到下一步
            } else {
                ans += Math.max(rMax - height[r], 0);
                rMax = Math.max(rMax, height[r]);
                r--;
            }
        }
        return ans;
    }

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public int trap2(int[] height) {
        // 对于下标i，它能接到的雨水，等于左右两边的最高值的的最小值，减去当前i的高度。
        // 这一步抽象很重要，这奠定了能否解答这道题，但是怎么能想到这里呢？
        int n = height.length;
        if (n == 0){
            return 0;
        }

        // 如何在O(N)的时间找到每个i的左右最高值呢？
        // 查找i的左侧的最高值，可以一直向右传递，知道遇到更高的。意味着对于i<j, leftMax[i]<=leftMax[j]，是个单调递增的
        // 有重叠，所以可以记忆下来，所以是一种动态规划的方法, 状态转移方程：leftMax[i] = max(leftMax[i-1], height[i])
        int[] leftMax = new int[height.length];
        int prev = 0;
        for (int i = 0; i < n; i++){
            leftMax[i] = prev;
            if (height[i] > prev){
                prev = height[i];
            }
        }

        // 查找右侧的的最高值
        prev = 0;
        int[] rightMax = new int[height.length];
        for (int j = n - 1; j >= 0; j--){
            rightMax[j] = prev;
            if (height[j] > prev){
                prev = height[j];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++){
            int rain = Math.min(rightMax[i], leftMax[i]) - height[i];
            if (rain > 0){
                ans += rain;
            }
        }
        return ans;
    }

    static void show(int[] n){
        for (int i : n){
            System.out.print(i + ", ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        TrappingRainWater inst = new TrappingRainWater();
        System.out.println(inst.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(inst.trap(new int[]{4,2,0,3,2,5}));
    }
}

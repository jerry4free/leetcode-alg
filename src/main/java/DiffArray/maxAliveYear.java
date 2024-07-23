package DiffArray;

/**
 * 程序员面试金典6
 * 16.10 生存人数
 */
public class maxAliveYear {

    public int maxAliveYear(int[] birth, int[] death) {
        // 一共有101年，
        // 为了覆盖101年，差分数组需要102个元素，因为对某个区间[i,j]加N，需要diff[i]+n, diff[j+1]-n
        int[] diff = new int[101]; // [0, 100]
        // diff[i] = nums[i] - nums[i-1];
        // 差分数组，存储的都是delta（微分），加和（积分）就是原数组个数。
        for (int k = 0; k < birth.length; k++) {
            int i = birth[k] - 1900;
            int j = death[k] - 1900;
            diff[i]++;
            if (j + 1 < diff.length) { // 如果j+1超过数组长度，即所有数组都需要加，则不需要处理，因为所有i到j已经加上
                diff[j + 1]--;
            }
        }

        int maxYear = 0;
        int maxCnt = 0;
        int sum = 0;
        for (int i = 0; i < diff.length; i++) {
            sum += diff[i];
            if (sum > maxCnt) {
                maxCnt = sum;
                maxYear = 1900 + i;
            }
        }
        return maxYear;
    }
}

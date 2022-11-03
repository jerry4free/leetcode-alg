package BinarySearch;

/**
 * 875 koko eating bananas
 */
public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int h) {
        // 符合要起的k的区间是：最小值是1，最大值是max
        int max = 1;
        int min = 1;
        for (int i = 0; i < piles.length; i++){
            max = Math.max(max, piles[i]);
        }
        // 二分查找满足h小时内吃完的最小速度k
        return binarySearch(piles, 1, max, h);
    }

    private int binarySearch2(int[] piles, int min, int max, int h){
        int l = min;
        int r = max;
        while (l <= r){
//            System.out.println("l:" + l + ",r:" + r);
            int m = l + (r - l) / 2;
            // 当速度为m时的小时数
            int hour = getHour(piles, m);
            if (l == r){
                return l;
            }
            if (hour <= h){  // 如果小于h，说明每小时吃的太多，可以吃少点;等于h，尝试再降速度
                r = m;
            } else {
                l = m + 1;
            }
        }

        // 结束后，l是
        return l;
    }

    private int binarySearch(int[] piles, int min, int max, int h){
        int l = min;
        int r = max;
        // 这种方法比较好理解，结束后一定是l==r，就是要找的那一个
        while (l < r){
            int m = l + (r - l) / 2;
            // 当速度为m时的小时数
            int hour = getHour(piles, m);
            if (hour <= h){  // 如果小于等于h，说明每小时吃的太多，可以吃少点;等于h，尝试再降速度，但是当前速度m是符合要求的。
                r = m;  // 由于r=m，所以max可以遍历到。
            } else {
                l = m + 1;
            }
        }

        // 结束后，l和r相等
        return l;
    }

    private int getHour(int[] piles, int k) {
        int hour = 0;
        for (int i = 0; i < piles.length; i++){
            hour += piles[i] / k;
            if (piles[i] % k > 0){
                hour += 1;
            }
        }
        return hour;
    }

    public static void main(String[] args) {
        MinEatingSpeed inst = new MinEatingSpeed();
        System.out.println("result: " + inst.minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000) + ", expected: 3");
    }
}

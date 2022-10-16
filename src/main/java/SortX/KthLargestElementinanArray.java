package SortX;

import Util.Utils;

import java.util.PriorityQueue;
import java.util.Random;


/**
 * 215. Kth Largest Element in an Array
 */
public class KthLargestElementinanArray {
    /**
     * 借鉴快排和二分的思想
     * 快排：随机选取一个元素，然后对数组进行切分，使得切分元素在其最终位置，左边元素都小于它，右边元素都大于它。
     * 二分：2分性质，如果每次能排除一半的元素，就可以用二分的思想
     * 复杂度分析:
     * 快排，平均时间复杂度，每一轮循环需要O(n)，一共需要O(lgN)轮，
     * 而在快拍的基础上再加上二分的话：第1轮需要n, 第2轮需要n/2, 第3轮需要n/4...，整体是2n
     * 所以平均时间复杂度是O(n)
     * 由于用了递归的实现方式，考虑栈空间，所以空间复杂度是O(lgN)
     */
    public int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        // 倒数第k个，就是下标len - k
        return sort(nums, 0, len - 1, len - k);
    }

    // l子数组最左边下标，r子数组最右边下标，t是要找的下标
    private int sort(int[] nums, int l, int r, int t){
        int i = partition(nums, l, r);
        if (i < t){ // 切分值的下标小于要找的下标
            return sort(nums, i+1, r, t);
        } else if (i > t){  // 切分值的下标大于要找的下标
            return sort(nums, l, i-1, t);
        } else {
            return nums[t];
        }
    }

    private void randomLow(int[] nums, int low, int high){
        Random random = new Random();
        int i = random.nextInt(high - low + 1) + low;
        exch(nums, low, i);
    }

    private int partition(int[] nums, int low, int high){
        // 将low下标替换为数组中随机的元素，避免切分不平衡
        randomLow(nums, low, high);
        int v = nums[low];
        int i = low + 1;
        int j = high;
//        Utils.show(nums);
        while (i <= j){  // 注意要加上等号，因为可能只有2个元素，此时i==j
            // 注意：先判断i<=high，顺序要对，否则i可能先越界，再判断大小，就报错了。
            // 利用&&的特性
            while (i <= high && nums[i] < v) i++;
            // 结束后nums[i]一定大于等于nums[low]
            while (j > low && nums[j] >= v) j--;
            // 结束后nums[j]一定小于nums[v]
//            System.out.println("==i:"+i+",j:"+j + ",nums[j]:" + nums[j]);
            if (i < j){
                exch(nums, i, j);
                i++;
                j--;
            }
        }
        // 循环结束后，j右边的元素都大于等于nums[v], j左边的元素都小于nums[v]
        exch(nums, low, j);

        return j;
    }

    private void exch(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    /**
     * 采用堆排序
     * java中用优先队列
     * 找倒数第k大的数字，那么可以维护一个大小为k的小根堆，这样堆顶元素就是已经遍历过的元素中第k大的元素
     * 然后枚举剩下元素，每次O(1)的时间跟堆顶的元素进行比较，
     * 比堆顶元素小，即比当前第k大的元素还小，则忽略；比堆顶元素大，则将堆顶元素替换掉平衡下
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++){
            pq.add(nums[i]);
        }
        int len = nums.length;
        for (int i = k; i < len; i++){
            if (nums[i] > pq.peek()){
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        KthLargestElementinanArray inst = new KthLargestElementinanArray();
        System.out.println(inst.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(inst.findKthLargest(new int[]{3,4,4,5,6,4}, 2));
        System.out.println(inst.findKthLargest(new int[]{3,4}, 2));
        System.out.println(inst.findKthLargest(new int[]{3}, 1));
        System.out.println(inst.findKthLargest(new int[]{2,1}, 1));
    }
}

package SortX;

/**
 * 1122. Relative Sort Array
 */
public class RelativeSortArray {

    /**
     * 计数排序
     * 时间复杂度：O(m+n)，m是arr1的大小，n是arr2的大小
     * 空间复杂度：O(m)
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 存储arr1中每个数字对应count
        int max = 0;
        for (int n : arr1){
            max = Math.max(n, max);
        }
        // 这里的数组大小没用1001，算个最大值，就是arr1的基数，从而降低存储
        int[] arr1Cnt = new int[max+1]; //
        int len2 = arr1.length;
        for (int n : arr1){
            arr1Cnt[n]++;
        }

        // 遍历1遍,按照arr2的顺序，将在arr1中在arr2中出现过的进行排序
        int[] ret = new int[len2];
        int j = 0;
        for (int n : arr2){
            while (arr1Cnt[n] > 0){
                ret[j++] = n;
                arr1Cnt[n]--;  // 对应count减1
            }
        }

        // 遍历第2遍,将没在arr2中出现的进行排序
        for (int i = 0; i < arr1Cnt.length; i++){
            while (arr1Cnt[i] > 0){
                ret[j++] = i;
                arr1Cnt[i]--;  // 对应count减1
            }
        }

        return ret;
    }

    public static void main(String[] args) {

    }
}

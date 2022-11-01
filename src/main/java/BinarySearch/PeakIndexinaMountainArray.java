package BinarySearch;

/**
 * 852. Peak Index in a Mountain Array
 */
public class PeakIndexinaMountainArray {

    public int peakIndexInMountainArray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (arr[m-1] < arr[m] && arr[m] > arr[m+1]){
                return m;
            } else if (arr[m-1] < arr[m] && arr[m] < arr[m+1]){
                l = m;
            } else {
                r = m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}

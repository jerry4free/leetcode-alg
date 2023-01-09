package BinarySearch;

/**
 * 240. Search a 2D Matrix II
 */
public class Searcha2DMatrixII {

    // 如下时间复杂度是O(M*N)，未通过
    public boolean searchMatrix2(int[][] matrix, int target) {
        return search(matrix, target, 0, 0);
    }

    private boolean search(int[][] matrix, int target, int i, int j){
        int m = matrix.length;
        int n = matrix[0].length;
        if (i > (m - 1) || j > (n - 1) || matrix[i][j] > target){
            return false;
        } else if (matrix[i][j] == target){
            return true;
        }

        return search(matrix, target, i+1, j) || search(matrix, target, i, j+1);
    }

    /**
     * 题意说是要查找，而且从左到右，从上到下，是有序的。
     * 第1种方法的时间低效，可以联想到用二分查找来加快。O(MlgN)
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length - 1;

        for (int i = 0; i < m; i++){
            if (matrix[i][0] <= target && matrix[i][n] >= target){
                if (binarySearch(matrix[i], target)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean binarySearch(int[] row, int target){
        int l = 0;
        int r = row.length - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (row[m] == target){
                return true;
            } else if (row[m] < target){
                l = m + 1;
            } else if (row[m] > target){
                r = m - 1;
            }
        }
        return false;
    }

    // 从右上角进行二分查找, 时间复杂度：O(M+N)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int i = 0;
        int j = matrix[0].length - 1;

        while (i <= (m - 1) && j >= 0){
            if (matrix[i][j] < target){
                i++;
            } else if (matrix[i][j] > target){
                j--;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}

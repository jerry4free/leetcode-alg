package search;

/**
 * 74. Search a 2D Matrix
 */
public class Searcha2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        // m行，n列
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0;
        int r = m * n - 1; // 二维转一维
        // 二维矩阵，转成1维，不就是二分查找嘛
        while (l <= r){
            int mid = l + (r - l) / 2;
            // 注意转化为行列值的正确方式：每一行有n列，那么第i行就是x / n，第j列就是余数 x % n
            // 行不是mid / m
            int i = mid / n;
            int j = mid % n;
            if (matrix[i][j] == target){
                return true;
            } else if (matrix[i][j] < target){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Searcha2DMatrix inst = new Searcha2DMatrix();
        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(inst.searchMatrix(matrix, 13));
        System.out.println(inst.searchMatrix(matrix, 3));
        System.out.println(inst.searchMatrix(new int[][]{{1}}, 3));
        System.out.println(inst.searchMatrix(new int[][]{{1}}, 0));
        System.out.println(inst.searchMatrix(new int[][]{{1, 1}}, 2));
    }
}

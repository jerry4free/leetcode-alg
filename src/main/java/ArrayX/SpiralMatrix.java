package ArrayX;

import java.util.ArrayList;
import java.util.List;

/**
 * 54 Spiral Matrix
 */
public class SpiralMatrix {

    static final int[][] offset = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // 第一次写的，3层循环，debug多次，终于AC了。1。非常丑，不简洁，容易出错；2。改动了matrix
    public List<Integer> spiralOrder2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int i = 0;
        int j = 0;
        List<Integer> ret = new ArrayList<>();
        ret.add(matrix[i][j]);
        matrix[i][j] = 101;

        System.out.println(n + "," + m);
        // 枚举螺旋形
        while (true) {
            int iPrev = i;
            int jPrev = j;

            //  枚举4个方向, 按照right、down、left、up方向来遍历,
            for (int k = 0; k < 4; k++) {
                // 枚举一个方向的所有元素，直到越界或访问过
                while (true) {
                    // 如果下一节点越界或遇到101则跳出循环。否则加入ret
                    int iNext = i + offset[k][0];
                    int jNext = j + offset[k][1];
//                    System.out.print("iNext:" + iNext + ",jNext:" + jNext + "\n");
                    if (iNext < 0 || iNext > (n - 1) || jNext < 0 || jNext > (m - 1) || matrix[iNext][jNext] > 100) {
                        break;
                    }
//                    System.out.println("->" + matrix[iNext][jNext]);
                    ret.add(matrix[iNext][jNext]);
                    matrix[iNext][jNext] = 101;
                    i = iNext;
                    j = jNext;
                }
            }
            if (i == iPrev && j == jPrev) {
                break;
            }
        }

        return ret;
    }

    /**
     * 所以如何减少循环层数
     * 1。枚举螺旋形状。因为知道矩阵的长宽，所以元素个数是M*N个，所以可以改为枚举元素个数直到是M*N个。
     * 2。循环使用4个方向，可以通过模。数组的循环使用，比如DP中经常用的循环数组用来压缩二维状态到一维
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int directionCnt = offset.length;
        List<Integer> ret = new ArrayList<>();

        int cnt = n * m;
        int i = 0;
        int j = 0;
        int d = 0;
        for (int k = 0; k < cnt; k++){
            ret.add(matrix[i][j]);
            matrix[i][j] = 101;  // mark visited

            // 如果越界或访问过，则换方向
            int iNext = i + offset[d][0];
            int jNext = j + offset[d][1];
            if (iNext < 0 || iNext > (n - 1) || jNext < 0 || jNext > (m - 1) || matrix[iNext][jNext] > 100) {
                d = (d + 1) % directionCnt;
                i += offset[d][0];
                j += offset[d][1];
            } else { // 否则不换方向
                i = iNext;
                j = jNext;
            }
        }

        return ret;
    }

    public static void main(String[] args) {

    }
}
